package com.cardinal.voca4j.api.album.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.TrackField;
import com.cardinal.voca4j.api.entities.album.TrackFields;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets track fields for an album by the
 * album's ID.
 * 
 * @author Cardinal System
 *
 */
public class AlbumTrackFieldsGet extends FieldValueRequest implements ResponseDeserializer<List<TrackFields>> {

	private static final Type RESPONSE_TYPE = new TypeToken<List<TrackFields>>() {
	}.getType();

	@Require
	private Integer id;
	@JoiningFormat
	private TrackField[] field;
	private Integer discNumber;
	private Language lang;

	private AlbumTrackFieldsGet() {
		super("/albums/{id}/tracks/fields");
	}

	private AlbumTrackFieldsGet(Integer id, TrackField[] field, Integer discNumber, Language lang) {
		super("/albums/{id}/tracks/fields");
		this.id = id;
		this.field = field;
		this.discNumber = discNumber;
		this.lang = lang;
	}

	@Override
	public List<TrackFields> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	@Override
	public AlbumTrackFieldsGet copy() {
		return new AlbumTrackFieldsGet(id, field, discNumber, lang);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumTrackFieldsGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumTrackFieldsGetBuilder extends StateControlledBuilder<AlbumTrackFieldsGet> {

		protected AlbumTrackFieldsGetBuilder() {
			super(new AlbumTrackFieldsGet());
		}

		/**
		 * Sets the ID of the album whose track fields this request will get.
		 * 
		 * @param id the album Id.
		 * @return this, for chaining.
		 */
		public AlbumTrackFieldsGetBuilder setAlbumID(int id) {
			this.built.id = id;
			return this;
		}

		/**
		 * Sets the track fields which this request will get.
		 * 
		 * @param fields the fields.
		 * @return this, for chaining.
		 */
		public AlbumTrackFieldsGetBuilder setFields(TrackField... fields) {
			this.built.field = fields;
			return this;
		}

		/**
		 * Sets the disc number which the track will be retrieved from.
		 * 
		 * @param discNumber the disc number.
		 * @return this, for chaining.
		 */
		public AlbumTrackFieldsGetBuilder setDiscNumber(int discNumber) {
			this.built.discNumber = discNumber;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param languagePreference
		 * @return
		 */
		public AlbumTrackFieldsGetBuilder setLanguagePreference(Language languagePreference) {
			this.built.lang = languagePreference;
			return this;
		}
	}

}
