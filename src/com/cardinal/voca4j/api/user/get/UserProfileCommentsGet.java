package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Require;
/**
 * TODO: Document UserProfileCommentsGet
 */
public class UserProfileCommentsGet extends FieldValueRequest {

	@Require
	private Integer id;
	private Integer start, maxResults;
	private Boolean getTotalCount;

	private UserProfileCommentsGet() {
		super("/users/{id}/profileComments");
	}

	private UserProfileCommentsGet(Integer id, Integer start, Integer maxResults, Boolean getTotalCount) {
		super("/users/{id}/profileComments");
		this.id = id;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
	}

	@Override
	public Request copy() {
		return new UserProfileCommentsGet(id, start, maxResults, getTotalCount);
	}

	public static class UserProfileCommentsGetBuilder extends StateControlledBuilder<UserProfileCommentsGet> {

		protected UserProfileCommentsGetBuilder() {
			super(new UserProfileCommentsGet());
		}

		public UserProfileCommentsGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserProfileCommentsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public UserProfileCommentsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserProfileCommentsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

	}

}
