
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TagTagEditableFieldNames {

	@SerializedName("Status")
	@Expose
	public String status;
	@SerializedName("Parent")
	@Expose
	public String parent;
	@SerializedName("Picture")
	@Expose
	public String picture;
	@SerializedName("AliasedTo")
	@Expose
	public String aliasedTo;
	@SerializedName("Description")
	@Expose
	public String description;
	@SerializedName("CategoryName")
	@Expose
	public String categoryName;

	public String getStatus() {
		return status;
	}

	public String getParent() {
		return parent;
	}

	public String getPicture() {
		return picture;
	}

	public String getAliasedTo() {
		return aliasedTo;
	}

	public String getDescription() {
		return description;
	}

	public String getCategoryName() {
		return categoryName;
	}

}
