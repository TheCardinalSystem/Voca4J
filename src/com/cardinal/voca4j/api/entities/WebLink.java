package com.cardinal.voca4j.api.entities;

public class WebLink {
	private WebLinkCategory category;
	private String description, url;
	private boolean disabled;
	private int id;

	public WebLinkCategory getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public int getId() {
		return id;
	}

	public static enum WebLinkCategory {
		Official, Commercial, Reference, Other;
	}
}
