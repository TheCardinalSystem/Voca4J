package com.cardinal.voca4j.api.entities.songlist;

import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.comment.Comment;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.entities.tag.TagUsage;
import com.cardinal.voca4j.api.entities.user.User;

public class SongList extends SongListBase implements LinkableEntity {

	private User author;
	private boolean deleted, featuredList;
	private String description;
	private LocalDateTime eventDate;
	private ReleaseEvent[] events;
	private Comment[] latestComments;
	private EntryThumbnail mainPicture;
	private EntryStatus status;
	private TagUsage[] tags;

	public User getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isFeaturedList() {
		return featuredList;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public ReleaseEvent[] getEvents() {
		return events;
	}

	public Comment[] getLatestComments() {
		return latestComments;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public TagUsage[] getTags() {
		return tags;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/L/" + id;
	}

}
