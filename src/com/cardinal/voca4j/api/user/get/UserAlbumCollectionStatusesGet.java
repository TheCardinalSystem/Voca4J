package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document UserAlbumCollectionStatusesGet
 */
public class UserAlbumCollectionStatusesGet extends FieldValueRequest {

	@Require
	private Integer id, albumId;

	private UserAlbumCollectionStatusesGet() {
		super("/users/{id}/album-collection-statuses/{albumId}");
	}

	private UserAlbumCollectionStatusesGet(Integer id, Integer albumId) {
		super("/users/{id}/album-collection-statuses/{albumId}");
		this.id = id;
		this.albumId = albumId;
	}

	@Override
	public Request copy() {
		return new UserAlbumCollectionStatusesGet(id, albumId);
	}

	public static class UserAlbumCollectionStatusesGetBuilder
			extends StateControlledBuilder<UserAlbumCollectionStatusesGet> {

		protected UserAlbumCollectionStatusesGetBuilder() {
			super(new UserAlbumCollectionStatusesGet());
		}

		public UserAlbumCollectionStatusesGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserAlbumCollectionStatusesGetBuilder setAlbumID(int albumId) {
			this.built.albumId = albumId;
			return this;
		}

	}

}
