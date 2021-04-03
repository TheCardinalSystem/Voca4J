package com.cardinal.voca4j.api.album;

import com.cardinal.voca4j.api.album.query.AlbumQuery;

/**
 * An enum that represents rules for sorting {@link AlbumQuery} results.
 * 
 * @author Cardinal System
 *
 */
public enum AlbumSort {
	None, Name, ReleaseDate, ReleaseDateWithNulls, AdditionDate, RatingAverage, RatingTotal, NameThenReleaseDate,
	CollectionCount;
}