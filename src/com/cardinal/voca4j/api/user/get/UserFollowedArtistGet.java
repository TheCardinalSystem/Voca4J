package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document UserFollowedArtistGet
 */
public class UserFollowedArtistGet extends FieldValueRequest {
	@Require
	private Integer id, artistId;

	private UserFollowedArtistGet() {
		super("/users/{id}/followedArtists/{artistId}");
	}

	private UserFollowedArtistGet(Integer id, Integer artistId) {
		super("/users/{id}/followedArtists/{artistId}");
		this.id = id;
		this.artistId = artistId;
	}

	@Override
	public Request copy() {
		return new UserFollowedArtistGet(id, artistId);
	}

	public static class UserFollowedArtistGetBuilder extends StateControlledBuilder<UserFollowedArtistGet> {

		protected UserFollowedArtistGetBuilder() {
			super(new UserFollowedArtistGet());
		}

		public UserFollowedArtistGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserFollowedArtistGetBuilder setArtistID(int artistId) {
			this.built.artistId = artistId;
			return this;
		}
	}

}
