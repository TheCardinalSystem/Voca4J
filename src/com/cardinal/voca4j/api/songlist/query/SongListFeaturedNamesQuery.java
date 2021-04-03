package com.cardinal.voca4j.api.songlist.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.songlist.SongListFeaturedCategory;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * TODO: Document SongListFeaturedNamesQuery
 */
public class SongListFeaturedNamesQuery extends FieldValueRequest {

	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private SongListFeaturedCategory featuredCategory;
	private Integer maxResults;

	private SongListFeaturedNamesQuery() {
		super("/songLists/featured/names");
	}

	private SongListFeaturedNamesQuery(String query, NameMatchMode nameMatchMode, SongListFeaturedCategory featuredCategory,
			Integer maxResults) {
		super("/songLists/featured/names");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.featuredCategory = featuredCategory;
		this.maxResults = maxResults;
	}

	@Override
	public Request copy() {
		return new SongListFeaturedNamesQuery(query, nameMatchMode, featuredCategory, maxResults);
	}

	public static class SongListFeaturedNamesQueryBuilder extends StateControlledBuilder<SongListFeaturedNamesQuery> {

		protected SongListFeaturedNamesQueryBuilder() {
			super(new SongListFeaturedNamesQuery());
		}

		public SongListFeaturedNamesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public SongListFeaturedNamesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public SongListFeaturedNamesQueryBuilder filterCategory(SongListFeaturedCategory category) {
			this.built.featuredCategory = category;
			return this;
		}

		public SongListFeaturedNamesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}
	}
}
