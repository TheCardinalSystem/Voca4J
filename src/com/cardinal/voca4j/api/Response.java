package com.cardinal.voca4j.api;

import java.util.List;
import java.util.Map;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.util.HTTPUtils;

public class Response {

	private Map<String, List<String>> headers;
	private int httpStatusCode;
	private String httpMessage, contentEncoding;
	private int contentLength;
	private byte[] data;

	public Response(Map<String, List<String>> headers, int statusCode, String contentEncoding, int contentLength,
			byte[] data) {
		this.headers = headers;
		this.httpStatusCode = statusCode;
		this.httpMessage = HTTPUtils.getStatusMessage(statusCode);
		this.contentEncoding = contentEncoding;
		this.contentLength = contentLength;
		this.data = data;
	}

	public Map<String, List<String>> getHeaders() {
		return headers;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getHttpMessage() {
		return httpMessage;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public int getContentLength() {
		return contentLength;
	}

	public byte[] getData() {
		return data;
	}

	@Override
	public String toString() {
		if (data != null && data.length > 0)
			return new String(data, Constants.contentEncoding);
		return super.toString();
	}

}
