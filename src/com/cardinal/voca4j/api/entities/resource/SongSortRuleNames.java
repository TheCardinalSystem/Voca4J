
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongSortRuleNames {

	@SerializedName("FavoritedTimes")
	@Expose
	public String favoritedTimes;
	@SerializedName("PublishDate")
	@Expose
	public String publishDate;
	@SerializedName("RatingScore")
	@Expose
	public String ratingScore;
	@SerializedName("AdditionDate")
	@Expose
	public String additionDate;
	@SerializedName("None")
	@Expose
	public String none;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getFavoritedTimes() {
		return favoritedTimes;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getRatingScore() {
		return ratingScore;
	}

	public String getAdditionDate() {
		return additionDate;
	}

	public String getNone() {
		return none;
	}

	public String getName() {
		return name;
	}

}
