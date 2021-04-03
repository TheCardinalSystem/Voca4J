package com.cardinal.voca4j.api.entities.album;

public class AlbumDisc {
	private int discNumber = -1, id = -1;
	private String name;
	private DiscMediaType mediaType;

	public int getDiscNumber() {
		return discNumber;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public DiscMediaType getMediaType() {
		return mediaType;
	}

	public static enum DiscMediaType {
		Audio, Video;
	}
}
