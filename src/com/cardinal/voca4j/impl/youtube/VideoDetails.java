package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class VideoDetails {
	private String videoId;
	private String title;
	private String lengthSeconds;
	private List<String> keywords;
	private String channelId;
	private boolean isOwnerViewing;
	private String shortDescription;
	private boolean isCrawlable;
	private Thumbnail thumbnail;
	private double averageRating;
	private boolean allowRatings;
	private String viewCount;
	private String author;
	private boolean isPrivate;
	private boolean isUnpluggedCorpus;
	private boolean isLiveContent;

	public String getVideoId() {
		return this.videoId;
	}

	public String getTitle() {
		return this.title;
	}

	public String getLengthSeconds() {
		return this.lengthSeconds;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getKeywords() {
		return this.keywords;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public boolean getIsOwnerViewing() {
		return this.isOwnerViewing;
	}

	public String getShortDescription() {
		return this.shortDescription;
	}

	public boolean getIsCrawlable() {
		return this.isCrawlable;
	}

	public Thumbnail getThumbnail() {
		return this.thumbnail;
	}

	public double getAverageRating() {
		return this.averageRating;
	}

	public boolean getAllowRatings() {
		return this.allowRatings;
	}

	public String getViewCount() {
		return this.viewCount;
	}

	public String getAuthor() {
		return this.author;
	}

	public boolean getIsPrivate() {
		return this.isPrivate;
	}

	public boolean getIsUnpluggedCorpus() {
		return this.isUnpluggedCorpus;
	}

	public boolean getIsLiveContent() {
		return this.isLiveContent;
	}
}
