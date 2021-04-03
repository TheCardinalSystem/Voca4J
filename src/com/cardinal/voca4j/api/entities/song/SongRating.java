package com.cardinal.voca4j.api.entities.song;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.entities.user.User;
import com.cardinal.voca4j.api.user.SongVoteRating;

public class SongRating {

	private LocalDateTime date;
	private Song song;
	private User user;
	private SongVoteRating rating;

	public Song getSong() {
		return song;
	}

	public User getUser() {
		return user;
	}

	public SongVoteRating getRating() {
		return rating;
	}

	public LocalDateTime getDate() {
		return date;
	}

}
