package com.cardinal.voca4j.api.song;

import com.cardinal.voca4j.api.AdvancedFilters;

public class AdvancedSongFilters extends AdvancedFilters {

	public static final AdvancedSongFilters ArtistTypeVocaloid = new AdvancedSongFilters("ArtistType", "Vocaloid",
			"Artisttype: Vocaloid", false),
			ArtistTypeUTAU = new AdvancedSongFilters("ArtistType", "UTAU", "Artisttype: UTAU", false),
			ArtistTypeCeVIO = new AdvancedSongFilters("ArtistType", "CeVIO", "Artisttype: CeVIO", false),
			ArtistTypeSynthesizerV = new AdvancedSongFilters("ArtistType", "SynthesizerV", "Artist type: Synthesizer V",
					false),
			ArtistTypeOtherVoiceSynth = new AdvancedSongFilters("ArtistType", "OtherVoiceSynthesizer",
					"Artisttype: other voice synthesizer", false),
			MultipleVoicebanks = new AdvancedSongFilters("HasMultipleVoicebanks", "", "Multiple voicebanks", false),
			LyricsAnyLanguage = new AdvancedSongFilters("Lyrics", "*", "Lyrics: Any language", false),
			LyricsEnglish = new AdvancedSongFilters("Lyrics", "en", "Lyrics: English", false),
			LyricsJapanese = new AdvancedSongFilters("Lyrics", "ja", "Lyrics: Japanese", false),
			LyricsChinese = new AdvancedSongFilters("Lyrics", "zh", "Lyrics: Chinese", false),
			LyricsOtherOrUnspecifiedLanguage = new AdvancedSongFilters("Lyrics", "",
					"Lyrics: Other/unspecified language", false),
			HasPublishDate = new AdvancedSongFilters("HasPublishDate", "", "Has publish date", false),
			AlbumSong = new AdvancedSongFilters("HasAlbum", "", "Album song", false),
			Standalone = new AdvancedSongFilters("HasAlbum", "", "Standalone (no album)", true),
			NoOriginalMedia = new AdvancedSongFilters("HasOriginalMedia", "", "No original media", true),
			NoMedia = new AdvancedSongFilters("HasMedia", "", "No media", true);

	private AdvancedSongFilters(String type, String param, String desc, Boolean negate) {
		super(type, param, desc, negate);
	}

}
