package com.cardinal.voca4j.api.user.get;

import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.songlist.SongListFields;
import com.cardinal.voca4j.api.songlist.SongListSort;
import com.cardinal.voca4j.lang.AllowZero;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.IfSet;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document UserRatedSongsGet
 */
public class UserSongListsGet extends FieldValueRequest {

	@Require
	private Integer id;
	@Encode
	private String query;
	@JoiningFormat
	private Integer[] tagId;
	private Boolean childTags, getTotalCount;
	@IfSet("query")
	private NameMatchMode nameMatchMode = NameMatchMode.Auto;
	private Integer start, maxResults;
	private SongListSort sort;
	private SongListFields[] fields;

	private UserSongListsGet() {
		super("/users/{id}/songLists");
	}

	private UserSongListsGet(Integer id, String query, Integer[] tagId, Boolean childTags, Boolean getTotalCount,
			NameMatchMode nameMatchMode, Integer start, Integer maxResults, SongListSort sort,
			SongListFields[] fields) {
		super("/users/{id}/songLists");
		this.id = id;
		this.query = query;
		this.tagId = tagId;
		this.childTags = childTags;
		this.getTotalCount = getTotalCount;
		this.nameMatchMode = nameMatchMode;
		this.start = start;
		this.maxResults = maxResults;
		this.sort = sort;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new UserSongListsGet(id, query, tagId, childTags, getTotalCount, nameMatchMode, start, maxResults, sort,
				fields);
	}

	public static class UserSongListsGetBuilder extends StateControlledBuilder<UserSongListsGet> {

		protected UserSongListsGetBuilder() {
			super(new UserSongListsGet());
		}

		public UserSongListsGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserSongListsGetBuilder setQuery(String query) {
			this.built.query = query;
			return this;
		}

		public UserSongListsGetBuilder includeChildTags(boolean childTags) {
			this.built.childTags = childTags;
			return this;
		}

		public UserSongListsGetBuilder setNameMatchMode(NameMatchMode nameMatchMode) {
			this.built.nameMatchMode = nameMatchMode;
			return this;
		}

		public UserSongListsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		public UserSongListsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		public UserSongListsGetBuilder getToalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		public UserSongListsGetBuilder setSortingRule(SongListSort sortingRule) {
			this.built.sort = sortingRule;
			return this;
		}

		public UserSongListsGetBuilder includeFields(SongListFields... fields) {
			this.built.fields = fields;
			return this;
		}
	}
}
