package com.cardinal.voca4j.api.songlist.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document SongListCommentsGet
 */
public class SongListCommentsGet extends FieldValueRequest {

	@Require
	private Integer listId;

	private SongListCommentsGet() {
		super("/songLists/{listId}/comments");
	}

	private SongListCommentsGet(Integer listId) {
		super("/songLists/{listId}/comments");
		this.listId = listId;
	}

	@Override
	public Request copy() {
		return new SongListCommentsGet(listId);
	}

	public static class SongListCommentsGetBuilder extends StateControlledBuilder<SongListCommentsGet> {

		protected SongListCommentsGetBuilder() {
			super(new SongListCommentsGet());
		}

		public SongListCommentsGetBuilder setID(int listId) {
			this.built.listId = listId;
			return this;
		}

	}

}
