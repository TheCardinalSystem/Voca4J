package com.cardinal.voca4j.api;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.album.TrackField;
import com.cardinal.voca4j.lang.Body;
import com.cardinal.voca4j.lang.Encode;
import com.cardinal.voca4j.lang.Ignore;
import com.cardinal.voca4j.lang.JoiningFormat;

/**
 * An abstract {@link Request} implementation that builds the request URL based
 * on class fields and their values.<br>
 * 
 * <br>
 * 
 * If the root URL passed to the constructor contains any character sequences
 * matching the pattern <code>(?&lt;=\{).+?(?=\})</code>, then those values will
 * be replaced with fields that have matching names. For example, the root URL
 * <code>/api/users/{id}</code> in a class with the Integer field "id" assigned
 * the value '32' will be changed to <code>/api/users/32</code> during
 * processing.<br>
 * 
 * <br>
 * 
 * Fields with the {@linkplain Ignore} annotation will not be added to the URL.
 * If you need to manually format a field before adding it to the URL, then add
 * the ignore annotation, override {@linkplain Request#buildRequestURL()} in
 * your implementation, and process the field there (NOTE: Do not forget to call
 * <code>super.buildRequestURL()</code> so that all your other fields will be
 * processed!).<br>
 * 
 * <br>
 * 
 * Fields with the {@linkplain Encode} annotation will be encoded (see
 * {@linkplain URLEncoder}) before being added to the URL. Array type fields
 * with the {@linkplain JoiningFormat} annotation will be joined with the
 * demiliter <code>&amp;{fieldName}={fieldValue}</code> before being added to
 * the URL.<br>
 * 
 * <br>
 * 
 * Any array fields without this annotation will be joined by commas before
 * being added. Any boxed primitive types, {@linkplain AdvancedFilters}
 * instances, {@linkplain LocalDate} instances, or {@linkplain Enum} types will
 * automatically be processed and formatted accordingly.
 * 
 * @author Cardinal System
 *
 */
public abstract class FieldValueRequest extends AbstractRequest {

	@Ignore
	protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	@Ignore
	protected static final String URL_ENCODING = "UTF-8";

	@Ignore
	private static final Pattern URL_INSERT_PATTERN = Pattern.compile("(?<=\\{).+?(?=\\})");

	@Ignore
	protected String rootUrl;
	@Ignore
	protected String url;
	@Ignore
	private Set<String> urlFields = new HashSet<String>();
	@Ignore
	private Set<Field> queryFields = new HashSet<Field>(), bodyFields = new HashSet<Field>();

	/**
	 * Constructs a {@link FieldValueRequest} with the given root URL.
	 * 
	 * @param rootUrl the root URL that field names/values will be appended to.
	 */
	protected FieldValueRequest(String rootUrl) {
		if (rootUrl == null || rootUrl.isEmpty())
			throw new IllegalArgumentException("Root URL cannot be empty!");
		if (!rootUrl.startsWith("http"))
			rootUrl = Constants.rootAPIURL + (rootUrl.startsWith("/") ? "" : "/") + rootUrl;

		this.rootUrl = rootUrl;
		Matcher m = URL_INSERT_PATTERN.matcher(rootUrl);
		while (m.find()) {
			urlFields.add(m.group());
		}

		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.getType().equals(Type.class))
				continue;

			if (field.isAnnotationPresent(Body.class))
				bodyFields.add(field);
			else if (!field.isAnnotationPresent(Ignore.class))
				queryFields.add(field);

		}
	}

	@Override
	public String buildRequestURL() throws UnsupportedEncodingException {
		if (url != null)
			return url;
		url = rootUrl + "?";
		boolean flag = false;
		for (Field field : queryFields) {
			String name = field.getName();

			field.setAccessible(true);
			try {
				Object value = field.get(this);
				if (value == null)
					continue;

				boolean encode = field.isAnnotationPresent(Encode.class), alreadyEncoded = false;
				Class<?> type = field.getType();

				if (type.isArray()) {
					boolean joiningStyle = field.isAnnotationPresent(JoiningFormat.class);

					Class<?> componentType = type.getComponentType();
					type = componentType;
					if (componentType.isInstance(AdvancedFilters.class)) {
						AdvancedFilters[] tmp = (AdvancedFilters[]) value;
						String tmp2 = "";
						for (int i = 0; i < tmp.length; i++) {
							tmp2 += "&" + encode("advancedFilters[" + i + "][filterType]");
							tmp2 += "=" + tmp[i].getFilterType();
							tmp2 += "&" + encode("advancedFilters[" + i + "][param]");
							tmp2 += "=" + tmp[i].getParameter();
							tmp2 += "&" + encode("advancedFilters[" + i + "][description]");
							tmp2 += "=" + encode(tmp[i].getDescription());
							tmp2 += "&" + encode("advancedFilters[" + i + "][negate]");
							tmp2 += "=" + tmp[i].shouldNegate();
						}
						value = tmp2;
					} else if (componentType.equals(TrackField.class)) {
						value = Arrays.stream((Enum[]) value).map(
								e -> encode(e.name().toLowerCase().replaceAll("\\_", ".").replaceFirst("not\\.", "!")))
								.collect(Collectors.joining(joiningStyle ? "&" + name + "=" : ","));
					} else if (componentType.isEnum()) {
						value = Arrays.stream((Enum[]) value).map(e -> encode ? encode(e.name()) : e.name())
								.collect(Collectors.joining(joiningStyle ? "&" + name + "=" : ","));
					} else {
						value = Arrays.stream((Object[]) value).map(o -> encode ? encode(o.toString()) : o.toString())
								.collect(Collectors.joining(joiningStyle ? "&" + name + "=" : ","));
					}
					alreadyEncoded = true;
				} else if (type.isEnum()) {
					value = ((Enum<?>) value).name();
				} else if (type.equals(LocalDate.class)) {
					value = ((LocalDate) value).format(DATE_FORMATTER);
				}
				String finalValue = value.toString();

				if (finalValue.isEmpty())
					continue;

				if (encode && !alreadyEncoded)
					finalValue = encode(finalValue);

				if (urlFields.contains(name)) {
					url = url.replaceAll("\\{" + name + "\\}", finalValue);
				} else {
					url += (flag ? "&" : "") + name + "=" + finalValue;
					flag = true;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			} finally {
				field.setAccessible(false);
			}
		}

		if (url.endsWith("?"))
			url = url.substring(0, url.length() - 1);

		return url;
	}

	@Override
	public String buildRequestBody() {
		if (bodyFields.size() > 1) {
			List<Object> list = new ArrayList<Object>();
			for (Field field : bodyFields) {
				field.setAccessible(true);

				Object value = null;
				try {
					value = field.get(this);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

				if (value != null)
					list.add(value);
				field.setAccessible(false);
			}
			return Constants.GSON.toJson(list);
		} else if (!bodyFields.isEmpty()) {
			Field field = bodyFields.toArray(new Field[1])[0];
			field.setAccessible(true);

			Object value = null;
			try {
				value = field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}

			field.setAccessible(false);

			return value == null ? null : Constants.GSON.toJson(value);
		}
		return null;
	}

	private static String encode(String s) {
		try {
			return URLEncoder.encode(s, URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return s;
		}
	}

}