package com.cardinal.voca4j.impl.youtube;

public class UnsubscribeCommand {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private UnsubscribeEndpoint unsubscribeEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public UnsubscribeEndpoint getUnsubscribeEndpoint() {
		return this.unsubscribeEndpoint;
	}
}
