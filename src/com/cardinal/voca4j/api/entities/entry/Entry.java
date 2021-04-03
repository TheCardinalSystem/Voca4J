package com.cardinal.voca4j.api.entities.entry;

import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.album.AlbumType;
import com.cardinal.voca4j.api.artist.ArtistType;
import com.cardinal.voca4j.api.entities.ContentLanguageSelection;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.api.entities.artist.Artist;
import com.cardinal.voca4j.api.entities.discussion.DiscussionTopic;
import com.cardinal.voca4j.api.entities.pv.PromotionalVideo;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEventSeries;
import com.cardinal.voca4j.api.entities.song.Song;
import com.cardinal.voca4j.api.entities.songlist.SongList;
import com.cardinal.voca4j.api.entities.tag.Tag;
import com.cardinal.voca4j.api.entities.user.User;
import com.cardinal.voca4j.api.entities.venue.Venue;
import com.cardinal.voca4j.api.releaseevent.EventCategory;
import com.cardinal.voca4j.api.song.SongType;
import com.cardinal.voca4j.api.songlist.SongListFeaturedCategory;
import com.cardinal.voca4j.util.ReflectionUtils;

public class Entry implements LinkableEntity {

	private LocalDateTime activityDate, createDate;
	private String additionalNames[], artistString, defaultName, name, description, releaseEventSeriesName,
			tagCategoryName, urlSlug;
	private ArtistType artistType;
	private ContentLanguageSelection defaultNameLanguage;
	private AlbumType discType;
	private EventCategory eventCategory;
	private EntryType entryType;
	private int id = -1, version = -1;
	private EntryThumbnail mainPicture;
	private LocalizedName[] names;
	private PromotionalVideo[] pVs;
	private SongListFeaturedCategory songListFeaturedCategory;
	private SongType songType;
	private EntryStatus status;
	private Tag[] tags;
	private WebLink[] webLinks;

	public String getName() {
		return name;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public LocalizedName[] getLocalizedNames() {
		return names;
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

	public String getDescription() {
		return description;
	}

	public LocalDateTime getActivityDate() {
		return activityDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getReleaseEventSeriesName() {
		return releaseEventSeriesName;
	}

	public String getTagCategoryName() {
		return tagCategoryName;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	public ArtistType getArtistType() {
		return artistType;
	}

	public AlbumType getDiscType() {
		return discType;
	}

	public EventCategory getEventCategory() {
		return eventCategory;
	}

	public EntryType getEntryType() {
		return entryType;
	}

	public int getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public PromotionalVideo[] getPVs() {
		return pVs;
	}

	public SongListFeaturedCategory getSongListFeaturedCategory() {
		return songListFeaturedCategory;
	}

	public SongType getSongType() {
		return songType;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public Tag[] getTags() {
		return tags;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	public Album getAsAlbum() {
		return getAsT(new Album());
	}

	public Artist getAsArtist() {
		return getAsT(new Artist());
	}

	public Song getAsSong() {
		return getAsT(new Song());
	}

	public SongList getAsSongList() {
		return getAsT(new SongList());
	}

	public DiscussionTopic getAsDiscussionTopic() {
		return getAsT(new DiscussionTopic());
	}

	public PromotionalVideo getAsPV() {
		return getAsT(new PromotionalVideo());
	}

	public ReleaseEvent getAsReleaseEvent() {
		return getAsT(new ReleaseEvent());
	}

	public ReleaseEventSeries getAsReleaseEventSeries() {
		return getAsT(new ReleaseEventSeries());
	}

	public Tag getAsTag() {
		return getAsT(new Tag());
	}

	public User getAsUser() {
		return getAsT(new User());
	}

	public Venue getAsVenue() {
		return getAsT(new Venue());
	}

	public Object getBasedOnEntryType() {
		switch (entryType) {
		case Album:
			return getAsAlbum();
		case Artist:
			return getAsArtist();
		case DiscussionTopic:
			return getAsDiscussionTopic();
		case PV:
			return getAsPV();
		case ReleaseEvent:
			return getAsReleaseEvent();
		case ReleaseEventSeries:
			return getAsReleaseEventSeries();
		case Song:
			return getAsSong();
		case SongList:
			return getAsSongList();
		case Tag:
			return getAsTag();
		case User:
		default:
			return this;
		}

	}

	private <T> T getAsT(T t) {
		ReflectionUtils.copyFields(this, t);
		return t;
	}

	@Override
	public String getURL() {
		switch (entryType) {
		case Album:
			return Constants.rootSiteURL + "/Al/" + id;
		case Artist:
			return Constants.rootSiteURL + "/Ar/" + id;
		case DiscussionTopic:
			return Constants.rootSiteURL + "/discussion/topics/" + id;
		case ReleaseEvent:
			return Constants.rootSiteURL + "/E/" + id + "/" + urlSlug;
		case ReleaseEventSeries:
			return Constants.rootSiteURL + "/Es/" + id + "/" + urlSlug;
		case Song:
			return Constants.rootSiteURL + "/S/" + id;
		case SongList:
			return Constants.rootSiteURL + "/L/" + id;
		case Tag:
			return Constants.rootSiteURL + "/T/" + id + "/" + urlSlug;
		case User:
			return Constants.rootSiteURL + "/User/Profile/" + name;
		default:
			throw new IllegalStateException(
					"This operation is not supported by: " + entryType.getClass().getName() + "#" + entryType);

		}
	}

	@Override
	public String toString() {
		return entryType + ":" + id;
	}
}
