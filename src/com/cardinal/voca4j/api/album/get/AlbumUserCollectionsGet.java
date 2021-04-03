package com.cardinal.voca4j.api.album.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.user.UserCollectionAlbum;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * A {@link Request} implementation that gets a list of user collections
 * containing a given album.
 * 
 * @author Cardinal System
 *
 */
public class AlbumUserCollectionsGet extends FieldValueRequest
		implements ResponseDeserializer<List<UserCollectionAlbum>> {
	
	private static final Type RESPONSE_TYPE = new TypeToken<List<UserCollectionAlbum>>() {}.getType();

	@Require
	private Integer id;
	private Language languagePreference;

	private AlbumUserCollectionsGet() {
		super("/albums/{id}/user-collections");
	}

	private AlbumUserCollectionsGet(int id, Language languagePreference) {
		super("/albums/{id}/user-collections");
		this.id = id;
		this.languagePreference = languagePreference;
	}

	@Override
	public AlbumUserCollectionsGet copy() {
		return new AlbumUserCollectionsGet(id, languagePreference);
	}

	@Override
	public List<UserCollectionAlbum> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an
	 * {@link AlbumUserCollectionsGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumUserCollectionsGetBuilder extends StateControlledBuilder<AlbumUserCollectionsGet> {

		protected AlbumUserCollectionsGetBuilder() {
			super(new AlbumUserCollectionsGet());
		}

		/**
		 * Sets the album Id.
		 * 
		 * @param id the id.
		 * @return this, for chaining.
		 */
		public AlbumUserCollectionsGetBuilder setAlbumID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param languagePreference language preference.
		 * @return this, or chaining.
		 */
		public AlbumUserCollectionsGetBuilder setLanguagePreference(Language languagePreference) {
			super.built.languagePreference = languagePreference;
			return this;
		}
	}

}
