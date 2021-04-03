package com.cardinal.voca4j.api.entities.tag;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.ContentLanguageSelection;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LocalizedNameWithID;
import com.cardinal.voca4j.api.entities.WebLink;

public class Tag extends TagBase {

	private String defaultName, description;
	private TagBase aliasedTo;
	private LocalDateTime createDate;
	private ContentLanguageSelection defaultNameLanguage;
	private int targets = -1, usageCount = -1, version = -1;
	private EntryThumbnail mainPicture;
	private LocalizedNameWithID[] names;
	private Tag parent;
	private Tag[] relatedTags;
	private EntryStatus status;
	private TranslatedDescription translatedDescription;
	private WebLink[] webLinks;

	public String getDefaultName() {
		return defaultName;
	}

	public String getDescription() {
		return description;
	}

	public TagBase getAliasedTo() {
		return aliasedTo;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public ContentLanguageSelection getDefaultNameLanguage() {
		return defaultNameLanguage;
	}

	public int getTargets() {
		return targets;
	}

	public int getUsageCount() {
		return usageCount;
	}

	public int getVersion() {
		return version;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public LocalizedNameWithID[] getNames() {
		return names;
	}

	public Tag getParent() {
		return parent;
	}

	public Tag[] getRelatedTags() {
		return relatedTags;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public TranslatedDescription getTranslatedDescription() {
		return translatedDescription;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	public static class TranslatedDescription {
		private String english, original;

		public String getEnglish() {
			return english;
		}

		public String getOriginal() {
			return original;
		}
	}
}
