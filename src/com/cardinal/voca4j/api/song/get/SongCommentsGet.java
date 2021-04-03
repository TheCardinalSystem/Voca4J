package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets a song's comments by its ID.
 * 
 * @author Cardinal System
 *
 */
public class SongCommentsGet extends FieldValueRequest {

	@Require
	private Integer id;

	private SongCommentsGet() {
		super("/songs/{id}/comments");
	}

	private SongCommentsGet(Integer id) {
		super("songs/{id}/comments");
		this.id = id;
	}

	@Override
	public SongCommentsGet copy() {
		return new SongCommentsGet(id);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongCommentsGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongCommentsGetBuilder extends StateControlledBuilder<SongCommentsGet> {

		protected SongCommentsGetBuilder() {
			super(new SongCommentsGet());
		}

		/**
		 * Sets the ID of the song where the comments will be retrieved from.
		 * 
		 * @param id id.
		 * @return this, for chaining.
		 */
		public SongCommentsGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}
	}

}
