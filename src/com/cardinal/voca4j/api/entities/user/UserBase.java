package com.cardinal.voca4j.api.entities.user;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.entities.LinkableEntity;

public class UserBase implements LinkableEntity {
	protected int id = -1;
	protected String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/User/Profile/" + name;
	}
}
