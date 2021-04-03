package com.cardinal.voca4j.impl.youtube;

public class AdaptiveFormat {
	private int itag;
	private String mimeType;
	private int bitrate;
	private int width;
	private int height;
	private InitRange initRange;
	private IndexRange indexRange;
	private String lastModified;
	private String contentLength;
	private String quality;
	private int fps;
	private String qualityLabel;
	private String projectionType;
	private int averageBitrate;
	private String approxDurationMs;
	private String signatureCipher;

	public int getItag() {
		return this.itag;
	}

	public String getMimeType() {
		return this.mimeType;
	}

	public int getBitrate() {
		return this.bitrate;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public InitRange getInitRange() {
		return this.initRange;
	}

	public IndexRange getIndexRange() {
		return this.indexRange;
	}

	public String getLastModified() {
		return this.lastModified;
	}

	public String getContentLength() {
		return this.contentLength;
	}

	public String getQuality() {
		return this.quality;
	}

	public int getFps() {
		return this.fps;
	}

	public String getQualityLabel() {
		return this.qualityLabel;
	}

	public String getProjectionType() {
		return this.projectionType;
	}

	public int getAverageBitrate() {
		return this.averageBitrate;
	}

	public String getApproxDurationMs() {
		return this.approxDurationMs;
	}

	public String getSignatureCipher() {
		return this.signatureCipher;
	}
}
