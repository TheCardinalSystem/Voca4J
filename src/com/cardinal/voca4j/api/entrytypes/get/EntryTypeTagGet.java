package com.cardinal.voca4j.api.entrytypes.get;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryType;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.tag.Tag;
import com.cardinal.voca4j.api.entrytypes.EntryTypeFields;
import com.cardinal.voca4j.lang.Require;
import com.cardinal.voca4j.lang.RequireSet;

/**
 * TODO: Document EntryTypeGet
 */
public class EntryTypeTagGet extends FieldValueRequest implements ResponseDeserializer<Tag> {

	@Require
	private EntryType entryType;
	@Require
	private String subType;
	private EntryTypeFields[] fields;

	private EntryTypeTagGet() {
		super("/entry-types/{entryType}/{subType}/tag");
	}

	private EntryTypeTagGet(EntryType entryType, String subType, EntryTypeFields[] fields) {
		super("/entry-types/{entryType}/{subType}/tag");
		this.entryType = entryType;
		this.subType = subType;
		this.fields = fields;
	}

	@Override
	public Request copy() {
		return new EntryTypeTagGet(entryType, subType, fields);
	}

	@Override
	public Tag deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, Tag.class);
	}

	public static class EntryTypeTagGetBuilder extends StateControlledBuilder<EntryTypeTagGet> {

		protected EntryTypeTagGetBuilder() {
			super(new EntryTypeTagGet());
		}

		public EntryTypeTagGetBuilder setEntryType(EntryType entryType) {
			this.built.entryType = entryType;
			return this;
		}

		@RequireSet("entryType")
		public EntryTypeTagGetBuilder setSubType(Enum<?> subType) {
			String name = subType.getDeclaringClass().getSimpleName().toLowerCase();
			String type = this.built.entryType.name();
			if (!name.startsWith(type.toLowerCase()) || (!name.endsWith("type") && !name.endsWith("types")))
				throw new IllegalArgumentException(
						subType.getClass().getCanonicalName() + " is not a valid '" + type + "' type class.");
			this.built.subType = subType.name();
			return this;
		}

		@Deprecated
		public EntryTypeTagGetBuilder setSubType(String subType) {
			this.built.subType = subType;
			return this;
		}

		public EntryTypeTagGetBuilder includeFields(EntryTypeFields... fields) {
			this.built.fields = fields;
			return this;
		}
	}
}
