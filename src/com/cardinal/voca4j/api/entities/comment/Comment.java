package com.cardinal.voca4j.api.entities.comment;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.entities.user.User;

public class Comment {

	private User author;
	private String authorName, message;
	private LocalDateTime created;
	private int id = -1;

	public int getId() {
		return id;
	}

	public User getAuthor() {
		return author;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreated() {
		return created;
	}

}
