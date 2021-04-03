package com.cardinal.voca4j.api.user.get;

import java.lang.reflect.Type;
import java.util.List;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.user.SubscriptionType;
import com.cardinal.voca4j.lang.Require;
import com.google.gson.reflect.TypeToken;

/**
 * TODO: Document UserEventsGet
 */
public class UserEventsGet extends FieldValueRequest implements ResponseDeserializer<List<ReleaseEvent>> {

	public static final Type RESPONSE_TYPE = new TypeToken<List<ReleaseEvent>>() {
	}.getType();

	@Require
	private Integer id;
	@Require
	private SubscriptionType relationshipType;

	private UserEventsGet() {
		super("/users/{id}/events");
	}

	private UserEventsGet(Integer id, SubscriptionType relationshipType) {
		super("/users/{id}/events");
		this.id = id;
		this.relationshipType = relationshipType;
	}

	@Override
	public Request copy() {
		return new UserEventsGet(id, relationshipType);
	}

	@Override
	public List<ReleaseEvent> deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, RESPONSE_TYPE);
	}

	public static class UserEventsGetBuilder extends StateControlledBuilder<UserEventsGet> {

		protected UserEventsGetBuilder() {
			super(new UserEventsGet());
		}

		public UserEventsGetBuilder setUserID(int userId) {
			this.built.id = userId;
			return this;
		}

		public UserEventsGetBuilder setRelationshipType(SubscriptionType relationshipType) {
			this.built.relationshipType = relationshipType;
			return this;
		}

	}

}
