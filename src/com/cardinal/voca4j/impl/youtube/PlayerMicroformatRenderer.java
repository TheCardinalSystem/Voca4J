package com.cardinal.voca4j.impl.youtube;

import java.time.LocalDate;
import java.util.List;

public class PlayerMicroformatRenderer {
	private Thumbnail thumbnail;
	private Embed embed;
	private Title title;
	private Description description;
	private String lengthSeconds;
	private String ownerProfileUrl;
	private String externalChannelId;
	private List<String> availableCountries;
	private boolean isUnlisted;
	private boolean hasYpcMetadata;
	private String viewCount;
	private String category;
	private LocalDate publishDate;
	private String ownerChannelName;
	private LocalDate uploadDate;

	public Thumbnail getThumbnail() {
		return this.thumbnail;
	}

	public Embed getEmbed() {
		return this.embed;
	}

	public Title getTitle() {
		return this.title;
	}

	public Description getDescription() {
		return this.description;
	}

	public String getLengthSeconds() {
		return this.lengthSeconds;
	}

	public String getOwnerProfileUrl() {
		return this.ownerProfileUrl;
	}

	public String getExternalChannelId() {
		return this.externalChannelId;
	}

	public void setAvailableCountries(List<String> availableCountries) {
		this.availableCountries = availableCountries;
	}

	public List<String> getAvailableCountries() {
		return this.availableCountries;
	}

	public boolean getIsUnlisted() {
		return this.isUnlisted;
	}

	public boolean getHasYpcMetadata() {
		return this.hasYpcMetadata;
	}

	public String getViewCount() {
		return this.viewCount;
	}

	public String getCategory() {
		return this.category;
	}

	public LocalDate getPublishDate() {
		return this.publishDate;
	}

	public String getOwnerChannelName() {
		return this.ownerChannelName;
	}

	public LocalDate getUploadDate() {
		return this.uploadDate;
	}
}
