package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.tag.TagField;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document TagByNameGet
 */
public class TagByNameGet extends FieldValueRequest {

	@Require
	@Encode
	private String name;
	private TagField[] fields;
	private Language lang;

	private TagByNameGet() {
		super("/tags/byName/{name}");
	}

	private TagByNameGet(String name, TagField[] fields, Language lang) {
		super("/tags/byName/{name}");
		this.name = name;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new TagByNameGet(name, fields, lang);
	}

	public static class TagByNameGetBuilder extends StateControlledBuilder<TagByNameGet> {

		protected TagByNameGetBuilder() {
			super(new TagByNameGet());
		}

		public TagByNameGetBuilder setName(String tagName) {
			this.built.name = tagName;
			return this;
		}

		public TagByNameGetBuilder includeFields(TagField... fields) {
			this.built.fields = fields;
			return this;
		}

		public TagByNameGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}

	}
}
