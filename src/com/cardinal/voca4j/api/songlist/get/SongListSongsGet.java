package com.cardinal.voca4j.api.songlist.get;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.songlist.SongListSong;
import com.cardinal.voca4j.api.song.AdvancedSongFilters;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.api.song.SongSort;
import com.cardinal.voca4j.api.song.SongType;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;
import com.cardinal.voca4j.lang.RequireSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document SongListSongsGet
 */
public class SongListSongsGet extends FieldValueRequest implements ResponseDeserializer<QueriedList<SongListSong>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<SongListSong>>() {
	}.getType();

	@Require
	private Integer listId;
	@Encode
	private String query;
	private SongType[] songTypes;
	private PVService[] pvServices;
	@JoiningFormat
	private Integer[] tagId, artistId;
	private Boolean childVoicebanks, getTotalCount;
	private AdvancedSongFilters[] advancedFilters;
	private Integer start, maxResults;
	private SongSort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private SongField[] fields;
	private Language lang;

	private SongListSongsGet() {
		super("/songLists/{listId}/songs");
	}

	private SongListSongsGet(Integer listId, String query, SongType[] songTypes, PVService[] pvServices,
			Integer[] tagId, Integer[] artistId, Boolean childVoicebanks, Boolean getTotalCount,
			AdvancedSongFilters[] advancedFilters, Integer start, Integer maxResults, SongSort sort,
			NameMatchMode nameMatchMode, SongField[] fields, Language lang) {
		super("/songLists/{listId}/songs");
		this.listId = listId;
		this.query = query;
		this.songTypes = songTypes;
		this.pvServices = pvServices;
		this.tagId = tagId;
		this.artistId = artistId;
		this.childVoicebanks = childVoicebanks;
		this.getTotalCount = getTotalCount;
		this.advancedFilters = advancedFilters;
		this.start = start;
		this.maxResults = maxResults;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new SongListSongsGet(listId, query, songTypes, pvServices, tagId, artistId, childVoicebanks,
				getTotalCount, advancedFilters, start, maxResults, sort, nameMatchMode, fields, lang);
	}

	@Override
	public QueriedList<SongListSong> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class SongListSongsGetBuilder extends StateControlledBuilder<SongListSongsGet> {

		protected SongListSongsGetBuilder() {
			super(new SongListSongsGet());
		}

		public SongListSongsGetBuilder setListID(int listId) {
			this.built.listId = listId;
			return this;
		}

		public SongListSongsGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public SongListSongsGetBuilder filterSongTypes(SongType... songTypes) {
			this.built.songTypes = songTypes;
			return this;
		}

		public SongListSongsGetBuilder filterPVServices(PVService... pvServices) {
			this.built.pvServices = pvServices;
			return this;
		}

		public SongListSongsGetBuilder filterTags(Integer... tagIDs) {
			this.built.tagId = tagIDs;
			return this;
		}

		public SongListSongsGetBuilder filterAritsts(Integer... artistIDs) {
			this.built.artistId = artistIDs;
			return this;
		}

		@RequireSet("artistId")
		public SongListSongsGetBuilder includeChildVoicebanks(boolean childVoicebanks) {
			this.built.childVoicebanks = childVoicebanks;
			return this;
		}

		public SongListSongsGetBuilder setAdvancedFilters(AdvancedSongFilters... advancedFilters) {
			this.built.advancedFilters = advancedFilters;
			return this;
		}

		public SongListSongsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		@BypassChecks(state = false)
		public SongListSongsGetBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 50)
				throw new IllegalArgumentException(maxResults + " | Expected number between 1 and 50!");
			this.built.maxResults = maxResults;
			return this;
		}

		public SongListSongsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public SongListSongsGetBuilder setSortingRule(SongSort sort) {
			this.built.sort = sort;
			return this;
		}

		public SongListSongsGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public SongListSongsGetBuilder includeFields(SongField... fields) {
			this.built.fields = fields;
			return this;
		}

		public SongListSongsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
