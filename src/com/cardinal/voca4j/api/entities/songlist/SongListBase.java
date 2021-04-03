package com.cardinal.voca4j.api.entities.songlist;

import com.cardinal.voca4j.api.songlist.SongListFeaturedCategory;

public class SongListBase {

	protected SongListFeaturedCategory featuredCategory;
	protected int id = -1;
	protected String name;

	public SongListFeaturedCategory getFeaturedCategory() {
		return featuredCategory;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
