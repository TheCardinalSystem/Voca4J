package com.cardinal.voca4j.api.entities.songlist;

import com.cardinal.voca4j.api.entities.song.Song;

public class SongListSong {

	private String notes;
	private int order;
	private Song song;

	public String getNotes() {
		return notes;
	}

	public int getOrder() {
		return order;
	}

	public Song getSong() {
		return song;
	}

}
