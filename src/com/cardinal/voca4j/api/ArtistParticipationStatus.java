package com.cardinal.voca4j.api;

import com.cardinal.voca4j.api.album.query.AlbumQuery;

/**
 * An enum that represents an artist's participation status in albums queried by
 * an {@link AlbumQuery}.
 * 
 * @author Cardinal System
 *
 */
public enum ArtistParticipationStatus {
	/**
	 * Everything (default): Show all albums by the specified artist(s) (no filter).
	 */
	Everything,
	/**
	 * OnlyMainAlbums: Show only main albums by the specified artist(s).
	 */
	OnlyMainAlbums,
	/**
	 * OnlyCollaborations: Show only collaborations by by the specified artist(s).
	 */
	OnlyCollaborations;
}