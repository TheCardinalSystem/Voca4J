package com.cardinal.voca4j.api.discussion.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.discussion.DiscussionTopicFields;
import com.cardinal.voca4j.api.entities.discussion.DiscussionTopic;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets a list of discussion topics in a
 * given folder.
 * 
 * @author Cardinal System
 *
 */
public class DiscussionFolderTopicsGet extends FieldValueRequest
		implements ResponseDeserializer<List<DiscussionTopic>> {
	
	private static final Type RESPONSE_TYPE = new TypeToken<List<DiscussionTopic>>() {}.getType();
	
	@Require
	private Integer folderId;
	private DiscussionTopicFields[] fields;

	private DiscussionFolderTopicsGet() {
		super("/discussions/folders/{folderId}/topics");
	}

	private DiscussionFolderTopicsGet(String rootUrl, Integer folderId, DiscussionTopicFields[] fields) {
		super("/discussions/folders/{folderId}/topics");
		this.folderId = folderId;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new DiscussionFolderTopicsGet(rootUrl, folderId, fields);
	}

	@Override
	public List<DiscussionTopic> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a
	 * {@link DiscussionFolderTopicsGet} request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class DiscussionFolderTopicsGetBuilder extends StateControlledBuilder<DiscussionFolderTopicsGet> {

		protected DiscussionFolderTopicsGetBuilder() {
			super(new DiscussionFolderTopicsGet());
		}

		/**
		 * Sets which the built request will retrieve discussion topics form.
		 * 
		 * @param folderID folder ID.
		 * @return this, for chaining.
		 */
		public DiscussionFolderTopicsGetBuilder setFolderID(int folderID) {
			this.built.folderId = folderID;
			return this;
		}

		/**
		 * Optional fields to be included in the repsonse.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public DiscussionFolderTopicsGetBuilder includeFields(DiscussionTopicFields... fields) {
			this.built.fields = fields;
			return this;
		}

	}
}
