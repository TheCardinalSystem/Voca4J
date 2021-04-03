package com.cardinal.voca4j.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardinal.voca4j.api.Builder.State;
import com.cardinal.voca4j.api.BuilderFactory;
import com.cardinal.voca4j.api.StateControlledBuilder;

/**
 * A utility class that provides {@link StateControlledBuilder} instances while
 * providing efficient memory management.
 * 
 * @author Cardinal System <br>
 * 
 *         <hr>
 *         Complete list of available builer types: <br>
 * 
 *         <br>
 * 
 *         <ul>
 *         <li>{@linkplain com.cardinal.voca4j.api.activityentry.get.ActivityEntriesGet.ActivityEntriesGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumCommentsGet.AlbumCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumGet.AlbumGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumReviewsGet.AlbumReviewsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumTrackFieldsGet.AlbumTrackFieldsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumTracksGet.AlbumTracksGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.get.AlbumUserCollectionsGet.AlbumUserCollectionsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.query.AlbumNamesQuery.AlbumNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.query.AlbumQuery.AlbumQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.query.NewAlbumsQuery.NewAlbumsQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.album.query.TopAlbumsQuery.TopAlbumsQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.artist.get.ArtistCommentsGet.ArtistCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.artist.get.ArtistGet.ArtistGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.artist.query.ArtistNamesQuery.ArtistNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.artist.query.ArtistQuery.ArtistQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.comment.get.EntryCommentsGet.EntryCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.discussion.get.DiscussionFoldersGet.DiscussionFoldersGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.discussion.get.DiscussionFolderTopicsGet.DiscussionFolderTopicsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.discussion.get.DiscussionTopicGet.DiscussionTopicGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.discussion.get.DiscussionTopicsGet.DiscussionTopicsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.LyricsGet.LyricsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongByPVGet.SongByPVGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongCommentsGet.SongCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongDerivativesGet.SongDerivativesGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongGet.SongGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongRatingsGet.SongRatingsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongRelatedGet.SongRelatedGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongsHightlightedGet.SongsHightlightedGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.get.SongTopRatedGet.SongTopRatedGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.query.SongNamesQuery.SongNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.song.query.SongQuery.SongQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.entry.query.EntryNamesQuery.EntryNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.entry.query.EntryQuery.EntryQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.entrytypes.get.EntryTypeTagGet.EntryTypeTagGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.pv.get.PVsForSongsGet.PVsForSongsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseevent.get.ReleaseEventAlbumsGet.ReleaseEventAlbumsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseevent.get.ReleaseEventGet.ReleaseEventGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseevent.get.ReleaseEventSongsGet.ReleaseEventSongsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseevent.query.ReleaseEventNamesQuery.ReleaseEventNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseevent.query.ReleaseEventQuery.ReleaseEventQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseeventseries.get.ReleaseEventSeriesGet.ReleaseEventSeriesGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.releaseeventseries.query.ReleaseEventSeriesQuery.ReleaseEventSeriesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.songlist.get.SongListCommentsGet.SongListCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.songlist.get.SongListSongsGet.SongListSongsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.songlist.query.SongListFeaturedNamesQuery.SongListFeaturedNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.songlist.query.SongListFeaturedQuery.SongListFeaturedQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TagByNameGet.TagByNameGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TagGet.TagGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TagChildrenGet.TagChildrenGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TagCommentsGet.TagCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TagCategoryNamesGet.TagCategoryNamesGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.get.TopTagsGet.TopTagsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.query.TagsQuery.TagsQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.tag.query.TagNamesQuery.TagNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserAlbumsGet.UserAlbumsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserEventsGet.UserEventsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserFollowedArtistsGet.UserFollowedArtistsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserGet.UserGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserProfileCommentsGet.UserProfileCommentsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserRatedSongsGet.UserRatedSongsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserSongListsGet.UserSongListsGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserSongRatingGet.UserSongRatingGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserFollowedArtistGet.UserFollowedArtistGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.get.UserAlbumCollectionStatusesGet.UserAlbumCollectionStatusesGetBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.query.UserQuery.UserQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.user.query.UserNamesQuery.UserNamesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.venue.query.VenuesQuery.VenuesQueryBuilder}</li>
 * 
 *         <li>{@linkplain com.cardinal.voca4j.api.resource.get.ResourcesGet.ResourcesGetBuilder}</li>
 *         </ul>
 */
public class BuilderProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(BuilderProvider.class);

	private Map<Class<?>, List<StateControlledBuilder<?>>> builderMapping = new HashMap<Class<?>, List<StateControlledBuilder<?>>>();

	private BuilderFactory factory;

	public BuilderProvider() {
		this.factory = new BuilderFactory();
	}

	public BuilderFactory getFactory() {
		return factory;
	}

	/**
	 * Finds a previously used builder, resets and recycles it for reuse, then
	 * returns it. If there are no available builders, this will create a new one
	 * and map it for later reuse.
	 * 
	 * @param <T>         builder type.
	 * @param builderType builder class.
	 * @return builder.
	 */
	@SuppressWarnings("unchecked")
	public <T extends StateControlledBuilder<?>> T getBuilder(Class<T> builderType) {
		LOGGER.info("Fetching builder of type: " + ((Type) builderType).getTypeName());
		boolean append = false;
		if (builderMapping.containsKey(builderType)) {
			List<StateControlledBuilder<?>> builders = builderMapping.get(builderType);
			StateControlledBuilder<?> builder = builders.get(0);
			int i = 1;
			while (!builder.getState().equals(State.BUILT)) {
				if (i == builders.size()) {
					builder = null;
					append = true;
					break;
				}
				builder = builders.get(i);
				LOGGER.info("Checking builder state: " + builder.toString() + " | " + builder.getState().name());
				i++;
			}
			if (builder != null) {
				LOGGER.info("Found available builder: " + builder.toString());
				return (T) builder.resetRecycle();
			}
		}

		LOGGER.info("No builders available.");
		T builder = factory.create(builderType);

		if (append)
			builderMapping.get(builderType).add(builder);
		else {
			List<StateControlledBuilder<?>> list = new ArrayList<StateControlledBuilder<?>>();
			list.add(builder);
			builderMapping.put(builderType, list);
		}
		return builder;
	}

	/**
	 * Finds a previously used builder, resets and recycles it for reuse, then
	 * returns it. If there are no available builders, this will create a new one.
	 * This method does NOT allow the builder to be reused later.
	 * 
	 * @param <T>         builder type.
	 * @param builderType builder class.
	 * @return builder.
	 */
	@SuppressWarnings("unchecked")
	public <T extends StateControlledBuilder<?>> T getPrivateBuilder(Class<T> builderType) {
		LOGGER.info("Fetching private builder of type: " + ((Type) builderType).getTypeName());
		if (builderMapping.containsKey(builderType)) {
			List<StateControlledBuilder<?>> builders = builderMapping.get(builderType);
			StateControlledBuilder<?> builder = builders.get(0);
			int i = 1;
			while (!builder.getState().equals(State.BUILT)) {
				if (i == builders.size()) {
					builder = null;
					break;
				}
				builder = builders.get(i);
				LOGGER.info("Checking builder state: " + builder.toString() + " | " + builder.getState().name());
				i++;
			}
			if (builder != null) {
				LOGGER.info("Found available builder: " + builder.toString());
				builders.remove(builder);
				if (builders.isEmpty())
					builderMapping.remove(builderType);
				return (T) builder.resetRecycle();
			}
		}
		LOGGER.info("No builders available");
		T builder = factory.create(builderType);
		return builder;
	}
}
