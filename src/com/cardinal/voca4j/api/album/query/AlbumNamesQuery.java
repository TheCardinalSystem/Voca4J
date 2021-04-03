package com.cardinal.voca4j.api.album.query;

import java.io.UnsupportedEncodingException;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * A {@link Request} implementation that gets a list of album names. This is
 * ideal for autocomplete boxes.
 * 
 * @author Cardinal System
 *
 */
public class AlbumNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	private Integer maxResults;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;

	private AlbumNamesQuery() {
		super("https://vocadb.net/api/albums/names");
	}

	private AlbumNamesQuery(String query, Integer maxResults, NameMatchMode nameMatchMode) {
		super("https://vocadb.net/api/albums/names");
		this.query = query;
		this.maxResults = maxResults;
		this.nameMatchMode = nameMatchMode;
	}

	@Override
	public String buildRequestURL() throws UnsupportedEncodingException {
		return super.buildRequestURL();
	}

	@Override
	public AlbumNamesQuery copy() {
		return new AlbumNamesQuery(query, maxResults, nameMatchMode);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumNamesQuery}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumNamesQueryBuilder extends StateControlledBuilder<AlbumNamesQuery> {

		protected AlbumNamesQueryBuilder() {
			super(new AlbumNamesQuery());
		}

		/**
		 * Sets the album name to be queried
		 * 
		 * @param query Album name query.
		 * @return this, for chaining.
		 */
		public AlbumNamesQueryBuilder setQuery(String query) {
			super.built.query = query;
			return this;
		}

		/**
		 * Sets the match mode for artist name (in this implementation, defaults to
		 * {@link NameMatchMode#Auto}).
		 * 
		 * @param mode mode.
		 * @return this, for chaining.
		 * @see NameMatchMode
		 */
		public AlbumNamesQueryBuilder setNameMatchMode(NameMatchMode mode) {
			super.built.nameMatchMode = mode;
			return this;
		}

		/**
		 * Sets the maximum number of results to be loaded (defaults to 10, maximum of
		 * 50).
		 * 
		 * @param max the max
		 * @return this, for chaining.
		 */
		@BypassChecks(state = false)
		public AlbumNamesQueryBuilder setMaximumResults(int max) {
			if (max > 50 || max < 1)
				throw new IllegalArgumentException(max + " | Expected 'max > 0' and 'max <= 50'");
			super.built.maxResults = max;
			return this;
		}
	}

}
