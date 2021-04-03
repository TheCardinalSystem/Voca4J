package com.cardinal.voca4j.api.entities.activityentry;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.activityentry.EditEvent;
import com.cardinal.voca4j.api.entities.entry.Entry;
import com.cardinal.voca4j.api.entities.user.User;

public class ActivityEntry {

	private ArchivedVersion archivedVersion;
	private User author;
	private LocalDateTime createDate;
	private EditEvent editEvent;
	private Entry entry;

	public ArchivedVersion getArchivedVersion() {
		return archivedVersion;
	}

	public User getAuthor() {
		return author;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public EditEvent getEditEvent() {
		return editEvent;
	}

	public Entry getEntry() {
		return entry;
	}

	public static class ArchivedVersion {
		private String[] changedFields;
		private int id, version;
		private String notes;

		public String[] getChangedFields() {
			return changedFields;
		}

		public int getId() {
			return id;
		}

		public int getVersion() {
			return version;
		}

		public String getNotes() {
			return notes;
		}
	}

}
