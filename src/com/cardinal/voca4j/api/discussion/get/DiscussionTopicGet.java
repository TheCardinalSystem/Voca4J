package com.cardinal.voca4j.api.discussion.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.discussion.DiscussionTopicFields;
import com.cardinal.voca4j.api.entities.discussion.DiscussionTopic;
import com.cardinal.voca4j.lang.Require;

/**
 * A {@link Request} implementation that gets a discussion topic by its ID.
 * 
 * @author Cardinal System
 *
 */
public class DiscussionTopicGet extends FieldValueRequest implements ResponseDeserializer<DiscussionTopic> {

	@Require
	private Integer topicId;
	private DiscussionTopicFields[] fields;

	private DiscussionTopicGet() {
		super("/discussions/topics/{topicId}");
	}

	private DiscussionTopicGet(Integer topicId, DiscussionTopicFields[] fields) {
		super("/discussions/topics/{topicId}");
		this.topicId = topicId;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new DiscussionTopicGet(topicId, fields);
	}

	@Override
	public DiscussionTopic deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, DiscussionTopic.class);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link DiscussionTopicGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class DiscussionTopicGetBuilder extends StateControlledBuilder<DiscussionTopicGet> {

		protected DiscussionTopicGetBuilder() {
			super(new DiscussionTopicGet());
		}

		/**
		 * Sets the ID of the discussion topic which the built request will get.
		 * 
		 * @param topicID topic ID.
		 * @return this, for chaining.
		 */
		public DiscussionTopicGetBuilder setTopicID(int topicID) {
			this.built.topicId = topicID;
			return this;
		}

		/**
		 * Optional fields to include on the repsonse.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public DiscussionTopicGetBuilder includeFields(DiscussionTopicFields... fields) {
			this.built.fields = fields;
			return this;
		}

	}
}
