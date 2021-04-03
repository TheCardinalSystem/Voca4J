package com.cardinal.voca4j.api.entities.album;

import com.cardinal.voca4j.api.entities.ArtistCategory;
import com.cardinal.voca4j.api.entities.ArtistRole;
import com.cardinal.voca4j.api.entities.artist.Artist;

public class AlbumArtist {
	private Artist artist;
	private ArtistCategory categories;
	private ArtistRole roles, effectiveRoles;
	private String name;
	private boolean isSupport;

	public Artist getAsArtist() {
		return artist;
	}

	public ArtistCategory getCategory() {
		return categories;
	}

	public ArtistRole getEffectiveRole() {
		return effectiveRoles;
	}

	public ArtistRole getRole() {
		return roles;
	}

	public String getName() {
		return name;
	}

	public boolean isSupport() {
		return isSupport;
	}
}