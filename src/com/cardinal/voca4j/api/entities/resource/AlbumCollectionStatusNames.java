
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumCollectionStatusNames {

	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("Owned")
	@Expose
	public String owned;
	@SerializedName("Ordered")
	@Expose
	public String ordered;
	@SerializedName("Wishlisted")
	@Expose
	public String wishlisted;

	public String getNothing() {
		return nothing;
	}

	public String getOwned() {
		return owned;
	}

	public String getOrdered() {
		return ordered;
	}

	public String getWishlisted() {
		return wishlisted;
	}

}
