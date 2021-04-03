package com.cardinal.voca4j.api.album.query;

import java.time.LocalDate;

import com.cardinal.voca4j.api.ArtistParticipationStatus;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AdvancedAlbumFilter;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.api.album.AlbumSort;
import com.cardinal.voca4j.api.album.AlbumType;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.RequireSet;

/**
 * A {@link Request} implementation that queries albums.
 * 
 * @author Cardinal System
 *
 */
public class AlbumQuery extends FieldValueRequest {
	/**
	 * Album name query (optional).
	 */
	@Encode
	private String query;
	/**
	 * Disc type. By default nothing. Possible values are Album, Single, EP,
	 * SplitAlbum, Compilation, Video, Other. Note: only one type supported for now.
	 */
	private AlbumType discTypes;
	// Query string looks like ?tagName=tag1&tagName=tag2
	/**
	 * Filter by tag name (optional). This filter can be specified multiple times.
	 */
	@Encode
	@JoiningFormat
	private String[] tagName;
	// Same format as above (?tagId=1)
	/**
	 * Filter by tag Id (optional). This filter can be specified multiple times.
	 */
	@JoiningFormat
	private Integer[] tagId;
	/**
	 * Include child tags, if the tags being filtered by have any.
	 */
	private Boolean childTags;
	// Same format as tagNames
	/**
	 * Filter by artist Id (optional).
	 */
	@JoiningFormat
	private Integer[] artistId;
	/**
	 * Filter by artist participation status. Only valid if artistId is specified.
	 * Everything (default): Show all albums by that artist (no filter).
	 * OnlyMainAlbums: Show only main albums by that artist. OnlyCollaborations:
	 * Show only collaborations by that artist.
	 */
	private ArtistParticipationStatus artistParticipationStatus;

	/**
	 * Include child voicebanks, if the artist being filtered by has any.
	 */
	private Boolean childVoicebanks;

	/**
	 * Include members of groups. This applies if {artistId} is a group.
	 */
	private Boolean includeMembers;

	/**
	 * Filter by album barcode (optional).
	 */
	private Long barcode;

	/**
	 * Filter by entry status (optional).
	 */
	private EntryStatus status;

	/**
	 * Filter by albums whose release date is after this date (inclusive).
	 */
	private LocalDate releaseDateAfter;

	/**
	 * Filter by albums whose release date is before this date (exclusive).
	 */
	private LocalDate releaseDateBefore;

	/**
	 * List of advanced filters (optional).
	 */
	@JoiningFormat
	private AdvancedAlbumFilter[] AdvancedAlbumFilters;

	/**
	 * First item to be retrieved (optional, defaults to 0).
	 */
	private Integer start;

	/**
	 * Maximum number of results to be loaded (optional, defaults to 10, maximum of
	 * 50).
	 */
	private Integer maxResults;

	/**
	 * Whether to load total number of items (optional, default to false).
	 */
	private Boolean getTotalCount;

	/**
	 * Sort rule (optional, defaults to Name). Possible values are None, Name,
	 * ReleaseDate, ReleaseDateWithNulls, AdditionDate, RatingAverage, RatingTotal,
	 * NameThenReleaseDate
	 */
	private AlbumSort sort;

	/**
	 * Whether the search should prefer accurate matches. If this is true, entries
	 * that match by prefix will be moved first, instead of being sorted
	 * alphabetically. Requires a text query. Does not support pagination. This is
	 * mostly useful for autocomplete boxes.
	 */
	private Boolean preferAccurateMatches;

	/**
	 * Whether to search for deleted entries. If this is true, only deleted entries
	 * will be returned. If this is false (default), deleted entries are not
	 * returned.
	 */
	private Boolean deleted;

	/**
	 * Match mode for artist name (optional, defaults to Exact).
	 */
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;

	/**
	 * Optional fields (optional). Possible values are artists, names, pvs, tags,
	 * tracks, webLinks.
	 */
	private AlbumField[] fields;

	/**
	 * Content language preference (optional).
	 */
	private Language lang;

	private AlbumQuery() {
		super("https://vocadb.net/api/albums");
	}

