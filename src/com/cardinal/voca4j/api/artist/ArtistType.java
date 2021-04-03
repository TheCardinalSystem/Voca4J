package com.cardinal.voca4j.api.artist;

public enum ArtistType {
	Unknown,
	/**
	 * Doujin circle. A group of doujin producers that also releases music (acts as
	 * a label).
	 */
	Circle,

	/**
	 * Commercial music label. Does not produce music by itself.
	 */
	Label,

	/**
	 * Producer is the maker or the song (usually an individual, for example doriko)
	 */
	Producer, Animator, Illustrator, Lyricist, Vocaloid, UTAU, CeVIO, OtherVoiceSynthesizer, OtherVocalist, OtherGroup,
	OtherIndividual, Utaite, Band, Vocalist, Character
}
