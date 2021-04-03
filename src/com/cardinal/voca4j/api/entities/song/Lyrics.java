package com.cardinal.voca4j.api.entities.song;

public class Lyrics {

	private String cultureCode, source, url, value;
	private int id = -1;
	private TranslationType translationType;

	public int getId() {
		return id;
	}

	public String getCultureCode() {
		return cultureCode;
	}

	public String getSource() {
		return source;
	}

	public String getUrl() {
		return url;
	}

	public TranslationType getTranslationType() {
		return translationType;
	}

	public String getValue() {
		return value;
	}

}
