package com.cardinal.voca4j.api.song.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * A {@link Request} implementation that queries a list of song names.
 * 
 * @author Cardinal System
 *
 */
public class SongNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer maxResults;

	private SongNamesQuery() {
		super("https://vocadb.net/api/songs/names");
	}

	private SongNamesQuery(String rootUrl, String query, NameMatchMode nameMatchMode, Integer maxResults) {
		super(rootUrl);
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.maxResults = maxResults;
	}

	@Override
	public SongNamesQuery copy() {
		return new SongNamesQuery(query, query, nameMatchMode, maxResults);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongNamesQuery}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongNamesQueryBuilder extends StateControlledBuilder<SongNamesQuery> {

		protected SongNamesQueryBuilder() {
			super(new SongNamesQuery());
		}

		/**
		 * Sets the name query.
		 * 
		 * @param query the query.
		 * @return this, for chaining.
		 */
		public SongNamesQueryBuilder setQuery(String query) {
			super.built.query = query;
			return this;
		}

		/**
		 * Sets the name match mode. Defaults to {@link NameMatchMode#Auto} in this
		 * implementation.
		 * 
		 * @param nameMatchMode name match mode.
		 * @return this, for chaining.
		 */
		public SongNamesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			super.built.nameMatchMode = nameMatchMode;
			return this;
		}

		/**
		 * Sets the maximum number of results returned.
		 * 
		 * @param maxResults max results.
		 * @return this, for chaining.
		 */
		public SongNamesQueryBuilder setMaximumResults(int maxResults) {
			super.built.maxResults = maxResults;
			return this;
		}

	}

}
