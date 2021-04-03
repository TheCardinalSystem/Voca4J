package com.cardinal.voca4j.api.pv.get;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.pv.PromotionalVideo;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.lang.Encode;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation which finds a song by a provided PV
 * (promotional video) and retrieves all the PVs listed for that song.
 * 
 * @author Cardinal System
 *
 */
public class PVsForSongsGet extends FieldValueRequest implements ResponseDeserializer<QueriedList<PromotionalVideo>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<PromotionalVideo>>() {
	}.getType();

	@Encode
	private String name, author;
	private PVService service;
	private Integer maxResults;
	private Boolean getTotalCount;
	private Language lang;

	private PVsForSongsGet() {
		super("/pvs/for-songs");
	}

	private PVsForSongsGet(String name, String author, PVService service, Integer maxResults, Boolean getTotalCount,
			Language lang) {
		super("/pvs/for-songs");
		this.name = name;
		this.author = author;
		this.service = service;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new PVsForSongsGet(name, author, service, maxResults, getTotalCount, lang);
	}

	@Override
	public QueriedList<PromotionalVideo> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class PVsForSongsGetBuilder extends StateControlledBuilder<PVsForSongsGet> {

		protected PVsForSongsGetBuilder() {
			super(new PVsForSongsGet());
		}

		public PVsForSongsGetBuilder filterPVTitle(String title) {
			this.built.name = title;
			return this;
		}

		public PVsForSongsGetBuilder filterUploaderName(String uploaderName) {
			this.built.author = uploaderName;
			return this;
		}

		public PVsForSongsGetBuilder filterPVService(PVService service) {
			this.built.service = service;
			return this;
		}

		public PVsForSongsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public PVsForSongsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public PVsForSongsGetBuilder setLangaugePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
