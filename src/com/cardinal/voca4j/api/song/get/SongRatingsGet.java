package com.cardinal.voca4j.api.song.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.song.SongRating;
import com.cardinal.voca4j.api.user.UserField;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets a song's rating by the song's
 * ID.<br>
 * <br>
 * Implementation Notes: The result includes ratings and user information. For
 * users who have requested not to make their ratings public, the user will be
 * empty.
 * 
 * @author Cardinal System
 *
 */
public class SongRatingsGet extends FieldValueRequest implements ResponseDeserializer<List<SongRating>> {

	public static final Type RESPONSE_TYPE = new TypeToken<List<SongRating>>() {
	}.getType();

	@Require
	private Integer id;
	private UserField[] userFields;
	private Language lang;

	private SongRatingsGet() {
		super("/songs/{id}/ratings");
	}

	private SongRatingsGet(Integer id, UserField[] userFields, Language lang) {
		super("/songs/{id}/ratings");
		this.id = id;
		this.userFields = userFields;
		this.lang = lang;
	}

	@Override
	public SongRatingsGet copy() {
		return new SongRatingsGet(id, userFields, lang);
	}

	@Override
	public List<SongRating> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongRatingsGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongRatingsGetBuilder extends StateControlledBuilder<SongRatingsGet> {

		protected SongRatingsGetBuilder() {
			super(new SongRatingsGet());
		}

		/**
		 * Sets the ID of the song whose ratings this request will get.
		 * 
		 * @param id id.
		 * 
		 * @return this, for chaining.
		 */
		public SongRatingsGetBuilder setID(int id) {
			super.built.id = id;
			return this;
		}

		/**
		 * Optional fields for the users.
		 * 
		 * @param userFields user fields.
		 * @return this, for chaining.
		 */
		public SongRatingsGetBuilder setUserFields(UserField... userFields) {
			super.built.userFields = userFields;
			return this;
		}

		/**
		 * 
		 * Content language preference.
		 * 
		 * @param lang lang.
		 * @return this, for chaining.
		 */
		public SongRatingsGetBuilder setLanguage(Language lang) {
			super.built.lang = lang;
			return this;
		}

	}

}
