package com.cardinal.voca4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.api.album.get.AlbumCommentsGet;
import com.cardinal.voca4j.api.album.get.AlbumGet.AlbumGetBuilder;
import com.cardinal.voca4j.api.album.query.AlbumNamesQuery;
import com.cardinal.voca4j.api.album.query.AlbumQuery;
import com.cardinal.voca4j.api.album.query.NewAlbumsQuery;
import com.cardinal.voca4j.api.album.query.TopAlbumsQuery;
import com.cardinal.voca4j.api.artist.ArtistFields;
import com.cardinal.voca4j.api.artist.get.ArtistCommentsGet;
import com.cardinal.voca4j.api.artist.get.ArtistGet.ArtistGetBuilder;
import com.cardinal.voca4j.api.artist.query.ArtistNamesQuery;
import com.cardinal.voca4j.api.artist.query.ArtistQuery;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.artist.Artist;
import com.cardinal.voca4j.api.entities.song.Song;
import com.cardinal.voca4j.api.entities.tag.QueriedTagList;
import com.cardinal.voca4j.api.entities.tag.Tag;
import com.cardinal.voca4j.api.entities.user.User;
import com.cardinal.voca4j.api.entities.user.UserCollectionAlbum;
import com.cardinal.voca4j.api.entry.query.EntryNamesQuery;
import com.cardinal.voca4j.api.releaseevent.get.ReleaseEventSongsGet;
import com.cardinal.voca4j.api.releaseevent.query.ReleaseEventNamesQuery;
import com.cardinal.voca4j.api.song.SongField;
import com.cardinal.voca4j.api.song.get.SongByPVGet;
import com.cardinal.voca4j.api.song.get.SongCommentsGet;
import com.cardinal.voca4j.api.song.get.SongDerivativesGet;
import com.cardinal.voca4j.api.song.get.SongGet;
import com.cardinal.voca4j.api.song.get.SongGet.SongGetBuilder;
import com.cardinal.voca4j.api.song.get.SongTopRatedGet;
import com.cardinal.voca4j.api.song.get.SongsHightlightedGet;
import com.cardinal.voca4j.api.song.query.SongNamesQuery;
import com.cardinal.voca4j.api.songlist.get.SongListCommentsGet;
import com.cardinal.voca4j.api.songlist.get.SongListSongsGet.SongListSongsGetBuilder;
import com.cardinal.voca4j.api.songlist.query.SongListFeaturedNamesQuery;
import com.cardinal.voca4j.api.songlist.query.SongListFeaturedQuery;
import com.cardinal.voca4j.api.tag.TagField;
import com.cardinal.voca4j.api.tag.get.TagByNameGet;
import com.cardinal.voca4j.api.tag.get.TagCategoryNamesGet;
import com.cardinal.voca4j.api.tag.get.TagChildrenGet;
import com.cardinal.voca4j.api.tag.get.TagCommentsGet;
import com.cardinal.voca4j.api.tag.get.TagGet;
import com.cardinal.voca4j.api.tag.get.TagGet.TagGetBuilder;
import com.cardinal.voca4j.api.tag.get.TopTagsGet;
import com.cardinal.voca4j.api.tag.query.TagNamesQuery;
import com.cardinal.voca4j.api.tag.query.TagsQuery;
import com.cardinal.voca4j.api.user.SongVoteRating;
import com.cardinal.voca4j.api.user.UserField;
import com.cardinal.voca4j.api.user.get.UserAlbumCollectionStatusesGet;
import com.cardinal.voca4j.api.user.get.UserFollowedArtistGet;
import com.cardinal.voca4j.api.user.get.UserFollowedArtistsGet;
import com.cardinal.voca4j.api.user.get.UserGet;
import com.cardinal.voca4j.api.user.get.UserProfileCommentsGet;
import com.cardinal.voca4j.api.user.get.UserSongListsGet;
import com.cardinal.voca4j.api.user.get.UserSongRatingGet;
import com.cardinal.voca4j.api.user.query.UserNamesQuery;
import com.cardinal.voca4j.api.user.query.UserQuery;
import com.cardinal.voca4j.api.user.query.UserQuery.UserQueryBuilder;
import com.cardinal.voca4j.impl.APIService;
import com.cardinal.voca4j.impl.BuilderProvider;
import com.cardinal.voca4j.impl.DeserializationContext;
import com.cardinal.voca4j.util.TaskExectuorService;

/**
 * A class used to centralize all the components used in this API wrapper.
 * 
 * @author Cardinal System
 *
 */
public class Voca4J {

	private TaskExectuorService exectuorService;
	private BuilderProvider builderProvider;
	private APIService apiService;

	public Voca4J() {
		System.out.println(
				"----------------------------------------------------------------------------------- INITIALIZING VOCA4J -----------------------------------------------------------------------------------");
		exectuorService = new TaskExectuorService();
		builderProvider = new BuilderProvider();
		apiService = new APIService();

		setupDeserializer(apiService.getDeserializationContext());
		System.out.println(
				"----------------------------------------------------------------------------------------- VOCA4J ------------------------------------------------------------------------------------------");
	}

	private static final Pattern NUMBER_PATTERN = Pattern.compile("\\d+");
	private static final Pattern USERNAME_PATTERN = Pattern.compile("(?<=profile.)\\w+", Pattern.CASE_INSENSITIVE);

