
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EntryTypeNames {

	@SerializedName("Artist")
	@Expose
	public String artist;
	@SerializedName("Undefined")
	@Expose
	public String undefined;
	@SerializedName("SongList")
	@Expose
	public String songList;
	@SerializedName("ReleaseEvent")
	@Expose
	public String releaseEvent;
	@SerializedName("Tag")
	@Expose
	public String tag;
	@SerializedName("Album")
	@Expose
	public String album;
	@SerializedName("User")
	@Expose
	public String user;
	@SerializedName("Song")
	@Expose
	public String song;

	public String getArtist() {
		return artist;
	}

	public String getUndefined() {
		return undefined;
	}

	public String getSongList() {
		return songList;
	}

	public String getReleaseEvent() {
		return releaseEvent;
	}

	public String getTag() {
		return tag;
	}

	public String getAlbum() {
		return album;
	}

	public String getUser() {
		return user;
	}

	public String getSong() {
		return song;
	}

}
