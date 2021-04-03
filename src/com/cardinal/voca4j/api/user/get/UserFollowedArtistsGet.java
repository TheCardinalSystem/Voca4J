package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.artist.AdvancedArtistFilters;
import com.cardinal.voca4j.api.artist.ArtistFields;
import com.cardinal.voca4j.api.artist.ArtistSort;
import com.cardinal.voca4j.api.artist.ArtistType;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document UserFollowedArtistsGet
 */
public class UserFollowedArtistsGet extends FieldValueRequest {

	@Require
	private Integer id;
	@Encode
	private String query;
	private Boolean allowBaseVoicebanks, childTags, getTotalCount, preferAccurateMatches;
	private ArtistType artistType;
	@JoiningFormat
	private Integer[] tagId;
	private Integer followedByUserId, start, maxResults;
	private EntryStatus status;
	private AdvancedArtistFilters[] advancedFilters;
	private ArtistSort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private ArtistFields[] fields;
	private Language lang;

	private UserFollowedArtistsGet() {
		super("/users/{id}/followedArtists");
	}

	private UserFollowedArtistsGet(Integer id, String query, Boolean allowBaseVoicebanks, Boolean childTags,
			Boolean getTotalCount, Boolean preferAccurateMatches, ArtistType artistTypes, Integer[] tagId,
			Integer start, Integer maxResults, Integer followedByUserId, EntryStatus status,
			AdvancedArtistFilters[] advancedFilters, ArtistSort sort, NameMatchMode nameMatchMode,
			ArtistFields[] fields, Language lang) {
		super("/users/{id}/followedArtists");
		this.id = id;
		this.query = query;
		this.allowBaseVoicebanks = allowBaseVoicebanks;
		this.childTags = childTags;
		this.getTotalCount = getTotalCount;
		this.preferAccurateMatches = preferAccurateMatches;
		this.artistType = artistTypes;
		this.tagId = tagId;
		this.start = start;
		this.maxResults = maxResults;
		this.followedByUserId = followedByUserId;
		this.status = status;
		this.advancedFilters = advancedFilters;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new UserFollowedArtistsGet(id, query, allowBaseVoicebanks, childTags, getTotalCount,
				preferAccurateMatches, artistType, tagId, start, maxResults, followedByUserId, status, advancedFilters,
				sort, nameMatchMode, fields, lang);
	}

	public static class UserFollowedArtistsGetBuilder extends StateControlledBuilder<UserFollowedArtistsGet> {

		protected UserFollowedArtistsGetBuilder() {
			super(new UserFollowedArtistsGet());
		}

		public UserFollowedArtistsGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserFollowedArtistsGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserFollowedArtistsGetBuilder filterByTagIDs(Integer... tagIds) {
			this.built.tagId = tagIds;
			return this;
		}

		public UserFollowedArtistsGetBuilder filterByArtistType(ArtistType artistType) {
			this.built.artistType = artistType;
			return this;
		}

		public UserFollowedArtistsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public UserFollowedArtistsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserFollowedArtistsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public UserFollowedArtistsGetBuilder setSortingRule(ArtistSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public UserFollowedArtistsGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserFollowedArtistsGetBuilder includeFields(ArtistFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public UserFollowedArtistsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}
}
