package com.cardinal.voca4j.api.comment.get;

import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.get.AlbumCommentsGet;
import com.cardinal.voca4j.api.artist.get.ArtistCommentsGet;
import com.cardinal.voca4j.api.song.get.SongCommentsGet;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets comments on an entry.
 * 
 * @author Cardinal System
 * @deprecated This request is in the VocaDB API documentation, but current does
 *             not work. There are other methods to retrieve comments for
 *             entries (e.g. {@linkplain SongCommentsGet},
 *             {@linkplain ArtistCommentsGet}, {@linkplain AlbumCommentsGet},
 *             etc).
 */
@Deprecated
public class EntryCommentsGet extends FieldValueRequest {
	@Require
	private EntryType entryType;
	@Require
	private Integer entryId;

	private EntryCommentsGet() {
		super("comments/{entryType}-comments");
	}

	private EntryCommentsGet(EntryType entryType, Integer entryId) {
		super("comments/{entryType}-comments");
		this.entryType = entryType;
		this.entryId = entryId;
	}

	@Override
	public Request copy() {
		return new EntryCommentsGet(entryType, entryId);
	}

	/**
	 * A {@link StateControlledBuilder} which creates an {@link EntryCommentsGet}
	 * request.
	 * 
	 * @author Cardinal System
	 * @deprecated see {@linkplain EntryCommentsGet}.
	 */
	public static class EntryCommentsGetBuilder extends StateControlledBuilder<EntryCommentsGet> {

		protected EntryCommentsGetBuilder() {
			super(new EntryCommentsGet());
		}

		/**
		 * Sets the type of entry to retrieve comments from.
		 * 
		 * @param entryType entry type.
		 * @return this, for chaining.
		 */
		public EntryCommentsGetBuilder setEntryType(EntryType entryType) {
			this.built.entryType = entryType;
			return this;
		}

		/**
		 * Sets the ID of the entry to retrieve comments from.
		 * 
		 * @param entryID entry ID.
		 * @return this, for chaining.
		 */
		public EntryCommentsGetBuilder setEntryID(int entryID) {
			this.built.entryId = entryID;
			return this;
		}
	}

}
