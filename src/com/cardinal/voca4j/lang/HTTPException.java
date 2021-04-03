package com.cardinal.voca4j.lang;

import java.io.IOException;

import javax.net.ssl.HttpsURLConnection;

import com.cardinal.voca4j.util.HTTPUtils;

public class HTTPException extends Exception {

	private static final long serialVersionUID = -504788895774392953L;
	private int code;

	public HTTPException(HttpsURLConnection connection) throws IOException {
		this(connection.getResponseCode(), connection);
	}

	private HTTPException(int code, HttpsURLConnection connection) {
		super("Server returned HTTP response code: " + code + " \"" + HTTPUtils.getStatusMessage(code) + "\" for URL: "
				+ connection.getURL());
		this.code = code;
	}

	private HTTPException(int code, HttpsURLConnection connection, Throwable cause) {
		super("Server returned HTTP response code: " + code + " \"" + HTTPUtils.getStatusMessage(code) + "\" for URL: "
				+ connection.getURL(), cause);
		this.code = code;
	}

	public HTTPException(HttpsURLConnection connection, Throwable cause) throws IOException {
		this(connection.getResponseCode(), connection, cause);
	}

	public int getCode() {
		return code;
	}

}
