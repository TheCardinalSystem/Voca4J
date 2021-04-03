package com.cardinal.voca4j.impl.youtube;

public class DynamicReadaheadConfig {
	private int maxReadAheadMediaTimeMs;
	private int minReadAheadMediaTimeMs;
	private int readAheadGrowthRateMs;

	public int getMaxReadAheadMediaTimeMs() {
		return this.maxReadAheadMediaTimeMs;
	}

	public int getMinReadAheadMediaTimeMs() {
		return this.minReadAheadMediaTimeMs;
	}

	public int getReadAheadGrowthRateMs() {
		return this.readAheadGrowthRateMs;
	}
}
