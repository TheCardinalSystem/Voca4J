package com.cardinal.voca4j.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.ResponseDeserializer;

/**
 * A class which centralizes the components used for deserializing JSON
 * responses from the API.
 * 
 * @author Cardinal System
 *
 */
public class DeserializationContext {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeserializationContext.class);

	private Map<Type, Type> typePairs = new HashMap<Type, Type>();
	private Map<Type, ResponseDeserializer<?>> typeAdapters = new HashMap<Type, ResponseDeserializer<?>>();

	/**
	 * Registers a type adapter for the given request type. This is String consumer
	 * which accepts a JSON string and parses it to the response wrapper that
	 * corresponds to the paired request type.
	 * 
	 * @param requestType          {@linkplain Request} type.
	 * @param responseDeserializer JSON consumer.
	 */
	public void registerTypeAdapter(Type requestType, ResponseDeserializer<?> responseDeserializer) {
		LOGGER.info("Registering type adapter \"" + ((Type) responseDeserializer.getClass()).getTypeName()
				+ "\" for type: " + requestType.getTypeName());
		verifyType(requestType);
		typeAdapters.put(requestType, responseDeserializer);
	}

	/**
	 * Registers a type pair. This is a {@linkplain Request} type and the
	 * corresponding response wrapper type which the results will be parsed to.
	 * 
	 * @param requestType  request type.
	 * @param responseType response wrapper type.
	 */
	public void registerPairType(Type requestType, Type responseType) {
		verifyType(requestType);
		LOGGER.info("Registering type pair: <" + requestType.getTypeName() + ", " + responseType.getTypeName() + ">");
		typePairs.put(requestType, responseType);
	}

	/**
	 * Registers multiple request types to the same type adapter. See
	 * {@linkplain DeserializationContext#registerTypeAdapter(Type, ResponseDeserializer)}.
	 * 
	 * @param responseDeserializer JSON consumer.
	 * @param requestTypes         {@linkplain Request} types.
	 */
	public void registerMultiple(ResponseDeserializer<?> responseDeserializer, Type... requestTypes) {
		for (Type type : requestTypes) {
			registerTypeAdapter(type, responseDeserializer);
		}
	}

	/**
	 * Registers multiple request types to the same response wrapper type. See
	 * {@linkplain DeserializationContext#registerPairType(Type, Type)}.
	 * 
	 * @param responseType response wrapper type.
	 * @param requestTypes request types.
	 */
	public void registerMultiple(Type responseType, Type... requestTypes) {
		for (Type type : requestTypes) {
			registerPairType(type, responseType);
		}
	}

	/**
	 * Deserializes the given JSON response using the response type wrapper or type
	 * adapter mapped to the given request type, and casts it to the given generic
	 * type before returning it.
	 * 
	 * @param <T>         response wrapper generic type.
	 * @param json        JSON repsonse.
	 * @param requestType request type.
	 * @return parsed and cast response wrapper instance.
	 */
	@SuppressWarnings("unchecked")
	public <T> T deserializeTypeKnown(String json, Type requestType) {
		LOGGER.info("Deserializing type: " + requestType.getTypeName());
		if (typeAdapters.containsKey(requestType)) {
			T t = (T) typeAdapters.get(requestType).deserializeableResponse(json);
			LOGGER.info("Deserialized to type: " + ((Type) t.getClass()).getTypeName());
			return t;
		} else if (typePairs.containsKey(requestType)) {
			T t = Constants.GSON.fromJson(json, typePairs.get(requestType));
			LOGGER.info("Deserialized to type: " + ((Type) t.getClass()).getTypeName());
			return t;
		}
		throw new IllegalArgumentException("No type adapter for " + requestType.getTypeName());
	}

	/**
	 * Deserializes the given JSON response using the response type wrapper or type
	 * adapter mapped to the given request type, then returns the results.
	 * 
	 * @param json        the response JSON.
	 * @param requestType the request type.
	 * @return the parsed but not cast response wrapper instance.
	 */
	public Object deserializeTypeAmbiguous(String json, Type requestType) {
		return deserializeTypeKnown(json, requestType);
	}

	/**
	 * Checks to see if a type adapter is registered for the given request type.
	 * 
	 * @param requestType request type.
	 * @return <b>true</b> - a type adapter is registered for the given request
	 *         type.<br>
	 * 
	 *         <b>false</b> - a type adapter is not registered for the given request
	 *         type.
	 */
	public boolean isTypeAdapterRegistered(Type requestType) {
		return typeAdapters.containsKey(requestType);
	}

	/**
	 * Checks to see if a respones wrapper type is registered for the given request
	 * type.
	 * 
	 * @param requestType request type.
	 * @return <b>true</b> - a respones wrapper type is registered for the given
	 *         request type.<br>
	 * 
	 *         <b>false</b> - a respones wrapper type is not registered for the
	 *         given request type.
	 */
	public boolean isTypePairRegistered(Type requestType) {
		return typePairs.containsKey(requestType);
	}

	/**
	 * Checks if there any type adapters or respones wrapper types registered to the
	 * given request type.
	 * 
	 * @param requestType request type.
	 * @return <b>true</b> - a type adapter or respones wrapper type is registered
	 *         for the given request type.<br>
	 * 
	 *         <b>false</b> - a type adapter or respones wrapper type is not
	 *         registered for the given request type.
	 */
	public boolean isTypeRegistered(Type requestType) {
		return isTypePairRegistered(requestType) || isTypeAdapterRegistered(requestType);
	}

	private void verifyType(Type requestType) {
		String s = requestType.getTypeName();
		if (s.toLowerCase().endsWith("builder"))
			throw new IllegalArgumentException(s + " is a builder type!");
	}
}
