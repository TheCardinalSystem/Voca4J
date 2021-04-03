package com.cardinal.voca4j.impl;

import java.io.IOException;

import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.Response;
import com.cardinal.voca4j.lang.HTTPException;
import com.cardinal.voca4j.util.TaskExectuorService;
import com.cardinal.voca4j.util.TaskExectuorService.Task;

public class CompleteTask implements Task {

	private Request request;
	private APIService apiService;

	public CompleteTask(Request request, APIService apiService) {
		this.request = request;
		this.apiService = apiService;
	}

	@Override
	public Response execute(TaskExectuorService executor) throws Exception {
		try {
			return apiService.getApiBridge().complete(request);
		} catch (IOException | HTTPException e) {
			e.printStackTrace();
		}
		return null;
	}

}
