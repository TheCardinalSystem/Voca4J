package com.cardinal.voca4j.impl.niconico;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Tags {
	@XmlElement(name = "tag")
	private List<Tag> tags = new ArrayList<Tag>();
	@XmlAttribute(name = "domain")
	private String domain;

	public List<Tag> getList() {
		return tags;
	}

	public String getDomain() {
		return domain;
	}
}
