package com.cardinal.voca4j.api.entities.discussion;

import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.user.User;

public class DiscussionFolder implements LinkableEntity {

	private String descritpion, name;
	private int id = -1, topicCount = -1;
	private User lastTopicAuthor;
	private LocalDateTime lastTopicDate;

	public String getName() {
		return name;
	}

	public String getDescritpion() {
		return descritpion;
	}

	public int getId() {
		return id;
	}

	public int getTopicCount() {
		return topicCount;
	}

	public User getLastTopicAuthor() {
		return lastTopicAuthor;
	}

	public LocalDateTime getLastTopicDate() {
		return lastTopicDate;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/discussion/folders/" + id;
	}
}
