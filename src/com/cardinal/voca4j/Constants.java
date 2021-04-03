package com.cardinal.voca4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBException;

import com.cardinal.voca4j.api.EntryFields;
import com.cardinal.voca4j.api.NameMatchMode;
import com.cardinal.voca4j.api.artist.ArtistFields;
import com.cardinal.voca4j.api.artist.ArtistType;
import com.cardinal.voca4j.api.artist.query.ArtistQuery.ArtistQueryBuilder;
import com.cardinal.voca4j.api.entities.ArtistRole;
import com.cardinal.voca4j.api.entities.LocalizedName;
import com.cardinal.voca4j.api.entities.QueriedList;
import com.cardinal.voca4j.api.entities.album.Album;
import com.cardinal.voca4j.api.entities.album.TrackFields;
import com.cardinal.voca4j.api.entities.artist.Artist;
import com.cardinal.voca4j.api.entities.comment.Comment;
import com.cardinal.voca4j.api.entities.entry.Entry;
import com.cardinal.voca4j.api.entities.song.Song;
import com.cardinal.voca4j.api.entities.songlist.SongList;
import com.cardinal.voca4j.api.entities.tag.Tag;
import com.cardinal.voca4j.api.entry.query.EntryQuery.EntryQueryBuilder;
import com.cardinal.voca4j.api.song.PVService;
import com.cardinal.voca4j.impl.ArrayDeserializer;
import com.cardinal.voca4j.impl.LocalDateDeserializer;
import com.cardinal.voca4j.impl.LocalDateTimeProcessor;
import com.cardinal.voca4j.impl.StringDeserializer;
import com.cardinal.voca4j.impl.TrackFieldsDeserializer;
import com.cardinal.voca4j.impl.YoutubeInfo;
import com.cardinal.voca4j.impl.niconico.NicoNicoInfo;
import com.cardinal.voca4j.lang.HTTPException;
import com.cardinal.voca4j.util.PatternUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * A class of various constants fields used throughout the wrapper.
 * 
 * @author Cardinal System
 *
 */
public class Constants {
	/**
	 * The domain or root URL for the API.
	 */
	public static String rootAPIURL = "https://vocadb.net/api";
	public static String rootSiteURL = "https://vocadb.net";
	public static String userAgent = "Voca4J/0.0.1BETA";
	public static Charset contentEncoding = StandardCharsets.UTF_8;

