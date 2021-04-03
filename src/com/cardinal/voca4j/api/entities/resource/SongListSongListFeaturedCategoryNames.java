
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongListSongListFeaturedCategoryNames {

	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("VocaloidRanking")
	@Expose
	public String vocaloidRanking;
	@SerializedName("Other")
	@Expose
	public String other;
	@SerializedName("Pools")
	@Expose
	public String pools;
	@SerializedName("Concerts")
	@Expose
	public String concerts;

	public String getNothing() {
		return nothing;
	}

	public String getVocaloidRanking() {
		return vocaloidRanking;
	}

	public String getOther() {
		return other;
	}

	public String getPools() {
		return pools;
	}

	public String getConcerts() {
		return concerts;
	}

}
