package com.cardinal.voca4j.api.entities.releaseevent;

import java.time.LocalDateTime;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.artist.Artist;
import com.cardinal.voca4j.api.entities.tag.Tag;
import com.cardinal.voca4j.api.entities.venue.Venue;
import com.cardinal.voca4j.api.releaseevent.EventCategory;

public class ReleaseEvent implements LinkableEntity {

	private String additionalNames[], description, name, seriesSuffix, urlSlug, venueName;
	private List<Artist> artists;
	private EventCategory category;
	private LocalDateTime date, endDate;
	private boolean hasVenueOrVenueName;
	private int id = -1, seriesId = -1, seriesNumber = -1, version = -1;
	private EntryThumbnail mainPicture;
	private LocalizedName[] names;
	private ReleaseEventSeries series;
	private EntryStatus status;
	private Tag[] tags;
	private Venue venue;
	private WebLink[] webLinks;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public LocalizedName[] getNames() {
		return names;
	}

	public String getDescription() {
		return description;
	}

	public String getSeriesSuffix() {
		return seriesSuffix;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	public String getVenueName() {
		return venueName;
	}

	public List<Artist> getArtists() {
		return artists;
	}

	public EventCategory getCategory() {
		return category;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public boolean isHasVenueOrVenueName() {
		return hasVenueOrVenueName;
	}

	public int getSeriesId() {
		return seriesId;
	}

	public int getSeriesNumber() {
		return seriesNumber;
	}

	public int getVersion() {
		return version;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public ReleaseEventSeries getSeries() {
		return series;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public Tag[] getTags() {
		return tags;
	}

	public Venue getVenue() {
		return venue;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/E/" + id + "/" + urlSlug;
	}

}
