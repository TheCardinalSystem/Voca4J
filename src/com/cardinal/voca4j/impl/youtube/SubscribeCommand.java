package com.cardinal.voca4j.impl.youtube;

public class SubscribeCommand {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private SubscribeEndpoint subscribeEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public SubscribeEndpoint getSubscribeEndpoint() {
		return this.subscribeEndpoint;
	}
}
