
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscTypeNames {

	@SerializedName("Compilation")
	@Expose
	public String compilation;
	@SerializedName("Single")
	@Expose
	public String single;
	@SerializedName("Unknown")
	@Expose
	public String unknown;
	@SerializedName("EP")
	@Expose
	public String eP;
	@SerializedName("Album")
	@Expose
	public String album;
	@SerializedName("Other")
	@Expose
	public String other;
	@SerializedName("Video")
	@Expose
	public String video;
	@SerializedName("SplitAlbum")
	@Expose
	public String splitAlbum;
	@SerializedName("Artbook")
	@Expose
	public String artbook;
	@SerializedName("Instrumental")
	@Expose
	public String instrumental;

	public String getCompilation() {
		return compilation;
	}

	public String getSingle() {
		return single;
	}

	public String getUnknown() {
		return unknown;
	}

	public String geteP() {
		return eP;
	}

	public String getAlbum() {
		return album;
	}

	public String getOther() {
		return other;
	}

	public String getVideo() {
		return video;
	}

	public String getSplitAlbum() {
		return splitAlbum;
	}

	public String getArtbook() {
		return artbook;
	}

	public String getInstrumental() {
		return instrumental;
	}

}
