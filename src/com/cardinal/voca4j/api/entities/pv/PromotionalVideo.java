package com.cardinal.voca4j.api.entities.pv;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.entities.song.Song;
import com.cardinal.voca4j.api.song.PVService;

public class PromotionalVideo {

	private Song song;
	private String author, name, pvId, thumbUrl, url;
	private boolean disabled;
	private ExtendedMetadata extendedMetadata;
	private int id = -1, length = -1, createdBy = -1;
	private LocalDateTime publishDate;
	private PVService service;
	private PVType pvType;

	public int getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public int getCreatorId() {
		return createdBy;
	}

	public Song getSong() {
		return song;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public ExtendedMetadata getExtendedMetadata() {
		return extendedMetadata;
	}

	public int getLength() {
		return length;
	}

	public String getName() {
		return name;
	}

	public String getPvId() {
		return pvId;
	}

	public PVService getService() {
		return service;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public String getUrl() {
		return url;
	}

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public PVType getPVType() {
		return pvType;
	}
}
