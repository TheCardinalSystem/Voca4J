package com.cardinal.voca4j.impl.youtube;

import java.util.List;

public class StreamingData {
	private String expiresInSeconds;
	private List<Format> formats;
	private List<AdaptiveFormat> adaptiveFormats;

	public String getExpiresInSeconds() {
		return this.expiresInSeconds;
	}

	public void setFormats(List<Format> formats) {
		this.formats = formats;
	}

	public List<Format> getFormats() {
		return this.formats;
	}

	public void setAdaptiveFormats(List<AdaptiveFormat> adaptiveFormats) {
		this.adaptiveFormats = adaptiveFormats;
	}

	public List<AdaptiveFormat> getAdaptiveFormats() {
		return this.adaptiveFormats;
	}
}
