package com.cardinal.voca4j.api.tag.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.tag.TagCategory;
import com.cardinal.voca4j.api.tag.TagField;
import com.cardinal.voca4j.api.tag.TagSort;
import com.cardinal.voca4j.api.tag.TagTargets;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * TODO: Document TagsQuery
 */
public class TagsQuery extends FieldValueRequest {
	@Encode
	private String query;
	private Boolean allowChildren, getTotalCount, preferAccurateMatches;
	private TagCategory[] categories;
	private Integer start, maxResults;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private TagSort sort;
	private TagField[] fields;
	private Language lang;
	private TagTargets target;

	private TagsQuery() {
		super("/tags");
	}

	private TagsQuery(String query, Boolean allowChildren, TagCategory[] categories, Integer start,
			Integer maxResults, Boolean getTotalCount, NameMatchMode nameMatchMode, TagSort sort,
			Boolean preferAccurateMatches, TagField[] fields, Language lang, TagTargets target) {
		super("/tags");
		this.query = query;
		this.allowChildren = allowChildren;
		this.categories = categories;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.nameMatchMode = nameMatchMode;
		this.sort = sort;
		this.preferAccurateMatches = preferAccurateMatches;
		this.fields = fields;
		this.lang = lang;
		this.target = target;
	}

	@Override
	public Request copy() {
		return new TagsQuery(query, allowChildren, categories, start, maxResults, getTotalCount, nameMatchMode, sort,
				preferAccurateMatches, fields, lang, target);
	}

	public static class TagsQueryBuilder extends StateControlledBuilder<TagsQuery> {

		protected TagsQueryBuilder() {
			super(new TagsQuery());
		}

		public TagsQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public TagsQueryBuilder allowChildren(boolean allowChildren) {
			this.built.allowChildren = allowChildren;
			return this;
		}

		public TagsQueryBuilder filterCategories(TagCategory... categories) {
			this.built.categories = categories;
			return this;
		}

		public TagsQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		@BypassChecks(state = false)
		public TagsQueryBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 30)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 30!");
			this.built.maxResults = maxResults;
			return this;
		}

		public TagsQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public TagsQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public TagsQueryBuilder setSortingRule(TagSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public TagsQueryBuilder preferAccurateMatches(boolean preferAccurateMatches) {
			this.built.preferAccurateMatches = preferAccurateMatches;
			return this;
		}

		public TagsQueryBuilder includeFields(TagField... fields) {
			this.built.fields = fields;
			return this;
		}

		public TagsQueryBuilder setLangaugePreference(Language language) {
			this.built.lang = language;
			return this;
		}

		public TagsQueryBuilder setTarget(TagTargets target) {
			this.built.target = target;
			return this;
		}
	}
}
