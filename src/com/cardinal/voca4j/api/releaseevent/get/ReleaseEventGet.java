package com.cardinal.voca4j.api.releaseevent.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.releaseevent.ReleaseEventFields;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document ReleaseEventGet
 */
public class ReleaseEventGet extends FieldValueRequest implements ResponseDeserializer<ReleaseEvent> {

	@Require
	private Integer id;
	private ReleaseEventFields[] fields;
	private Language lang;

	private ReleaseEventGet() {
		super("/releaseEvents/{id}");
	}

	private ReleaseEventGet(String rootUrl, Integer id, ReleaseEventFields[] fields, Language lang) {
		super("/releaseEvents/{id}");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventGet(rootUrl, id, fields, lang);
	}

	@Override
	public ReleaseEvent deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, ReleaseEvent.class);
	}

	public static class ReleaseEventGetBuilder extends StateControlledBuilder<ReleaseEventGet> {

		protected ReleaseEventGetBuilder() {
			super(new ReleaseEventGet());
		}

		public ReleaseEventGetBuilder setID(int id) {
			this.built.id = id;
			return this;
		}

		public ReleaseEventGetBuilder includeFields(ReleaseEventFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
