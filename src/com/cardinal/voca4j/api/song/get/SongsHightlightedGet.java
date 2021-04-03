package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.JoiningFormat;

/**
 * A {@link Request} implementation that gets list of highlighted songs (same as
 * VocaDB front page).
 * 
 * @author Cardinal System
 *
 */
public class SongsHightlightedGet extends FieldValueRequest {

	private Language languagePreference;
	@JoiningFormat
	private SongField[] fields;

	private SongsHightlightedGet() {
		super("/songs/highlighted");
	}

	@Override
	public SongsHightlightedGet copy() {
		return new SongsHightlightedGet(languagePreference, fields);
	}

	private SongsHightlightedGet(Language languagePreference, SongField[] fields) {
		super("/songs/highlighted");
		this.languagePreference = languagePreference;
		this.fields = fields;
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongsHightlightedGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongsHightlightedGetBuilder extends StateControlledBuilder<SongsHightlightedGet> {

		protected SongsHightlightedGetBuilder() {
			super(new SongsHightlightedGet());
		}

		/**
		 * Sets the preferred response language.
		 * 
		 * @param language the language.
		 * @return this, for chaining.
		 */
		public SongsHightlightedGetBuilder setLanguagePreference(Language language) {
			super.built.languagePreference = language;
			return this;
		}

		/**
		 * Sets the track fields contained in the response.
		 * 
		 * @param fields the fields.
		 * @return this, for chaining.
		 */
		public SongsHightlightedGetBuilder setFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

	}
}