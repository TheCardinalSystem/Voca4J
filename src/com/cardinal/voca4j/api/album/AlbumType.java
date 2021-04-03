package com.cardinal.voca4j.api.album;

import com.cardinal.voca4j.api.album.query.AlbumQuery;

/**
 * An enum that represents the album/disc types that can be filtered out by an
 * {@link AlbumQuery}
 * 
 * @author Cardinal System
 *
 */
public enum AlbumType {
	Unknown, Album, Single, EP, SplitAlbum, Compilation, Video, Artbook, Game, Fanmade, Instrumental, Other;
}