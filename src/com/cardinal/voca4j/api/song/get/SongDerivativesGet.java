package com.cardinal.voca4j.api.song.get;

import java.io.UnsupportedEncodingException;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets a song's derived versions
 * (alternate versions).
 * 
 * @author Cardinal System
 *
 */
public class SongDerivativesGet extends FieldValueRequest {

	@Require
	private Integer id;
	@JoiningFormat
	private SongField[] fields;
	private Language lang;

	private SongDerivativesGet() {
		super("/songs/{id}/derived");
	}

	private SongDerivativesGet(Integer id, SongField[] fields, Language lang) {
		super("/songs/{id}/derived");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public SongDerivativesGet copy() {
		return new SongDerivativesGet(id, fields, lang);
	}

	@Override
	public String buildRequestURL() throws UnsupportedEncodingException {
		if (url != null)
			return url;
		super.buildRequestURL();
		return url;
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongDerivativesGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongDerivativesGetBuilder extends StateControlledBuilder<SongDerivativesGet> {

		protected SongDerivativesGetBuilder() {
			super(new SongDerivativesGet());
		}

		/**
		 * Sets the ID of the song whose derived versions this request will retrieve.
		 * 
		 * @param id id.
		 * @return this, for chaining.
		 */
		public SongDerivativesGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * List of optional fields
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public SongDerivativesGetBuilder setFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Content language preference
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public SongDerivativesGetBuilder setLangauge(Language lang) {
			super.built.lang = lang;
			return this;
		}
	}

}
