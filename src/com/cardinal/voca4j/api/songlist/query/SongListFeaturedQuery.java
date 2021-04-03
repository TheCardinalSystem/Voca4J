package com.cardinal.voca4j.api.songlist.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.songlist.SongListFeaturedCategory;
import com.cardinal.voca4j.api.songlist.SongListFields;
import com.cardinal.voca4j.api.songlist.SongListSort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;

/**
 * TODO: Document SongListFeaturedQuery
 */
public class SongListFeaturedQuery extends FieldValueRequest {

	@Encode
	private String query;
	@JoiningFormat
	private Integer[] tagId;
	private Boolean childTags, getTotalCount;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private SongListFeaturedCategory featuredCategory;
	private Integer start, maxResults;
	private SongListSort sort;
	private SongListFields[] fields;
	private Language lang;

	private SongListFeaturedQuery() {
		super("/songLists/featured");
	}

	private SongListFeaturedQuery(String query, Integer[] tagId, Boolean childTags, Boolean getTotalCount,
			NameMatchMode nameMatchMode, SongListFeaturedCategory featuredCategory, Integer start, Integer maxResults,
			SongListSort sort, SongListFields[] fields, Language lang) {
		super("/songLists/featured");
		this.query = query;
		this.tagId = tagId;
		this.childTags = childTags;
		this.getTotalCount = getTotalCount;
		this.nameMatchMode = nameMatchMode;
		this.featuredCategory = featuredCategory;
		this.start = start;
		this.maxResults = maxResults;
		this.sort = sort;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new SongListFeaturedQuery(query, tagId, childTags, getTotalCount, nameMatchMode, featuredCategory, start,
				maxResults, sort, fields, lang);
	}

	public static class SongListFeaturedQueryBuilder extends StateControlledBuilder<SongListFeaturedQuery> {

		protected SongListFeaturedQueryBuilder() {
			super(new SongListFeaturedQuery());
		}

		public SongListFeaturedQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public SongListFeaturedQueryBuilder filterTagIDs(Integer... tagIds) {
			this.built.tagId = tagIds;
			return this;
		}

		public SongListFeaturedQueryBuilder includeChildTags(boolean childTags) {
			this.built.childTags = childTags;
			return this;
		}

		public SongListFeaturedQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public SongListFeaturedQueryBuilder filterCategory(SongListFeaturedCategory category) {
			this.built.featuredCategory = category;
			return this;
		}

		public SongListFeaturedQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		@BypassChecks(state = false)
		public SongListFeaturedQueryBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 50)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 50!");
			this.built.maxResults = maxResults;
			return this;
		}

		public SongListFeaturedQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public SongListFeaturedQueryBuilder setSortingRule(SongListSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public SongListFeaturedQueryBuilder includeFields(SongListFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public SongListFeaturedQueryBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
