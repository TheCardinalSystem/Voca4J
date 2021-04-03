package com.cardinal.voca4j.api.releaseevent.query;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.releaseevent.EventCategory;
import com.cardinal.voca4j.api.releaseevent.ReleaseEventFields;
import com.cardinal.voca4j.api.releaseevent.ReleaseEventSort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.RequireSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document ReleaseEventQuery
 */
public class ReleaseEventQuery extends FieldValueRequest implements ResponseDeserializer<QueriedList<ReleaseEvent>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<ReleaseEvent>>() {
	}.getType();

	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer seriesId, userCollectionId, start, maxResults;
	private LocalDate afterDate, beforeDate;
	private EventCategory category;
	@JoiningFormat
	private Integer[] tagId, artistId;
	private Boolean childTags, childVoicebanks, includeMembers, getTotalCount;
	private EntryStatus status;
	private ReleaseEventSort sort;
	private ReleaseEventFields[] fields;
	private Language lang;

	private ReleaseEventQuery() {
		super("/releaseEvents");
	}

	private ReleaseEventQuery(String query, NameMatchMode nameMatchMode, Integer seriesId, Integer userCollectionId,
			Integer start, Integer maxResults, LocalDate afterDate, LocalDate beforeDate, EventCategory category,
			Integer[] tagId, Integer[] artistId, Boolean childTags, Boolean childVoicebanks, Boolean includeMembers,
			Boolean getTotalCount, EntryStatus status, ReleaseEventSort sort, ReleaseEventFields[] fields,
			Language lang) {
		super("/releaseEvents");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
		this.seriesId = seriesId;
		this.userCollectionId = userCollectionId;
		this.start = start;
		this.maxResults = maxResults;
		this.afterDate = afterDate;
		this.beforeDate = beforeDate;
		this.category = category;
		this.tagId = tagId;
		this.artistId = artistId;
		this.childTags = childTags;
		this.childVoicebanks = childVoicebanks;
		this.includeMembers = includeMembers;
		this.getTotalCount = getTotalCount;
		this.status = status;
		this.sort = sort;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventQuery(query, nameMatchMode, seriesId, userCollectionId, start, maxResults, afterDate,
				beforeDate, category, tagId, artistId, childTags, childVoicebanks, includeMembers, getTotalCount,
				status, sort, fields, lang);
	}

	@Override
	public QueriedList<ReleaseEvent> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class ReleaseEventQueryBuilder extends StateControlledBuilder<ReleaseEventQuery> {

		protected ReleaseEventQueryBuilder() {
			super(new ReleaseEventQuery());
		}

		public ReleaseEventQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public ReleaseEventQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public ReleaseEventQueryBuilder setSeriesID(int seriesId) {
			this.built.seriesId = seriesId;
			return this;
		}

		public ReleaseEventQueryBuilder filterBefore(LocalDate before) {
			this.built.beforeDate = before;
			return this;
		}

		public ReleaseEventQueryBuilder filterAfter(LocalDate after) {
			this.built.afterDate = after;
			return this;
		}

		public ReleaseEventQueryBuilder filterCategory(EventCategory category) {
			this.built.category = category;
			return this;
		}

		public ReleaseEventQueryBuilder filterByUser(int userCollectionId) {
			this.built.userCollectionId = userCollectionId;
			return this;
		}

		public ReleaseEventQueryBuilder filterByTags(Integer... tagIDs) {
			this.built.tagId = tagIDs;
			return this;
		}

		public ReleaseEventQueryBuilder filterByArtists(Integer... artistIDs) {
			this.built.artistId = artistIDs;
			return this;
		}

		public ReleaseEventQueryBuilder inludeChildTags(boolean childTags) {
			this.built.childTags = childTags;
			return this;
		}

		@RequireSet("artistId")
		public ReleaseEventQueryBuilder includeChildVoicebanks(boolean childVoicebanks) {
			this.built.childTags = childVoicebanks;
			return this;
		}

		@RequireSet("artistId")
		public ReleaseEventQueryBuilder includeMembers(boolean includeMembers) {
			this.built.includeMembers = includeMembers;
			return this;
		}

		public ReleaseEventQueryBuilder filterByStatus(EntryStatus status) {
			this.built.status = status;
			return this;
		}

		public ReleaseEventQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public ReleaseEventQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public ReleaseEventQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public ReleaseEventQueryBuilder setSortingRule(ReleaseEventSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public ReleaseEventQueryBuilder includeFields(ReleaseEventFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventQueryBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
