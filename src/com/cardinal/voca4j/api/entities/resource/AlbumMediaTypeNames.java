
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumMediaTypeNames {

	@SerializedName("PhysicalDisc")
	@Expose
	public String physicalDisc;
	@SerializedName("DigitalDownload")
	@Expose
	public String digitalDownload;
	@SerializedName("Other")
	@Expose
	public String other;

	public String getPhysicalDisc() {
		return physicalDisc;
	}

	public String getDigitalDownload() {
		return digitalDownload;
	}

	public String getOther() {
		return other;
	}

}
