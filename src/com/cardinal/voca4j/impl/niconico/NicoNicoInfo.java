
package com.cardinal.voca4j.impl.niconico;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nicovideo_thumb_response")
public class NicoNicoInfo {
	@XmlElement(name = "thumb")
	private Thumb thumb;
	@XmlAttribute(name = "status")
	private String status;

	public Thumb getThumb() {
		return thumb;
	}

	public String getStatus() {
		return status;
	}

}
