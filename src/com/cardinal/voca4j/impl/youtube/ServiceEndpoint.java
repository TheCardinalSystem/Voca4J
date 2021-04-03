package com.cardinal.voca4j.impl.youtube;

public class ServiceEndpoint {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private FeedbackEndpoint feedbackEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public FeedbackEndpoint getFeedbackEndpoint() {
		return this.feedbackEndpoint;
	}
}
