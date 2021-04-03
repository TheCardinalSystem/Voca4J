package com.cardinal.voca4j.impl;

import java.util.List;

import com.cardinal.voca4j.impl.youtube.Attestation;
import com.cardinal.voca4j.impl.youtube.Message;
import com.cardinal.voca4j.impl.youtube.Microformat;
import com.cardinal.voca4j.impl.youtube.PlayabilityStatus;
import com.cardinal.voca4j.impl.youtube.PlaybackTracking;
import com.cardinal.voca4j.impl.youtube.PlayerConfig;
import com.cardinal.voca4j.impl.youtube.ResponseContext;
import com.cardinal.voca4j.impl.youtube.Storyboard;
import com.cardinal.voca4j.impl.youtube.StreamingData;
import com.cardinal.voca4j.impl.youtube.VideoDetails;
import com.cardinal.voca4j.impl.youtube.VideoQualityPromoSupportedRenderers;

public class YoutubeInfo {
	private ResponseContext responseContext;
	private PlayabilityStatus playabilityStatus;
	private StreamingData streamingData;
	private PlaybackTracking playbackTracking;
	private VideoDetails videoDetails;
	private PlayerConfig playerConfig;
	private Storyboard storyboards;
	private Microformat microformat;
	private String trackingParams;
	private Attestation attestation;
	private VideoQualityPromoSupportedRenderers videoQualityPromoSupportedRenderers;
	private List<Message> messages;

	public ResponseContext getResponseContext() {
		return this.responseContext;
	}

	public PlayabilityStatus getPlayabilityStatus() {
		return this.playabilityStatus;
	}

	public StreamingData getStreamingData() {
		return this.streamingData;

	}

	public PlaybackTracking getPlaybackTracking() {
		return this.playbackTracking;
	}

	public VideoDetails getVideoDetails() {
		return this.videoDetails;
	}

	public PlayerConfig getPlayerConfig() {
		return this.playerConfig;
	}

	public Storyboard getStoryboard() {
		return this.storyboards;
	}

	public Microformat getMicroformat() {
		return this.microformat;
	}

	public String getTrackingParams() {
		return this.trackingParams;
	}

	public Attestation getAttestation() {
		return this.attestation;
	}

	public VideoQualityPromoSupportedRenderers getVideoQualityPromoSupportedRenderers() {
		return this.videoQualityPromoSupportedRenderers;
	}

	public List<Message> getMessages() {
		return this.messages;
	}
}