package com.cardinal.voca4j.api.tag;

public enum TagField {
	None, AdditionalNames, AliasedTo, Description, MainPicture, Names, Parent, RelatedTags, TranslatedDescription,
	WebLinks;

	public static TagField[] VALUES = { AdditionalNames, AliasedTo, Description, MainPicture, Names, Parent,
			RelatedTags, TranslatedDescription, WebLinks };
}
