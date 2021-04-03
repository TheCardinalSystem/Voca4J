package com.cardinal.voca4j.api.releaseevent.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;

/**
 * TODO: Document ReleaseEventNamesQuery
 */
public class ReleaseEventNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	private Integer maxResults;

	private ReleaseEventNamesQuery() {
		super("/releaseEvents/names");
	}

	private ReleaseEventNamesQuery(String query, Integer maxResults) {
		super("/releaseEvents/names");
		this.query = query;
		this.maxResults = maxResults;
	}

	@Override
	public Request copy() {
		return new ReleaseEventNamesQuery(query, maxResults);
	}

	public static class ReleaseEventNamesQueryBuilder extends StateControlledBuilder<ReleaseEventNamesQuery> {

		protected ReleaseEventNamesQueryBuilder() {
			super(new ReleaseEventNamesQuery());
		}

		public ReleaseEventNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public ReleaseEventNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}
	}

}
