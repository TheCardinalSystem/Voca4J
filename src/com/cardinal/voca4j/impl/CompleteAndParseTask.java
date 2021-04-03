package com.cardinal.voca4j.impl;

import java.io.IOException;
import java.lang.reflect.Type;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.Response;
import com.cardinal.voca4j.api.ResponseDeserializer;
import com.cardinal.voca4j.lang.HTTPException;
import com.cardinal.voca4j.util.TaskExectuorService;
import com.cardinal.voca4j.util.TaskExectuorService.Task;

public class CompleteAndParseTask implements Task {
	private Request request;
	private APIService service;

	public CompleteAndParseTask(Request request, APIService service) {
		this.request = request;
		this.service = service;
	}

	@Override
	public Object execute(TaskExectuorService executor) throws Exception {
		Response response = null;
		try {
			response = service.getApiBridge().complete(request);
		} catch (IOException | HTTPException e) {
			e.printStackTrace();
			return null;
		}

		String data = new String(response.getData(), Constants.contentEncoding);
		Type type = request.getClass();
		if (service.getDeserializationContext().isTypeRegistered(type)) {
			return service.getDeserializationContext().deserializeTypeAmbiguous(data, type);
		} else if (request instanceof ResponseDeserializer) {
			service.getDeserializationContext().registerTypeAdapter(type, (ResponseDeserializer<?>) request);
			return service.getDeserializationContext().deserializeTypeAmbiguous(data, type);
		} else {
			return response;
		}
	}

}
