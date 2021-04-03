package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document UserSongRatingGet
 */
public class UserSongRatingGet extends FieldValueRequest {

	@Require
	private Integer id, songId;

	private UserSongRatingGet() {
		super("/users/{id}/ratedSongs/{songId}");
	}

	private UserSongRatingGet(Integer id, Integer songId) {
		super("/users/{id}/ratedSongs/{songId}");
		this.id = id;
		this.songId = songId;
	}

	@Override
	public Request copy() {
		return new UserSongRatingGet(id, songId);
	}

	public static class UserSongRatingGetBuilder extends StateControlledBuilder<UserSongRatingGet> {

		protected UserSongRatingGetBuilder() {
			super(new UserSongRatingGet());
		}

		public UserSongRatingGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserSongRatingGetBuilder setSongID(int songId) {
			this.built.songId = songId;
			return this;
		}
	}
}
