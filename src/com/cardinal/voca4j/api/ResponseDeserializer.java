package com.cardinal.voca4j.api;

/**
 * A template for a class which deserializes a response to a given
 * {@link Request}.
 * 
 * @author Cardinal System
 *
 * @param <T> deserialized repsonse type.
 */
public interface ResponseDeserializer<T> {

	/**
	 * Deserializes the response.
	 * 
	 * @param json the repsonse JSON.
	 * @return deserialized response type.
	 */
	public T deserializeableResponse(String json);

}
