package com.cardinal.voca4j.api.entities.user;

import java.time.LocalDateTime;

import com.cardinal.voca4j.api.user.UserLanguageProficiency;

public class User extends UserBase {
	private UserGroupID groupId;
	private boolean active, verifiedArtist;
	private KnownLanguage[] knownLanguages;
	private String[] oldUsernames;
	private UserMainPicture mainPicture;
	private LocalDateTime memberSince;

	public boolean isActive() {
		return active;
	}

	public boolean isVerifiedArtist() {
		return verifiedArtist;
	}

	public UserGroupID getGroupId() {
		return groupId;
	}

	public KnownLanguage[] getKnownLanguages() {
		return knownLanguages;
	}

	public String[] getOldUsernames() {
		return oldUsernames;
	}

	public UserMainPicture getMainPicture() {
		return mainPicture;
	}

	public LocalDateTime getJoinTimeF() {
		return memberSince;
	}

	public static class UserMainPicture {
		private String urlSmallThumb, urlThumb, urlTinyThumb;

		public String getUrlSmallThumb() {
			return urlSmallThumb;
		}

		public String getUrlThumb() {
			return urlThumb;
		}

		public String getUrlTinyThumb() {
			return urlTinyThumb;
		}
	}

	public static class KnownLanguage {
		private String cultureCode;
		private UserLanguageProficiency proficiency;

		public String getCultureCode() {
			return cultureCode;
		}

		public UserLanguageProficiency getProficiency() {
			return proficiency;
		}
	}
}
