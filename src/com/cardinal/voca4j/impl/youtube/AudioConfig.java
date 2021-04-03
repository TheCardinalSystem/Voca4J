package com.cardinal.voca4j.impl.youtube;

public class AudioConfig {
	private double loudnessDb;
	private double perceptualLoudnessDb;
	private boolean enablePerFormatLoudness;

	public double getLoudnessDb() {
		return this.loudnessDb;
	}

	public double getPerceptualLoudnessDb() {
		return this.perceptualLoudnessDb;
	}

	public boolean getEnablePerFormatLoudness() {
		return this.enablePerFormatLoudness;
	}
}
