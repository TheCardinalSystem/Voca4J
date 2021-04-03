
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongSongEditableFieldNames {

	@SerializedName("OriginalName")
	@Expose
	public String originalName;
	@SerializedName("Groups")
	@Expose
	public String groups;
	@SerializedName("Length")
	@Expose
	public String length;
	@SerializedName("Lyrics")
	@Expose
	public String lyrics;
	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("OriginalVersion")
	@Expose
	public String originalVersion;
	@SerializedName("Status")
	@Expose
	public String status;
	@SerializedName("SongType")
	@Expose
	public String songType;
	@SerializedName("PublishDate")
	@Expose
	public String publishDate;
	@SerializedName("WebLinks")
	@Expose
	public String webLinks;
	@SerializedName("PVs")
	@Expose
	public String pVs;
	@SerializedName("Notes")
	@Expose
	public String notes;
	@SerializedName("Names")
	@Expose
	public String names;
	@SerializedName("Artists")
	@Expose
	public String artists;

	public String getOriginalName() {
		return originalName;
	}

	public String getGroups() {
		return groups;
	}

	public String getLength() {
		return length;
	}

	public String getLyrics() {
		return lyrics;
	}

	public String getNothing() {
		return nothing;
	}

	public String getOriginalVersion() {
		return originalVersion;
	}

	public String getStatus() {
		return status;
	}

	public String getSongType() {
		return songType;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public String getWebLinks() {
		return webLinks;
	}

	public String getpVs() {
		return pVs;
	}

	public String getNotes() {
		return notes;
	}

	public String getNames() {
		return names;
	}

	public String getArtists() {
		return artists;
	}

}
