package com.cardinal.voca4j.api.user.get;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Language;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.album.AdvancedAlbumFilter;
import com.cardinal.voca4j.api.album.AlbumField;
import com.cardinal.voca4j.api.album.AlbumSort;
import com.cardinal.voca4j.api.album.AlbumType;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.user.UserCollectionAlbum;
import com.cardinal.voca4j.api.user.UserPurchaseStatus;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.BypassChecks;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document UserAlbumsGet
 */
public class UserAlbumsGet extends FieldValueRequest implements ResponseDeserializer<QueriedList<UserCollectionAlbum>> {

	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<UserCollectionAlbum>>() {
	}.getType();

	@Require
	private Integer id;
	@Encode
	private String query;
	private Integer tadId, artistId, releaseEventId, start, maxResults;
	private String tag;
	private UserPurchaseStatus[] purchaseStatuses;
	private AlbumType[] albumTypes;
	private AdvancedAlbumFilter[] advancedFilters;
	private Boolean getTotalCount;
	private AlbumSort sort;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private AlbumField[] fields;
	private Language lang;

	private UserAlbumsGet() {
		super("/users/{id}/albums");
	}

	private UserAlbumsGet(Integer id, String query, Integer tadId, Integer artistId, Integer releaseEventId,
			Integer start, Integer maxResults, String tag, UserPurchaseStatus[] purchaseStatuses,
			AlbumType[] albumTypes, AdvancedAlbumFilter[] advancedFilters, Boolean getTotalCount, AlbumSort sort,
			NameMatchMode nameMatchMode, AlbumField[] fields, Language lang) {
		super("/users/{id}/albums");
		this.id = id;
		this.query = query;
		this.tadId = tadId;
		this.artistId = artistId;
		this.releaseEventId = releaseEventId;
		this.start = start;
		this.maxResults = maxResults;
		this.tag = tag;
		this.purchaseStatuses = purchaseStatuses;
		this.albumTypes = albumTypes;
		this.advancedFilters = advancedFilters;
		this.getTotalCount = getTotalCount;
		this.sort = sort;
		this.nameMatchMode = nameMatchMode;
		this.fields = fields;
		this.lang = lang;
	}

	@Override
	public Request copy() {
		return new UserAlbumsGet(id, query, tadId, artistId, releaseEventId, start, maxResults, tag, purchaseStatuses,
				albumTypes, advancedFilters, getTotalCount, sort, nameMatchMode, fields, lang);
	}

	@Override
	public QueriedList<UserCollectionAlbum> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class UserAlbumsGetBuilder extends StateControlledBuilder<UserAlbumsGet> {

		protected UserAlbumsGetBuilder() {
			super(new UserAlbumsGet());
		}

		public UserAlbumsGetBuilder setUserID(int userID) {
			this.built.id = userID;
			return this;
		}

		public UserAlbumsGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserAlbumsGetBuilder filterTagID(int tagId) {
			this.built.tadId = tagId;
			return this;
		}

		public UserAlbumsGetBuilder filterTagName(String tagName) {
			this.built.tag = tagName;
			return this;
		}

		public UserAlbumsGetBuilder filterArtist(int artistId) {
			this.built.artistId = artistId;
			return this;
		}

		public UserAlbumsGetBuilder filterPurchaseStatuses(UserPurchaseStatus... purchaseStatuses) {
			this.built.purchaseStatuses = purchaseStatuses;
			return this;
		}

		public UserAlbumsGetBuilder filterReleaseEventId(int releaseEventID) {
			this.built.releaseEventId = releaseEventID;
			return this;
		}

		public UserAlbumsGetBuilder filterAlbumTypes(AlbumType... albumTypes) {
			this.built.albumTypes = albumTypes;
			return this;
		}

		public UserAlbumsGetBuilder setAdvancedFilters(AdvancedAlbumFilter... advancedFilters) {
			this.built.advancedFilters = advancedFilters;
			return this;
		}

		public UserAlbumsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		@BypassChecks(state = false)
		public UserAlbumsGetBuilder setMaximumResults(int maxResults) {
			if (maxResults < 1 || maxResults > 50)
				throw new IllegalArgumentException(maxResults + " | Expected value between 1 and 50!");
			this.built.maxResults = maxResults;
			return this;
		}

		public UserAlbumsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public UserAlbumsGetBuilder setSortingRule(AlbumSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public UserAlbumsGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserAlbumsGetBuilder includeFields(AlbumField... fields) {
			this.built.fields = fields;
			return this;
		}

		public UserAlbumsGetBuilder setLanguagePreference(Language language) {
			this.built.lang = language;
			return this;
		}
	}

}
