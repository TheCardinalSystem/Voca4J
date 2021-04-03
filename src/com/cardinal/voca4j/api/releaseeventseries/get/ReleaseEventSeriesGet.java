package com.cardinal.voca4j.api.releaseeventseries.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.releaseeventseries.ReleaseEventSeriesFields;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document ReleaseEventSeriesGet
 */
public class ReleaseEventSeriesGet extends FieldValueRequest implements ResponseDeserializer<ReleaseEvent> {

	@Require
	private Integer id;
	private ReleaseEventSeriesFields[] fields;
	private Language lang;

	private ReleaseEventSeriesGet() {
		super("/releaseEventSeries/{id}");
	}

	private ReleaseEventSeriesGet(Integer id, ReleaseEventSeriesFields[] fields, Language lang) {
		super("/releaseEventSeries/{id}");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventSeriesGet(id, fields, lang);
	}

	@Override
	public ReleaseEvent deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, ReleaseEvent.class);
	}

	public static class ReleaseEventSeriesGetBuilder extends StateControlledBuilder<ReleaseEventSeriesGet> {

		protected ReleaseEventSeriesGetBuilder() {
			super(new ReleaseEventSeriesGet());
		}

		public ReleaseEventSeriesGetBuilder setID(int id) {
			this.built.id = id;
			return this;
		}

		public ReleaseEventSeriesGetBuilder includeFields(ReleaseEventSeriesFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventSeriesGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}

	}

}
