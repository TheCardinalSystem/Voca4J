package com.cardinal.voca4j.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class ArrayDeserializer<T> implements JsonDeserializer<T> {

	private Type componentType;

	public ArrayDeserializer(Type componentType) {
		this.componentType = componentType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

		if (json.isJsonArray()) {
			JsonArray array = json.getAsJsonArray();
			T t = (T) Array.newInstance((Class<?>) componentType, array.size());
			for (int i = 0; i < array.size(); i++) {
				Array.set(t, i, context.deserialize(array.get(i), componentType));
			}
			return t;
		}

		if (json.isJsonPrimitive()) {
			String[] parts = json.getAsJsonPrimitive().getAsString().split("\\,\\s{0,1}");
			List<?> list = Arrays.stream(parts).map(JsonPrimitive::new).map(p -> context.deserialize(p, componentType))
					.collect(Collectors.toList());

			T t = (T) Array.newInstance((Class<?>) componentType, list.size());
			for (int i = 0; i < list.size(); i++) {
				Array.set(t, i, list.get(i));
			}

			return t;
		}

		return null;
	}

}
