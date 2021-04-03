package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class UnsubscribeEndpoint {
	private List<String> channelIds;
	private String params;

	public void setChannelIds(List<String> channelIds) {
		this.channelIds = channelIds;
	}

	public List<String> getChannelIds() {
		return this.channelIds;
	}

	public String getParams() {
		return this.params;
	}
}