	public static final Type STRING_SET_TYPE = new TypeToken<Set<String>>() {
	}.getType();
	public static final Type COMMENT_LIST_TYPE = new TypeToken<List<Comment>>() {
	}.getType();
	public static final Type QUERIED_COMMENT_LIST_TYPE = new TypeToken<QueriedList<Comment>>() {
	}.getType();
	public static final Type SONG_LIST_TYPE = new TypeToken<List<Song>>() {
	}.getType();
	public static final Type TAG_LIST_TYPE = new TypeToken<List<Tag>>() {
	}.getType();
	public static final Type ALBUM_QUERIED_LIST_TYPE = new TypeToken<QueriedList<Album>>() {
	}.getType();
	public static final Type ARTIST_QUERIED_LIST_TYPE = new TypeToken<QueriedList<Artist>>() {
	}.getType();
	public static final Type SONG_LIST_QUERIED_LIST_TYPE = new TypeToken<QueriedList<SongList>>() {
	}.getType();

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
			.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeProcessor())
			.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
			.registerTypeAdapter(TrackFields.class, new TrackFieldsDeserializer())
			.registerTypeAdapter(String.class, new StringDeserializer())
			.registerTypeAdapter(ArtistRole[].class, new ArrayDeserializer<ArtistRole[]>(ArtistRole.class))
			.registerTypeAdapter(String[].class, new ArrayDeserializer<ArtistRole[]>(String.class))
			.registerTypeAdapter(PVService[].class, new ArrayDeserializer<PVService[]>(PVService.class)).create();

	static {
		if (rootAPIURL.contains("localhost")) {
			try {
				rootAPIURL = rootAPIURL.replaceAll("localhost", InetAddress.getLocalHost().getHostAddress());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}
	private static Voca4J voca = new Voca4J();

	@SuppressWarnings("unchecked")
	public static QueriedList<Artist> getNext(AtomicInteger last) {
		System.out.println(last.get());
		return (QueriedList<Artist>) voca.getApiService()
				.completeAndParse(voca.getBuilderProvider().getBuilder(ArtistQueryBuilder.class)
						.filterArtistTypes(ArtistType.Vocaloid).includeFields(ArtistFields.Names)
						.setStart(last.getAndAdd(100)).setMaximumResults(100).build());
	}

	private static final String LEADING_CONTAINERS = "\u3010|\u300C|\uFF08";
	private static final String TRAILING_CONTAINERS = "\u3011|\u300D|\uFF09";
	private static final String VOCALOIDS = "(\u3010|\u300C)?(Amy|AVANNA|AZUKI|\u6B63\u5CA1\u5C0F\u8C46 V4|Masaoka Azuki V4|Big Al|Bruno|Chika|Chris|Clara|CUL|\u30AB\u30EB|CYBER DIVA|CYVA|VY3|CYBER DIVA II|CYBER SONGMAN|VY4|CYAN|CYBER SONGMAN II|DAINA|DEX|Fukase (English)|\u3075\u304B\u305B (English)|Fukase (Normal)|\u3075\u304B\u305B (Normal)|Fukase (Soft)|\u3075\u304B\u305B (Soft)|Fukase (Unknown)|\u3075\u304B\u305B (Unknown)|galaco|\u30AE\u30E3\u30E9\u5B50|galaco (Blue)|\u30AE\u30E3\u30E9\u5B50 (Blue)|\u30AE\u30E3\u30E9\u5B50 NEO (Blue)|galaco NEO (Blue)|galaco (Red)|\u30AE\u30E3\u30E9\u5B50 (Red)|\u30AE\u30E3\u30E9\u5B50 NEO (Red)|galaco NEO (Red)|galaco (Unknown)|\u30AE\u30E3\u30E9\u5B50 (Unknown)|\u30AE\u30E3\u30E9\u5B50 NEO (Unknown)|galaco NEO (Unknown)|GUMI|Megpoid|\u30E1\u30B0\u30C3\u30DD\u30A4\u30C9|\u30B0\u30DF|hide|(?-i)IA(?i)|IA -ARIA ON THE PLANETES-|\u30A4\u30A2|IA ROCKS|IA ROCKS -ARIA ON THE PLANETES-|IA alpha type C|IA \u03B1 type C|KAITO|\u30AB\u30A4\u30C8|KAITO Append (\u03B2) (Unknown)|KAITO Append (Beta) (Unknown)|KAITO Append Normal (\u03B2)|KAITO Append Normal (Beta)|KAITO Append Power (\u03B2)|KAITO Append Power (Beta)|KAITO Append Soft (\u03B2)|KAITO Append Soft (Beta)|KAITO Append Whisper (\u03B2)|KAITO Append Whisper (Beta)|KAITO V3 (English)|\u30AB\u30A4\u30C8V3 (English)|KAITO V3 (Soft)|\u30AB\u30A4\u30C8V3 (Soft)|KAITO V3 (Straight)|\u30AB\u30A4\u30C8V3 (Straight)|KAITO V3 (Unknown)|\u30AB\u30A4\u30C8V3 (Unknown)|KAITO V3 (Whisper)|\u30AB\u30A4\u30C8V3 (Whisper)|Kaori|Ken|kokone|\u5FC3\u97FF|KYO|V3 KYO|ZOLA PROJECT KYO|LEON|\u30EC\u30AA\u30F3|LE\u2642N|Lily|\u30EA\u30EA\u30A3|LOLA|\u30ED\u30FC\u30E9|L\u2640LA|LUAN|Luc\u00EDa|LUMi|\u30EB\u30DF|MAIKA|ONA|MATCHA|Kobayashi Matcha V4|\u5C0F\u6797\u62B9\u8336 V4|MAYU|\u30DE\u30E6|MEIKO|\u30E1\u30A4\u30B3|MEIKO Append (\u03B2) (Unknown)|\u30E1\u30A4\u30B3 Append (\u03B2) (Unknown)|MEIKO Append (Beta)|MEIKO V3 (Dark)|\u30E1\u30A4\u30B3V3 (Dark)|MEIKO V3 (English)|\u30E1\u30A4\u30B3V3 (English)|MEIKO V3 (Power)|\u30E1\u30A4\u30B3V3 (Power)|MEIKO V3 (Straight)|\u30E1\u30A4\u30B3V3 (Straight)|MEIKO V3 (Unknown)|\u30E1\u30A4\u30B3V3 (Unknown)|MEIKO V3 (Whisper)|\u30E1\u30A4\u30B3V3 (Whisper)|Merli|\u30E1\u30EB\u30EA|Mew|MIRIAM|\u30DF\u30EA\u30A2\u30E0|Oliver|Prima|Project if\u2026|Rana|Rana V4|Ruby|Sachiko|SeeU|\uC2DC\uC720|\u30B7\u30E6|SV01 SeeU|SF-A2 \u958B\u767A\u30B3\u30FC\u30C9 miki|SF-A2 Codename miki|SF-A2 Kaihatsu Code miki|SF-A2 miki|miki|SF-A2 \u958B\u767A\u30B3\u30FC\u30C9 miki V4|SF-A2 Kaihatsu Code miki V4|SF-A2 miki V4|miki V4|SF-A2 \u958B\u767A\u30B3\u30FC\u30C9 miki V4 Natural|SF-A2 Kaihatsu Code miki V4 Natural|SF-A2 miki V4 Natural|SONiKA|\u30BD\u30CB\u30AB|Sweet Ann|Tonio|\u30C8\u30CB\u30AA|UNI|\uC720\uB2C8|\u30E6\u30CB|unity-chan!|Unity-chan|\u5927\u9CE5\u3053\u306F\u304F|\u30E6\u30CB\u30C6\u30A3\u3061\u3083\u3093|Otori Kohaku|\u30A2\u30FC\u30AB\u30FC\u30B7\u30E3|AKAZA|v flower|\u30D5\u30E9\u30EF|\u30D6\u30A4\u30D5\u30E9\u30EF|flower|V3 GUMI (Adult)|VOCALOID3 Megpoid (Adult)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Adult)|V3 GUMI (English)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (English)|VOCALOID3 Megpoid (English)|V3 GUMI (Native)|VOCALOID3 Megpoid (Native)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Native)|V3 GUMI (Power)|VOCALOID3 Megpoid (Power)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Power)|V3 GUMI (Sweet)|VOCALOID3 Megpoid (Sweet)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Sweet)|V3 GUMI (Unknown)|VOCALOID3 Megpoid (Unknown)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Unknown)|V3 GUMI (Whisper)|VOCALOID3 Megpoid (Whisper)|VOCALOID3 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Whisper)|V3 Lily|VOCALOID3 Lily|V3 \u30EA\u30EA\u30A3|V3 Lily (Native)|V3 \u30EA\u30EA\u30A3 (Native)|VOCALOID 3 Lily (Native)|V3 \u795E\u5A01\u304C\u304F\u307D (Native)|V3 Kamui Gakupo (Native)|V3 Gackpoid (Native)|V3 \u304C\u304F\u3063\u307D\u3044\u3069 (Native)|V3 \u795E\u5A01\u304C\u304F\u307D (Power)|V3 Kamui Gakupo (Power)|V3 \u304C\u304F\u3063\u307D\u3044\u3069 (Power)|V3 Gackpoid (Power)|V3 \u795E\u5A01\u304C\u304F\u307D (Unknown)|V3 Kamui Gakupo (Unknown)|V3 Gackpoid (Unknown)|V3 \u304C\u304F\u3063\u307D\u3044\u3069 (Unknown)|V3 \u795E\u5A01\u304C\u304F\u307D (Whisper)|V3 \u304C\u304F\u3063\u307D\u3044\u3069 (Whisper)|V3 Gackpoid (Whisper)|V3 Kamui Gakupo (Whisper)|v4 flower|v4 \u30D5\u30E9\u30EF|\u30D6\u30A4\u30D5\u30A9\u30A6\u30D5\u30E9\u30EF|V4 GUMI (Adult)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Adult)|VOCALOID4 Megpoid (Adult)|V4 GUMI (MellowAdult)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (MellowAdult)|VOCALOID4 Megpoid (MellowAdult)|V4 GUMI (Native)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Native)|VOCALOID4 Megpoid (Native)|V4 GUMI (NativeFat)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (NativeFat)|VOCALOID4 Megpoid (NativeFat)|V4 GUMI (NaturalSweet)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (NaturalSweet)|VOCALOID4 Megpoid (NaturalSweet)|V4 GUMI (Power)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Power)|VOCALOID4 Megpoid (Power)|V4 GUMI (PowerFat)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (PowerFat)|VOCALOID4 Megpoid (PowerFat)|V4 GUMI (SoftWhisper)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (SoftWhisper)|VOCALOID4 Megpoid (SoftWhisper)|V4 GUMI (Sweet)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Sweet)|VOCALOID4 Megpoid (Sweet)|V4 GUMI (Unknown)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Unknown)|VOCALOID4 Megpoid (Unknown)|V4 GUMI (Whisper)|VOCALOID4 \u30E1\u30B0\u30C3\u30DD\u30A4\u30C9 (Whisper)|VOCALOID4 Megpoid (Whisper)|V4 \u30DE\u30AF\u30CD\u30CA\u30CA (English)|V4 Macne Nana (English)|V4 \u30DE\u30AF\u30CD\u30CA\u30CA \u30CA\u30C1\u30E5\u30E9\u30EB|V4 Macne Nana Natural|V4\u30DE\u30AF\u30CD\u30CA\u30CA (Japanese)|V4 Macne Nana (Japanese)|V4 \u30DE\u30AF\u30CD\u30CA\u30CA \u30D7\u30C1|V4 Macne Nana Petit|V4\u30DE\u30AF\u30CD\u30D7\u30C1|V4 Macne Petit|V4 Chibi Nana|V4 \u795E\u5A01\u304C\u304F\u307D (Native)|V4 Kamui Gakupo (Native)|V4 \u304C\u304F\u3063\u307D\u3044\u3069 (Native)|V4 Gackpoid (Native)|V4 \u795E\u5A01\u304C\u304F\u307D (Power)|V4 Kamui Gakupo (Power)|V4 \u304C\u304F\u3063\u307D\u3044\u3069 (Power)|V4 Gackpoid (Power)|V4 \u795E\u5A01\u304C\u304F\u307D (Unknown)|V4 Kamui Gakupo (Unknown)|V4 Gackpoid (Unknown)|V4 \u304C\u304F\u3063\u307D\u3044\u3069 (Unknown)|V4 Camui Gackpo (Unknown)|V4 \u795E\u5A01\u304C\u304F\u307D (Whisper)|V4 Kamui Gakupo (Whisper)|V4 Gackpoid (Whisper)|V4 \u304C\u304F\u3063\u307D\u3044\u3069 (Whisper)|V4 Camui Gackpo (Whisper)|WIL|V3 WIL|ZOLA PROJECT WIL|VY1|MIZKI|VY1V3|MIZKI V3|VY1V4 (Natural)|MIZKI V4 (Natural)|VY1V4 (Normal)|MIZKI V4 (Normal)|VY1V4 (Power)|MIZKI V4 (Power)|VY1V4 (Soft)|MIZKI V4 (Soft)|VY1V4 (Unknown)|MIZKI V4 (Unknown)|VY1V5|Mizki V5|VY2|\u52C7\u99AC|Yuuma|VY2V3 (Falsetto)|VY2V3 \u30D5\u30A1\u30EB\u30BB\u30C3\u30C8\u30E9\u30A4\u30D6\u30E9\u30EA|VY2V3 (Standard)|VY2V3 \u6A19\u6E96\u30E9\u30A4\u30D6\u30E9\u30EA|VY2V3 (Unknown)|VY2V5|Yuuma V5|YOHIOloid (English)|\u30E8\u30D2\u30AA\u30ED\u30A4\u30C9 (\u82F1\u8A9E)|YOHIOloid (Japanese)|\u30E8\u30D2\u30AA\u30ED\u30A4\u30C9 (\u65E5\u672C\u8A9E)|YUU|V3 YUU|ZOLA PROJECT YUU|Zing|\u3042\u304D\u3053\u30ED\u30A4\u30C9\u3061\u3083\u3093|Akikoloid-chan|Akikoroid-chan|\u30A2\u30EB\u30B9\u30ED\u30A4\u30C9|Arsloid|\u795E\u751F\u30A2\u30AD\u30E9|Kanou Akira|Alsloid|\u30AC\u30C1\u30E3\u30C3\u30DD\u30A4\u30C9|Ryuto|\u30EA\u30E5\u30A6\u30C8|Gachapoid|\u30AC\u30C1\u30E3\u30C3\u30DD\u30A4\u30C9 V3|Gachapoid V3|Ryuto V3|\u30EA\u30E5\u30A6\u30C8 V3|\u30B3\u30B3\u30ED\u30DC|COCOROBO|\u30DE\u30AF\u30CD\u30CA\u30CA (English)|Macne Nana (English)|\u30DE\u30AF\u30CD\u30CA\u30CA (Japanese)|Macne Nana (Japanese)|\u30DF\u30E9\u30A4\u5C0F\u753A|Mirai Komachi|\u30EA\u30F3\u30B0\u30FB\u30B9\u30BA\u30CD|Ring Suzune|\u4E50\u6B63\u7EEB|\u697D\u6B63\u7DBE|\u6A02\u6B63\u7DBE|Yuezheng Ling|\u4E50\u6B63\u7EEB V5 (Unknown)|Yuezheng Ling V5 (Unknown)|\u4E50\u6B63\u9F99\u7259 (Unknown)|Yuezheng Longya (Unknown)|\u6A02\u6B63\u9F8D\u7259 (Unknown)|\u697D\u6B63\u9F8D\u7259 (Unknown)|\u4E50\u6B63\u9F99\u7259 (\u6DF3)|Yuezheng Longya (Chun)|Yuezheng Longya (Normal)|\u6A02\u6B63\u9F8D\u7259 (\u6DF3)|\u4E50\u6B63\u9F99\u7259 (\u96C5)|Yuezheng Longya (Ya)|Yuezheng Longya (Soft)|\u6A02\u6B63\u9F8D\u7259 (\u96C5)|\u514E\u7720\u308A\u304A\u3093|Tone Rion|\u514E\u7720\u308A\u304A\u3093V4|Tone Rion V4|\u521D\u97F3\u30DF\u30AF|Hatsune Miku|\u521D\u97F3\u30DF\u30AF Append (Dark)|Hatsune Miku Append (Dark)|\u521D\u97F3\u30DF\u30AF Append (Light)|Hatsune Miku Append (Light)|\u521D\u97F3\u30DF\u30AF Append (Soft)|Hatsune Miku Append (Soft)|\u521D\u97F3\u30DF\u30AF Append (Solid)|Hatsune Miku Append (Solid)|\u521D\u97F3\u30DF\u30AF Append (Sweet)|Hatsune Miku Append (Sweet)|\u521D\u97F3\u30DF\u30AF Append (Unknown)|Hatsune Miku Append (Unknown)|\u521D\u97F3\u30DF\u30AF Append (Vivid)|Hatsune Miku Append (Vivid)|\u521D\u97F3\u30DF\u30AF V3 (Dark)|Hatsune Miku V3 (Dark)|\u521D\u97F3\u30DF\u30AF V3 (English Ver. 2 Beta)|Hatsune Miku V3 (English Ver. 2 Beta)|\u521D\u97F3\u30DF\u30AF V3 (English)|Hatsune Miku V3 (English)|\u521D\u97F3\u30DF\u30AF\u82F1\u8A9E\u7248|Hatsune Miku Eigo-ban|\u521D\u97F3\u30DF\u30AF V3 (Light)|Hatsune Miku V3 (Light)|\u521D\u97F3\u30DF\u30AF V3 (Original)|Hatsune Miku V3 (Original)|\u521D\u97F3\u30DF\u30AF V3 (Soft)|Hatsune Miku V3 (Soft)|\u521D\u97F3\u30DF\u30AF V3 (Solid)|Hatsune Miku V3 (Solid)|\u521D\u97F3\u30DF\u30AF V3 (Sweet)|Hatsune Miku V3 (Sweet)|\u521D\u97F3\u30DF\u30AF V3 (Unknown)|Hatsune Miku V3 (Unknown)|\u521D\u97F3\u30DF\u30AF V3 (Vivid)|Hatsune Miku V3 (Vivid)|\u521D\u97F3\u30DF\u30AF V4 (English)|Hatsune Miku V4 (English)|\u521D\u97F3\u30DF\u30AF V4X (Dark)|Hatsune Miku V4X (Dark)|\u521D\u97F3\u30DF\u30AF V4X (Original)|Hatsune Miku V4X (Original)|\u521D\u97F3\u30DF\u30AF V4X\u03B2|Hatsune Miku V4X\u03B2|\u521D\u97F3\u30DF\u30AF V4X (Soft)|Hatsune Miku V4X (Soft)|\u521D\u97F3\u30DF\u30AF V4X (Solid)|Hatsune Miku V4X (Solid)|\u521D\u97F3\u30DF\u30AF V4X (Sweet)|Hatsune Miku V4X (Sweet)|\u521D\u97F3\u30DF\u30AF V4X (Unknown)|Hatsune Miku V4X (Unknown)|\u521D\u97F3\u672A\u6765 V4 (\u4E2D\u6587\u7248)|Hatsune Miku V4 (Chinese)|\u521D\u97F3\u30DF\u30AF V4C|\u521D\u97F3\u672A\u4F86|Ch\u016By\u012Bn W\u00E8il\u00E1i V4 (Chinese)|\u58A8\u6E05\u5F26|Mo Qingxian|\u5922\u7720\u30CD\u30E0|Yumemi Nemu|\u5996 \u9E7F\u5A18|Yao Luniang|\u5C0F\u6797\u62B9\u8336|Kobayashi Matcha|\u5DE1\u97F3\u30EB\u30AB|Megurine Luka|\u5DE1\u97F3\u30EB\u30AB Append (\u03B2)|Megurine Luka Append (\u03B2)|Megurine Luka Append (Beta)|\u5DE1\u97F3\u30EB\u30AB V4X (Hard)|Megurine Luka V4X (Hard)|\u5DE1\u97F3\u30EB\u30AB V4X (Soft)|Megurine Luka V4X (Soft)|\u5DE1\u97F3\u30EB\u30AB V4X (Unknown)|Megurine Luka V4X (Unknown)|\u5DE1\u97F3\u30EB\u30AB V4X English (Soft)|Megurine Luka V4X English (Soft)|\u5DE1\u97F3\u30EB\u30AB V4X English (Straight)|Megurine Luka V4X English (Straight)|\u5FB5\u7FBD\u6469\u67EF|Zhiyu Moke|\u5FC3\u83EF|Xin Hua|\u5FC3\u534E|X\u012Bn Hu\u00E1|\u30B7\u30F3\u30D5\u30A1|\u5FC3\u83EF V4|Xin Hua V4|\u5FC3\u534E V4|X\u012Bn Hu\u00E1 V4|\u5FC3\u83EFV4 \u4E2D\u56FD\u8A9E\u7248|\u5FC3\u534EV4 \u4E2D\u56FD\u8BED\u7248|Xin Hua V4 Chinese|\u5FC3\u83EF \u65E5\u672C\u8A9E\u7248 (Natural)|Xin Hua Japanese (Natural)|\u5FC3\u534E \u65E5\u672C\u8BED\u7248 (Natural)|\u30B7\u30F3\u30D5\u30A1 \u65E5\u672C\u8A9E (Natural)|\u5FC3\u83EF \u65E5\u672C\u8A9E\u7248 (Power)|Xin Hua Japanese (Power)|\u5FC3\u534E \u65E5\u672C\u8BED\u7248 (Power)|\u30B7\u30F3\u30D5\u30A1 \u65E5\u672C\u8A9E (Power)|\u5FC3\u83EF \u65E5\u672C\u8A9E\u7248 (Unknown)|Xin Hua Japanese (Unknown)|\u5FC3\u534E \u65E5\u672C\u8BED\u7248 (Unknown)|\u30B7\u30F3\u30D5\u30A1 \u65E5\u672C\u8A9E (Unknown)|\u60A6\u6210|Yuecheng|King|\u6218\u97F3Lorra|\u6230\u97F3Lorra|\u6226\u97F3Lorra|Zhanyin Lorra|\u661F\u5C18|Xingchen|Stardust|\u661F\u5875|X\u012Bngch\u00E9n|\u674F\u68A8\u30EB\u30CD|Anri Rune|\u674F\u97F3|Anon|\u30A2\u30CE\u30F3|\u6771\u5317\u305A\u3093\u5B50|Touhoku Zunko|Tohoku Zunko|\u6771\u5317\u305A\u3093\u5B50 V4 \u30CA\u30C1\u30E5\u30E9\u30EB|Touhoku Zunko V4 Natural|\u6771\u5317\u305A\u3093\u5B50 V4|Tohoku Zunko V4 Natural|Touhoku Zunko V4|Tohoku Zunko V4|\u685C\u4E43\u305D\u3089 (Unknown)|Haruno Sora (Unknown)|\u685C\u4E43\u305D\u3089 \u30AF\u30FC\u30EB|Haruno Sora (Cool)|\u685C\u4E43\u305D\u3089 \u30CA\u30C1\u30E5\u30E9\u30EB|Haruno Sora (Natural)|\u690D\u6728\u30ED\u30A4\u30C9|Ueki-loid|\u6B4C\u611B\u30E6\u30AD|Kaai Yuki|\u6B4C\u611B\u30E6\u30AD V4|Kaai Yuki V4|\u6B4C\u611B\u30E6\u30AD V4 Natural|Kaai Yuki V4 Natural|\u6B4C\u624B\u97F3\u30D4\u30B3|Utatane Piko|\u6B63\u5CA1\u5C0F\u8C46|Masaoka Azuki|\u6C37\u5C71\u30AD\u30E8\u30C6\u30EB|Hiyama Kiyoteru|\u6C37\u5C71\u30AD\u30E8\u30C6\u30EB V4 (Unknown)|Hiyama Kiyoteru V4 (Unknown)|\u6C37\u5C71\u30AD\u30E8\u30C6\u30EB V4 Natural|Hiyama Kiyoteru V4 Natural|\u6C37\u5C71\u30AD\u30E8\u30C6\u30EB V4 Rock|Hiyama Kiyoteru V4 Rock|\u6D1B\u5929\u4F9D|Luo Tianyi|\u6D1B\u5929\u4F9D V4 (Unknown)|Luo Tianyi V4 (Unknown)|\u6D1B\u5929\u4F9D V4 (\u51DD)|Luo Tianyi V4 (Power)|\u6D1B\u5929\u4F9D V4 (\u840C)|Luo Tianyi V4 (Normal)|\u6D1B\u5929\u4F9D V4 \u65E5\u672C\u8A9E (Normal)|\u30EB\u30A9\u30FB\u30C6\u30F3\u30A4 V4 \u65E5\u672C\u8A9E\u7248 (Normal)|Luo Tianyi V4 Japanese (Normal)|\u6D1B\u5929\u4F9D V4 \u65E5\u672C\u8A9E (Sweet)|Luo Tianyi V4 Japanese (Sweet)|\u30EB\u30A9\u30FB\u30C6\u30F3\u30A4 V4 \u65E5\u672C\u8A9E\u7248 (Sweet)|\u6D1B\u5929\u4F9D V4 \u65E5\u672C\u8A9E\u7248 (Unknown)|Luo Tianyi V4 Japanese (Unknown)|\u30EB\u30A9\u30FB\u30C6\u30F3\u30A4 V4 \u65E5\u672C\u8A9E\u7248 (Unknown)|\u6D1B\u5929\u4F9D V5 (Nature)|Luo Tianyi V5 (Nature)|\u6D1B\u5929\u4F9D V5 (Unknown)|Luo Tianyi V5 (Unknown)|\u732B\u6751\u3044\u308D\u306F|Nekomura Iroha|\u732B\u6751\u3044\u308D\u306F V4 (Natural)|Nekomura Iroha V4 (Natural)|\u732B\u6751\u3044\u308D\u306F V4 (Soft)|Nekomura Iroha V4 (Soft)|\u732B\u6751\u3044\u308D\u306F V4 (Unknown)|Nekomura Iroha V4 (Unknown)|\u795E\u5A01\u304C\u304F\u307D|Kamui Gakupo|Gackpoid|\u304C\u304F\u3063\u307D\u3044\u3069|Camui Gackpo|\u7AE0\u695A\u695A|Zhang Chuchu|\u695A\u695A|\u7D32\u661F\u3042\u304B\u308A|Kizuna Akari|\u7D50\u6708\u3086\u304B\u308A|Yuzuki Yukari|\u7D50\u6708\u3086\u304B\u308A V4 (Unknown)|Yuzuki Yukari V4 (Unknown)|\u7D50\u6708\u3086\u304B\u308A V4 (\u51DB)|Yuzuki Yukari V4 (Lin)|Yuzuki Yukari V4 (Power)|\u7D50\u6708\u3086\u304B\u308A V4 (\u7A4F)|Yuzuki Yukari V4 (Onn)|Yuzuki Yukari V4 (Whisper)|\u7D50\u6708\u3086\u304B\u308A V4 (\u7D14)|Yuzuki Yukari V4 (Jun)|Yuzuki Yukari V4 (Natural)|\u7F8E\u7A7A\u3072\u3070\u308A|Misora Hibari|\u84BC\u59EB\u30E9\u30D4\u30B9|Aoki Lapis|\u8A00\u548C|YANHE|Yan He|\u8A00\u548C V5 (Sweet)|YANHE V5 (Sweet)|Yan He V5 (Sweet)|\u93E1\u97F3\u30EA\u30F3|Kagamine Rin|\u93E1\u97F3\u30EA\u30F3 Append (Power)|Kagamine Rin Append (Power)|\u93E1\u97F3\u30EA\u30F3 Append (Sweet)|Kagamine Rin Append (Sweet)|\u93E1\u97F3\u30EA\u30F3 Append (Unknown)|Kagamine Rin Append (Unknown)|\u93E1\u97F3\u30EA\u30F3 Append (Warm)|Kagamine Rin Append (Warm)|\u93E1\u97F3\u30EA\u30F3 V4 (English)|Kagamine Rin V4 (English)|\u93E1\u97F3\u30EA\u30F3 V4X (Power)|Kagamine Rin V4X (Power)|\u93E1\u97F3\u30EA\u30F3 V4X (Sweet)|Kagamine Rin V4X (Sweet)|\u93E1\u97F3\u30EA\u30F3 V4X (Unknown)|Kagamine Rin V4X (Unknown)|\u93E1\u97F3\u30EA\u30F3 V4X (Warm)|Kagamine Rin V4X (Warm)|\u93E1\u97F3\u30EC\u30F3|Kagamine Len|\u93E1\u97F3\u30EC\u30F3 Append (Cold)|Kagamine Len Append (Cold)|\u93E1\u97F3\u30EC\u30F3 Append (Power)|Kagamine Len Append (Power)|\u93E1\u97F3\u30EC\u30F3 Append (Serious)|Kagamine Len Append (Serious)|\u93E1\u97F3\u30EC\u30F3 Append (Unknown)|Kagamine Len Append (Unknown)|\u93E1\u97F3\u30EC\u30F3 V4 (English)|Kagamine Len V4 (English)|\u93E1\u97F3\u30EC\u30F3 V4X (Cold)|Kagamine Len V4X (Cold)|\u93E1\u97F3\u30EC\u30F3 V4X (Power)|Kagamine Len V4X (Power)|\u93E1\u97F3\u30EC\u30F3 V4X (Serious)|Kagamine Len V4X (Serious)|\u93E1\u97F3\u30EC\u30F3 V4X (Unknown)|Kagamine Len V4X (Unknown)|\u97F3\u8857\u30A6\u30CA (Unknown)|Otomachi Una (Unknown)|\u97F3\u8857\u30A6\u30CA|Otomachi Una|\u97F3\u8857\u30A6\u30CA Spicy|Otomachi Una Spicy|\u97F3\u8857\u30A6\u30CA Sugar|Otomachi Una Sugar|\u9CE5\u97F3|Kanon|\u30AB\u30CE\u30F3|\u9CF4\u82B1\u30D2\u30E1|Meika Hime|\u9CF4\u82B1\u30DF\u30B3\u30C8|Meika Mikoto)(\u3011|\u300D)?";
	private static final Pattern VOCALOIDS_PATTERN = Pattern.compile(VOCALOIDS, Pattern.CASE_INSENSITIVE);

	public static void main(String[] args) throws IOException, HTTPException, JAXBException {
		String youtubeId = "ATnh-S0XcY4";
		YoutubeInfo info = PVService.getYoutubeInfo(youtubeId);

		NicoNicoInfo nico = PVService.findPVUrls(info.getVideoDetails().getShortDescription()).stream().filter(u -> {
			PVService service = PVService.getService(u);
			return /* service.equals(PVService.Youtube) || */ service.equals(PVService.NicoNicoDouga);
		}).map(t -> {
			try {
				return PVService.getNicoInfo(PVService.NicoNicoDouga.getID(t));
			} catch (IOException | JAXBException e) {
				e.printStackTrace();
				return null;
			}
		}).filter(n -> n != null).findFirst().get();

		ProcessedInfo youtubeInfo = processInfo(info);
		ProcessedInfo nicoInfo = processInfo(nico);

		youtubeInfo.getRelevantKeywords().sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));
		nicoInfo.getRelevantKeywords().sort((s1, s2) -> Integer.compare(s2.length(), s1.length()));

		System.out.println("YouTube Results:");
		System.out.println(youtubeInfo);
		EntryQueryBuilder builder = voca.getBuilderProvider().getBuilder(EntryQueryBuilder.class);
		for (String keyword : youtubeInfo.getRelevantKeywords()) {
			builder.resetRecycle();
			builder.setQuery(keyword).setNameMatchMode(NameMatchMode.Exact).includeFields(EntryFields.Names,
					EntryFields.AdditionalNames);
			@SuppressWarnings("unchecked")
			QueriedList<Entry> results = (QueriedList<Entry>) voca.getApiService().completeAndParse(builder.build());
			results.get().forEach(
					entry -> System.out.println(Arrays.stream(entry.getLocalizedNames()).map(LocalizedName::toString)
							.collect(Collectors.joining(",")) + " | " + entry.getEntryType() + " : " + entry.getURL()));
		}
		System.out.println();
		System.out.println("Nico Results:");
		System.out.println(nicoInfo);

		for (String keyword : nicoInfo.getRelevantKeywords()) {
			builder.resetRecycle();
			builder.setQuery(keyword).setNameMatchMode(NameMatchMode.Exact).includeFields(EntryFields.Names,
					EntryFields.AdditionalNames);
			@SuppressWarnings("unchecked")
			QueriedList<Entry> results = (QueriedList<Entry>) voca.getApiService().completeAndParse(builder.build());
			results.get().forEach(
					entry -> System.out.println(Arrays.stream(entry.getLocalizedNames()).map(LocalizedName::toString)
							.collect(Collectors.joining(",")) + " | " + entry.getEntryType() + " : " + entry.getURL()));
		}
		System.exit(0);

		List<Artist> list = new ArrayList<>(), last = new ArrayList<Artist>();
		AtomicInteger integer = new AtomicInteger(0);
		do {
			last = getNext(integer).get();
			list.addAll(last);
		} while (!last.isEmpty());

		for (Artist artist : list) {
			String[] names = artist.getAdditionalNames();
			System.out.println(artist.getName());
			if (names != null && names.length > 0) {
				for (String name : names) {
					System.out.println(name);
				}
			}
			LocalizedName[] names2 = artist.getNames();
			if (names2 != null && names2.length > 0)
				for (LocalizedName name : names2) {
					System.out.println(name.getName());
				}
		}

		System.exit(0);
	}

	private static ProcessedInfo processInfo(NicoNicoInfo info) {
		return processInfo(info.getThumb().getTitle(), info.getThumb().getUserNickname(), info.getThumb().getTags()
				.getList().stream().map(com.cardinal.voca4j.impl.niconico.Tag::getName).collect(Collectors.toList()));
	}

	private static ProcessedInfo processInfo(YoutubeInfo info) {
		return processInfo(info.getVideoDetails().getTitle(), info.getVideoDetails().getAuthor(),
				info.getVideoDetails().getKeywords());
	}

	private static ProcessedInfo processInfo(String title, String channelName, List<String> keywords) {
		List<String> relevantKeywords = new ArrayList<String>();
		Set<String> vocalists = new HashSet<String>();

		Matcher m = VOCALOIDS_PATTERN.matcher(title);
		while (m.find()) {
			vocalists.add(m.group().replaceAll("(" + LEADING_CONTAINERS + "|" + TRAILING_CONTAINERS + ")", ""));
		}

		String escapedAuthor = PatternUtils.escapeSpecialRegexChars(channelName);
		title = m.replaceAll("").trim()
				.replaceAll("(?i)(" + LEADING_CONTAINERS + ")" + escapedAuthor + ".+?(\\s|" + TRAILING_CONTAINERS + ")",
						"")
				.replaceAll("(?i)" + escapedAuthor + ".+?(?=\\s)", "").replaceAll("(?<=\\s)\\s+", "");

		m = VOCALOIDS_PATTERN.matcher(String.join("", keywords));
		while (m.find()) {
			vocalists.add(m.group());
		}

		String caseOfLower = title.toLowerCase();
		for (String keyword : keywords) {
			if (caseOfLower.contains(keyword.toLowerCase())) {
				if (!Pattern.compile(PatternUtils.escapeSpecialRegexChars(keyword), Pattern.CASE_INSENSITIVE)
						.matcher(VOCALOIDS).find())
					relevantKeywords.add(keyword);
			}
		}

		String leftOvers = Pattern
				.compile(relevantKeywords.stream().map(PatternUtils::escapeSpecialRegexChars)
						.collect(Collectors.joining("|")), Pattern.CASE_INSENSITIVE)
				.matcher(title).replaceAll("").trim().replaceAll("(?<=\\s)\\s+", "");
		
		return new ProcessedInfo(relevantKeywords, vocalists, leftOvers);
	}

	public static class ProcessedInfo {
		private List<String> relevantKeywords, vocalists;
		private String remainingTitle;

		protected ProcessedInfo(List<String> relevantKeywords, Collection<String> vocalists, String remainingTitle) {
			this.relevantKeywords = relevantKeywords;
			this.vocalists = new ArrayList<String>(vocalists);
			this.remainingTitle = remainingTitle;
		}

		public List<String> getRelevantKeywords() {
			return relevantKeywords;
		}

		public List<String> getVocalists() {
			return vocalists;
		}

		public String getRemainingTitle() {
			return remainingTitle;
		}

		@Override
		public String toString() {
			return "{ Remaining Title: \"" + remainingTitle + "\" | Relevant Keywords: [ "
					+ relevantKeywords.stream().collect(Collectors.joining(",")) + " ] | Vocalists: [ "
					+ vocalists.stream().collect(Collectors.joining(",")) + " ] }";
		}
	}
}
