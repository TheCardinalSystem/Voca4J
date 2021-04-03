package com.cardinal.voca4j.api.entry.query;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryFields;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.entry.Entry;
import com.cardinal.voca4j.api.entry.EntrySort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document EntryQuery
 */
public class EntryQuery extends FieldValueRequest implements ResponseDeserializer<QueriedList<Entry>> {
	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<Entry>>() {
	}.getType();

	@Encode
	private String query;
	@JoiningFormat
	private String[] tagName;
	@JoiningFormat
	private Integer[] tagId;
	private Boolean childTags, getTotalCount;
	@JoiningFormat
	private EntryType entryTypes;
	private EntryStatus status;
	private Integer start, maxResults;
	private EntrySort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private EntryFields[] fields;
	private Language lang;

	private EntryQuery() {
		super("/entries");
	}

	private EntryQuery(String query, String[] tagName, Integer[] tagId, Boolean childTags, Boolean getTotalCount,
			EntryType entryType, EntryStatus status, Integer start, Integer maxResults, EntrySort sort,
			NameMatchMode nameMatchMode, EntryFields[] fields, Language lang) {
		super("/entries");
		this.query = query;
		this.tagName = tagName;
		this.tagId = tagId;
		this.childTags = childTags;
		this.getTotalCount = getTotalCount;
		this.entryTypes = entryType;
		this.status = status;
		this.start = start;
		this.maxResults = maxResults;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new EntryQuery(query, tagName, tagId, childTags, getTotalCount, entryTypes, status, start, maxResults,
				sort, nameMatchMode, fields, lang);
	}

	@Override
	public QueriedList<Entry> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class EntryQueryBuilder extends StateControlledBuilder<EntryQuery> {
		protected EntryQueryBuilder() {
			super(new EntryQuery());
		}

		public EntryQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public EntryQueryBuilder filterTagNames(String... tagNames) {
			this.built.tagName = tagNames;
			return this;
		}

		public EntryQueryBuilder filterTagIDs(Integer... tagIDs) {
			this.built.tagId = tagIDs;
			return this;
		}

		public EntryQueryBuilder includeChildTags(boolean childTags) {
			this.built.childTags = childTags;
			return this;
		}

		public EntryQueryBuilder includeEntryType(EntryType entryType) {
			this.built.entryTypes = entryType;
			return this;
		}

		public EntryQueryBuilder includeEntryStatus(EntryStatus status) {
			this.built.status = status;
			return this;
		}

		public EntryQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		@BypassChecks(state = false)
		public EntryQueryBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 30)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 30.");
			this.built.maxResults = maxResults;
			return this;
		}

		public EntryQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public EntryQueryBuilder setSortingRule(EntrySort sortRule) {
			this.built.sort = sortRule;
			return this;
		}

		public EntryQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public EntryQueryBuilder includeFields(EntryFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public EntryQueryBuilder setLanguagePreference(Language lang) {
			this.built.lang = lang;
			return this;
		}
	}

}
