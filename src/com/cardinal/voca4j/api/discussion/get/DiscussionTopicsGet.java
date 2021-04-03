package com.cardinal.voca4j.api.discussion.get;

import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.discussion.DiscussionTopicFields;
import com.cardinal.voca4j.api.discussion.DiscussionTopicsSort;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.discussion.DiscussionTopic;
import com.cardinal.voca4j.lang.AllowZero;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets a list of discussion topics.
 * 
 * @author Cardinal System
 *
 */
public class DiscussionTopicsGet extends FieldValueRequest
		implements ResponseDeserializer<QueriedList<DiscussionTopic>> {
	public static final Type RESPONSE_TYPE = new TypeToken<QueriedList<DiscussionTopic>>() {
	}.getType();

	private Integer folderId, start, maxResults;
	private Boolean getTotalCount;
	private DiscussionTopicsSort sort;
	private DiscussionTopicFields[] fields;

	private DiscussionTopicsGet() {
		super("/discussions/topics");
	}

	private DiscussionTopicsGet(Integer folderId, Integer start, Integer maxResults, Boolean getTotalCount,
			DiscussionTopicsSort sort, DiscussionTopicFields[] fields) {
		super("/discussions/topics");
		this.folderId = folderId;
		this.start = start;
		this.maxResults = maxResults;
		this.getTotalCount = getTotalCount;
		this.sort = sort;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new DiscussionTopicsGet(folderId, start, maxResults, getTotalCount, sort, fields);
	}

	@Override
	public QueriedList<DiscussionTopic> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} implementation that creates a
	 * {@link DiscussionTopicsGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class DiscussionTopicsGetBuilder extends StateControlledBuilder<DiscussionTopicsGet> {

		protected DiscussionTopicsGetBuilder() {
			super(new DiscussionTopicsGet());
		}

		/**
		 * Sets the request to get topics in the given discussion folder.
		 * 
		 * @param folderID folder ID.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder inFolder(int folderID) {
			this.built.folderId = folderID;
			return this;
		}

		/**
		 * Sets the first item to be retrieved from the results. This is essentially an
		 * offset.
		 * 
		 * @param start start.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder setStart(@AllowZero int start) {
			this.built.start = start;
			return this;
		}

		/***
		 * Sets the maximum number of results to be included in the response.
		 * 
		 * @param maxResults maximum results.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder setMaximumResults(int maxResults) {
			this.built.maxResults = maxResults;
			return this;
		}

		/**
		 * 
		 * Set whether this will load the total number of items.
		 * 
		 * @param getTotalCount get total count.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder getTotalCount(boolean getTotalCount) {
			this.built.getTotalCount = getTotalCount;
			return this;
		}

		/**
		 * Sets the sorting rule for the results.
		 * 
		 * @param sort sorting rule.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder setSortingRule(DiscussionTopicsSort sort) {
			this.built.sort = sort;
			return this;
		}

		/**
		 * Optional fields to include in the response.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public DiscussionTopicsGetBuilder includeFields(DiscussionTopicFields... fields) {
			this.built.fields = fields;
			return this;
		}
	}

}
