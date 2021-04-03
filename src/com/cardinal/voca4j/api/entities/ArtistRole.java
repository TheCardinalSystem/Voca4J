package com.cardinal.voca4j.api.entities;

public enum ArtistRole {
	Default,
	/**
	 * Mostly PVs
	 */
	Animator,
	/**
	 * Usually associated with remixes/covers
	 */
	Arranger, Composer,
	/**
	 * Usually circle/label
	 */
	Distributor,
	/**
	 * PVs, cover art, booklet
	 */
	Illustrator,
	/**
	 * Plays instruments
	 */
	Instrumentalist, Lyricist, Mastering,
	/**
	 * Usually circle/label
	 */
	Publisher, Vocalist,
	/**
	 * Vocaloid voice manipulator
	 */
	VoiceManipulator, Other, Mixer,
	/**
	 * For UtaiteDB. "Utaites sometimes like to provide backing vocals for other
	 * utaites, and it happens frequently enough that it should be defined as a
	 * role."
	 */
	Chorus,
	/**
	 * For UtaiteDB.
	 */
	Encoder, VocalDataProvider
}