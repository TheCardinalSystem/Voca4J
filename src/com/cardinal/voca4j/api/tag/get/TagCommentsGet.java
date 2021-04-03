package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document TagCommentsGet
 */
public class TagCommentsGet extends FieldValueRequest {
	@Require
	private Integer tagId;

	private TagCommentsGet() {
		super("/tags/{tagId}/comments");
	}

	private TagCommentsGet(Integer tagId) {
		super("/tags/{tagId}/comments");
		this.tagId = tagId;
	}

	@Override
	public Request copy() {
		return new TagCommentsGet(tagId);
	}

	public static class TagCommentsGetBuilder extends StateControlledBuilder<TagCommentsGet> {
		protected TagCommentsGetBuilder() {
			super(new TagCommentsGet());
		}

		public TagCommentsGetBuilder setTagID(int tagId) {
			this.built.tagId = tagId;
			return this;
		}
	}
}
