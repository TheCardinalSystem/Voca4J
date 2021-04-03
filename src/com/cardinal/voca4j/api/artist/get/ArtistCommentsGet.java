package com.cardinal.voca4j.api.artist.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets comments on artist's profile by
 * the artist ID.
 * 
 * @author Cardinal System
 *
 */
public class ArtistCommentsGet extends FieldValueRequest {

	@Require
	private Integer id;

	private ArtistCommentsGet() {
		super("/artists/{id}/comments");
	}

	private ArtistCommentsGet(Integer id) {
		super("/artists/{id}/comments");
		this.id = id;
	}

	@Override
	public ArtistCommentsGet copy() {
		return new ArtistCommentsGet(id);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link ArtistCommentsGet}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class ArtistCommentsGetBuilder extends StateControlledBuilder<ArtistCommentsGet> {

		protected ArtistCommentsGetBuilder() {
			super(new ArtistCommentsGet());
		}

		/**
		 * Sets the ID of the artist.
		 * 
		 * @param artistID artist ID.
		 * @return this, for chaining.
		 */
		public ArtistCommentsGetBuilder setID(int artistID) {
			this.built.id = artistID;
			return this;
		}

	}

}
