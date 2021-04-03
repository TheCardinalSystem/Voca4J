package com.cardinal.voca4j.api.artist;

import com.cardinal.voca4j.api.AdvancedFilters;
import com.cardinal.voca4j.api.artist.query.ArtistQuery;

/**
 * An enum that represents advanced filtering rules for an {@link ArtistQuery}.
 * 
 * @author Cardinal System
 *
 */
public class AdvancedArtistFilters extends AdvancedFilters {
	public static final AdvancedArtistFilters VoiceProviderVocaloid = new AdvancedArtistFilters("VoiceProvider",
			"Vocaloid", "VoiceProvider: Vocaloid", false),
			VoiceProviderUTAU = new AdvancedArtistFilters("VoiceProvider", "UTAU", "VoiceProvider: UTAU", false),
			VoiceProviderCeVIO = new AdvancedArtistFilters("VoiceProvider", "CeVIO", "VoiceProvider: CeVIO", false),
			VoiceProviderSynthesizerV = new AdvancedArtistFilters("VoiceProvider", "SynthesizerV",
					"Voice provider of: Synthesizer V", false),
			VoiceProviderOther = new AdvancedArtistFilters("VoiceProvider", "OtherVoiceSynthesizer",
					"VoiceProvider: other voice synthesizer", false),
			RootVoicebank = new AdvancedArtistFilters("RootVoicebank", "", "Root voicebank (no base)", false),
			DerivedVoicebank = new AdvancedArtistFilters("RootVoicebank", "", "Derived voicebank", true),
			HadVocaDBAccount = new AdvancedArtistFilters("HasUserAccount", "", "User account on VocaDB", false);

	private AdvancedArtistFilters(String type, String param, String desc, Boolean negate) {
		super(type, param, desc, negate);
	}
}