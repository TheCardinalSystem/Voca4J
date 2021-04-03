package com.cardinal.voca4j.api.entities.album;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.cardinal.voca4j.Constants;
import com.cardinal.voca4j.api.EntryStatus;
import com.cardinal.voca4j.api.album.AlbumType;
import com.cardinal.voca4j.api.entities.ContentLanguageSelection;
import com.cardinal.voca4j.api.entities.EntryThumbnail;
import com.cardinal.voca4j.api.entities.LinkableEntity;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.WebLink;
import com.cardinal.voca4j.api.entities.pv.PromotionalVideo;
import com.cardinal.voca4j.api.entities.releaseevent.ReleaseEvent;
import com.cardinal.voca4j.api.entities.tag.TagUsage;

public class Album implements LinkableEntity {
	private String additionalNames[], artistString, barcode, catalogNumber, defaultName, description, name;
	private AlbumArtist[] artists;
	private LocalDateTime createDate;
	private ContentLanguageSelection defaultNameLanguage;
	private boolean deleted;
	private AlbumDisc[] discs;
	private AlbumType discType;
	private int id = -1, ratingCount = -1, mergedTo = -1;
	private AlbumIdentifier[] identifiers;
	private EntryThumbnail mainPicture;
	private LocalizedName[] names;
	private PromotionalVideo[] pvs;
	private double ratingAverage = -1;
	private ReleaseDate releaseDate;
	private ReleaseEvent releaseEvent;
	private EntryStatus status;
	private TagUsage[] tags;
	private Track[] tracks;
	private WebLink[] webLinks;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String[] getAdditionalNames() {
		return additionalNames;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public String getDescription() {
		return description;
	}

	public String getArtistString() {
		return artistString;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public AlbumArtist[] getArtists() {
		return artists;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public ContentLanguageSelection getDefaultNameLanguage() {
		return defaultNameLanguage;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public AlbumDisc[] getDiscs() {
		return discs;
	}

	public AlbumType getDiscType() {
		return discType;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public int getMergedTo() {
		return mergedTo;
	}

	public AlbumIdentifier[] getIdentifiers() {
		return identifiers;
	}

	public EntryThumbnail getMainPicture() {
		return mainPicture;
	}

	public LocalizedName[] getNames() {
		return names;
	}

	public PromotionalVideo[] getPvs() {
		return pvs;
	}

	public double getRatingAverage() {
		return ratingAverage;
	}

	public ReleaseDate getReleaseDate() {
		return releaseDate;
	}

	public ReleaseEvent getReleaseEvent() {
		return releaseEvent;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public TagUsage[] getTags() {
		return tags;
	}

	public Track[] getTracks() {
		return tracks;
	}

	public WebLink[] getWebLinks() {
		return webLinks;
	}

	public static class ReleaseDate {
		private int day, month, year;
		private boolean isEmpty;
		private String formatted;

		public int getDay() {
			return day;
		}

		public int getMonth() {
			return month;
		}

		public int getYear() {
			return year;
		}

		public boolean isEmpty() {
			return isEmpty;
		}

		public String getFormatted() {
			return formatted;
		}

		public LocalDate toLocalDate() {
			return isEmpty ? null : LocalDate.of(year, month, day);
		}
	}

	@Override
	public String getURL() {
		return Constants.rootSiteURL + "/Al/" + id;
	}

}
