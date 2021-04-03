package com.cardinal.voca4j.api.song;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.impl.YoutubeInfo;
import com.cardinal.voca4j.impl.niconico.NicoNicoInfo;
import com.cardinal.voca4j.util.InputStreamUtils;

public enum PVService {
	Nothing,
	/**
	 * Same as NicoVideo
	 */
	NicoNicoDouga, Youtube, SoundCloud, Vimeo, Piapro, Bilibili, File, LocalFile, Creofuga, Bandcamp;

	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.182 Safari/537.36";
	private static final Pattern SERVICE_PATTERN = Pattern.compile(
			"(?<=^https{0,1}\\:\\/\\/(www\\.){0,1})(nicovideo|youtu\\.{0,1}be|soundcloud|vimeo|piapro|bilibili|creofuga|\\w+\\.bandcamp)"),
			URL_PATTERN = Pattern.compile(
					"https{0,1}\\:\\/\\/(www\\.){0,1}(nicovideo|youtu\\.{0,1}be|soundcloud|vimeo|piapro|bilibili|creofuga|\\w+\\.bandcamp)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]"),
			YOUTUBE_ID_PATTERN = Pattern.compile("(?<=v\\=|youtu\\.be\\/)\\w+"),
			NICONICODOUGA_ID_PATTERN = Pattern.compile("(?<=watch\\/|nico\\.ms\\/)\\w+"),
			VIMEO_ID_PATTERN = Pattern.compile("(?<=vimeo\\.com\\/)\\w+"),
			PIAPRO_ID_URL_PATTERN = Pattern.compile("(?<=piapro.jp\\/content\\/)\\w+"),
			PIAPRO_ID_COOKIE_PATTERN = Pattern.compile("(?<=contents\\_display\\_history\\=)\\w+?(?=\\;)"),
			BILILBILI_ID_PATTERN = Pattern.compile("(?<=bilibili\\.com\\/video\\/av)\\d+"),
			CREOFUGA_ID_PATTERN = Pattern.compile("(?<=creofuga\\.net\\/audios\\/)\\w+"),
			BANDCAMP_ID_PATTERN = Pattern.compile("(?<=bandcamp\\.com\\/EmbeddedPlayer\\/v\\=\\d\\/track\\=)\\d+");

	private static final HashMap<String, PVService> SERVICES = new HashMap<String, PVService>();

	static {
		SERVICES.put("nicovideo", NicoNicoDouga);
		SERVICES.put("youtube", Youtube);
		SERVICES.put("soundcloud", SoundCloud);
		SERVICES.put("vimeo", Vimeo);
		SERVICES.put("piapro", Piapro);
		SERVICES.put("bilibili", Bilibili);
		SERVICES.put("creofuga", Creofuga);
		SERVICES.put("bandcamp", Bandcamp);
	}

	public static List<String> findPVUrls(String string) {
		Matcher m = URL_PATTERN.matcher(string);
		List<String> urls = new ArrayList<String>();
		while (m.find()) {
			urls.add(m.group());
		}
		return urls;
	}

	/**
	 * Gets the PV Service associated with the given URL.
	 * 
	 * @param url URL.
	 * @return the PV Service.
	 */
	public static PVService getService(String url) {
		Matcher m = SERVICE_PATTERN.matcher(url);
		if (!m.find())
			throw new IllegalArgumentException(url + " does not belong to a registered PV Service.");

		String key = m.group();
		if (key.indexOf(".") > -1) {
			key = key.replaceAll("\\.", "");
		}

		return SERVICES.get(key);
	}

	/**
	 * Gets the ID for the given PV from the PV URL.
	 * 
	 * @param pvUrl PV URL.
	 * @return PV ID.
	 * @throws IOException there was a problem loading the webpage at the given URL
	 *                     (some URL's require the page to be loaded to retrieve the
	 *                     ID).
	 */
	public static String getPvId(String pvUrl) throws IOException {
		return getService(pvUrl).getID(pvUrl);
	}

	/**
	 * Gets the PV ID for the given PV URL.
	 * 
	 * @param url URL.
	 * @return ID.
	 * @throws IOException there was a problem loading the webpage at the given URL
	 *                     (some URL's require the page to be loaded to retrieve the
	 *                     ID).
	 */
	public String getID(String url) throws IOException {
		switch (this) {
		case NicoNicoDouga: {
			Matcher m = NICONICODOUGA_ID_PATTERN.matcher(url);
			if (!m.find())
				throw new IllegalArgumentException("Invalid NicoNicoDouga URL.");
			return m.group();
		}
		case SoundCloud: {
			throw new IllegalArgumentException("Cannot get SoundCloud IDs from URLs.");
		}
		case Youtube: {
			Matcher m = YOUTUBE_ID_PATTERN.matcher(url);
			if (!m.find())
				throw new IllegalArgumentException("Invalid YouTube URL.");
			return m.group();
		}
		case Vimeo: {
			Matcher m = VIMEO_ID_PATTERN.matcher(url);
			if (!m.find())
				throw new IllegalArgumentException("Invalid Vimeo URL.");
			return m.group();
		}
		case Piapro: {
			return getPiaproID(url);
		}
		case Bilibili: {
			return getBilibilitID(url);
		}
		case Creofuga: {
			Matcher m = CREOFUGA_ID_PATTERN.matcher(url);
			if (!m.find())
				throw new IllegalArgumentException("Invalid Creofuga URL.");
			return m.group();
		}
		case Bandcamp: {
			return getBandcampID(url);
		}
		default:
			throw new IllegalStateException(name() + " does not provide IDs.");
		}
	}

