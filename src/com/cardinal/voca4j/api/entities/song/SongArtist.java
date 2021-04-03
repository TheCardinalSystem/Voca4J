package com.cardinal.voca4j.api.entities.song;

import com.cardinal.voca4j.api.entities.ArtistCategory;
import com.cardinal.voca4j.api.entities.ArtistRole;
import com.cardinal.voca4j.api.entities.artist.Artist;

public class SongArtist {
	private Artist artist;
	private ArtistCategory categories;
	private ArtistRole[] effectiveRoles, roles;
	private int id;
	private boolean isCustomName, isSupport;
	private String name;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public Artist getAsArtist() {
		return artist;
	}

	public ArtistCategory getCategories() {
		return categories;
	}

	public ArtistRole[] getEffectiveRoles() {
		return effectiveRoles;
	}

	public ArtistRole[] getRoles() {
		return roles;
	}

	public boolean isCustomName() {
		return isCustomName;
	}

	public boolean isSupport() {
		return isSupport;
	}

}
