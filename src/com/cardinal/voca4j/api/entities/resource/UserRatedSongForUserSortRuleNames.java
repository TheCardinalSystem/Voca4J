
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRatedSongForUserSortRuleNames {

	@SerializedName("RatingDate")
	@Expose
	public String ratingDate;

	public String getRatingDate() {
		return ratingDate;
	}

}
