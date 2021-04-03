package com.cardinal.voca4j.impl.niconico;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Tag {
	@XmlAttribute(name = "category")
	private Integer category;
	@XmlAttribute(name = "lock")
	private Integer lock;
	@XmlValue
	public String stringValue;

	public int getCategory() {
		return category;
	}

	public int getLock() {
		return lock;
	}

	public String getName() {
		return stringValue;
	}

	@Override
	public String toString() {
		return getName();
	}
}