	private AlbumQuery(String query, AlbumType discTypes, String[] tagNames, Integer[] tagIDs, Boolean childTags,
			Integer[] artistId, ArtistParticipationStatus artistParticipationStatus, Boolean childVoicebanks,
			Boolean includeMembers, Long barcode, EntryStatus status, LocalDate releaseDateAfter,
			LocalDate releaseDateBefore, AdvancedAlbumFilter[] AdvancedAlbumFilters, Integer start, Integer maxResults,
			Boolean getTotalCount, AlbumSort sort, Boolean preferAccurateMatches, Boolean deleted,
			NameMatchMode nameMatchMode, AlbumField[] fields, Language lang) {
		super("https://vocadb.net/api/albums");
		this.query = query;
		this.discTypes = discTypes;
		this.tagName = tagNames;
		this.tagId = tagIDs;
		this.childTags = childTags;
		this.artistId = artistId;
		this.artistParticipationStatus = artistParticipationStatus;
		this.childVoicebanks = childVoicebanks;
		this.includeMembers = includeMembers;
		this.barcode = barcode;
		this.status = status;
		this.releaseDateAfter = releaseDateAfter;
		this.releaseDateBefore = releaseDateBefore;
		this.AdvancedAlbumFilters = AdvancedAlbumFilters;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.sort = sort;
		this.preferAccurateMatches = preferAccurateMatches;
		this.deleted = deleted;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public AlbumQuery copy() {
		return new AlbumQuery(query, discTypes, tagName, tagId, childTags, artistId, artistParticipationStatus,
				childVoicebanks, includeMembers, barcode, status, releaseDateAfter, releaseDateBefore,
				AdvancedAlbumFilters, start, maxResults, getTotalCount, sort, preferAccurateMatches, deleted,
				nameMatchMode, fields, lang);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link AlbumQuery} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class AlbumQueryBuilder extends StateControlledBuilder<AlbumQuery> {
		public int id = -1;

		protected AlbumQueryBuilder() {
			super(new AlbumQuery());
		}

		/**
		 * Sets the album name to be queried
		 * 
		 * @param query Album name query.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder setQuery(String query) {
			super.built.query = query;
			return this;
		}

		/**
		 * Sets the disc type to be filtered out. By default this is nothing.<br>
		 * <b>Note</b>: only one type is currently supported.
		 * 
		 * @param type the disc type.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterDiscType(AlbumType type) {
			super.built.discTypes = type;
			return this;
		}

		/**
		 * Sets the tag names to be filtered out.
		 * 
		 * @param names tag names.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterTagNames(String... names) {
			super.built.tagName = names;
			return this;
		}

		/**
		 * Sets the tags (by ID) to be filtered out.
		 * 
		 * @param ids tag IDs.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterTagIDs(Integer... ids) {
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
		public AlbumQueryBuilder includeChildTags(boolean includeChildTags) {
			super.built.childTags = includeChildTags;
			return this;
		}

		/**
		 * Sets the artist IDs that will be filtered out.
		 * 
		 * @param ids the artist IDs.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterArtistId(Integer... ids) {
			super.built.artistId = ids;
			return this;
		}

		/**
		 * Sets the artist participation status that will be filtered out. Only valid if
		 * {@link AlbumQueryBuilder#filterArtistId(Integer...)} is specified.
		 * 
		 * @param status the status.
		 * @return this, for chaining.
		 * @see ArtistParticipationStatus
		 */
		@RequireSet("artistId")
		public AlbumQueryBuilder filterArtistParticipationStatus(ArtistParticipationStatus status) {
			super.built.artistParticipationStatus = status;
			return this;
		}

		/**
		 * Sets the filter to include child voicebanks, if the artist being filtered by
		 * has any.
		 * 
		 * @param includeChildVoicebanks include child voicebanks.
		 * @return this, for chaining
		 */
		@RequireSet("artistId")
		public AlbumQueryBuilder includeChildVoicebanks(boolean includeChildVoicebanks) {
			super.built.childVoicebanks = includeChildVoicebanks;
			return this;
		}

		/**
		 * Sets the response to include members of groups. This applies if any of the
		 * specified artist IDs are groups.
		 * 
		 * @param includeMembers include members;
		 * @return this, for chaining.
		 */
		@RequireSet("artistId")
		public AlbumQueryBuilder includeMembers(boolean includeMembers) {
			super.built.includeMembers = includeMembers;
			return this;
		}

		/**
		 * Sets the album barcode to be queried.
		 * 
		 * @param barcode the barcode.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder setBarcode(long barcode) {
			super.built.barcode = barcode;
			return this;
		}

		/**
		 * Sets the query to filter by the specified entry status.
		 * 
		 * @param status the status.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterByEntryStatus(EntryStatus status) {
			super.built.status = status;
			return this;
		}

		/**
		 * Filter by albums whose release date is after the specified date (inclusive).
		 * 
		 * @param date the date.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterAfterDate(LocalDate date) {
			super.built.releaseDateAfter = date;
			return this;
		}

		/**
		 * Filter by albums whose release date is before the specified date (inclusive).
		 * 
		 * @param date the date.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterBeforeDate(LocalDate date) {
			super.built.releaseDateAfter = date;
			return this;
		}

		/**
		 * Filter albums whose release date is after the first date (inclusive) and
		 * before the second date (exclusive).
		 * 
		 * @param origin the origin.
		 * @param bound  the bound.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder filterBetween(LocalDate origin, LocalDate bound) {
			super.built.releaseDateAfter = origin;
			super.built.releaseDateBefore = bound;
			return this;
		}

		/**
		 * Sets the advanced filters to be used in the final query.
		 * 
		 * @param filters the filters.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder setAdvancedFilters(AdvancedAlbumFilter... filters) {
			super.built.AdvancedAlbumFilters = filters;
			return this;
		}

		/**
		 * Sets the first item to be retrieved from the results (Essentially an offset).
		 * 
		 * @param start
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder setStart(@AllowZero int start) {
			super.built.start = start;
			return this;
		}

		/**
		 * Sets the maximum number of results to be loaded (defaults to 10, maximum of
		 * 50).
		 * 
		 * @param max the max
		 * @return this, for chaining.
		 */
		@BypassChecks(state = false)
		public AlbumQueryBuilder setMaximumResults(int max) {
			if (max > 50 || max < 1)
				throw new IllegalArgumentException(max + " | Expected 'max > 0' and 'max <= 50'");
			super.built.maxResults = max;
			return this;
		}

		/**
		 * Sets whether to load total number of items (defaults to false).
		 * 
		 * @param getTotalCount get total count
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder getTotalCount(boolean getTotalCount) {
			super.built.getTotalCount = getTotalCount;
			return this;
		}

		/**
		 * Sets the sorting rule (defaults to {@link AlbumSort#Name}).
		 * 
		 * @param rule the rule.
		 * @return this, for chaining.
		 * @see AlbumSort
		 */
		public AlbumQueryBuilder setSortingRule(AlbumSort rule) {
			super.built.sort = rule;
			return this;
		}

		/**
		 * Whether the search should prefer accurate matches. If this is true, entries
		 * that match by prefix will be moved first, instead of being sorted
		 * alphabetically. Requires a text query. Does not support pagination. This is
		 * mostly useful for autocomplete boxes.
		 * 
		 * @param preferAccurateMatches prefer accurate matches
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder preferAccurateMatches(boolean preferAccurateMatches) {
			super.built.preferAccurateMatches = preferAccurateMatches;
			return this;
		}

		/**
		 * Sets whether to search for deleted entries. If this is true, only deleted
		 * entries will be returned. If this is false (default), deleted entries are not
		 * returned.
		 * 
		 * @param searchDeleted search deleted entries.
		 * @return this, for chaining.
		 */
		public AlbumQueryBuilder searchDeletedEntries(boolean searchDeleted) {
			super.built.deleted = searchDeleted;
			return this;
		}

		/**
		 * Sets the match mode for artist name (in this implementation, defaults to
		 * {@link NameMatchMode#Auto}).
		 * 
		 * @param mode mode.
		 * @return this, for chaining.
		 * @see NameMatchMode
		 */
		public AlbumQueryBuilder setNameMatchMode(NameMatchMode mode) {
			super.built.nameMatchMode = mode;
			return this;
		}

		/**
		 * Optional fields.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 * @see AlbumField
		 */
		public AlbumQueryBuilder includeFields(AlbumField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Sets the content language preferences.
		 * 
		 * @param language the preferred content language.
		 * @return this, for chaining.
		 * @see Language
		 */
		public AlbumQueryBuilder setPreferredLanguages(Language language) {
			super.built.lang = language;
			return this;
		}

	}

}
