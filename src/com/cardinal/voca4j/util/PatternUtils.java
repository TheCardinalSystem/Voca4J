package com.cardinal.voca4j.util;

import java.util.regex.Pattern;

public class PatternUtils {
	private static final Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

	/*
	 * https://stackoverflow.com/a/25853507/5645656
	 */
	public static String escapeSpecialRegexChars(String str) {
		return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
	}
}