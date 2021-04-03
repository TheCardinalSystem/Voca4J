package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class PlaylistEditEndpoint {
	private String playlistId;
	private List<Action> actions;

	public String getPlaylistId() {
		return this.playlistId;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<Action> getActions() {
		return this.actions;
	}
}
