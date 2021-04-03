package com.cardinal.voca4j.api.user.get;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.song.SongRating;
import com.cardinal.voca4j.api.song.AdvancedSongFilters;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.api.user.SongVoteRating;
import com.cardinal.voca4j.api.user.UserRatedSongSort;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.LogicalOperator;
import com.cardinal.voca4j.lang.Require;
import com.cardinal.voca4j.lang.RequireSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document UserRatedSongsGet
 */
public class UserRatedSongsGet extends FieldValueRequest implements ResponseDeserializer<QueriedList<SongRating>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<SongRating>>() {
	}.getType();

	@Require
	private Integer id;
	@Encode
	private String query, tagName;
	@JoiningFormat
	private Integer[] tagId, artistId;
	private Boolean childVoicebanks, groupByRating, getTotalCount;
	private LogicalOperator artistGrouping;
	private SongVoteRating rating;
	private Integer songListId, start, maxResults;
	private PVService[] pvServices;
	private AdvancedSongFilters[] advancedFilters;
	private UserRatedSongSort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private SongField[] fields;
	private Language lang;

	private UserRatedSongsGet() {
		super("/users/{id}/ratedSongs");
	}

	private UserRatedSongsGet(Integer id, String query, String tagName, Integer[] tagId, Integer[] artistId,
			Boolean childVoicebanks, Boolean groupByRating, Boolean getTotalCount, LogicalOperator artistGrouping,
			SongVoteRating rating, Integer songListId, Integer start, Integer maxResults, PVService[] pvServices,
			AdvancedSongFilters[] advancedFilters, UserRatedSongSort sort, NameMatchMode nameMatchMode,
			SongField[] fields, Language lang) {
		super("/users/{id}/ratedSongs");
		this.id = id;
		this.query = query;
		this.tagName = tagName;
		this.tagId = tagId;
		this.artistId = artistId;
		this.childVoicebanks = childVoicebanks;
		this.groupByRating = groupByRating;
		this.getTotalCount = getTotalCount;
		this.artistGrouping = artistGrouping;
		this.rating = rating;
		this.songListId = songListId;
		this.start = start;
		this.maxResults = maxResults;
		this.pvServices = pvServices;
		this.advancedFilters = advancedFilters;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new UserRatedSongsGet(id, query, tagName, tagId, artistId, childVoicebanks, groupByRating, getTotalCount,
				artistGrouping, rating, songListId, start, maxResults, pvServices, advancedFilters, sort, nameMatchMode,
				fields, lang);
	}

	@Override
	public QueriedList<SongRating> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class UserRatedSongsGetBuilder extends StateControlledBuilder<UserRatedSongsGet> {

		protected UserRatedSongsGetBuilder() {
			super(new UserRatedSongsGet());
		}

		public UserRatedSongsGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserRatedSongsGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserRatedSongsGetBuilder filterTagName(String tagName) {
			this.built.tagName = tagName;
			return this;
		}

		public UserRatedSongsGetBuilder filterTagIDs(Integer... tagIds) {
			this.built.tagId = tagIds;
			return this;
		}

		public UserRatedSongsGetBuilder filterArtistIDs(Integer... artistIds) {
			this.built.artistId = artistIds;
			return this;
		}

		@RequireSet("artistId")
		public UserRatedSongsGetBuilder includeChildVoicebanks(boolean childVoicebanks) {
			this.built.childVoicebanks = childVoicebanks;
			return this;
		}

		public UserRatedSongsGetBuilder setArtistGrouping(LogicalOperator artistGrouping) {
			this.built.artistGrouping = artistGrouping;
			return this;
		}

		public UserRatedSongsGetBuilder filterByRating(SongVoteRating rating) {
			this.built.rating = rating;
			return this;
		}

		public UserRatedSongsGetBuilder filterSongList(int songListId) {
			this.built.songListId = songListId;
			return this;
		}

		public UserRatedSongsGetBuilder groupByRating(boolean groupByRating) {
			this.built.groupByRating = groupByRating;
			return this;
		}

		public UserRatedSongsGetBuilder filterByPVSerives(PVService... pvServices) {
			this.built.pvServices = pvServices;
			return this;
		}

		public UserRatedSongsGetBuilder setAdvancedFilters(AdvancedSongFilters... advancedFilters) {
			this.built.advancedFilters = advancedFilters;
			return this;
		}

		public UserRatedSongsGetBuilder setStart(int start) {
			this.built.start = start;
			return this;
		}

		public UserRatedSongsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserRatedSongsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public UserRatedSongsGetBuilder setSortingRule(UserRatedSongSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public UserRatedSongsGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserRatedSongsGetBuilder includeFields(SongField... fields) {
			this.built.fields = fields;
			return this;
		}

		public UserRatedSongsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
