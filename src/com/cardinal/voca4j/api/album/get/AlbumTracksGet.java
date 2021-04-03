package com.cardinal.voca4j.api.album.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.album.Track;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets an ablum's track list by its ID.
 * 
 * @author Cardinal System
 *
 */
public class AlbumTracksGet extends FieldValueRequest implements ResponseDeserializer<List<Track>> {

	private static final Type RESPONSE_TYPE = new TypeToken<List<Track>>() {}.getType();

	@Require
	private Integer id;
	private SongField[] fields;
	private Language lang;

	private AlbumTracksGet() {
		super("/albums/{id}/tracks");
	}

	private AlbumTracksGet(String rootUrl, Integer id, SongField[] fields, Language lang) {
		super("/albums/{id}/tracks");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public AlbumTracksGet copy() {
		return new AlbumTracksGet(rootUrl, id, fields, lang);
	}

	@Override
	public List<Track> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumTracksGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumTracksGetBuilder extends StateControlledBuilder<AlbumTracksGet> {

		protected AlbumTracksGetBuilder() {
			super(new AlbumTracksGet());
		}

		/**
		 * Sets the ID of the user collection which the built request will get.
		 * 
		 * @param id user collection ID.
		 * @return this, for chaining.
		 */
		public AlbumTracksGetBuilder setAlbumID(Integer id) {
			this.built.id = id;
			return this;
		}

		/**
		 * Sets the track fields that will be included in this request's response.
		 * 
		 * @param fields the fields.
		 * @return this, for chaining.
		 */
		public AlbumTracksGetBuilder includeSongFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Sets the preferred response language.
		 * 
		 * @param language the language.
		 * @return this, for chaining.
		 */
		public AlbumTracksGetBuilder setLanguagePreference(Language language) {
			super.built.lang = language;
			return this;
		}
	}

}
