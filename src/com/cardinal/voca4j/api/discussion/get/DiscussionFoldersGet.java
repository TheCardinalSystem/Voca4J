package com.cardinal.voca4j.api.discussion.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.discussion.DiscussionFolderFields;
import com.cardinal.voca4j.api.entities.discussion.DiscussionFolder;
import com.google.gson.reflect.TypeToken;

/**
 * A {@link Request} implementation that gets a list of discussion folders.
 * 
 * @author Cardinal System
 *
 */
public class DiscussionFoldersGet extends FieldValueRequest implements ResponseDeserializer<List<DiscussionFolder>> {

	private static final Type RESPONSE_TYPE = new TypeToken<List<DiscussionFolder>>() {}.getType();

	private DiscussionFolderFields[] fields;

	private DiscussionFoldersGet() {
		super("/discussions/folders");
	}

	private DiscussionFoldersGet(DiscussionFolderFields[] fields) {
		super("/discussions/folders");
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new DiscussionFoldersGet(fields);
	}

	@Override
	public List<DiscussionFolder> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	/**
	 * A {@link StateControlledBuilder} which creates a {@link DiscussionFoldersGet}
	 * request.
	 * 
	 * @author Cardinal System
	 *
	 */
	public static class DiscussionFoldersGetBuilder extends StateControlledBuilder<DiscussionFoldersGet> {

		protected DiscussionFoldersGetBuilder() {
			super(new DiscussionFoldersGet());
		}

		/**
		 * Optional fields to include in the response.
		 * 
		 * @param fields fields.
		 * @return this, for chaining.
		 */
		public DiscussionFoldersGetBuilder includeFields(DiscussionFolderFields... fields) {
			this.built.fields = fields;
			return this;
		}

	}

}
