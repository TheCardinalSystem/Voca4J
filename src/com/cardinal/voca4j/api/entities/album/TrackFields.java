package com.cardinal.voca4j.api.entities.album;

import java.time.LocalDate;

public class TrackFields {
	private String completeArtistString, trackArtist, featVocalists, pvOriginalNicoNicoDouga,
			pvOriginalNotNicoNicoDouga, pvReprint, title, titleEnglish, titleRomanized, titleOriginal, url;

	private String[] vocalists, producers;
	private int id = -1, lengthSeconds = -1;
	private LocalDate publishDate;

	public String getCompleteArtistString() {
		return completeArtistString;
	}

	public String getTrackArtist() {
		return trackArtist;
	}

	public String getFeatVocalists() {
		return featVocalists;
	}

	public String[] getProducers() {
		return producers;
	}

	public String getPVOriginalNicoNicoDouga() {
		return pvOriginalNicoNicoDouga;
	}

	public String getPVOriginalNotNicoNicoDouga() {
		return pvOriginalNotNicoNicoDouga;
	}

	public String getPVReprint() {
		return pvReprint;
	}

	public String getTitle() {
		return title;
	}

	public String getTitleEnglish() {
		return titleEnglish;
	}

	public String getTitleRomanized() {
		return titleRomanized;
	}

	public String getTitleOriginal() {
		return titleOriginal;
	}

	public String getUrl() {
		return url;
	}

	public String[] getVocalists() {
		return vocalists;
	}

	public int getId() {
		return id;
	}

	public int getLengthSeconds() {
		return lengthSeconds;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

}
