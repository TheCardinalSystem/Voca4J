
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistSortRuleNames {

	@SerializedName("AdditionDateAsc")
	@Expose
	public String additionDateAsc;
	@SerializedName("SongCount")
	@Expose
	public String songCount;
	@SerializedName("FollowerCount")
	@Expose
	public String followerCount;
	@SerializedName("AdditionDate")
	@Expose
	public String additionDate;
	@SerializedName("SongRating")
	@Expose
	public String songRating;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getAdditionDateAsc() {
		return additionDateAsc;
	}

	public String getSongCount() {
		return songCount;
	}

	public String getFollowerCount() {
		return followerCount;
	}

	public String getAdditionDate() {
		return additionDate;
	}

	public String getSongRating() {
		return songRating;
	}

	public String getName() {
		return name;
	}

}
