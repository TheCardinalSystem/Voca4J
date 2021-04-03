package com.cardinal.voca4j.api;

public interface Builder {

	/**
	 * Gets this builder's internal state.
	 * 
	 * @return the state.
	 * @see State
	 */
	public State getState();

	/**
	 * Builds this builder, setting its internal state to {@link State#BUILT} and
	 * making it immutable. Call {@link Builder#recycle()} to make the builder
	 * mutable again.
	 * 
	 * @return this {@link Request} built by this builder.
	 */
	public Request build();

	/**
	 * Reconstructs this builder's internal {@link Request} instance with all the
	 * previously set values without affecting the previously built Request.
	 * 
	 * @return this, for chaining.
	 */
	public Builder recycle();

	/**
	 * An enum that represents the three states of a {@link Builder}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static enum State {
		/**
		 * The builder is new and has not been built yet.
		 */
		NEW,
		/**
		 * The builder has been built and is now immutable.
		 */
		BUILT,
		/**
		 * The builder has been recycled and can be used again.
		 */
		RECYCLED;
	}
}
