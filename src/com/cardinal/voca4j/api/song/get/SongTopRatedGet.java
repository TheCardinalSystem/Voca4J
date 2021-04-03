package com.cardinal.voca4j.api.song.get;

import java.time.LocalDate;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.song.Filter;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.api.song.Vocalist;

/**
 * A {@link Request} implementation that gets a list of top-rated songs.
 * 
 * @author Cardinal System
 *
 */
public class SongTopRatedGet extends FieldValueRequest {

	private Integer durationHours, maxResults;
	private LocalDate startDate;
	private Filter filterBy;
	private Vocalist vocalist;
	private SongField[] fields;
	private Language languagePreference;

	private SongTopRatedGet(String rootUrl, Integer durationHours, Integer maxResults, LocalDate startDate,
			Filter filterBy, Vocalist vocalist, SongField[] fields, Language languagePreference) {
		super(rootUrl);
		this.durationHours = durationHours;
		this.maxResults = maxResults;
		this.startDate = startDate;
		this.filterBy = filterBy;
		this.vocalist = vocalist;
		this.fields = fields;
		this.languagePreference = languagePreference;
	}

	private SongTopRatedGet() {
		super("/songs/top-rated");
	}

	@Override
	public SongTopRatedGet copy() {
		return new SongTopRatedGet(rootUrl, durationHours, maxResults, startDate, filterBy, vocalist, fields,
				languagePreference);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link SongTopRatedGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class SongTopRatedGetBuilder extends StateControlledBuilder<SongTopRatedGet> {

		protected SongTopRatedGetBuilder() {
			super(new SongTopRatedGet());
		}

		/**
		 * Duration in hours from which to get songs.
		 * 
		 * @param hours hours.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setDurationHours(int hours) {
			super.built.durationHours = hours;
			return this;
		}

		/**
		 * Lower bound of the date.
		 * 
		 * @param date date.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setStartDate(LocalDate date) {
			super.built.startDate = date;
			return this;
		}

		/**
		 * Filtering mode.
		 * 
		 * @param filter filter.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setFilter(Filter filter) {
			super.built.filterBy = filter;
			return this;
		}

		/**
		 * Vocalist selection
		 * 
		 * @param vocalist vocalist
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setVocaloid(Vocalist vocalist) {
			super.built.vocalist = vocalist;
			return this;
		}

		/**
		 * 
		 * Maximum number of results to be loaded.
		 * 
		 * @param maxResults maximum results.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setMaximumResults(int maxResults) {
			super.built.maxResults = maxResults;
			return this;
		}

		/**
		 * Song fields to load
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setFields(SongField... fields) {
			super.built.fields = fields;
			return this;
		}

		/**
		 * Content language preference.
		 * 
		 * @param lang language.
		 * @return this, for chaining.
		 */
		public SongTopRatedGetBuilder setLanguage(Language lang) {
			super.built.languagePreference = lang;
			return this;
		}

	}

}
