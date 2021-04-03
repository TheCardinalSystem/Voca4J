
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongTypeNames {

	@SerializedName("Mashup")
	@Expose
	public String mashup;
	@SerializedName("Original")
	@Expose
	public String original;
	@SerializedName("Unspecified")
	@Expose
	public String unspecified;
	@SerializedName("Cover")
	@Expose
	public String cover;
	@SerializedName("Other")
	@Expose
	public String other;
	@SerializedName("Remix")
	@Expose
	public String remix;
	@SerializedName("DramaPV")
	@Expose
	public String dramaPV;
	@SerializedName("Remaster")
	@Expose
	public String remaster;
	@SerializedName("Arrangement")
	@Expose
	public String arrangement;
	@SerializedName("Instrumental")
	@Expose
	public String instrumental;
	@SerializedName("MusicPV")
	@Expose
	public String musicPV;
	@SerializedName("Live")
	@Expose
	public String live;

	public String getMashup() {
		return mashup;
	}

	public String getOriginal() {
		return original;
	}

	public String getUnspecified() {
		return unspecified;
	}

	public String getCover() {
		return cover;
	}

	public String getOther() {
		return other;
	}

	public String getRemix() {
		return remix;
	}

	public String getDramaPV() {
		return dramaPV;
	}

	public String getRemaster() {
		return remaster;
	}

	public String getArrangement() {
		return arrangement;
	}

	public String getInstrumental() {
		return instrumental;
	}

	public String getMusicPV() {
		return musicPV;
	}

	public String getLive() {
		return live;
	}

}
