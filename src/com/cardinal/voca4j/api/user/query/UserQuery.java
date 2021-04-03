package com.cardinal.voca4j.api.user.query;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Locale;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.user.User;
import com.cardinal.voca4j.api.user.UserField;
import com.cardinal.voca4j.api.user.UserGroup;
import com.cardinal.voca4j.api.user.UserSort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document UserQuery
 */
public class UserQuery extends FieldValueRequest implements ResponseDeserializer<QueriedList<User>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<User>>() {
	}.getType();

	@Encode
	private String query;
	private UserGroup groups;
	private LocalDate joinDateAfter, joinDateBefore;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer start, maxResults;
	private Boolean getTotalCount, includeDisabled, onlyVerified;
	private UserSort sort;
	private String knowsLanguage;
	private UserField[] fields;

	private UserQuery() {
		super("/users");
	}

	private UserQuery(String query, UserGroup groups, LocalDate joinDateAfter, LocalDate joinDateBefore,
			NameMatchMode nameMatchMode, Integer start, Integer maxResults, Boolean getTotalCount,
			Boolean includeDisabled, Boolean onlyVerified, UserSort sort, String knowsLanguage, UserField[] fields) {
		super("/users");
		this.query = query;
		this.groups = groups;
		this.joinDateAfter = joinDateAfter;
		this.joinDateBefore = joinDateBefore;
		this.nameMatchMode = nameMatchMode;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.includeDisabled = includeDisabled;
		this.onlyVerified = onlyVerified;
		this.sort = sort;
		this.knowsLanguage = knowsLanguage;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new UserQuery(query, groups, joinDateAfter, joinDateBefore, nameMatchMode, start, maxResults,
				getTotalCount, includeDisabled, onlyVerified, sort, knowsLanguage, fields);
	}

	@Override
	public QueriedList<User> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class UserQueryBuilder extends StateControlledBuilder<UserQuery> {

		protected UserQueryBuilder() {
			super(new UserQuery());
		}

		public UserQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserQueryBuilder filterByGroup(UserGroup group) {
			this.built.groups = group;
			return this;
		}

		public UserQueryBuilder filterJoinedBefore(LocalDate joinDateBefore) {
			this.built.joinDateBefore = joinDateBefore;
			return this;
		}

		public UserQueryBuilder filterJoinedAfter(LocalDate joinDateAfter) {
			this.built.joinDateAfter = joinDateAfter;
			return this;
		}

		public UserQueryBuilder filterJoinedBetween(LocalDate origin, LocalDate bound) {
			this.built.joinDateAfter = origin;
			this.built.joinDateBefore = bound;
			return this;
		}

		public UserQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public UserQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public UserQueryBuilder setSortingRule(UserSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public UserQueryBuilder includeDisabledUsers(boolean includeDisabledUsers) {
			this.built.includeDisabled = includeDisabledUsers;
			return this;
		}

		public UserQueryBuilder onlyIncludeVerifiedArtists(boolean onlyVerified) {
			this.built.onlyVerified = onlyVerified;
			return this;
		}

		@Deprecated
		public UserQueryBuilder filterByKnownLanguage(String knowsLanguage) {
			this.built.knowsLanguage = knowsLanguage;
			return this;
		}

		public UserQueryBuilder filterByKnownLanguage(Locale knowsLanguage) {
			return filterByKnownLanguage(knowsLanguage.getLanguage());
		}

		public UserQueryBuilder includeFields(UserField... fields) {
			this.built.fields = fields;
			return this;
		}
	}

}
