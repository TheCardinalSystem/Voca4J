package com.cardinal.voca4j.api.entities.user;

import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.api.user.UserPurchaseStatus;

public class UserCollectionAlbum {
	private Album album;
	private MediaType mediaType;
	private UserPurchaseStatus purchaseStatus;
	private int rating = -1;
	private User user;

	public Album getAlbum() {
		return album;
	}

	public MediaType getMediaType() {
		return mediaType;
	}

	public UserPurchaseStatus getPurchaseStatus() {
		return purchaseStatus;
	}

	public int getRating() {
		return rating;
	}

	public User getUser() {
		return user;
	}
}
