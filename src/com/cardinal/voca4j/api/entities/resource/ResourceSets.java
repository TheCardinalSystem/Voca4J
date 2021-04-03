
package com.cardinal.voca4j.api.entities.resource;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResourceSets {

	@SerializedName("activityEntry_activityFeedEventNames")
	@Expose
	public ActivityEntryActivityFeedEventNames activityEntryActivityFeedEventNames;
	@SerializedName("album_albumEditableFieldNames")
	@Expose
	public AlbumAlbumEditableFieldNames albumAlbumEditableFieldNames;
	@SerializedName("artist_artistEditableFieldNames")
	@Expose
	public ArtistArtistEditableFieldNames artistArtistEditableFieldNames;
	@SerializedName("releaseEvent_releaseEventEditableFieldNames")
	@Expose
	public ReleaseEventReleaseEventEditableFieldNames releaseEventReleaseEventEditableFieldNames;
	@SerializedName("song_songEditableFieldNames")
	@Expose
	public SongSongEditableFieldNames songSongEditableFieldNames;
	@SerializedName("songList_songListEditableFieldNames")
	@Expose
	public SongListSongListEditableFieldNames songListSongListEditableFieldNames;
	@SerializedName("songList_songListFeaturedCategoryNames")
	@Expose
	public SongListSongListFeaturedCategoryNames songListSongListFeaturedCategoryNames;
	@SerializedName("tag_tagEditableFieldNames")
	@Expose
	public TagTagEditableFieldNames tagTagEditableFieldNames;
	@SerializedName("user_ratedSongForUserSortRuleNames")
	@Expose
	public UserRatedSongForUserSortRuleNames userRatedSongForUserSortRuleNames;
	@SerializedName("activityEntrySortRuleNames")
	@Expose
	public ActivityEntrySortRuleNames activityEntrySortRuleNames;
	@SerializedName("albumCollectionStatusNames")
	@Expose
	public AlbumCollectionStatusNames albumCollectionStatusNames;
	@SerializedName("albumMediaTypeNames")
	@Expose
	public AlbumMediaTypeNames albumMediaTypeNames;
	@SerializedName("albumSortRuleNames")
	@Expose
	public AlbumSortRuleNames albumSortRuleNames;
	@SerializedName("artistSortRuleNames")
	@Expose
	public ArtistSortRuleNames artistSortRuleNames;
	@SerializedName("artistTypeNames")
	@Expose
	public ArtistTypeNames artistTypeNames;
	@SerializedName("contentLanguageSelectionNames")
	@Expose
	public ContentLanguageSelectionNames contentLanguageSelectionNames;
	@SerializedName("discTypeNames")
	@Expose
	public DiscTypeNames discTypeNames;
	@SerializedName("entryTypeNames")
	@Expose
	public EntryTypeNames entryTypeNames;
	@SerializedName("eventCategoryNames")
	@Expose
	public EventCategoryNames eventCategoryNames;
	@SerializedName("eventSortRuleNames")
	@Expose
	public EventSortRuleNames eventSortRuleNames;
	@SerializedName("songListSortRuleNames")
	@Expose
	public SongListSortRuleNames songListSortRuleNames;
	@SerializedName("songSortRuleNames")
	@Expose
	public SongSortRuleNames songSortRuleNames;
	@SerializedName("songTypeNames")
	@Expose
	public SongTypeNames songTypeNames;
	@SerializedName("userGroupNames")
	@Expose
	public UserGroupNames userGroupNames;

	public ActivityEntryActivityFeedEventNames getActivityEntryActivityFeedEventNames() {
		return activityEntryActivityFeedEventNames;
	}

	public AlbumAlbumEditableFieldNames getAlbumAlbumEditableFieldNames() {
		return albumAlbumEditableFieldNames;
	}

	public ArtistArtistEditableFieldNames getArtistArtistEditableFieldNames() {
		return artistArtistEditableFieldNames;
	}

	public ReleaseEventReleaseEventEditableFieldNames getReleaseEventReleaseEventEditableFieldNames() {
		return releaseEventReleaseEventEditableFieldNames;
	}

	public SongSongEditableFieldNames getSongSongEditableFieldNames() {
		return songSongEditableFieldNames;
	}

	public SongListSongListEditableFieldNames getSongListSongListEditableFieldNames() {
		return songListSongListEditableFieldNames;
	}

	public SongListSongListFeaturedCategoryNames getSongListSongListFeaturedCategoryNames() {
		return songListSongListFeaturedCategoryNames;
	}

	public TagTagEditableFieldNames getTagTagEditableFieldNames() {
		return tagTagEditableFieldNames;
	}

	public UserRatedSongForUserSortRuleNames getUserRatedSongForUserSortRuleNames() {
		return userRatedSongForUserSortRuleNames;
	}

	public ActivityEntrySortRuleNames getActivityEntrySortRuleNames() {
		return activityEntrySortRuleNames;
	}

	public AlbumCollectionStatusNames getAlbumCollectionStatusNames() {
		return albumCollectionStatusNames;
	}

	public AlbumMediaTypeNames getAlbumMediaTypeNames() {
		return albumMediaTypeNames;
	}

	public AlbumSortRuleNames getAlbumSortRuleNames() {
		return albumSortRuleNames;
	}

	public ArtistSortRuleNames getArtistSortRuleNames() {
		return artistSortRuleNames;
	}

	public ArtistTypeNames getArtistTypeNames() {
		return artistTypeNames;
	}

	public ContentLanguageSelectionNames getContentLanguageSelectionNames() {
		return contentLanguageSelectionNames;
	}

	public DiscTypeNames getDiscTypeNames() {
		return discTypeNames;
	}

	public EntryTypeNames getEntryTypeNames() {
		return entryTypeNames;
	}

	public EventCategoryNames getEventCategoryNames() {
		return eventCategoryNames;
	}

	public EventSortRuleNames getEventSortRuleNames() {
		return eventSortRuleNames;
	}

	public SongListSortRuleNames getSongListSortRuleNames() {
		return songListSortRuleNames;
	}

	public SongSortRuleNames getSongSortRuleNames() {
		return songSortRuleNames;
	}

	public SongTypeNames getSongTypeNames() {
		return songTypeNames;
	}

	public UserGroupNames getUserGroupNames() {
		return userGroupNames;
	}

}
