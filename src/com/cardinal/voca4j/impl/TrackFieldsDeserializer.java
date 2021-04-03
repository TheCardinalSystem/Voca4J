package com.cardinal.voca4j.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.cardinal.voca4j.api.entities.album.TrackFields;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class TrackFieldsDeserializer implements JsonDeserializer<TrackFields> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	private static final Class<TrackFields> CLAZZ = TrackFields.class;

	@Override
	public TrackFields deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		TrackFields fields = new TrackFields();

		insertIfPresent("artist", "completeArtistString", obj, fields);
		insertIfPresent("trackartist", "trackArtist", obj, fields);
		insertIfPresent("featvocalists", "featVocalists", obj, fields);
		insertIfPresent("pv.original.niconicodouga", "pvOriginalNicoNicoDouga", obj, fields);
		insertIfPresent("pv.original.!niconicodouga", "pvOriginalNotNicoNicoDouga", obj, fields);
		insertIfPresent("pv.reprint", "pvReprint", obj, fields);
		insertIfPresent("title", "title", obj, fields);
		insertIfPresent("title.en", "titleEnglish", obj, fields);
		insertIfPresent("title.romanized", "titleRomanized", obj, fields);
		insertIfPresent("title.original", "titleOriginal", obj, fields);
		insertIfPresent("url", "url", obj, fields);

		String tmp = getIfPresent("vocalists", obj);
		if (tmp != null && !tmp.isEmpty())
			insert("vocalists",
					Arrays.stream(tmp.split("\\,")).map(s -> s.replaceAll("^\\s", "")).toArray(String[]::new), fields);

		tmp = getIfPresent("producers", obj);
		if (tmp != null && !tmp.isEmpty())
			insert("producers",
					Arrays.stream(tmp.split("\\,")).map(s -> s.replaceAll("^\\s", "")).toArray(String[]::new), fields);

		tmp = getIfPresent("id", obj);
		if (tmp != null && !tmp.isEmpty())
			insert("id", Integer.parseInt(tmp), fields);

		tmp = getIfPresent("lengthseconds", obj);
		if (tmp != null && !tmp.isEmpty())
			insert("lengthSeconds", Integer.parseInt(tmp), fields);

		tmp = getIfPresent("publishdate", obj);
		if (tmp != null && !tmp.isEmpty())
			insert("publishDate", LocalDate.parse(formatDateString(tmp), FORMATTER), fields);

		return fields;
	}

	private String formatDateString(String dateString) {
		String[] parts = dateString.split("\\/");
		if (parts[0].length() == 1) {
			parts[0] = "0" + parts[0];
		}
		if (parts[1].length() == 1) {
			parts[1] = "0" + parts[0];
		}
		return parts[0] + "/" + parts[1] + "/" + parts[2];
	}

	private String getIfPresent(String propName, JsonObject obj) {
		if (obj.has(propName))
			return obj.get(propName).getAsString();
		return null;
	}

	private void insertIfPresent(String propName, String fieldName, JsonObject obj, TrackFields instance) {
		if (obj.has(propName)) {
			insert(fieldName, obj.get(propName).getAsString(), instance);
		}
	}

	private void insert(String fieldName, Object value, TrackFields instance) {
		Field f = null;
		try {
			f = CLAZZ.getDeclaredField(fieldName);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				f.setAccessible(false);
		}
	}

}
