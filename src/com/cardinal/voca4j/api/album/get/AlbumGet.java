package com.cardinal.voca4j.api.album.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets an album by its ID.
 * 
 * @author Cardinal System
 *
 */
public class AlbumGet extends FieldValueRequest implements ResponseDeserializer<Album> {

	@Require
	private Integer id;
	private AlbumField[] fields;
	private SongField[] songFields;
	private Language lang;

	private AlbumGet() {
		super("/albums/{id}");
	}

	private AlbumGet(Integer id, AlbumField[] fields, SongField[] songFields, Language lang) {
		super("/albums/{id}");
		this.id = id;
		this.fields = fields;
		this.songFields = songFields;
		this.lang = lang;
	}

	@Override
	public AlbumGet copy() {
		return new AlbumGet(id, fields, songFields, lang);
	}

	@Override
	public Album deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, Album.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumGetBuilder extends StateControlledBuilder<AlbumGet> {

		protected AlbumGetBuilder() {
			super(new AlbumGet());
		}

		/**
		 * Sets the ID of the album that this request will get.
		 * 
		 * @param id the id.
		 * @return this, for chaining.
		 */
		public AlbumGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * Optional album fields.
		 * 
		 * @param fields the fields.
		 * @return this, for chaining.
		 */
		public AlbumGetBuilder setAlbumFields(AlbumField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Optional fields for tracks, if included.
		 * 
		 * @param fields the fields.
		 * @return this, for chaining.
		 */
		public AlbumGetBuilder setSongFields(SongField... fields) {
			super.built.songFields = fields;
			return this;
		}

		/**
		 * Sets the preferred response language.
		 * 
		 * @param language the language.
		 * @return this, for chaining.
		 */
		public AlbumGetBuilder setLanguagePreference(Language language) {
			super.built.lang = language;
			return this;
		}

	}

}
