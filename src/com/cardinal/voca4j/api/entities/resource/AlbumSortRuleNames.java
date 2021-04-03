
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumSortRuleNames {

	@SerializedName("RatingAverage")
	@Expose
	public String ratingAverage;
	@SerializedName("RatingTotal")
	@Expose
	public String ratingTotal;
	@SerializedName("ReleaseDate")
	@Expose
	public String releaseDate;
	@SerializedName("CollectionCount")
	@Expose
	public String collectionCount;
	@SerializedName("AdditionDate")
	@Expose
	public String additionDate;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getRatingAverage() {
		return ratingAverage;
	}

	public String getRatingTotal() {
		return ratingTotal;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getCollectionCount() {
		return collectionCount;
	}

	public String getAdditionDate() {
		return additionDate;
	}

	public String getName() {
		return name;
	}

}
