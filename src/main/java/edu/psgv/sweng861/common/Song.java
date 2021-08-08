package edu.psgv.sweng861.common;

/**
 * @author Jeremy Heath
 *	This class represents a Song object
 */
public class Song {

	private String artistID;
	private String songName;
	private String songID;
	private String albumID;
	private String albumName;
	private int duration;
	private String lyrics;
	private String releaseDate;
	private String artistName;
	private String lyricsURL;
	
	/**
	 * getSongID()
	 * @return id for the song
	 */
	public String getSongID() {
		return songID;
	}

	/**
	 * setSongID() sets the id for a song
	 * @param songID is the ID for the song
	 */
	public void setSongID(String songID) {
		this.songID = songID;
	}
	
	/**
	 * getLyricsURL()
	 * @return lyrics URL for a song
	 */
	public String getLyricsURL() {
		return lyricsURL;
	}

	/**
	 * setLyricsURL() sets the lyrics URL for a song
	 * @param lyricsURL is the URL for the lyrics
	 */
	public void setLyricsURL(String lyricsURL) {
		this.lyricsURL = lyricsURL;
	}

	/**
	 * getAlbumName()
	 * @return album name for a song
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * setAlbumName() sets the album's name for a song
	 * @param albumName is the name of the song's album
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * getArtistName()
	 * @return artist name for a song
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * setArtistName() sets the artist's name for a song
	 * @param artistName is the name of the song's artist
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * getReleaseDate()
	 * @return release date for the song
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * setReleaseDate() sets the release date of a song
	 * @param releaseDate is the release date of the song
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * getLyrics()
	 * @return lyrics of the song
	 */
	public String getLyrics() {
		return lyrics;
	}

	/**
	 * setLyrics() sets the lyrics of a song
	 * @param lyrics is the lyrics of a song
	 */
	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	/**
	 * getArtistID()
	 * @returns the artist ID
	 */
	public String getArtistID() {
		return artistID;
	}

	/**
	 * setArtistID() sets the artist ID
	 * @param artistID
	 */
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	/**
	 * getAlbumID()
	 * @return the album ID
	 */
	public String getAlbumID() {
		return albumID;
	}

	/**
	 * setAlbumID() sets the album ID
	 * @param albumID
	 */
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}


	/**
	 * getDuration()
	 * @return the song's duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * setDuration() sets the song's duration
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}


	/**
	 * getSongName()
	 * @return the song's name
	 */
	public String getSongName() {
		return songName;
	}

	/**
	 * setSongName() sets the song name
	 * @param songName
	 */
	public void setSongName(String songName) {
		this.songName = songName;
	}

}