	@SuppressWarnings("unchecked")
	public Object fetch(String url) {
		String type = url.replaceFirst("https{0,1}\\:\\/\\/vocadb\\.net\\/", "").toLowerCase();
		if (type.startsWith("al")) {
			Matcher m = NUMBER_PATTERN.matcher(type);
			m.find();
			int id = Integer.parseInt(m.group());
			return apiService.completeAndParse(builderProvider.getBuilder(AlbumGetBuilder.class).setID(id)
					.setAlbumFields(AlbumField.values()).setSongFields(SongField.VALUES).build());
		} else if (type.startsWith("ar")) {
			Matcher m = NUMBER_PATTERN.matcher(type);
			m.find();
			int id = Integer.parseInt(m.group());
			return apiService.completeAndParse(builderProvider.getBuilder(ArtistGetBuilder.class).setID(id)
					.setFields(ArtistFields.VALUES).build());
		} else if (type.startsWith("s")) {
			Matcher m = NUMBER_PATTERN.matcher(type);
			m.find();
			int id = Integer.parseInt(m.group());
			return apiService.completeAndParse(
					builderProvider.getBuilder(SongGetBuilder.class).setID(id).setFields(SongField.VALUES).build());
		} else if (type.startsWith("l")) {
			Matcher m = NUMBER_PATTERN.matcher(type);
			m.find();
			int id = Integer.parseInt(m.group());
			return apiService.completeAndParse(builderProvider.getBuilder(SongListSongsGetBuilder.class).setListID(id)
					.includeFields(SongField.VALUES).build());
		} else if (type.startsWith("t")) {
			Matcher m = NUMBER_PATTERN.matcher(type);
			m.find();
			int id = Integer.parseInt(m.group());
			return apiService.completeAndParse(
					builderProvider.getBuilder(TagGetBuilder.class).setID(id).includeFields(TagField.VALUES).build());
		} else if (type.startsWith("user")) {
			Matcher m = USERNAME_PATTERN.matcher(url);
			m.find();
			type = m.group();
			return ((QueriedList<UserQuery>) apiService
					.completeAndParse(builderProvider.getBuilder(UserQueryBuilder.class).setQuery(type)
							.setNameMatchMode(NameMatchMode.Exact).includeFields(UserField.VALUES).build())).get()
									.get(0);
		} else if (type.startsWith("venue")) {
			throw new IllegalArgumentException("Venues cannot be fetched by URL.");
		}
		throw new IllegalArgumentException("Unable to fetch URL: " + url);
	}

	/**
	 * Gets an executor service for performing concurrent tasks.
	 * 
	 * @return executor service.
	 */
	public TaskExectuorService getExectuorService() {
		return exectuorService;
	}

	/**
	 * Gets a {@link BuilderProvider} instance for creating builders.
	 * 
	 * @return builder provider.
	 */
	public BuilderProvider getBuilderProvider() {
		return builderProvider;
	}

	/**
	 * Gets the {@link APIService} which is used for interfacing the API.
	 * 
	 * @return API service.
	 */
	public APIService getApiService() {
		return apiService;
	}

	/**
	 * Registers all the default type pairs and type adapters in the given
	 * {@link DeserializationContext}.
	 * 
	 * @param deserializationContext deserialization context.
	 */
	public void setupDeserializer(DeserializationContext deserializationContext) {
		deserializationContext.registerMultiple(Constants.STRING_SET_TYPE, AlbumNamesQuery.class,
				ArtistNamesQuery.class, EntryNamesQuery.class, ReleaseEventNamesQuery.class, SongNamesQuery.class,
				SongListFeaturedNamesQuery.class, TagCategoryNamesGet.class, TagNamesQuery.class, UserNamesQuery.class);
		deserializationContext.registerMultiple(Constants.ALBUM_QUERIED_LIST_TYPE, AlbumQuery.class,
				NewAlbumsQuery.class, TopAlbumsQuery.class);
		deserializationContext.registerMultiple(Constants.COMMENT_LIST_TYPE, AlbumCommentsGet.class,
				ArtistCommentsGet.class, SongCommentsGet.class);
		deserializationContext.registerMultiple(Constants.SONG_LIST_TYPE, ReleaseEventSongsGet.class,
				SongDerivativesGet.class, SongsHightlightedGet.class, SongTopRatedGet.class);
		deserializationContext.registerMultiple(Constants.QUERIED_COMMENT_LIST_TYPE, SongListCommentsGet.class,
				TagCommentsGet.class, UserProfileCommentsGet.class);
		deserializationContext.registerMultiple(Constants.TAG_LIST_TYPE, TagChildrenGet.class, TopTagsGet.class);
		deserializationContext.registerMultiple(Constants.ARTIST_QUERIED_LIST_TYPE, ArtistQuery.class,
				UserFollowedArtistsGet.class);
		deserializationContext.registerMultiple(Constants.SONG_LIST_QUERIED_LIST_TYPE, SongListFeaturedQuery.class,
				UserSongListsGet.class);

		deserializationContext.registerMultiple(Song.class, SongGet.class, SongByPVGet.class);
		deserializationContext.registerMultiple(Tag.class, TagByNameGet.class, TagGet.class);
		deserializationContext.registerPairType(TagsQuery.class, QueriedTagList.class);
		deserializationContext.registerPairType(UserAlbumCollectionStatusesGet.class, UserCollectionAlbum.class);
		deserializationContext.registerPairType(UserFollowedArtistGet.class, Artist.class);
		deserializationContext.registerPairType(UserGet.class, User.class);
		deserializationContext.registerPairType(UserSongRatingGet.class, SongVoteRating.class);
	}

}
