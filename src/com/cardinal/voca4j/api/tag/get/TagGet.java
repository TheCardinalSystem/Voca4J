package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.tag.TagField;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document TagGet
 */
public class TagGet extends FieldValueRequest {

	@Require
	private Integer id;
	private TagField[] fields;
	private Language lang;

	private TagGet() {
		super("/tags/{id}");
	}

	private TagGet(Integer id, TagField[] fields, Language lang) {
		super("/tags/{id}");
		this.id = id;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new TagGet(id, fields, lang);
	}

	public static class TagGetBuilder extends StateControlledBuilder<TagGet> {

		protected TagGetBuilder() {
			super(new TagGet());
		}

		public TagGetBuilder setID(int tagId) {
			this.built.id = tagId;
			return this;
		}

		public TagGetBuilder includeFields(TagField... fields) {
			this.built.fields = fields;
			return this;
		}

		public TagGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}

	}

}
