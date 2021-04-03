package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class ServiceTrackingParams {
	private String service;
	private List<Param> params;

	public String getService() {
		return this.service;
	}

	public void setParams(List<Param> params) {
		this.params = params;
	}

	public List<Param> getParams() {
		return this.params;
	}
}
