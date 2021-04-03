package com.cardinal.voca4j.api.entities.artist;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.artist.ArtistRelations;
import com.cardinal.voca4j.api.entities.ContentLanguageSelection;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.tag.TagUsage;

public class Artist extends ArtistBase {
	private String defaultName, description;
	private ArtistLink[] artistLinks, artistLinksReverse;
	private ArtistBase baseVoicebank;
	private LocalDateTime createDate;
	private ContentLanguageSelection defaultNameLanguage;
	private EntryThumbnail mainPicture;
	private int mergedTo = -1;
	private LocalizedName[] names;
	private ArtistRelations relations;
	private TagUsage[] tags;
	private WebLink[] webLinks;

	public String getDefaultName() {
		return defaultName;
	}

	public String getDescription() {
		return description;
	}

	public ArtistLink[] getArtistLinks() {
		return artistLinks;
	}

	public ArtistLink[] getArtistLinksReverse() {
		return artistLinksReverse;
	}

	public ArtistBase getBaseVoicebank() {
		return baseVoicebank;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public ContentLanguageSelection getDefaultNameLanguage() {
		return defaultNameLanguage;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public int getMergedTo() {
		return mergedTo;
	}

	public LocalizedName[] getNames() {
		return names;
	}

	public ArtistRelations getRelations() {
		return relations;
	}

	public TagUsage[] getTags() {
		return tags;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	public static class ArtistLink {
		private Artist artist;

		public Artist get() {
			return artist;
		}
	}
}
