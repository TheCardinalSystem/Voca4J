package com.cardinal.voca4j.api.album.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.lang.JoiningFormat;

/**
 * A {@link Request} implementation that gets list of top rated album (same as
 * front page on vocadb.net).
 * 
 * @author Cardinal System
 *
 */
public class TopAlbumsQuery extends FieldValueRequest {

	@JoiningFormat
	private Integer[] ignoreIds;
	private Language languagePreference;
	private AlbumField[] fields;

	private TopAlbumsQuery() {
		super("https://vocadb.net/api/albums/new");
	}

	private TopAlbumsQuery(Language languagePreference, AlbumField[] fields) {
		super("https://vocadb.net/api/albums/new");
		this.languagePreference = languagePreference;
		this.fields = fields;
	}

	@Override
	public TopAlbumsQuery copy() {
		return new TopAlbumsQuery(languagePreference, fields);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link TopAlbumsQuery}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class TopAlbumsQueryBuilder extends StateControlledBuilder<TopAlbumsQuery> {

		protected TopAlbumsQueryBuilder() {
			super(new TopAlbumsQuery());
		}

		/**
		 * Albums IDs to exclude from the results.
		 * 
		 * @param ignoreIds ignored IDs.
		 * @return this, for chaining.
		 */
		public TopAlbumsQueryBuilder setIgnoredIDs(Integer... ignoreIds) {
			this.built.ignoreIds = ignoreIds;
			return this;
		}

		/**
		 * Sets the preferred languages for the query response.
		 * 
		 * @param languages the languages.
		 * @return this, for chaining.
		 */
		public TopAlbumsQueryBuilder setLanguagePreference(Language languages) {
			super.built.languagePreference = languages;
			return this;
		}

		/**
		 * Optional fields.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 * @see AlbumField
		 */
		public TopAlbumsQueryBuilder includeFields(AlbumField... fields) {
			super.built.fields = fields;
			return this;
		}

	}
}
