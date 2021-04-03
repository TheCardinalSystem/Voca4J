package com.cardinal.voca4j.api.entities.song;

public class RelatedSongs {

	private Song[] artistMatches, likeMatches, tagMatches;

	public Song[] getArtistMatches() {
		return artistMatches;
	}

	public Song[] getLikeMatches() {
		return likeMatches;
	}

	public Song[] getTagMatches() {
		return tagMatches;
	}

}
