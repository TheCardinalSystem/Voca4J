
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumAlbumEditableFieldNames {

	@SerializedName("OriginalName")
	@Expose
	public String originalName;
	@SerializedName("Barcode")
	@Expose
	public String barcode;
	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("Tracks")
	@Expose
	public String tracks;
	@SerializedName("Status")
	@Expose
	public String status;
	@SerializedName("OriginalRelease")
	@Expose
	public String originalRelease;
	@SerializedName("WebLinks")
	@Expose
	public String webLinks;
	@SerializedName("Identifiers")
	@Expose
	public String identifiers;
	@SerializedName("PVs")
	@Expose
	public String pVs;
	@SerializedName("Cover")
	@Expose
	public String cover;
	@SerializedName("Names")
	@Expose
	public String names;
	@SerializedName("DiscType")
	@Expose
	public String discType;
	@SerializedName("Artists")
	@Expose
	public String artists;
	@SerializedName("Description")
	@Expose
	public String description;
	@SerializedName("Pictures")
	@Expose
	public String pictures;

	public String getOriginalName() {
		return originalName;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getNothing() {
		return nothing;
	}

	public String getTracks() {
		return tracks;
	}

	public String getStatus() {
		return status;
	}

	public String getOriginalRelease() {
		return originalRelease;
	}

	public String getWebLinks() {
		return webLinks;
	}

	public String getIdentifiers() {
		return identifiers;
	}

	public String getpVs() {
		return pVs;
	}

	public String getCover() {
		return cover;
	}

	public String getNames() {
		return names;
	}

	public String getDiscType() {
		return discType;
	}

	public String getArtists() {
		return artists;
	}

	public String getDescription() {
		return description;
	}

	public String getPictures() {
		return pictures;
	}

}