	private static String getPiaproID(String url) throws IOException {
		if (!url.matches("(https{0,1}\\:\\/\\/){0,1}piapro\\.jp.+")) {
			throw new IllegalArgumentException("Invalid Piapro URL.");
		}

		Matcher m = PIAPRO_ID_URL_PATTERN.matcher(url);
		if (m.find()) {
			return m.group();
		}

		URL url1 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
		connection.setRequestProperty("user-agent", USER_AGENT);
		connection.connect();

		String cookie = connection.getHeaderField("Set-Cookie");
		m = PIAPRO_ID_COOKIE_PATTERN.matcher(cookie);
		if (m.find())
			return m.group();

		throw new IllegalArgumentException("Invalid Piapro URL.");
	}

	private static String getBilibilitID(String url) throws IOException {
		if (!url.matches("(https{0,1}\\:\\/\\/www\\.){0,1}bilibili\\.com\\/video\\/.+")) {
			throw new IllegalArgumentException("Invalid Bilibili URL.");
		}

		Matcher m = BILILBILI_ID_PATTERN.matcher(url);
		if (m.find()) {
			return m.group();
		}

		URL url1 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
		connection.setRequestProperty("user-agent", USER_AGENT);
		connection.connect();

		InputStream in = connection.getInputStream();
		GZIPInputStream stream = new GZIPInputStream(in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		String s = "";
		boolean read = false;
		int i;
		while ((i = reader.read()) != -1) {
			char c = (char) i;
			if (c == '<') {
				read = true;
				s += c;
			} else if (c == '>') {
				read = false;
				s += c;

				m = BILILBILI_ID_PATTERN.matcher(s);
				if (m.find()) {
					s = m.group();
					break;
				}

				s = "";
			} else if (read) {
				s += c;
			}
		}
		connection.disconnect();

		if (read == false)
			return s;

		throw new IllegalArgumentException("Invalid Bilibili URL.");
	}

	private static String getBandcampID(String url) throws IOException {
		URL url1 = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
		connection.setRequestProperty("user-agent", USER_AGENT);
		connection.connect();

		InputStream in = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String s = "";
		boolean read = false;
		int i;
		while ((i = reader.read()) != -1) {
			char c = (char) i;
			if (c == '<') {
				read = true;
				s += c;
			} else if (c == '>') {
				read = false;
				s += c;

				Matcher m = BANDCAMP_ID_PATTERN.matcher(s);
				if (m.find()) {
					s = m.group();
					break;
				}

				s = "";
			} else if (read) {
				s += c;
			}
		}
		connection.disconnect();

		if (read == false)
			return s;

		throw new IllegalArgumentException("Invalid Bandcamp URL.");
	}

	/**
	 * Get's information about a given NicoNico video.<br>
	 * <br>
	 * <b>NOTE</b>: Voca4J only provides information fetchers for NicoNicoDouga and
	 * YouTube. Other PV services require authenitcated API clients to retrieve such
	 * information.
	 * 
	 * @param niconicoId the video ID.
	 * @return the video information.
	 * @throws IOException   failed to load the video information.
	 * @throws JAXBException failed to parse the video information.
	 */
	public static NicoNicoInfo getNicoInfo(String niconicoId) throws IOException, JAXBException {
		if (!niconicoId.matches("sm\\d+"))
			throw new IllegalArgumentException(niconicoId + " is not a valid NicoNicoDouga video ID.");

		String url = "https://ext.nicovideo.jp/api/getthumbinfo/" + niconicoId;
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.addRequestProperty("user-agent", USER_AGENT);
		connection.connect();

		JAXBContext jaxbContext = JAXBContext.newInstance(NicoNicoInfo.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		NicoNicoInfo info = (NicoNicoInfo) jaxbUnmarshaller.unmarshal(connection.getInputStream());

		connection.disconnect();
		return info;
	}

	/**
	 * Get's information about a given YouTube video.<br>
	 * <br>
	 * <b>NOTE</b>: Voca4J only provides information fetchers for NicoNicoDouga and
	 * YouTube. Other PV services require authenitcated API clients to retrieve such
	 * information.
	 * 
	 * @param youtubeId the video ID.
	 * @return the video information.
	 * @throws IOException failed to load the video information.
	 */
	public static YoutubeInfo getYoutubeInfo(String youtubeId) throws IOException {
		String url = "https://www.youtube.com/get_video_info?video_id=" + youtubeId;
		HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
		connection.addRequestProperty("user-agent", USER_AGENT);
		connection.connect();

		String content = InputStreamUtils.toString(connection.getInputStream());
		connection.disconnect();

		String[] queryParams = content.split("\\&");
		for (String param : queryParams) {
			param = URLDecoder.decode(param, StandardCharsets.UTF_8.name());
			String[] parts = param.split("\\=", 2);
			if (parts[0].equalsIgnoreCase("player_response"))
				return Constants.GSON.fromJson(parts[1], YoutubeInfo.class);
		}

		throw new RuntimeException("Failed to get info for video: " + youtubeId);
	}
}
