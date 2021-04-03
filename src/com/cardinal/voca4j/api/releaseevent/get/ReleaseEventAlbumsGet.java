package com.cardinal.voca4j.api.releaseevent.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document ReleaseEventAlbumsGet
 */
public class ReleaseEventAlbumsGet extends FieldValueRequest implements ResponseDeserializer<List<Album>> {

	public static final Type RESPONSE_TYPE = new TypeToken<List<Album>>() {}.getType();

	@Require
	private Integer eventId;
	private AlbumField[] fields;
	private Language lang;

	private ReleaseEventAlbumsGet() {
		super("/releaseEvents/{eventId}/albums");
	}

	private ReleaseEventAlbumsGet(Integer eventId, AlbumField[] fields, Language lang) {
		super("/releaseEvents/{eventId}/albums");
		this.eventId = eventId;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventAlbumsGet(eventId, fields, lang);
	}

	@Override
	public List<Album> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class ReleaseEventAlbumsGetBuilder extends StateControlledBuilder<ReleaseEventAlbumsGet> {

		protected ReleaseEventAlbumsGetBuilder() {
			super(new ReleaseEventAlbumsGet());
		}

		public ReleaseEventAlbumsGetBuilder setEventID(int eventID) {
			this.built.eventId = eventID;
			return this;
		}

		public ReleaseEventAlbumsGetBuilder includeFields(AlbumField... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventAlbumsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}

	}

}
