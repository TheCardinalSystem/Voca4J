package com.cardinal.voca4j.impl.niconico;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.XmlElement;

public class Thumb {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm+x")
			.withZone(ZoneId.of("UTC"));

	@XmlElement(name = "video_id")
	private String videoID;
	@XmlElement(name = "title")
	private String title;
	@XmlElement(name = "description")
	private String description;
	@XmlElement(name = "thumbnail_url")
	private String thumbnailURL;
	@XmlElement(name = "first_retrieve")
	private String firstRetrieve;
	private LocalDateTime parsed;
	@XmlElement(name = "length")
	private String length;
	@XmlElement(name = "movie_type")
	private String movieType;
	@XmlElement(name = "size_high")
	private String sizeHigh;
	@XmlElement(name = "size_low")
	private String sizeLow;
	@XmlElement(name = "view_counter")
	private String viewCounter;
	@XmlElement(name = "comment_num")
	private String commentNum;
	@XmlElement(name = "mylist_counter")
	private String mylistCounter;
	@XmlElement(name = "last_res_body")
	private String lastResBody;
	@XmlElement(name = "watch_url")
	private String watchURL;
	@XmlElement(name = "thumb_type")
	private String thumbType;
	@XmlElement(name = "embeddable")
	private String embeddable;
	@XmlElement(name = "no_live_play")
	private String noLivePlay;
	@XmlElement(name = "tags")
	private Tags tags;
	@XmlElement(name = "genre")
	private String genre;
	@XmlElement(name = "user_id")
	private String userID;
	@XmlElement(name = "user_nickname")
	private String userNickname;
	@XmlElement(name = "user_icon_url")
	private String userIconURL;

	public String getVideoID() {
		return videoID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getThumbnailURL() {
		return thumbnailURL;
	}

	public LocalDateTime getFirstRetrieve() {
		return parsed == null ? parsed = LocalDateTime.parse(firstRetrieve, FORMATTER) : parsed;
	}

	public String getLength() {
		return length;
	}

	public String getMovieType() {
		return movieType;
	}

	public String getSizeHigh() {
		return sizeHigh;
	}

	public String getSizeLow() {
		return sizeLow;
	}

	public String getViewCounter() {
		return viewCounter;
	}

	public String getCommentNumber() {
		return commentNum;
	}

	public String getMylistCounter() {
		return mylistCounter;
	}

	public String getLastResBody() {
		return lastResBody;
	}

	public String getWatchURL() {
		return watchURL;
	}

	public String getThumbType() {
		return thumbType;
	}

	public String getEmbeddable() {
		return embeddable;
	}

	public String getNoLivePlay() {
		return noLivePlay;
	}

	public Tags getTags() {
		return tags;
	}

	public String getGenre() {
		return genre;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getUserIconURL() {
		return userIconURL;
	}

}