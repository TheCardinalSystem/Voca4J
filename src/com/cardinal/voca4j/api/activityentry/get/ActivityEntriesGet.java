package com.cardinal.voca4j.api.activityentry.get;

import java.lang.reflect.Type;
import java.time.LocalDate;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryFields;
import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.activityentry.ActivityEntryField;
import com.cardinal.voca4j.api.activityentry.ActivityEntrySort;
import com.cardinal.voca4j.api.activityentry.EditEvent;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.activityentry.ActivityEntry;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets a list of activity entries.
 * 
 * @author Cardinal System
 *
 */
public class ActivityEntriesGet extends FieldValueRequest implements ResponseDeserializer<QueriedList<ActivityEntry>> {
	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<ActivityEntry>>() {
	}.getType();

	private LocalDate before, since;
	private Integer userId, maxResults;
	private EditEvent editEvent;
	private EntryType entryType;
	private Boolean getTotalCount;
	@JoiningFormat
	private ActivityEntryField[] fields;
	@JoiningFormat
	private EntryFields[] entryFields;
	private Language lang;
	private ActivityEntrySort sort;

	private ActivityEntriesGet() {
		super("/activityEntries");
	}

	private ActivityEntriesGet(LocalDate before, LocalDate since, Integer userId, Integer maxResults,
			EditEvent editEvent, EntryType entryType, Boolean getTotalCount, ActivityEntryField[] fields,
			EntryFields[] entryFields, Language lang, ActivityEntrySort sort) {
		super("/activityEntries");
		this.before = before;
		this.since = since;
		this.userId = userId;
		this.maxResults = maxResults;
		this.editEvent = editEvent;
		this.entryType = entryType;
		this.getTotalCount = getTotalCount;
		this.fields = fields;
		this.entryFields = entryFields;
		this.lang = lang;
		this.sort = sort;
	}

	@Override
	public ActivityEntriesGet copy() {
		return new ActivityEntriesGet(before, since, userId, maxResults, editEvent, entryType, getTotalCount, fields,
				entryFields, lang, sort);
	}

	@Override
	public QueriedList<ActivityEntry> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link ActivityEntriesGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class ActivityEntriesGetBuilder extends StateControlledBuilder<ActivityEntriesGet> {

		protected ActivityEntriesGetBuilder() {
			super(new ActivityEntriesGet());
		}

		/**
		 * Filter to return activity entries only before this date.
		 * 
		 * @param before before date.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterBefore(LocalDate before) {
			this.built.before = before;
			return this;
		}

		/**
		 * 
		 * Filter to return activity entries only after this date.
		 * 
		 * @param since since date.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterAfter(LocalDate since) {
			this.built.since = since;
			return this;
		}

		/**
		 * Filter to return activity entries only before the specified dates.
		 * 
		 * @param origin the start date.
		 * @param bound  the end date.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterBetween(LocalDate origin, LocalDate bound) {
			this.built.before = bound;
			this.built.since = origin;
			return this;
		}

		/**
		 * 
		 * Filter by user ID.
		 * 
		 * @param userId user ID.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterUserID(int userId) {
			this.built.userId = userId;
			return this;
		}

		/**
		 * Filter by entry edit event.
		 * 
		 * @param editEvent edit event.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterEditEvent(EditEvent editEvent) {
			this.built.editEvent = editEvent;
			return this;
		}

		/**
		 * 
		 * Filter by entry type.
		 * 
		 * @param entryType Entry type.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder filterEntryType(EntryType entryType) {
			this.built.entryType = entryType;
			return this;
		}

		/**
		 * Maximum number of results to return. Default 50. Maximum value 500.
		 * 
		 * @param maxResults maximum results.
		 * @return this, for chaining.
		 */
		@BypassChecks(state = false)
		public ActivityEntriesGetBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 500)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 500.");
			this.built.maxResults = maxResults;
			return this;
		}

		/**
		 * 
		 * Set whether this will load the total number of items.
		 * 
		 * @param getTotalCount get total count.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		/**
		 * Optional activity entry fields to include in the response.<br>
		 * 
		 * <hr>
		 * I honestly do not know what this filter and the
		 * {@linkplain ActivityEntriesGetBuilder#includeEntryFields(EntryFields...)}
		 * filter do, or how they differ. According to the VocaDB devs, "The
		 * <code>fields</code> field is for <a href=
		 * 'https://github.com/VocaDB/vocadb/blob/8f35df9305595e1fa1c3d4bf6a9805e5192a559b/VocaDbModel/DataContracts/Activityfeed/ActivityEntryForApiContract.cs#L19'><code>ActivityEntryForApiContract</code></a>,
		 * while the <code>entryFields</code> field is for <a href=
		 * 'https://github.com/VocaDB/vocadb/blob/8f35df9305595e1fa1c3d4bf6a9805e5192a559b/VocaDbModel/DataContracts/Api/EntryForApiContract.cs#L23'><code>EntryForApiContract</code></a>."
		 * That doesn't really mean anything to me; I'm just a Java developer. Maybe you
		 * can make sense of it.
		 * <hr>
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder includeFields(ActivityEntryField... fields) {
			this.built.fields = fields;
			return this;
		}

		/**
		 * Optional entry fields to include in the response.
		 * <hr>
		 * I honestly do not know what this filter and the
		 * {@linkplain ActivityEntriesGetBuilder#includeFields(ActivityEntryField...)}
		 * filter do, or how they differ. According to the VocaDB devs, "The
		 * <code>fields</code> field is for <a href=
		 * 'https://github.com/VocaDB/vocadb/blob/8f35df9305595e1fa1c3d4bf6a9805e5192a559b/VocaDbModel/DataContracts/Activityfeed/ActivityEntryForApiContract.cs#L19'><code>ActivityEntryForApiContract</code></a>,
		 * while the <code>entryFields</code> field is for <a href=
		 * 'https://github.com/VocaDB/vocadb/blob/8f35df9305595e1fa1c3d4bf6a9805e5192a559b/VocaDbModel/DataContracts/Api/EntryForApiContract.cs#L23'><code>EntryForApiContract</code></a>."
		 * That doesn't really mean anything to me; I'm just a Java developer. Maybe you
		 * can make sense of it.
		 * <hr>
		 * 
		 * @param entryFields entry fields.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder includeEntryFields(EntryFields... entryFields) {
			this.built.entryFields = entryFields;
			return this;
		}

		/**
		 * Sets the content language preference for the response. If the preferred
		 * language is not available, this defaults to (I believe)
		 * {@link Language#English}.
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder setLanguagePreference(Language lang) {
			this.built.lang = lang;
			return this;
		}

		/**
		 * Sets the sorting rule for results included in the response.
		 * 
		 * @param sort sorting rule.
		 * @return this, for chaining.
		 */
		public ActivityEntriesGetBuilder setSortRule(ActivityEntrySort sort) {
			this.built.sort = sort;
			return this;
		}
	}

}
