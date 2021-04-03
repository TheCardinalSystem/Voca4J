package com.cardinal.voca4j.api.artist.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.artist.ArtistFields;
import com.cardinal.voca4j.api.artist.ArtistRelations;
import com.cardinal.voca4j.api.entities.artist.Artist;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets an artist by his ID.
 * 
 * @author Cardinal System
 *
 */
public class ArtistGet extends FieldValueRequest implements ResponseDeserializer<Artist> {

	@Require
	private Integer id;
	private ArtistFields[] fields;
	private ArtistRelations[] relations;
	private Language lang;

	private ArtistGet() {
		super("/artists/{id}");
	}

	private ArtistGet(Integer id, ArtistFields[] fields, ArtistRelations[] relations, Language lang) {
		super("/artists/{id}");
		this.id = id;
		this.fields = fields;
		this.relations = relations;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ArtistGet(id, fields, relations, lang);
	}

	@Override
	public Artist deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, Artist.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link ArtistGet}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class ArtistGetBuilder extends StateControlledBuilder<ArtistGet> {

		protected ArtistGetBuilder() {
			super(new ArtistGet());
		}

		/**
		 * Sets the artist ID.
		 * 
		 * @param id artist ID.
		 * @return this, for chaining.
		 */
		public ArtistGetBuilder setID(int id) {
			this.built.id = id;
			return this;
		}

		/**
		 * Optional fields to be included in the response.
		 * 
		 * @param fields artist fields.
		 * @return this, for chaining.
		 */
		public ArtistGetBuilder setFields(ArtistFields... fields) {
			this.built.fields = fields;
			return this;
		}

		/**
		 * Optional artist relations.
		 * 
		 * @param relations artist relations.
		 * @return this, for chaining.
		 */
		public ArtistGetBuilder setRelations(ArtistRelations... relations) {
			this.built.relations = relations;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public ArtistGetBuilder setLanguagePreference(Language lang) {
			this.built.lang = lang;
			return this;
		}

	}

}
