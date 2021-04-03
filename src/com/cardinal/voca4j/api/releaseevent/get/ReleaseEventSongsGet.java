package com.cardinal.voca4j.api.releaseevent.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document ReleaseEventSongsGet
 */
public class ReleaseEventSongsGet extends FieldValueRequest {

	@Require
	private Integer eventId;
	private SongField[] fields;
	private Language lang;

	private ReleaseEventSongsGet() {
		super("/releaseEvents/{eventId}/published-songs");
	}

	private ReleaseEventSongsGet(Integer eventId, SongField[] fields, Language lang) {
		super("/releaseEvents/{eventId}/published-songs");
		this.eventId = eventId;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ReleaseEventSongsGet(eventId, fields, lang);
	}

	public static class ReleaseEventSongsGetBuilder extends StateControlledBuilder<ReleaseEventSongsGet> {

		protected ReleaseEventSongsGetBuilder() {
			super(new ReleaseEventSongsGet());
		}

		public ReleaseEventSongsGetBuilder setEventID(int eventId) {
			this.built.eventId = eventId;
			return this;
		}

		public ReleaseEventSongsGetBuilder includeFields(SongField... fields) {
			this.built.fields = fields;
			return this;
		}

		public ReleaseEventSongsGetBuilder setLanguagePreference(Language lang) {
			this.built.lang = lang;
			return this;
		}

	}

}
