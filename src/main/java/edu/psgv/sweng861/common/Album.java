package edu.psgv.sweng861.common;

import java.util.List;

/**
 * @author Jeremy Heath 
 * This class represents an Album object
 *
 */
public class Album {

	private String albumTitle;
	private String albumImageURL;
	private String numberOfTracks;
	private String releaseDate;
	private String artistName;
	private List<String> tracks;
	private String albumID;
	private String duration;

	/**
	 * getAlbumTitle()
	 * 
	 * @return the album title
	 */
	public String getAlbumTitle() {
		return albumTitle;
	}

	/**
	 * setAlbumTitle() sets the album title
	 * 
	 * @param albumTitle
	 */
	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	/**
	 * getAlbumID()
	 * 
	 * @return the album ID
	 */
	public String getAlbumID() {
		return albumID;
	}

	/**
	 * setAlbumID() sets the album ID
	 * 
	 * @param albumID
	 */
	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}

	/**
	 * getAlbumImageURL()
	 * 
	 * @return the URL location of the album's image
	 */
	public String getAlbumImageURL() {
		return albumImageURL;
	}

	/**
	 * setAlbumImageURL() sets the album's image URL
	 * 
	 * @param albumImageURL
	 */
	public void setAlbumImageURL(String albumImageURL) {
		this.albumImageURL = albumImageURL;
	}

	/**
	 * getNumberOfTracks()
	 * 
	 * @return the number of tracks on the album
	 */
	public String getNumberOfTracks() {
		return numberOfTracks;
	}

	/**
	 * setNumberOfTracks() sets the number of tracks for the album
	 * 
	 * @param numberOfTracks
	 */
	public void setNumberOfTracks(String numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	/**
	 * getReleaseDate()
	 * 
	 * @return the album's release date
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * setReleaseDate() sets the album release date
	 * 
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * getArtistName()
	 * 
	 * @return the artist's name
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * setArtistName() sets the artist's name
	 * 
	 * @param artistName
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	/**
	 * getTracks()
	 * 
	 * @return a list of the album's tracks
	 */
	public List<String> getTracks() {
		return tracks;
	}

	/**
	 * setTracks() sets a list of the album's tracks
	 * 
	 * @param tracks
	 */
	public void setTracks(List<String> tracks) {
		this.tracks = tracks;
	}

	/**
	 * getDuration() gets the duration for the album
	 * @return the duration in seconds
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * setDuration() sets the duration for the album
	 * @param duration in seconds
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
}
