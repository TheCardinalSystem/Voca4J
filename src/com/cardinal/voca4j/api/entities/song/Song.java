package com.cardinal.voca4j.api.entities.song;

import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.entities.ContentLanguageSelection;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.api.entities.pv.PromotionalVideo;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.entities.tag.TagUsage;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.api.song.SongType;

public class Song implements LinkableEntity {

	private String artistString, additionalNames[], defaultName, name, thumbUrl;
	private PVService[] pvServices;
	private Album[] albums;
	private SongArtist[] artists;
	private LocalDateTime createDate, publishDate;
	private ContentLanguageSelection defaultNameLanguage;
	private boolean deleted;
	private int favoritedTimes = -1, id = -1, lengthSeconds = -1, mergedTo = -1, originalVersionId = -1,
			ratingScore = -1, version = -1;
	private Lyrics[] lyrics;
	private EntryThumbnail mainPicture;
	private LocalizedName[] names;
	private PromotionalVideo[] pvs;
	private PVService[] pServices;
	private ReleaseEvent releaseEvent;
	private SongType songType;
	private EntryStatus status;
	private TagUsage[] tags;
	private WebLink[] webLinks;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public ContentLanguageSelection getDefaultNameLanguage() {
		return defaultNameLanguage;
	}

	public String getArtistString() {
		return artistString;
	}

	public PVService[] getPvServices() {
		return pvServices;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public Album[] getAlbums() {
		return albums;
	}

	public SongArtist[] getArtists() {
		return artists;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getPublishDate() {
		return publishDate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public int getFavoritedTimes() {
		return favoritedTimes;
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public int getMergedTo() {
		return mergedTo;
	}

	public int getOriginalVersionId() {
		return originalVersionId;
	}

	public int getRatingScore() {
		return ratingScore;
	}

	public int getVersion() {
		return version;
	}

	public Lyrics[] getLyrics() {
		return lyrics;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public LocalizedName[] getNames() {
		return names;
	}

	public PromotionalVideo[] getPvs() {
		return pvs;
	}

	public PVService[] getPVServices() {
		return pServices;
	}

	public ReleaseEvent getReleaseEvent() {
		return releaseEvent;
	}

	public SongType getSongType() {
		return songType;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public TagUsage[] getTags() {
		return tags;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/S/" + id;
	}

}
