package com.cardinal.voca4j.impl.youtube;

public class NavigationEndpoint {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private UrlEndpoint urlEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public UrlEndpoint getUrlEndpoint() {
		return this.urlEndpoint;
	}
}
