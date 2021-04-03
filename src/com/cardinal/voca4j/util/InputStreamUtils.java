package com.cardinal.voca4j.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputStreamUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(InputStreamUtils.class);

	/**
	 * A convience method to read all the bytes from an input stream. This method
	 * closes the stream to prevent resource leaks.
	 * 
	 * @param stream input stream.
	 * @return bytes.
	 * @throws IOException some went wrong. Uh oh!
	 */
	public static byte[] toByteArray(InputStream stream) throws IOException {
		LOGGER.info("Reading InputStream to byte array");
		ByteArrayOutputStream boas = new ByteArrayOutputStream();
		copy(stream, boas, 2500);
		stream.close();
		return boas.toByteArray();
	}

	/**
	 * Reads all the bytes from the given input stream into a String with the given
	 * encoding.<br>
	 * <br>
	 * NOTE: This method does not close the stream.
	 * 
	 * @param is      the input stream.
	 * @param charset the String encoding charset.
	 * @return the String.
	 * @throws IOException something went wrong. Uh oh!
	 */
	public static String toString(InputStream is, Charset charset) throws IOException {
		return toString(is, charset.name());
	}

	/**
	 * Reads all the bytes from the given input stream into a String with the given
	 * encoding.<br>
	 * <br>
	 * NOTE: This method does not close the stream.
	 * 
	 * @param is      the input stream.
	 * @param charset the String encoding charset.
	 * @return the String.
	 * @throws IOException something went wrong. Uh oh!
	 */
	public static String toString(InputStream is, String charset) throws IOException {
		LOGGER.info("Reading InputStream to " + charset + " String");
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		copy(is, stream, 2048);
		String s = stream.toString(charset);
		stream.close();
		return s;
	}

	/**
	 * Reads all the bytes from the given input stream into a String.<br>
	 * <br>
	 * NOTE: This method does not close the stream.
	 * 
	 * @param is the input stream.
	 * @return the String.
	 * @throws IOException something went wrong. Uh oh!
	 */
	public static String toString(InputStream is) throws IOException {
		LOGGER.info("Reading InputStream to String");
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		copy(is, stream, 2048);
		String s = stream.toString();
		stream.close();
		return s;
	}

	/**
	 * Reads all the bytes from the given input stream into the given output stream
	 * at a buffered rate.<br>
	 * <br>
	 * NOTE: This method does not close either streams. <br>
	 * <br>
	 * Source: <a href=
	 * 'https://stackoverflow.com/a/11741948/5645656'>https://stackoverflow.com/a/11741948/5645656</a>
	 * 
	 * @param input      the input.
	 * @param output     the output.
	 * @param bufferSize the buffer size.
	 * @throws IOException something bad happened. Uh oh!
	 */
	public static void copy(InputStream input, OutputStream output, int bufferSize) throws IOException {
		LOGGER.info("Copying: " + input.getClass().getName() + " >> "
				+ output.getClass().getName());
		byte[] buf = new byte[bufferSize];
		int bytesRead = input.read(buf);
		while (bytesRead != -1) {
			output.write(buf, 0, bytesRead);
			bytesRead = input.read(buf);
		}
		LOGGER.info("Flushing");
		output.flush();
	}
}
