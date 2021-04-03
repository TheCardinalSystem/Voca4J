package com.cardinal.voca4j.api.song.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.song.Song;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets list of songs with the given PV.
 * 
 * @author Cardinal System
 *
 */
public class SongByPVGet extends FieldValueRequest implements ResponseDeserializer<Song> {

	@Require
	private PVService pvService;
	@Require
	private String pvId;
	@JoiningFormat
	private SongField[] fields;
	private Language lang;

	private SongByPVGet() {
		super("/songs/byPv");
	}

	private SongByPVGet(PVService pvService, String pvId, SongField[] fields, Language lang) {
		super("/songs/byPv");
		this.pvService = pvService;
		this.pvId = pvId;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public SongByPVGet copy() {
		return new SongByPVGet(pvService, pvId, fields, lang);
	}

	@Override
	public Song deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, Song.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongByPVGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongByPVGetBuilder extends StateControlledBuilder<SongByPVGet> {

		protected SongByPVGetBuilder() {
			super(new SongByPVGet());
		}

		/**
		 * PV ID (required). For example sm123456.
		 * 
		 * @param pvId PV ID.
		 * @return this, for chaining.
		 * @see PVService#getID(String)
		 * @see PVService#getService(String)
		 */
		public SongByPVGetBuilder setPVID(String pvId) {
			super.built.pvId = pvId;
			return this;
		}

		/**
		 * PV service (required).
		 * 
		 * @param pvService PV Service.
		 * @return this, for chaining.
		 * @see PVService#getService(String)
		 */
		public SongByPVGetBuilder setPVService(PVService pvService) {
			super.built.pvService = pvService;
			return this;
		}

		/**
		 * Song fields to load
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public SongByPVGetBuilder setFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public SongByPVGetBuilder setLanguage(Language lang) {
			super.built.lang = lang;
			return this;
		}

	}

}
