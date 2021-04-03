package com.cardinal.voca4j.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An executor service for completing API tasks.
 * 
 * @author Cardinal System
 *
 */
public class TaskExectuorService extends Thread {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskExectuorService.class);

	private boolean run = true;
	private PriorityBlockingQueue<Pair> QUEUE = new PriorityBlockingQueue<Pair>(11, (o1, o2) -> o1.compareTo(o2));
	private List<ConcurrentTaskWrapper> runningTasks = new ArrayList<ConcurrentTaskWrapper>();

	public TaskExectuorService() {
		super();
		setName("Voca4J");
	}

	/**
	 * Queues a task to the executor.
	 * 
	 * @param task the task.
	 */
	public void queue(Task task) {
		LOGGER.info("Queueing task: " + task.getClass().getName());
		QUEUE.add(new Pair(task));
		if (this.getState().equals(State.NEW))
			start();
	}

	/**
	 * Queues a task to the executor and sends the results to the consumer.
	 * 
	 * @param task            the task;
	 * @param resultsCallback the consumer.
	 */
	public void queue(Task task, Consumer<Object> resultsCallback) {
		LOGGER.info("Queueing task (with callback): " + task.getClass().getName());
		QUEUE.add(new Pair(task, resultsCallback));
		if (this.getState().equals(State.NEW))
			start();
	}

	/**
	 * Queues a task to the executor with the given priority and sends the results
	 * to the consumer.
	 * 
	 * @param task            the task;
	 * @param resultsCallback the consumer.
	 * @param priority        the priority.
	 */
	public void queue(Task task, Consumer<Object> resultsCallback, int priority) {
		LOGGER.info("Queueing task (with callback and priority '" + priority + "'): " + task.getClass().getName());
		QUEUE.add(new Pair(task, resultsCallback, priority));
		if (this.getState().equals(State.NEW))
			start();
	}

	/**
	 * A blocking method which executes the given task on the current thread.
	 * 
	 * @param task the task.
	 * @return the results.
	 * @throws Exception something went wrong.
	 */
	public Object execute(Task task) throws Exception {
		LOGGER.info("Executing task: " + task.getClass().getName());
		return task.execute(this);
	}

	/**
	 * Completes all queued tasks and shuts down the executor.
	 */
	public void shutdown() {
		LOGGER.info("Shutting down...");
		run = false;
		if (QUEUE.isEmpty())
			queue(new ShutdownTask(runningTasks));
	}

	@Override
	public void run() {
		LOGGER.info("Started executor service.");
		while (run) {
			try {
				Pair pair = QUEUE.take();
				ConcurrentTaskWrapper wrapeer = new ConcurrentTaskWrapper(pair, this);
				runningTasks.add(wrapeer);
				wrapeer.start();
			} catch (InterruptedException e) {
				LOGGER.error("Interrupted while waiting for task", e);
			}
		}
		LOGGER.info("Executor service ended");
	}

	public static interface Task {
		public Object execute(TaskExectuorService executor) throws Exception;
	}

	public class ConcurrentTaskWrapper extends Thread {

		private Pair pair;
		private TaskExectuorService executor;

		private ConcurrentTaskWrapper(Pair pair, TaskExectuorService executor) {
			this.pair = pair;
			setName("Voca4JExecutor");
			this.executor = executor;
		}

		@Override
		public void run() {
			LOGGER.info("Executing task: " + pair.getTask().getClass().getName());
			Consumer<Object> callback = null;
			try {
				callback = pair.getResultsCallback();
				if (callback != null) {
					Object obj = pair.getTask().execute(executor);
					if (obj instanceof Task) {
						new ConcurrentTaskWrapper(new Pair((Task) obj, callback, pair.getPriority()), executor).start();
					} else if (obj instanceof Pair) {
						new ConcurrentTaskWrapper((Pair) obj, executor).start();
					} else {
						callback.accept(obj);
					}
				} else {
					Object obj = pair.getTask().execute(executor);
					if (obj != null) {
						if (obj instanceof Task) {
							new ConcurrentTaskWrapper(new Pair((Task) obj, callback, pair.getPriority()), executor)
									.start();
						} else if (obj instanceof Pair) {
							new ConcurrentTaskWrapper((Pair) obj, executor).start();
						}
					}
				}
			} catch (Exception e) {
				if (callback != null)
					callback.accept(e);
				else
					LOGGER.error("Failed to execute task: " + pair.getTask().getClass().getName(), e);
			} finally {
				LOGGER.info("Task ended: " + pair.getTask().getClass().getName());
				runningTasks.remove(this);
			}
		}
	}

	public static class Pair implements Comparable<Pair> {
		private Task task;
		private Consumer<Object> resultsCallback;
		private int priority = 1;

		public Pair(Task task) {
			this.task = task;
		}

		public Pair(Task task, Consumer<Object> resultsCallback) {
			this.task = task;
			this.resultsCallback = resultsCallback;
		}

		public Pair(Task task, Consumer<Object> resultsCallback, int priority) {
			this.task = task;
			this.resultsCallback = resultsCallback;
			this.priority = priority;
		}

		private Task getTask() {
			return task;
		}

		private Consumer<Object> getResultsCallback() {
			return resultsCallback;
		}

		public int getPriority() {
			return priority;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.priority, o.getPriority());
		}
	}

	private class ShutdownTask implements Task {

		private List<ConcurrentTaskWrapper> runningTasks;

		private ShutdownTask(List<ConcurrentTaskWrapper> runningTasks) {
			this.runningTasks = runningTasks;
		}

		@Override
		public Object execute(TaskExectuorService executor) throws Exception {
			for (ConcurrentTaskWrapper wrapper : runningTasks) {
				try {
					wrapper.interrupt();
				} catch (Exception e) {
					LOGGER.warn("Problem interrupting task: " + wrapper.pair.getTask().getClass().getName(), e);
				}
			}
			return null;
		}

	}
}
