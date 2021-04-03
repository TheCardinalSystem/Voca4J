package com.cardinal.voca4j.impl.youtube;

public class AddToWatchLaterCommand {
	private String clickTrackingParams;
	private CommandMetadata commandMetadata;
	private PlaylistEditEndpoint playlistEditEndpoint;

	public String getClickTrackingParams() {
		return this.clickTrackingParams;
	}

	public CommandMetadata getCommandMetadata() {
		return this.commandMetadata;
	}

	public PlaylistEditEndpoint getPlaylistEditEndpoint() {
		return this.playlistEditEndpoint;
	}
}
