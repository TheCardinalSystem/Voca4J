package com.cardinal.voca4j.api.entities.releaseevent;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.releaseevent.EventCategory;

public class ReleaseEventSeries implements LinkableEntity {

	private String additionalNames[], description, name, pictureMime, urlSlug;
	private EventCategory category;
	private ReleaseEvent[] events;
	private int id = -1, version = -1;
	private boolean deleted;
	private EntryThumbnail mainPicture;
	private LocalizedName[] names;
	private EntryStatus stauts;
	private WebLink[] webLinks;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public String getPictureMime() {
		return pictureMime;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	public EventCategory getCategory() {
		return category;
	}

	public ReleaseEvent[] getEvents() {
		return events;
	}

	public int getVersion() {
		return version;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public LocalizedName[] getLocalizedNames() {
		return names;
	}

	public EntryStatus getStauts() {
		return stauts;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/Es/" + id + "/" + urlSlug;
	}

}
