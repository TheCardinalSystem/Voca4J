package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.tag.TagField;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document TagChildrenGet
 */
public class TagChildrenGet extends FieldValueRequest {

	@Require
	private Integer tagId;
	private TagField[] fields;
	private Language lang;

	private TagChildrenGet() {
		super("/tags/{tagId}/children");
	}

	private TagChildrenGet(Integer tagId, TagField[] fields, Language lang) {
		super("/tags/{tagId}/children");
		this.tagId = tagId;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new TagChildrenGet(tagId, fields, lang);
	}

	public static class TagChildrenGetBuilder extends StateControlledBuilder<TagChildrenGet> {
		protected TagChildrenGetBuilder() {
			super(new TagChildrenGet());
		}

		public TagChildrenGetBuilder setTagID(int tagId) {
			this.built.tagId = tagId;
			return this;
		}

		public TagChildrenGetBuilder includeFields(TagField... fields) {
			this.built.fields = fields;
			return this;
		}

		public TagChildrenGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
