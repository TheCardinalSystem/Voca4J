package com.cardinal.voca4j.api.entities;

import java.util.List;

public class QueriedList<E> {
	private List<E> items;
	private String term;
	private int totalCount;

	public List<E> get() {
		return items;
	}

	public String getSearchTerm() {
		return term;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
