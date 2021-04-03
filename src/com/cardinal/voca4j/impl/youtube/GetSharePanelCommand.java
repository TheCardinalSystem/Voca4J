package com.cardinal.voca4j.impl.youtube;

public class GetSharePanelCommand {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private WebPlayerShareEntityServiceEndpoint webPlayerShareEntityServiceEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public WebPlayerShareEntityServiceEndpoint getWebPlayerShareEntityServiceEndpoint() {
		return this.webPlayerShareEntityServiceEndpoint;
	}
}
