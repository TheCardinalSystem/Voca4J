
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistArtistEditableFieldNames {

	@SerializedName("OriginalName")
	@Expose
	public String originalName;
	@SerializedName("Albums")
	@Expose
	public String albums;
	@SerializedName("Groups")
	@Expose
	public String groups;
	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("Status")
	@Expose
	public String status;
	@SerializedName("ArtistType")
	@Expose
	public String artistType;
	@SerializedName("WebLinks")
	@Expose
	public String webLinks;
	@SerializedName("Picture")
	@Expose
	public String picture;
	@SerializedName("Names")
	@Expose
	public String names;
	@SerializedName("BaseVoicebank")
	@Expose
	public String baseVoicebank;
	@SerializedName("Description")
	@Expose
	public String description;
	@SerializedName("Pictures")
	@Expose
	public String pictures;

	public String getOriginalName() {
		return originalName;
	}

	public String getAlbums() {
		return albums;
	}

	public String getGroups() {
		return groups;
	}

	public String getNothing() {
		return nothing;
	}

	public String getStatus() {
		return status;
	}

	public String getArtistType() {
		return artistType;
	}

	public String getWebLinks() {
		return webLinks;
	}

	public String getPicture() {
		return picture;
	}

	public String getNames() {
		return names;
	}

	public String getBaseVoicebank() {
		return baseVoicebank;
	}

	public String getDescription() {
		return description;
	}

	public String getPictures() {
		return pictures;
	}

}
