package com.cardinal.voca4j.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Builder} template that uses {@link State States} to prevent unwanted
 * behaviors.
 * 
 * @author Cardinal System
 *
 * @param <T> the type of {@link Request} this builder will build.
 */
public abstract class StateControlledBuilder<T extends Request> implements Builder {

	/**
	 * The {@link Request} instance that is being built.
	 */
	protected T built;
	private State state = State.NEW;

	protected StateControlledBuilder(T instance) {
		this.built = instance;
	}

	@Override
	@BypassChecks
	public State getState() {
		return state;
	}

	@Override
	@BypassChecks
	public T build() {
		for (Field field : built.getClass().getDeclaredFields()) {

			if (field.isAnnotationPresent(IfSet.class)) {
				IfSet ifSet = field.getAnnotation(IfSet.class);
				String ifSetName = ifSet.value();
				Field ifSetField = null;
				Object ifSetValue = null;
				try {
					ifSetField = built.getClass().getDeclaredField(ifSetName);
					ifSetField.setAccessible(true);
					ifSetValue = ifSetField.get(this.built);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
						| IllegalAccessException e) {
					e.printStackTrace();
				} finally {
					if (ifSetField != null) {
						ifSetField.setAccessible(false);
					}
				}
				if (ifSetValue == null) {
					field.setAccessible(true);
					try {
						field.set(this.built, null);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					} finally {
						field.setAccessible(false);
					}
				}
			}

			if (!field.isAnnotationPresent(Require.class))
				continue;

			field.setAccessible(true);
			Object val;
			try {
				val = field.get(built);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}

			if (val == null) {
				throw new IllegalStateException("Cannot build unless '" + field.getName() + "' is set!");
			}
		}

		state = State.BUILT;
		return built;
	}

	@SuppressWarnings("unchecked")
	@Override
	@BypassChecks
	public StateControlledBuilder<T> recycle() {
		if (!this.state.equals(State.BUILT))
			return this;
		
		this.state = State.RECYCLED;
		this.built = (T) built.copy();
		if (this.built instanceof FieldValueRequest) {
			Field field = null;
			try {
				field = FieldValueRequest.class.getDeclaredField("url");
				field.setAccessible(true);
				field.set(this.built, null);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} finally {
				if (field != null)
					field.setAccessible(false);
			}
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@BypassChecks
	public StateControlledBuilder<T> resetRecycle() {
		if (!this.state.equals(State.BUILT))
			return this;

		this.state = State.RECYCLED;
		Constructor<T> contructor = null;
		try {
			contructor = (Constructor<T>) this.built.getClass().getDeclaredConstructor();
			contructor.setAccessible(true);
			this.built = contructor.newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			if (contructor != null)
				contructor.setAccessible(false);
		}

		return this;
	}

}
