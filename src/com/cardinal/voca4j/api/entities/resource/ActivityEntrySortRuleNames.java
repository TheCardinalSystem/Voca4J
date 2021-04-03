
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActivityEntrySortRuleNames {

	@SerializedName("CreateDate")
	@Expose
	public String createDate;
	@SerializedName("CreateDateDescending")
	@Expose
	public String createDateDescending;

	public String getCreateDate() {
		return createDate;
	}

	public String getCreateDateDescending() {
		return createDateDescending;
	}

}
