package com.cardinal.voca4j.api.entities;

public class LocalizedName {
	protected ContentLanguageSelection language;
	protected String value;

	public ContentLanguageSelection getLanguage() {
		return language;
	}

	public String getName() {
		return value;
	}

	@Override
	public String toString() {
		return "{" + value + " : " + language + "}";
	}
}