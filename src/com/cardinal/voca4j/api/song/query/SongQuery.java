package com.cardinal.voca4j.api.song.query;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.ArtistParticipationStatus;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.song.Song;
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
import com.cardinal.voca4j.lang.RequireSet;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that queries songs.
 * 
 * @author Cardinal System
 *
 */
public class SongQuery extends FieldValueRequest implements ResponseDeserializer<QueriedList<Song>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<Song>>() {
	}.getType();

	@Encode
	private String query;
	private SongType[] songTypes;
	private LocalDate afterDate, beforeDate;
	@Encode
	@JoiningFormat
	private String[] tagName;
	@JoiningFormat
	private Integer[] tagId, artistId;
	private Boolean childTags, unifyTypesAndTags, childVoicebanks, includeMembers, onlyWithPvs, getTotalCount,
			preferAccurateMatches;
	private ArtistParticipationStatus artistParticipationStatus;
	private PVService[] pvServices;
	private Integer since, minScore, userCollectionId, releaseEventId, parentSongId, start, maxResults;
	private EntryStatus status;
	private AdvancedSongFilters[] AdvancedSongFilters;
	private SongSort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private SongField[] fields;
	private Language lang;

	private SongQuery() {
		super("/songs");
	}

	private SongQuery(String query, SongType[] songTypes, LocalDate afterDate, LocalDate beforeDate, String[] tagName,
			Integer[] tagId, Integer[] artistId, Boolean childTags, Boolean unifyTypesAndTags, Boolean childVoicebanks,
			Boolean includeMembers, Boolean onlyWithPvs, Boolean getTotalCount, Boolean preferAccurateMatches,
			ArtistParticipationStatus artistParticipationStatus, PVService[] pvServices, Integer since,
			Integer minScore, Integer userCollectionId, Integer releaseEventId, Integer parentSongId, Integer start,
			Integer maxResults, EntryStatus status, AdvancedSongFilters[] AdvancedSongFilters, SongSort sort,
			NameMatchMode nameMatchMode, SongField[] fields, Language lang) {
		super("/songs");
		this.query = query;
		this.songTypes = songTypes;
		this.afterDate = afterDate;
		this.beforeDate = beforeDate;
		this.tagName = tagName;
		this.tagId = tagId;
		this.artistId = artistId;
		this.childTags = childTags;
		this.unifyTypesAndTags = unifyTypesAndTags;
		this.childVoicebanks = childVoicebanks;
		this.includeMembers = includeMembers;
		this.onlyWithPvs = onlyWithPvs;
		this.getTotalCount = getTotalCount;
		this.preferAccurateMatches = preferAccurateMatches;
		this.artistParticipationStatus = artistParticipationStatus;
		this.pvServices = pvServices;
		this.since = since;
		this.minScore = minScore;
		this.userCollectionId = userCollectionId;
		this.releaseEventId = releaseEventId;
		this.parentSongId = parentSongId;
		this.start = start;
		this.maxResults = maxResults;
		this.status = status;
		this.AdvancedSongFilters = AdvancedSongFilters;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public SongQuery copy() {
		return new SongQuery(query, songTypes, afterDate, beforeDate, tagName, tagId, artistId, childTags,
				unifyTypesAndTags, childVoicebanks, includeMembers, onlyWithPvs, getTotalCount, preferAccurateMatches,
				artistParticipationStatus, pvServices, since, minScore, userCollectionId, releaseEventId, parentSongId,
				start, maxResults, status, AdvancedSongFilters, sort, nameMatchMode, fields, lang);
	}

	@Override
	public QueriedList<Song> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongQuery}.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongQueryBuilder extends StateControlledBuilder<SongQuery> {

		protected SongQueryBuilder() {
			super(new SongQuery());
		}

		/**
		 * Sets the song name to be queried
		 * 
		 * @param query Song name query.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setQuery(String query) {
			super.built.query = query;
			return this;
		}

		/**
		 * Filtered song types.
		 * 
		 * @param type the type.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterSongTypes(SongType type[]) {
			super.built.songTypes = type;
			return this;
		}

		/**
		 * Filter by songs published after this date (inclusive).
		 * 
		 * @param date the date.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterAfterDate(LocalDate date) {
			super.built.afterDate = date;
			return this;
		}

		/**
		 * Filter by songs published before this date (exclusive).
		 * 
		 * @param date the date.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterBeforeDate(LocalDate date) {
			super.built.beforeDate = date;
			return this;
		}

		/**
		 * Filter songs whose release date is after the first date (inclusive) and
		 * before the second date (exclusive).
		 * 
		 * @param origin the origin.
		 * @param bound  the bound.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterBetween(LocalDate origin, LocalDate bound) {
			this.built.afterDate = origin;
			this.built.beforeDate = bound;
			return this;
		}

		/**
		 * Sets the tag names to be filtered out.
		 * 
		 * @param names tag names.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterTagNames(String... names) {
			super.built.tagName = names;
			return this;
		}

		/**
		 * Sets the tags (by ID) to be filtered out.
		 * 
		 * @param ids tag IDs.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterTagIDs(Integer... ids) {
			super.built.tagId = ids;
			return this;
		}

		/**
		 * Sets the filter to include child tags, if the tags being filtered by have
		 * any.
		 * 
		 * @param includeChildTags include child tags.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder includeChildTags(boolean includeChildTags) {
			super.built.childTags = includeChildTags;
			return this;
		}

		/**
		 * 
		 * When searching by song type, search by associated tag as well, and vice
		 * versa.
		 * 
		 * @param unifyTypesAndTags unify types and tags.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder unifyTypesAndTags(boolean unifyTypesAndTags) {
			super.built.unifyTypesAndTags = unifyTypesAndTags;
			return this;
		}

		/**
		 * Filter by artist IDs.
		 * 
		 * @param artistIDs the artist IDs.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterArtistIDs(Integer... artistIDs) {
			super.built.artistId = artistIDs;
			return this;
		}

		/**
		 * Filter by artist participation status. Only valid if artistId is specified.
		 * 
		 * @param artistParticipationStatus artist participation status
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterArtistParticipationStatus(ArtistParticipationStatus artistParticipationStatus) {
			super.built.artistParticipationStatus = artistParticipationStatus;
			return this;
		}

		/**
		 * Include child voicebanks, if the artist being filtered by has any.
		 * 
		 * @param includeChildVoicebanks include child voicebanks.
		 * @return this, for chaining.
		 */
		@RequireSet("artistId")
		public SongQueryBuilder includeChildVoicebanks(boolean includeChildVoicebanks) {
			super.built.childVoicebanks = includeChildVoicebanks;
			return this;
		}

		/**
		 * 
		 * Include members of groups. This applies if
		 * {@link SongQueryBuilder#filterArtistIDs(Integer...)} contains a group.
		 * 
		 * @param includeMembers include members.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder includeMembers(boolean includeMembers) {
			super.built.includeMembers = includeMembers;
			return this;
		}

		/**
		 * 
		 * Whether to only include songs with at least one PV.
		 * 
		 * @param onlyWithPvs only with pvs
		 * @return this, for chaining.
		 */
		public SongQueryBuilder onlyWithPvs(boolean onlyWithPvs) {
			super.built.onlyWithPvs = onlyWithPvs;
			return this;
		}

		/**
		 * Filter by one or more PV services. The song will pass the filter if it has a
		 * PV for any of the matched services.
		 * 
		 * @param pvServices PV Services.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterPVServices(PVService... pvServices) {
			super.built.pvServices = pvServices;
			return this;
		}

		/**
		 * The documentation says: "Allow only entries that have been created at most
		 * this many hours ago. By default there is no filtering." However, I cannot
		 * figure out the actual behavior of this field.
		 * 
		 * @param hours hours.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterSince(int hours) {
			super.built.since = hours;
			return this;
		}

		/**
		 * Minimum rating score.
		 * 
		 * @param minScore minScore.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setMinimumScore(int minScore) {
			super.built.minScore = minScore;
			return this;
		}

		/**
		 * Filter by user's rated songs. By default there is no filtering.
		 * 
		 * @param userCollectionId user collection ID.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterUserCollectionID(int userCollectionId) {
			super.built.userCollectionId = userCollectionId;
			return this;
		}

		/**
		 * Filter by release event. By default there is no filtering.
		 * 
		 * @param releaseEventId release event ID.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterReleaseEventID(int releaseEventId) {
			super.built.releaseEventId = releaseEventId;
			return this;
		}

		/**
		 * Filter by parent song. By default there is no filtering.
		 * 
		 * @param parentSongId parent song ID.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterParentSongID(int parentSongId) {
			super.built.parentSongId = parentSongId;
			return this;
		}

		/**
		 * Filter by entry status.
		 * 
		 * @param status status.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder filterStatus(EntryStatus status) {
			super.built.status = status;
			return this;
		}

		/**
		 * List of advanced filters.
		 * 
		 * @param filters filters.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setAdvancedSongFilters(AdvancedSongFilters... filters) {
			super.built.AdvancedSongFilters = filters;
			return this;
		}

		/**
		 * First item to be retrieved (defaults to 0).
		 * 
		 * @param start start.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setStart(@AllowZero int start) {
			super.built.start = start;
			return this;
		}

		/**
		 * Maximum number of results to be loaded (defaults to 10, maximum of 50).
		 * 
		 * @param maxResults max results.
		 * @return this, for chaining.
		 */
		@BypassChecks(state = false)
		public SongQueryBuilder setMaxResults(int maxResults) {
			if (maxResults < 1 || maxResults > 50)
				throw new IllegalArgumentException(maxResults + " | Expected a value greater than 0 and less than 50.");
			super.built.maxResults = maxResults;
			return this;
		}

		/**
		 * 
		 * Whether to load total number of items (default to false).
		 * 
		 * @param getTotalCount get total count.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder getTotalCount(boolean getTotalCount) {
			super.built.getTotalCount = getTotalCount;
			return this;
		}

		/**
		 * Sort rule (optional, defaults to Name).
		 * 
		 * @param sort sort.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setSort(SongSort sort) {
			super.built.sort = sort;
			return this;
		}

		/**
		 * Whether the search should prefer accurate matches. If this is true, entries
		 * that match by prefix will be moved first, instead of being sorted
		 * alphabetically. Requires a text query. Does not support pagination. This is
		 * mostly useful for autocomplete boxes.
		 * 
		 * @param preferAccurateMatches prefer accurate matches.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder preferAccurateMatches(boolean preferAccurateMatches) {
			super.built.preferAccurateMatches = preferAccurateMatches;
			return this;
		}

		/**
		 * 
		 * Match mode for song name (defaults to {@link NameMatchMode#Auto} in this
		 * implementation).
		 * 
		 * @param nameMatchMode name match mode.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			super.built.nameMatchMode = nameMatchMode;
			return this;
		}

		/**
		 * List of optional fields.
		 * 
		 * @param fields field.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder includeFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public SongQueryBuilder setLanguage(Language lang) {
			super.built.lang = lang;
			return this;
		}
	}

}
