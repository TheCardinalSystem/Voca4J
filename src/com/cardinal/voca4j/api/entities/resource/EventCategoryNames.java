
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventCategoryNames {

	@SerializedName("Unspecified")
	@Expose
	public String unspecified;
	@SerializedName("Concert")
	@Expose
	public String concert;
	@SerializedName("Contest")
	@Expose
	public String contest;
	@SerializedName("Other")
	@Expose
	public String other;
	@SerializedName("Convention")
	@Expose
	public String convention;
	@SerializedName("AlbumRelease")
	@Expose
	public String albumRelease;
	@SerializedName("Anniversary")
	@Expose
	public String anniversary;
	@SerializedName("Club")
	@Expose
	public String club;

	public String getUnspecified() {
		return unspecified;
	}

	public String getConcert() {
		return concert;
	}

	public String getContest() {
		return contest;
	}

	public String getOther() {
		return other;
	}

	public String getConvention() {
		return convention;
	}

	public String getAlbumRelease() {
		return albumRelease;
	}

	public String getAnniversary() {
		return anniversary;
	}

	public String getClub() {
		return club;
	}

}
