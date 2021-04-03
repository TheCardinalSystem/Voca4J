package com.cardinal.voca4j.api.entry.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document EntryNamesQuery
 */
public class EntryNamesQuery extends FieldValueRequest {

	@Encode
	@Require
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer maxResults;

	private EntryNamesQuery() {
		super("/entries/names");
	}

	private EntryNamesQuery(String query, NameMatchMode nameMatchMode, Integer maxResults) {
		super("/entries/names");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.maxResults = maxResults;
	}

	@Override
	public Request copy() {
		return new EntryNamesQuery(query, nameMatchMode, maxResults);
	}

	public static class EntryNamesQueryBuilder extends StateControlledBuilder<EntryNamesQuery> {

		protected EntryNamesQueryBuilder() {
			super(new EntryNamesQuery());
		}

		public EntryNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public EntryNamesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public EntryNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}
	}

}
