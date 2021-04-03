package com.cardinal.voca4j.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.Request;
import com.cardinal.voca4j.api.Response;
import com.cardinal.voca4j.lang.HTTPException;

/**
 * A utility class for performing various API communications directly between
 * the API and the wrapper.<br>
 * <br>
 * API Reference: <a href=
 * "https://vocadb.net/swagger/ui/index#/">https://vocadb.net/swagger/ui/index#/</a>
 * <br>
 * <br>
 * <b>Previous name:</b> APIUtils.java
 * 
 * @author Cardinal System
 *
 */
public class APIBridge {

	private static final Logger LOGGER = LoggerFactory.getLogger(APIBridge.class);

	/**
	 * Completes the given request and returns the response.
	 * 
	 * @param request the request to be completed.
	 * @return the server response.
	 * @throws IOException   URL is malformed
	 * 
	 *                       <p style='padding-left: 12%'>
	 *                       - If an I/O error occurs while opening the URL
	 *                       connection
	 *                       </p>
	 *                       <p style='padding-left: 12%'>
	 *                       - If the timeout expires before the URL connection can
	 *                       be established
	 *                       </p>
	 *                       <p style='padding-left: 12%'>
	 *                       - If the URL protocol does not support input.
	 *                       </p>
	 * @throws IOException   there was an error completing the request.
	 * @throws HTTPException there was an error completing the request.
	 */
	public Response complete(Request request) throws IOException, HTTPException {
		HttpsURLConnection connection = connect(request);

		LOGGER.info("Reading body");
		byte[] data;
		try {
			data = InputStreamUtils.toByteArray(connection.getInputStream());
		} catch (Exception e) {
			throw new HTTPException(connection, e);
		}
		LOGGER.info("Disconnecting");
		connection.disconnect();
		LOGGER.info("Disconnected");

		return new Response(connection.getHeaderFields(), connection.getResponseCode(), connection.getContentEncoding(),
				connection.getContentLength(), data);
	}

	/**
	 * Completes the given request and returns the response.
	 * 
	 * @param request  the request to be completed.
	 * 
	 * @param username your VocaDB username
	 * @param password your VocaDB password
	 * @return the server response.
	 * @throws IOException   URL is malformed
	 * 
	 *                       <p style='padding-left: 12%'>
	 *                       - If an I/O error occurs while opening the URL
	 *                       connection
	 *                       </p>
	 *                       <p style='padding-left: 12%'>
	 *                       - If the timeout expires before the URL connection can
	 *                       be established
	 *                       </p>
	 *                       <p style='padding-left: 12%'>
	 *                       - If the URL protocol does not support input.
	 *                       </p>
	 * @throws IOException   there was an error completing the request.
	 * @throws HTTPException there was an error completing the request.
	 */
	public Response complete(Request request, String username, String password) throws IOException, HTTPException {
		HttpsURLConnection connection = connect(request, username, password);

		LOGGER.info("Reading body");
		byte[] data;
		try {
			data = InputStreamUtils.toByteArray(connection.getInputStream());
		} catch (Exception e) {
			throw new HTTPException(connection, e);
		}
		LOGGER.info("Disconnecting");
		connection.disconnect();
		LOGGER.info("Disconnected");

		return new Response(connection.getHeaderFields(), connection.getResponseCode(), connection.getContentEncoding(),
				connection.getContentLength(), data);
	}

	/**
	 * Connects to the given request URL and returns the connection.
	 * 
	 * @param request the request.
	 * @return the connection.
	 * @throws IOException URL is malformed
	 * 
	 *                     <p style='padding-left: 12%'>
	 *                     - If an I/O error occurs while opening the URL connection
	 *                     </p>
	 *                     <p style='padding-left: 12%'>
	 *                     - If the timeout expires before the URL connection can be
	 *                     established
	 *                     </p>
	 *                     <p style='padding-left: 12%'>
	 *                     - If the URL protocol does not support input.
	 *                     </p>
	 * @throws IOException there was an error connecting to the request URL.
	 */
	public HttpsURLConnection connect(Request request) throws IOException {
		HttpsURLConnection connection = prepareConnection(request);
		LOGGER.info("Connecting");
		connection.connect();
		LOGGER.info("Connected");

		return connection;
	}

	/**
	 * Connects to the given request URL and returns the connection.
	 * 
	 * @param request  the request.
	 * 
	 * @param username your VocaDB username.
	 * @param password your VocaDB password.
	 * @return the server response, in JSON format.
	 * @throws IOException URL is malformed
	 * 
	 *                     <p style='padding-left: 12%'>
	 *                     - If an I/O error occurs while opening the URL connection
	 *                     </p>
	 *                     <p style='padding-left: 12%'>
	 *                     - If the timeout expires before the URL connection can be
	 *                     established
	 *                     </p>
	 *                     <p style='padding-left: 12%'>
	 *                     - If the URL protocol does not support input.
	 *                     </p>
	 * 
	 * @throws IOException there was an error connecting to the request URL.
	 */
	public HttpsURLConnection connect(Request request, String username, String password) throws IOException {
		HttpsURLConnection connection = prepareConnection(request);
		connection.addRequestProperty("UserName", username);
		connection.addRequestProperty("Password", password);
		connection.addRequestProperty("KeepLoggedIn", "true");

		LOGGER.info("Connecting");
		connection.connect();
		LOGGER.info("Connected");

		return connection;
	}

	/**
	 * Prepares an {@link HttpsURLConnection} to execute the given {@link Request}
	 * without actually calling {@link HttpsURLConnection#connect()}.
	 * 
	 * @param request the request.
	 * @return the (not yet connected) connection.
	 * @throws IOException there was an error.
	 */
	private HttpsURLConnection prepareConnection(Request request) throws IOException {
		String url = request.buildRequestURL();
		LOGGER.info("Preparing connection to: " + url);
		URL urlObj = new URL(url);
		HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
		connection.setRequestMethod(request.getMethod().name());
		connection.addRequestProperty("Accept", "application/json");
		connection.addRequestProperty("user-agent", Constants.userAgent);

		String body = request.buildRequestBody();
		if (body != null) {
			connection.setDoOutput(true);
			OutputStream out = connection.getOutputStream();
			OutputStreamWriter writer = new OutputStreamWriter(out, Constants.contentEncoding);
			writer.write(body);
			writer.flush();
			writer.close();
			out.close();
		}

		return connection;
	}
}
