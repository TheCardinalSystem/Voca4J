package com.cardinal.voca4j.api.entities.venue;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;

public class Venue implements LinkableEntity {

	private String additionalNames[], address, addressCountryCode, description, name;
	private GeoPoint coordinates;
	private boolean deleted;
	private ReleaseEvent[] events;
	private int id = -1, version = -1;
	private LocalizedName[] names;
	private EntryStatus status;
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

	public LocalizedName[] getLocalizedNames() {
		return names;
	}

	public String getAddress() {
		return address;
	}

	public String getAddressCountryCode() {
		return addressCountryCode;
	}

	public String getDescription() {
		return description;
	}

	public GeoPoint getCoordinates() {
		return coordinates;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public ReleaseEvent[] getEvents() {
		return events;
	}

	public int getVersion() {
		return version;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/Venue/Details/" + id;
	}

}
