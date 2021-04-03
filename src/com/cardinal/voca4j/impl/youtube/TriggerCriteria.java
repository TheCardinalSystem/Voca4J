package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class TriggerCriteria {
	private List<String> connectionWhitelists;
	private int joinLatencySeconds;
	private int rebufferTimeSeconds;
	private int watchTimeWindowSeconds;
	private int refractorySeconds;

	public void setConnectionWhitelists(List<String> connectionWhitelists) {
		this.connectionWhitelists = connectionWhitelists;
	}

	public List<String> getConnectionWhitelists() {
		return this.connectionWhitelists;
	}

	public int getJoinLatencySeconds() {
		return this.joinLatencySeconds;
	}

	public int getRebufferTimeSeconds() {
		return this.rebufferTimeSeconds;
	}

	public int getWatchTimeWindowSeconds() {
		return this.watchTimeWindowSeconds;
	}

	public int getRefractorySeconds() {
		return this.refractorySeconds;
	}
}
