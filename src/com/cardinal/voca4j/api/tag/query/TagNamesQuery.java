package com.cardinal.voca4j.api.tag.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;

/**
 * TODO: Document TagNamesQuery
 */
public class TagNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	private Boolean allowAliases;
	private Integer maxResults;

	private TagNamesQuery() {
		super("/tags/names");
	}

	private TagNamesQuery(String query, Boolean allowAliases, Integer maxResults) {
		super("/tags/names");
		this.query = query;
		this.allowAliases = allowAliases;
		this.maxResults = maxResults;
	}

	@Override
	public Request copy() {
		return new TagNamesQuery(query, allowAliases, maxResults);
	}

	public static class TagNamesQueryBuilder extends StateControlledBuilder<TagNamesQuery> {

		protected TagNamesQueryBuilder() {
			super(new TagNamesQuery());
		}

		public TagNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public TagNamesQueryBuilder allowAliases(boolean allowAliases) {
			this.built.allowAliases = allowAliases;
			return this;
		}

		public TagNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}
	}
}
