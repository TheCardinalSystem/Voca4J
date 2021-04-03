package com.cardinal.voca4j.impl.youtube;

public class PlayerConfig {
	private AudioConfig audioConfig;
	private StreamSelectionConfig streamSelectionConfig;
	private DaiConfig daiConfig;
	private MediaCommonConfig mediaCommonConfig;
	private WebPlayerConfig webPlayerConfig;

	public AudioConfig getAudioConfig() {
		return this.audioConfig;
	}

	public StreamSelectionConfig getStreamSelectionConfig() {
		return this.streamSelectionConfig;
	}

	public DaiConfig getDaiConfig() {
		return this.daiConfig;
	}

	public MediaCommonConfig getMediaCommonConfig() {
		return this.mediaCommonConfig;
	}

	public WebPlayerConfig getWebPlayerConfig() {
		return this.webPlayerConfig;
	}
}
