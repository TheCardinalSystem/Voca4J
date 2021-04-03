
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SongListSortRuleNames {

	@SerializedName("CreateDate")
	@Expose
	public String createDate;
	@SerializedName("Date")
	@Expose
	public String date;
	@SerializedName("Name")
	@Expose
	public String name;

	public String getCreateDate() {
		return createDate;
	}

	public String getDate() {
		return date;
	}

	public String getName() {
		return name;
	}

}
