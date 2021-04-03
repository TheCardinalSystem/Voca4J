package com.cardinal.voca4j.api.artist.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * A {@link Request} implementation that queries artist names.
 * 
 * @author Cardinal System
 *
 */
public class ArtistNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer maximumResults;

	private ArtistNamesQuery() {
		super("/artists/names");
	}

	private ArtistNamesQuery(String query, NameMatchMode nameMatchMode, Integer maximumResults) {
		super("/artists/names");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.maximumResults = maximumResults;
	}

	@Override
	public Request copy() {
		return new ArtistNamesQuery(query, nameMatchMode, maximumResults);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link ArtistNamesQuery}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class ArtistNamesQueryBuilder extends StateControlledBuilder<ArtistNamesQuery> {

		protected ArtistNamesQueryBuilder() {
			super(new ArtistNamesQuery());
		}

		/**
		 * Sets the artist name to be queried.
		 * 
		 * @param query artist name.
		 * @return this, for chaining.
		 */
		public ArtistNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		/**
		 * Sets the artist name match mode. Defaults to {@link NameMatchMode#Auto} in
		 * this implementation.
		 * 
		 * @param nameMatchMode name match mode.
		 * @return this, for chaining.
		 */
		public ArtistNamesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		/**
		 * Sets the maximum number of results to be included in the response.
		 * 
		 * @param maxResults maximum results.
		 * @return this, for chaining.
		 */
		public ArtistNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maximumResults = maxResults;
			return this;
		}

	}

}
