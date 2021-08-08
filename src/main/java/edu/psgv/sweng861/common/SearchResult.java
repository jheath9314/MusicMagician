package edu.psgv.sweng861.common;

/**
 * @author Jeremy Heath This class represents the object returned from a search
 */
public class SearchResult {

	private String artistName;
	private String artistID;
	private String songName;
	private String songID;
	private String albumName;
	private String albumID;
	
	/**
	 * getArtistID()
	 * @return the artist ID
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
	 * getSongID()
	 * @return the song ID
	 */
	public String getSongID() {
		return songID;
	}

	/**
	 * setSongID()
	 * @param songID
	 */
	public void setSongID(String songID) {
		this.songID = songID;
	}

	/**
	 * getAlbumName()
	 * @return the album name
	 */
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * setAlbumName() sets the album name
	 * @param albumName
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
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
	 * getSongName() 
	 * @return the song name
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

	/**
	 * getArtistName
	 * @return the artist name
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * setArtistName() sets the artist name
	 * @param artistName
	 */
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	

}
