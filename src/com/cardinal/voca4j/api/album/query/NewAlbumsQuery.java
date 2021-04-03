package com.cardinal.voca4j.api.album.query;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AlbumField;

/**
 * Gets list of upcoming or recent albums, same as front page.
 * 
 * @author Cardinal System
 *
 */
public class NewAlbumsQuery extends FieldValueRequest {

	private Language languagePreference;
	private AlbumField[] fields;

	private NewAlbumsQuery() {
		super("/albums/new");
	}

	private NewAlbumsQuery(Language languagePreference, AlbumField[] fields) {
		super("/albums/new");
		this.languagePreference = languagePreference;
		this.fields = fields;
	}

	@Override
	public NewAlbumsQuery copy() {
		return new NewAlbumsQuery(languagePreference, fields);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumQuery} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class NewAlbumsQueryBuilder extends StateControlledBuilder<NewAlbumsQuery> {
		protected NewAlbumsQueryBuilder() {
			super(new NewAlbumsQuery());
		}

		/**
		 * Sets the preferred languages for the query response.
		 * 
		 * @param languages the languages.
		 * @return this, for chaining.
		 */
		public NewAlbumsQueryBuilder setLanguagePreference(Language languages) {
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
		public NewAlbumsQueryBuilder includeFields(AlbumField... fields) {
			super.built.fields = fields;
			return this;
		}

	}

}
