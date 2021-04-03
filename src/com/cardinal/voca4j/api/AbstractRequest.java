package com.cardinal.voca4j.api;

/**
 * An abstract {@link Request} class that does not require implementations to
 * include the {@link Request#buildRequestBody()} method, but still allows
 * implementation if necessary. The same does for {@link Request#getMethod()},
 * which will default to {@link HttpMethod#GET} unless overridden by a subclass.
 * 
 * @author Cardinal System
 *
 */
public abstract class AbstractRequest implements Request {
	@Override
	public String buildRequestBody() {
		return null;
	}

	@Override
	public HttpMethod getMethod() {
		return HttpMethod.GET;
	}
}
