package com.cardinal.voca4j.api.user.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
/**
 * TODO: Document UserNamesQuery
 */
public class UserNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer maxResults;
	private Boolean includeDisabled;

	private UserNamesQuery() {
		super("/users/names");
	}

	private UserNamesQuery(String query, NameMatchMode nameMatchMode, Integer maxResults, Boolean includeDisabled) {
		super("/users/names");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.maxResults = maxResults;
		this.includeDisabled = includeDisabled;
	}

	@Override
	public Request copy() {
		return new UserNamesQuery(query, nameMatchMode, maxResults, includeDisabled);
	}

	public static class UserNamesQueryBuilder extends StateControlledBuilder<UserNamesQuery> {

		protected UserNamesQueryBuilder() {
			super(new UserNamesQuery());
		}

		public UserNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserNamesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserNamesQueryBuilder includeDisabledUsers(boolean includeDisabled) {
			this.built.includeDisabled = includeDisabled;
			return this;
		}
	}

}
