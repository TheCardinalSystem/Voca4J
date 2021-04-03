package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;

/**
 * TODO: Document TagCategoryNamesGet
 */
public class TagCategoryNamesGet extends FieldValueRequest {
	@Encode
	private String query;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;

	private TagCategoryNamesGet() {
		super("/tags/categoryNames");
	}

	private TagCategoryNamesGet(String query, NameMatchMode nameMatchMode) {
		super("/tags/categoryNames");
		this.query = query;
		this.nameMatchMode = nameMatchMode;
	}

	@Override
	public Request copy() {
		return new TagCategoryNamesGet(query, nameMatchMode);
	}

	public static class TagCategoryNamesGetBuilder extends StateControlledBuilder<TagCategoryNamesGet> {
		protected TagCategoryNamesGetBuilder() {
			super(new TagCategoryNamesGet());
		}

		public TagCategoryNamesGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public TagCategoryNamesGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}
	}
}
