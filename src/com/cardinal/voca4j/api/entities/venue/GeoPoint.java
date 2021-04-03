package com.cardinal.voca4j.api.entities.venue;

public class GeoPoint {

	public String formatted;
	private boolean hasValue;
	private double latitude, longitude;

	public String getFormatted() {
		return formatted;
	}

	public boolean hasValue() {
		return hasValue;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

}
