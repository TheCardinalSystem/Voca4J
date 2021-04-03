package com.cardinal.voca4j.api;

public class AdvancedFilters {
	private String type, param, desc;
	private Boolean negate;

	protected AdvancedFilters(String type, String param, String desc, Boolean negate) {
		this.type = type;
		this.param = param;
		this.desc = desc;
		this.negate = negate;
	}

	public String getFilterType() {
		return type;
	}

	public String getParameter() {
		return param;
	}

	public String getDescription() {
		return desc;
	}

	public boolean shouldNegate() {
		return negate;
	}

}
