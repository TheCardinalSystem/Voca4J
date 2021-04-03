package com.cardinal.voca4j.api.album;

import com.cardinal.voca4j.api.AdvancedFilters;

public class AdvancedAlbumFilter extends AdvancedFilters {

	public static final AdvancedAlbumFilter ArtistTypeVocaloid = new AdvancedAlbumFilter("ArtistType", "Vocaloid",
			"Artisttype: Vocaloid", false),
			ArtistTypeUTAU = new AdvancedAlbumFilter("ArtistType", "UTAU", "Artisttype: UTAU", false),
			ArtistTypeCeVIO = new AdvancedAlbumFilter("ArtistType", "CeVIO", "Artisttype: CeVIO", false),
			ArtistTypeSynthesizerV = new AdvancedAlbumFilter("ArtistType", "SynthesizerV",
					"Artist type: Synthesizer V", false),
			ArtistTypeOther = new AdvancedAlbumFilter("ArtistType", "OtherVoiceSynthesizer",
					"Artisttype: other voice synthesizer", false),
			NoCoverPicture = new AdvancedAlbumFilter("NoCoverPicture", "", "No cover picture", false),
			WithStoreLink = new AdvancedAlbumFilter("HasStoreLink", "", "With store link", false),
			NoTracks = new AdvancedAlbumFilter("HasTracks", "", "No tracks", true);

	protected AdvancedAlbumFilter(String type, String param, String desc, Boolean negate) {
		super(type, param, desc, negate);
	}
}
