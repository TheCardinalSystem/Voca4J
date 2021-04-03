package com.cardinal.voca4j.api.resource.get;

import java.util.Locale;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.FieldValueRequest;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.api.StateControlledBuilder;
import com.cardinal.voca4j.api.entities.resource.ResourceSets;
import com.cardinal.voca4j.api.resource.ResourceSetNames;
import com.cardinal.voca4j.lang.JoiningFormat;
import com.cardinal.voca4j.lang.Require;

/**
 * TODO: Document ResourcesGet
 */
public class ResourcesGet extends FieldValueRequest implements ResponseDeserializer<ResourceSets> {

	@Require
	private String cultureCode;
	@Require
	@JoiningFormat
	private ResourceSetNames[] setNames;

	private ResourcesGet() {
		super("/resources/{cultureCode}");
	}

	private ResourcesGet(String cultureCode, ResourceSetNames[] setNames) {
		super("/resources/{cultureCode}");
		this.cultureCode = cultureCode;
		this.setNames = setNames;
	}

	@Override
	public Request copy() {
		return new ResourcesGet(cultureCode, setNames);
	}

	@Override
	public ResourceSets deserializeableResponse(String json) {
		return Constants.GSON.fromJson(json, ResourceSets.class);
	}

	public static class ResourcesGetBuilder extends StateControlledBuilder<ResourcesGet> {

		protected ResourcesGetBuilder() {
			super(new ResourcesGet());
		}

		@Deprecated
		public ResourcesGetBuilder setCultureCode(String cultureCode) {
			this.built.cultureCode = cultureCode;
			return this;
		}

		public ResourcesGetBuilder setCulture(Locale locale) {
			if (locale.getCountry().isEmpty())
				throw new IllegalArgumentException(locale + " | Expected country locale!");
			return this.setCultureCode(locale.toLanguageTag());
		}

		public ResourcesGetBuilder includeSets(ResourceSetNames... setNames) {
			this.built.setNames = setNames;
			return this;
		}

	}

}
