package com.cardinal.voca4j.api.releaseeventseries.query;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEventSeries;
import com.cardinal.voca4j.api.releaseeventseries.ReleaseEventSeriesFields;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document ReleaseEventSeriesQuery
 */
public class ReleaseEventSeriesQuery extends FieldValueRequest
		implements ResponseDeserializer<QueriedList<ReleaseEventSeries>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<ReleaseEventSeries>>() {
	}.getType();

	@Encode
	private String query;
	private ReleaseEventSeriesFields[] fields;
	private Integer start, maxResults;
	private Boolean getTotalCount;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Language lang;

	private ReleaseEventSeriesQuery() {
		super("/releaseEventSeries");
	}

	private ReleaseEventSeriesQuery(String query, ReleaseEventSeriesFields[] fields, Integer start, Integer maxResults,
			Boolean getTotalCount, NameMatchMode nameMatchMode, Language lang) {
		super("/releaseEventSeries");
		this.query = query;
		this.fields = fields;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.nameMatchMode = nameMatchMode;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventSeriesQuery(query, fields, start, maxResults, getTotalCount, nameMatchMode, lang);
	}

	@Override
	public QueriedList<ReleaseEventSeries> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class ReleaseEventSeriesQueryBuilder extends StateControlledBuilder<ReleaseEventSeriesQuery> {

		protected ReleaseEventSeriesQueryBuilder() {
			super(new ReleaseEventSeriesQuery());
		}

		public ReleaseEventSeriesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder includeFields(ReleaseEventSeriesFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public ReleaseEventSeriesQueryBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
