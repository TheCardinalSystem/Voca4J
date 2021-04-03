package com.cardinal.voca4j.api;

import java.io.UnsupportedEncodingException;

import com.cardinal.voca4j.Constants;

/**
 * A template representing a request made to the API.
 * 
 * @author Cardinal System
 *
 */
public interface Request {

	/**
	 * Gets the {@link HttpMethod} used for this request.
	 * 
	 * @return this HTTP method.
	 */
	public HttpMethod getMethod();

	/**
	 * Builds the URL represented by this request using the request fields.
	 * 
	 * @return the URL.
	 * @throws UnsupportedEncodingException the URL encoding is not support (should
	 *                                      never happen).
	 * @see Constants#rootAPIURL
	 */
	public String buildRequestURL() throws UnsupportedEncodingException;

	/**
	 * Builds the <a href="https://en.wikipedia.org/wiki/HTTP_message_body">request
	 * body</a> for this HTTP request.
	 * 
	 * @return the body.
	 */
	public String buildRequestBody();

	/**
	 * Creates a copy of this object.
	 * 
	 * @return a copy.
	 */
	abstract Request copy();

	/**
	 * An enumeration that represents <a href=
	 * 'https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods'>HTTP
	 * request methods</a>.
	 * 
	 * @author Cardinal System
	 *
	 */
	public enum HttpMethod {
		GET, HEAD, POST, PUT, PATCH, DELETE, CONNECT, OPTIONS, TRACE;
	}

}
