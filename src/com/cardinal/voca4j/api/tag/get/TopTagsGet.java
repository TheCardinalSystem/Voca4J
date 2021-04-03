package com.cardinal.voca4j.api.tag.get;

import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.tag.TagCategory;

/**
 * TODO: Document TopTagsGet
 */
public class TopTagsGet extends FieldValueRequest {

	private TagCategory categoryName;
	private EntryType entryType;
	private Integer maxResults;
	private Language lang;

	private TopTagsGet() {
		super("/tags/top");
	}

	private TopTagsGet(TagCategory categoryName, EntryType entryType, Integer maxResults, Language lang) {
		super("/tags/top");
		this.categoryName = categoryName;
		this.entryType = entryType;
		this.maxResults = maxResults;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new TopTagsGet(categoryName, entryType, maxResults, lang);
	}

	public static class TopTagsGetBuilder extends StateControlledBuilder<TopTagsGet> {

		protected TopTagsGetBuilder() {
			super(new TopTagsGet());
		}

		public TopTagsGetBuilder filterCategory(TagCategory category) {
			this.built.categoryName = category;
			return this;
		}

		public TopTagsGetBuilder filterEntryType(EntryType entryType) {
			switch (entryType) {
			case Undefined:
			case DiscussionTopic:
			case PV:
			case Tag:
			case User:
			case Venue:
				throw new IllegalArgumentException("EntryType#" + entryType + " is not supported.");
			default:
				break;
			}
			this.built.entryType = entryType;
			return this;
		}

		public TopTagsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public TopTagsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}
}
