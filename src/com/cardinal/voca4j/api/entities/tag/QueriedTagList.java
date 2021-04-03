package com.cardinal.voca4j.api.entities.tag;

import java.util.List;

public class QueriedTagList {

	private List<Tag> items;
	private int totalCount;
	private String term;

	public List<Tag> get() {
		return items;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String getSearchTerm() {
		return term;
	}

}
