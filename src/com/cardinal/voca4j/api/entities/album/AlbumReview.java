package com.cardinal.voca4j.api.entities.album;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.entities.user.User;

public class AlbumReview {

	private int id = -1, albumId = -1;
	private LocalDateTime date;
	private String languageCode, text, title;
	private User user;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public User getAuthor() {
		return user;
	}

	public int getAlbumId() {
		return albumId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public String getText() {
		return text;
	}

}
