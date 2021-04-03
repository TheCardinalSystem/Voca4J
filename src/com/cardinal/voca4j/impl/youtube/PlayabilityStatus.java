package com.cardinal.voca4j.impl.youtube;

public class PlayabilityStatus {
	private String status;
	private boolean playableInEmbed;
	private Miniplayer miniplayer;
	private String contextParams;

	public String getStatus() {
		return this.status;
	}

	public boolean getPlayableInEmbed() {
		return this.playableInEmbed;
	}

	public Miniplayer getMiniplayer() {
		return this.miniplayer;
	}

	public String getContextParams() {
		return this.contextParams;
	}
}
