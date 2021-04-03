package com.cardinal.voca4j.api.entities.artist;

import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.artist.ArtistType;
import com.cardinal.voca4j.api.entities.LinkableEntity;

public class ArtistBase implements LinkableEntity {

	protected String additionalNames[], name, pictureMime;
	protected ArtistType artistType;
	protected boolean deleted;
	protected int id = -1, version = -1;
	protected LocalDateTime releaseDate;
	protected EntryStatus status;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public String getPictureMime() {
		return pictureMime;
	}

	public ArtistType getArtistType() {
		return artistType;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public int getVersion() {
		return version;
	}

	public LocalDateTime getReleaseDate() {
		return releaseDate;
	}

	public EntryStatus getStatus() {
		return status;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/Ar/" + id;
	}

}
