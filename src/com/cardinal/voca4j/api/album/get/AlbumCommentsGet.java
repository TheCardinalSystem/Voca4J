package com.cardinal.voca4j.api.album.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets an album's comments by its ID.
 * 
 * @author Cardinal System
 *
 */
public class AlbumCommentsGet extends FieldValueRequest {

	@Require
	private Integer id;

	private AlbumCommentsGet() {
		super("/albums/{id}/comments");
	}

	private AlbumCommentsGet(int id) {
		super("/albums/{id}/comments");
		this.id = id;
	}

	@Override
	public AlbumCommentsGet copy() {
		return new AlbumCommentsGet(id);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumCommentsGet}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumCommentsGetBuilder extends StateControlledBuilder<AlbumCommentsGet> {

		protected AlbumCommentsGetBuilder() {
			super(new AlbumCommentsGet());
		}

		/**
		 * Sets the ID of the album which the comments will be loaded from.
		 * 
		 * @param id the id.
		 * @return this, for chaining.
		 */
		public AlbumCommentsGetBuilder setAlbumID(int id) {
			super.built.id = id;
			return this;
		}

	}

}
