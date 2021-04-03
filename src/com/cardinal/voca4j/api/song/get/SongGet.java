package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets a song by its ID.
 * 
 * @author Cardinal System
 *
 */
public class SongGet extends FieldValueRequest {

	@Require
	private Integer id;
	private SongField[] fields;
	private Language lang;

	private SongGet() {
		super("/songs/{id}");
	}

	private SongGet(Integer id, SongField[] fields, Language lang) {
		super("/songs/{id}");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public SongGet copy() {
		return new SongGet(id, fields, lang);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongGetBuilder extends StateControlledBuilder<SongGet> {

		protected SongGetBuilder() {
			super(new SongGet());
		}

		/**
		 * Sets the ID of the song this request retrieves (required).
		 * 
		 * @param id id.
		 * @return this, for chaining.
		 */
		public SongGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * List of optional fields.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public SongGetBuilder setFields(SongField... fields) {
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
		public SongGetBuilder setLanguage(Language lang) {
			super.built.lang = lang;
			return this;
		}
	}
}
