package com.cardinal.voca4j.api.venue.query;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.venue.Venue;
import com.cardinal.voca4j.api.venue.DistanceUnit;
import com.cardinal.voca4j.api.venue.VenueFields;
import com.cardinal.voca4j.api.venue.VenueSort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.RequireSet;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document VenuesQuery
 */
public class VenuesQuery extends FieldValueRequest implements ResponseDeserializer<QueriedList<Venue>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<Venue>>() {
	}.getType();

	@Encode
	private String query;
	private VenueFields[] fields;
	private Integer start, maxResults;
	private Boolean getTotalCount;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Language lang;
	private VenueSort sortRule;
	private Double latitude;
	@IfSet("latitude")
	private Double longitude;
	@IfSet("longitude")
	private Double radius;
	@IfSet("radius")
	private DistanceUnit distanceUnit;

	private VenuesQuery() {
		super("/venues");
	}

	private VenuesQuery(String query, VenueFields[] fields, Integer start, Integer maxResults, Boolean getTotalCount,
			NameMatchMode nameMatchMode, Language lang, VenueSort sortRule, Double latitude, Double longitude,
			Double radius, DistanceUnit distanceUnit) {
		super("/venues");
		this.query = query;
		this.fields = fields;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.nameMatchMode = nameMatchMode;
		this.lang = lang;
		this.sortRule = sortRule;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.distanceUnit = distanceUnit;
	}

	@Override
	public Request copy() {
		return new VenuesQuery(query, fields, start, maxResults, getTotalCount, nameMatchMode, lang, sortRule, latitude,
				longitude, radius, distanceUnit);
	}

	@Override
	public QueriedList<Venue> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class VenuesQueryBuilder extends StateControlledBuilder<VenuesQuery> {

		protected VenuesQueryBuilder() {
			super(new VenuesQuery());
		}

		public VenuesQueryBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public VenuesQueryBuilder includeFields(VenueFields... fields) {
			this.built.fields = fields;
			return this;
		}

		public VenuesQueryBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public VenuesQueryBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public VenuesQueryBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public VenuesQueryBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public VenuesQueryBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}

		public VenuesQueryBuilder setSortingRule(VenueSort sortingRule) {
			this.built.sortRule = sortingRule;
			return this;
		}

		public VenuesQueryBuilder setLatitude(double lat) {
			this.built.latitude = lat;
			return this;
		}

		public VenuesQueryBuilder setLongitude(double lng) {
			this.built.longitude = lng;
			return this;
		}

		@RequireSet("distanceUnit")
		public VenuesQueryBuilder setRadius(double radius) {
			if (this.built.latitude == null)
				throw new IllegalStateException("latitude must be must be set before you can invoke this method.");
			if (this.built.longitude == null)
				throw new IllegalStateException("longitude must be must be set before you can invoke this method.");

			this.built.radius = radius;
			return this;
		}

		public VenuesQueryBuilder setDistanceUnit(DistanceUnit unit) {
			this.built.distanceUnit = unit;
			return this;
		}
	}

}
