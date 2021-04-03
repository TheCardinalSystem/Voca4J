package com.cardinal.voca4j.api.entities.album;

import com.cardinal.voca4j.api.entities.song.Song;

public class Track {
	private int discNumber = -1, id = -1, trackNumber = -1;
	private String name;
	private Song song;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getDiscNumber() {
		return discNumber;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public Song getAsSong() {
		return song;
	}
}
