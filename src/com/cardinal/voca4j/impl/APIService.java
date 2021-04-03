package com.cardinal.voca4j.impl;

import java.io.IOException;
import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.Response;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.lang.HTTPException;
import com.cardinal.voca4j.util.APIBridge;
import com.cardinal.voca4j.util.TaskExectuorService;

/**
 * A class used to centralize and control the components used to interface with
 * the API.
 * 
 * @author Cardinal System
 *
 */
public class APIService {

	private DeserializationContext deserializationContext;
	private APIBridge apiBridge;

	public APIService() {
		deserializationContext = new DeserializationContext();
		apiBridge = new APIBridge();
	}

	/**
	 * Gets the {@link DeserializationContext} which this class uses to parse API
	 * responses.
	 * 
	 * @return deserialization context.
	 */
	public DeserializationContext getDeserializationContext() {
		return deserializationContext;
	}

	/**
	 * Changes the {@link DeserializationContext} this class uses to parse API
	 * responses (you you feel like messing around).
	 * 
	 * @param deserializationContext deserialization context.
	 */
	public void setDeserializationContext(DeserializationContext deserializationContext) {
		this.deserializationContext = deserializationContext;
	}

	/**
	 * Gets the {@link APIBridge}. This is the class that communicates directly
	 * between the API and the wrapper.
	 * 
	 * @return the API bridge.
	 */
	public APIBridge getApiBridge() {
		return apiBridge;
	}

	/**
	 * Processes the given {@link Request}, sends it to the API, and returns the
	 * response.
	 * 
	 * @param request the request.
	 * @return the {@link Response}.
	 */
	public Response complete(Request request) {
		try {
			return apiBridge.complete(request);
		} catch (IOException | HTTPException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Processes the given {@linkplain Request}, sends it to the API, recieves the
	 * response, parses it using this instance's
	 * {@linkplain DeserializationContext}, and returns the resulting object.
	 * 
	 * @param request the request.
	 * @return the parsed response.
	 */
	public Object completeAndParse(Request request) {
		Response response = complete(request);

		if (response == null)
			return null;

		String data = new String(response.getData(), Constants.contentEncoding);
		Type type = request.getClass();
		if (deserializationContext.isTypeRegistered(type)) {
			return deserializationContext.deserializeTypeAmbiguous(data, type);
		} else if (request instanceof ResponseDeserializer) {
			deserializationContext.registerTypeAdapter(type, (ResponseDeserializer<?>) request);
			return deserializationContext.deserializeTypeAmbiguous(data, type);
		} else {
			return response;
		}
	}

	/**
	 * Processes the given {@linkplain Request}, sends it to the API, recieves the
	 * response, parses it using this instance's
	 * {@linkplain DeserializationContext}, and casts the returns the resulting
	 * object to the provided generic type before returning it.
	 * 
	 * @param <T>     parsed response type.
	 * @param request the request.
	 * @return the parsed response.
	 */
	@SuppressWarnings("unchecked")
	public <T> T completeAndParseGenerically(Request request) {
		Response response = complete(request);

		if (response == null)
			return null;

		String data = new String(response.getData(), Constants.contentEncoding);
		Type type = request.getClass();
		if (deserializationContext.isTypeRegistered(type)) {
			return (T) deserializationContext.deserializeTypeAmbiguous(data, type);
		} else if (request instanceof ResponseDeserializer) {
			deserializationContext.registerTypeAdapter(type, (ResponseDeserializer<T>) request);
			return (T) deserializationContext.deserializeTypeAmbiguous(data, type);
		} else {
			return null;
		}
	}

	/**
	 * Creates a concurrent task which achieves the results as
	 * {@linkplain APIService#complete(Request)}. This task can be executed from a
	 * {@linkplain TaskExectuorService}.
	 * 
	 * @param request the request.
	 * @return the task.
	 */
	public CompleteTask createCompleteTask(Request request) {
		return new CompleteTask(request, this);
	}

	/**
	 * Creates a concurrent task which achieves the results as
	 * {@linkplain APIService#completeAndParse(Request)}. This task can be executed
	 * from a {@linkplain TaskExectuorService}.
	 * 
	 * @param request the request.
	 * @return the task.
	 */
	public CompleteAndParseTask createCompleteAndParseTask(Request request) {
		return new CompleteAndParseTask(request, this);
	}
}
