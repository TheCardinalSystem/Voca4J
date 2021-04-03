
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserGroupNames {

	@SerializedName("Trusted")
	@Expose
	public String trusted;
	@SerializedName("Nothing")
	@Expose
	public String nothing;
	@SerializedName("Moderator")
	@Expose
	public String moderator;
	@SerializedName("Admin")
	@Expose
	public String admin;
	@SerializedName("Limited")
	@Expose
	public String limited;
	@SerializedName("Regular")
	@Expose
	public String regular;

	public String getTrusted() {
		return trusted;
	}

	public String getNothing() {
		return nothing;
	}

	public String getModerator() {
		return moderator;
	}

	public String getAdmin() {
		return admin;
	}

	public String getLimited() {
		return limited;
	}

	public String getRegular() {
		return regular;
	}

}
