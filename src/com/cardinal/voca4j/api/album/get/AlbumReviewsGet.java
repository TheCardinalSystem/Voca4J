package com.cardinal.voca4j.api.album.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.album.AlbumReview;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets an album's reviews by its ID.
 * 
 * @author Cardinal System
 *
 */
public class AlbumReviewsGet extends FieldValueRequest implements ResponseDeserializer<List<AlbumReview>> {

	private static final Type RESPONSE_TYPE = new TypeToken<List<AlbumReview>>(){}.getType();

	@Require
	private Integer id;
	private String languageCode;

	private AlbumReviewsGet() {
		super("/albums/{id}/reviews");
	}

	private AlbumReviewsGet(int id, String languageCode) {
		super("/albums/{id}/reviews");
		this.id = id;
		this.languageCode = languageCode;
	}

	@Override
	public AlbumReviewsGet copy() {
		return new AlbumReviewsGet(id, languageCode);
	}

	@Override
	public List<AlbumReview> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumReviewsGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumReviewsGetBuilder extends StateControlledBuilder<AlbumReviewsGet> {

		protected AlbumReviewsGetBuilder() {
			super(new AlbumReviewsGet());
		}

		/**
		 * Sets the ID of the album which the comments will be loaded from.
		 * 
		 * @param id the id.
		 * @return this, for chaining.
		 */
		public AlbumReviewsGetBuilder setAlbumID(int id) {
			this.built.id = id;
			return this;
		}

		/**
		 * Sets the ISO language code (e.g. 'ja', 'de', 'en')
		 * 
		 * @param languageCode the language code
		 * @return this, for chaining.
		 */
		public AlbumReviewsGetBuilder setLanguageCode(String languageCode) {
			this.built.languageCode = languageCode;
			return this;
		}

	}

}
