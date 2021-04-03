
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentLanguageSelectionNames {

	@SerializedName("Romaji")
	@Expose
	public String romaji;
	@SerializedName("Unspecified")
	@Expose
	public String unspecified;
	@SerializedName("Default")
	@Expose
	public String _default;
	@SerializedName("English")
	@Expose
	public String english;
	@SerializedName("Japanese")
	@Expose
	public String japanese;

	public String getRomaji() {
		return romaji;
	}

	public String getUnspecified() {
		return unspecified;
	}

	public String get_default() {
		return _default;
	}

	public String getEnglish() {
		return english;
	}

	public String getJapanese() {
		return japanese;
	}

}
