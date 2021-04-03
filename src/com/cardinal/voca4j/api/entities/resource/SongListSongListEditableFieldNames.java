
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongListSongListEditableFieldNames {

	@SerializedName("FeaturedCategory")
	@Expose
	public String featuredCategory;
	@SerializedName("Status")
	@Expose
	public String status;
	@SerializedName("EventDate")
	@Expose
	public String eventDate;
	@SerializedName("Songs")
	@Expose
	public String songs;
	@SerializedName("Description")
	@Expose
	public String description;
	@SerializedName("Name")
	@Expose
	public String name;
	@SerializedName("Thumbnail")
	@Expose
	public String thumbnail;

	public String getFeaturedCategory() {
		return featuredCategory;
	}

	public String getStatus() {
		return status;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getSongs() {
		return songs;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getThumbnail() {
		return thumbnail;
	}

}
