package com.cardinal.voca4j.api.entities.tag;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.entities.LinkableEntity;

public class TagBase implements LinkableEntity {

	protected String additionalNames[], categoryName, name, urlSlug;
	protected int id = -1;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/T/" + id + "/" + urlSlug;
	}

}
