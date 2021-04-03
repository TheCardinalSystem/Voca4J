
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventSortRuleNames {

	@SerializedName("SeriesName")
	@Expose
	public String seriesName;
	@SerializedName("VenueName")
	@Expose
	public String venueName;
	@SerializedName("AdditionDate")
	@Expose
	public String additionDate;
	@SerializedName("Date")
	@Expose
	public String date;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getSeriesName() {
		return seriesName;
	}

	public String getVenueName() {
		return venueName;
	}

	public String getAdditionDate() {
		return additionDate;
	}

	public String getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

}
