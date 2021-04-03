package com.cardinal.voca4j.api.entities.discussion;

import java.time.LocalDateTime;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.comment.Comment;
import com.cardinal.voca4j.api.entities.user.User;

public class DiscussionTopic implements LinkableEntity {

	private User author;
	private int commentCount = -1, folderId = -1, id = -1;
	private List<Comment> comments;
	private String content, name;
	private LocalDateTime created;
	private Comment lastComment;
	private boolean locked;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public User getAuthor() {
		return author;
	}

	public int getFolderId() {
		return folderId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public Comment getLastComment() {
		return lastComment;
	}

	public boolean isLocked() {
		return locked;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/discussion/topics/" + id;
	}

}
