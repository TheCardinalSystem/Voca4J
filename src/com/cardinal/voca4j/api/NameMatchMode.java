package com.cardinal.voca4j.api;

/**
 * An enum that represents rules for matching queries with database entries.
 * 
 * @author Cardinal System
 *
 */
public enum NameMatchMode {
	Auto, Partial, StartsWith, Exact, Words;
}