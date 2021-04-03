package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.song.Lyrics;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets a song's lyrics by the lyrics ID.
 * 
 * @author Cardinal System
 *
 */
public class LyricsGet extends FieldValueRequest implements ResponseDeserializer<Lyrics> {

	@Require
	private Integer id;

	private LyricsGet() {
		super("/songs/lyrics/{id}");
	}

	private LyricsGet(Integer id) {
		super("/songs/lyrics/{id}");
		this.id = id;
	}

	@Override
	public LyricsGet copy() {
		return new LyricsGet(id);
	}

	@Override
	public Lyrics deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, Lyrics.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link LyricsGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class LyricsGetBuilder extends StateControlledBuilder<LyricsGet> {

		protected LyricsGetBuilder() {
			super(new LyricsGet());
		}

		/**
		 * Set's the ID of the lyrics that this request will get.
		 * 
		 * @param id id.
		 * @return this, for chaining.
		 */
		public LyricsGetBuilder setID(int id) {
			this.built.id = id;
			return this;
		}
	}

}
