
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseEventReleaseEventEditableFieldNames {

	@SerializedName("Series")
	@Expose
	public String series;
	@SerializedName("Category")
	@Expose
	public String category;
	@SerializedName("Artists")
	@Expose
	public String artists;
	@SerializedName("SeriesNumber")
	@Expose
	public String seriesNumber;
	@SerializedName("SeriesSuffix")
	@Expose
	public String seriesSuffix;
	@SerializedName("Description")
	@Expose
	public String description;
	@SerializedName("Date")
	@Expose
	public String date;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getSeries() {
		return series;
	}

	public String getCategory() {
		return category;
	}

	public String getArtists() {
		return artists;
	}

	public String getSeriesNumber() {
		return seriesNumber;
	}

	public String getSeriesSuffix() {
		return seriesSuffix;
	}

	public String getDescription() {
		return description;
	}

	public String getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

}
