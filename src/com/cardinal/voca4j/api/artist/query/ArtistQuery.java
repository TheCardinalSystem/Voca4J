package com.cardinal.voca4j.api.artist.query;

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
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;

/**
 * A {@link Request} implementation that queries artists.
 * 
 * @author Cardinal System
 *
 */
public class ArtistQuery extends FieldValueRequest {

	@Encode
	private String query;
	@JoiningFormat
	private ArtistType[] artistTypes;
	private Boolean allowBaseVoicebanks, childTags, getTotalCount, preferAccurateMatches;
	@Encode
	@JoiningFormat
	private String[] tagName;
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

	private ArtistQuery() {
		super("/artists");
	}

	private ArtistQuery(String query, ArtistType[] artistTypes, Boolean allowBaseVoicebanks, Boolean childTags,
			Boolean getTotalCount, Boolean preferAccurateMatches, String[] tagName, Integer[] tagId,
			Integer followedByUserId, Integer start, Integer maxResults, EntryStatus status,
			AdvancedArtistFilters[] advancedFilters, ArtistSort sort, NameMatchMode nameMatchMode,
			ArtistFields[] fields, Language lang) {
		super("/api/artists");
		this.query = query;
		this.artistTypes = artistTypes;
		this.allowBaseVoicebanks = allowBaseVoicebanks;
		this.childTags = childTags;
		this.getTotalCount = getTotalCount;
		this.preferAccurateMatches = preferAccurateMatches;
		this.tagName = tagName;
		this.tagId = tagId;
		this.followedByUserId = followedByUserId;
		this.start = start;
		this.maxResults = maxResults;
		this.status = status;
		this.advancedFilters = advancedFilters;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new ArtistQuery(query, artistTypes, allowBaseVoicebanks, childTags, getTotalCount, preferAccurateMatches,
				tagName, tagId, followedByUserId, start, maxResults, status, advancedFilters, sort, nameMatchMode,
				fields, lang);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link ArtistQuery}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class ArtistQueryBuilder extends StateControlledBuilder<ArtistQuery> {

		protected ArtistQueryBuilder() {
			super(new ArtistQuery());
		}

		/**
		 * Sets the artist name to be queried.
		 * 
		 * @param query the query.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		/**
		 * Filters results by the specified artist type.
		 * 
		 * @param artistType artist type.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder filterArtistTypes(ArtistType... artistTypes) {
			this.built.artistTypes = artistTypes;
			return this;
		}

		/**
		 * Allow base voicebanks. If false, only root voicebanks will be allowed. Only
		 * affects voice synthesizers that can have base voicebanks.
		 * 
		 * @param allowBaseVoicebanks Allow base voicebanks.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder allowBaseVoicebanks(boolean allowBaseVoicebanks) {
			this.built.allowBaseVoicebanks = allowBaseVoicebanks;
			return this;
		}

		/**
		 * Filters results with the given tag names.
		 * 
		 * @param tagNames tag names.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder filterTagNames(String... tagNames) {
			this.built.tagName = tagNames;
			return this;
		}

		/**
		 * Filter results with the given tag IDs.
		 * 
		 * @param tagIDs tags IDs.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder filterTagIDs(Integer... tagIDs) {
			this.built.tagId = tagIDs;
			return this;
		}

		/**
		 * Include child tags, if the tags being filtered by have any.
		 * 
		 * @param allowChildTags allow child tags.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder allowChildTags(boolean allowChildTags) {
			this.built.childTags = allowChildTags;
			return this;
		}

		/**
		 * Filter artists followed by the given user.
		 * 
		 * @param userID user ID.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder filterFollowedBy(int userID) {
			this.built.followedByUserId = userID;
			return this;
		}

		/**
		 * Filter results by entry status.
		 * 
		 * @param status entry status.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder filterByEntryStatus(EntryStatus status) {
			this.built.status = status;
			return this;
		}

		/**
		 * Filter using {@linkplain AdvancedArtistFilters}.
		 * 
		 * @param artistFilters
		 * @return
		 */
		public ArtistQueryBuilder setAdvancedFilters(AdvancedArtistFilters... artistFilters) {
			this.built.advancedFilters = artistFilters;
			return this;
		}

		/**
		 * Sets the first item to be retrieved from the results. This is essentially an
		 * offset.
		 * 
		 * @param start start.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		/**
		 * Sets the maximum number of results to be included in the response. Defaults
		 * to 10, cannot exceed 100.
		 * 
		 * @param maxResults maximum results.
		 * @return this, for chaining.
		 */
		@BypassChecks(state = false)
		public ArtistQueryBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 100)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 100.");
			this.built.maxResults = maxResults;
			return this;
		}

		/**
		 * Sets whether to load total number of items.
		 * 
		 * @param getTotalCount get total count.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		/**
		 * Sets the results sorting rule (defaults to Name).
		 * 
		 * @param sortRule sorting rule.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder setSortingRule(ArtistSort sortRule) {
			this.built.sort = sortRule;
			return this;
		}

		/**
		 * Sets whether the query should prefer accurate matches. If this is true,
		 * entries that match by prefix will be moved first, instead of being sorted
		 * alphabetically. Requires a text query. Does not support pagination. This is
		 * mostly useful for autocomplete boxes.
		 * 
		 * @param preferAccurateMatches prefer accurate matches.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder preferAccurateMatches(boolean preferAccurateMatches) {
			this.built.preferAccurateMatches = preferAccurateMatches;
			return this;
		}

		/**
		 * Sets the matching mode for the artist name (see
		 * {@linkplain ArtistQueryBuilder#setQuery(String)}). Defaults to
		 * {@link NameMatchMode#Auto} in this implementation.
		 * 
		 * @param nameMatchMode name match mode.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		/**
		 * Optional artist fields to be included in the response.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder includeFields(ArtistFields... fields) {
			this.built.fields = fields;
			return this;
		}

		/**
		 * Sets the content language preference. If the preferred language is not
		 * avaibable, I believe this will default to {@link Language#English}.
		 * 
		 * @param lang preferred language.
		 * @return this, for chaining.
		 */
		public ArtistQueryBuilder setPreferredLanguage(Language lang) {
			this.built.lang = lang;
			return this;
		}
	}

}