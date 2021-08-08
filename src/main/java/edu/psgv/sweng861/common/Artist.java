package edu.psgv.sweng861.common;

/**
 * @author Jeremy Heath
 *	This class represents an Artist object
 */
public class Artist {
	
	private String name;
	private String artistID;
	private String artistImageURL;
	private String artistBio;

	/**
	 * getArtistBio()
	 * @return the artist's biography
	 */
	public String getArtistBio() {
		return artistBio;
	}

	/**
	 * setArtistBio() sets the artist's biography
	 * @param artistBio is the biography text of the artist
	 */
	public void setArtistBio(String artistBio) {
		this.artistBio = artistBio;
	}

	/**
	 * getArtistImageURL()
	 * @return the URl of the artist's image
	 */
	public String getArtistImageURL() {
		return artistImageURL;
	}

	/**
	 * setArtistImageURL() sets the URL of the artist's image
	 * @param artistImageURL
	 */
	public void setArtistImageURL(String artistImageURL) {
		this.artistImageURL = artistImageURL;
	}

	/**
	 * getArtistID()
	 * @return the artist's ID
	 */
	public String getArtistID() {
		return artistID;
	}

	/**
	 * setArtistID() sets the artist's id
	 * @param artistID
	 */
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}

	/**
	 * getName()
	 * @return the artist's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName() sets the name of the artist
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
	}
}
