package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.song.RelatedSongs;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that get's songs related to another song by
 * its ID.
 * 
 * @author Cardinal System
 *
 */
public class SongRelatedGet extends FieldValueRequest implements ResponseDeserializer<RelatedSongs> {

	@Require
	private Integer id;
	private SongField[] fields;
	private Language lang;

	private SongRelatedGet() {
		super("/songs/{id}/related");
	}

	private SongRelatedGet(Integer id, SongField[] fields, Language lang) {
		super("/songs/{id}/related");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public SongRelatedGet copy() {
		return new SongRelatedGet(id, fields, lang);
	}

	@Override
	public RelatedSongs deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RelatedSongs.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongRelatedGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongRelatedGetBuilder extends StateControlledBuilder<SongRelatedGet> {

		protected SongRelatedGetBuilder() {
			super(new SongRelatedGet());
		}

		/**
		 * Sets the ID of the song whose related songs this request will get.
		 * 
		 * @param id id.
		 * 
		 * @return this, for chaining.
		 */
		public SongRelatedGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * Optional song fields.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public SongRelatedGetBuilder setFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * 
		 * Content language preference.
		 * 
		 * @param lang lang.
		 * @return this, for chaining.
		 */
		public SongRelatedGetBuilder setLanguage(Language lang) {
			super.built.lang = lang;
			return this;
		}

	}

}
