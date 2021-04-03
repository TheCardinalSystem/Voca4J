package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.user.UserField;
import com.cardinal.voca4j.lang.Require;
/**
 * TODO: Document UserGet
 */
public class UserGet extends FieldValueRequest {

	@Require
	private Integer id;
	private UserField[] fields;

	private UserGet() {
		super("/users/{id}");
	}

	private UserGet(Integer id, UserField[] fields) {
		super("/users/{id}");
		this.id = id;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new UserGet(id, fields);
	}

	public static class UserGetBuilder extends StateControlledBuilder<UserGet> {

		protected UserGetBuilder() {
			super(new UserGet());
		}

		public UserGetBuilder setID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserGetBuilder includeFields(UserField... fields) {
			this.built.fields = fields;
			return this;
		}
	}
}
