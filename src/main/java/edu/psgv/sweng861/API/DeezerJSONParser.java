package edu.psgv.sweng861.API;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.psgv.sweng861.common.Album;
import edu.psgv.sweng861.common.Artist;
import edu.psgv.sweng861.common.SearchResult;
import edu.psgv.sweng861.common.Song;

/**
 * @author Jeremy Heath
 * This class handles parsing JSON received from the Deezer API
 *
 */
public class DeezerJSONParser {

	
	/**
	 * getArtistFromJSON() parses the artist JSON and
	 * returns an Artist object
	 * @param json from the Deezer API
	 * @return the Artist object representing the JSON
	 * @throws org.json.JSONException if a JSON exception occurs
	 */
	public Artist getArtistFromJSON(JSONObject json) throws org.json.JSONException {
		Artist artist = new Artist();
		artist.setName(json.getString("name"));
		artist.setArtistID(Integer.toString(json.getInt("id")));
		artist.setArtistImageURL(json.getString("picture"));
		return artist;
	}
	

	/**
	 * getAlbumFromJSON() parses the album JSON and
	 * returns an Album object
	 * @param json from the Deezer API
	 * @return the Album object representing the JSON
	 * @throws org.json.JSONException if a JSON exception occurs
	 */
	public Album getAlbumFromJSON(JSONObject json) throws org.json.JSONException {

		//fill the album with the content from the JSON
		List<String> trackList = new ArrayList<String>();
		Album album = new Album();
		album.setAlbumID(Integer.toString(json.getInt("id")));
		album.setAlbumTitle(json.getString("title"));
		album.setAlbumImageURL(json.getString("cover"));
		album.setReleaseDate(json.getString("release_date"));
		album.setArtistName(json.getJSONObject("artist").getString("name"));
		album.setNumberOfTracks(Integer.toString(json.getInt("nb_tracks")));
		album.setDuration(Integer.toString(json.getInt("duration")));
		
		//set the tracks
		JSONArray jsonTracks; 
		jsonTracks = json.getJSONObject("tracks").getJSONArray("data");
		
		for(int i = 0; i < jsonTracks.length(); i++) {
			String trackTitle = jsonTracks.getJSONObject(i).getString("title");
			trackList.add(trackTitle);
		}
		
		album.setTracks(trackList);
		
		return album;
	}
	
	
	/**
	 * getSongFromJSON() returns the Song from the JSON data.
	 * @param json is the JSON data
	 * @return the Song from the data
	 * @throws org.json.JSONException if a JSON exception occurs
	 */
	public Song getSongFromJSON(JSONObject json) throws org.json.JSONException {


		Song song = new Song();
		//fill the song with the content from the JSON
		song.setAlbumName(json.getJSONObject("album").getString("title"));
		song.setAlbumID(Integer.toString(json.getJSONObject("album").getInt("id")));
		song.setSongName(json.getString("title"));
		song.setDuration(json.getInt("duration"));
		song.setReleaseDate(json.getString("release_date"));
		song.setArtistID(Integer.toString(json.getJSONObject("artist").getInt("id")));
		song.setArtistName(json.getJSONObject("artist").getString("name"));
		song.setLyricsURL(json.getString("link"));
		song.setSongID(Integer.toString(json.getInt("id")));

		return song;
	}
	
	
	/**
	 * getSearchResultFromJSON() parses the search result JSON and
	 * returns a SearchResult object
	 * @param json from the Deezer API
	 * @return the SearchResult object representing the JSON
	 * @throws org.json.JSONException if a JSON exception occurs
	 */
	public SearchResult getSearchResultFromJSON(JSONObject json) throws org.json.JSONException {
		
		//fill the search result with the JSON content. Although the API returns
		//a fuller list of information, only store the display data and IDs for future
		//use. Other API requests will handle gathering the additional data
		SearchResult result = new SearchResult();
		result.setAlbumID(Integer.toString(json.getJSONObject("album").getInt("id")));
		result.setAlbumName(json.getJSONObject("album").getString("title"));
		result.setSongID(Integer.toString(json.getInt("id")));
		result.setSongName(json.getString("title"));
		result.setArtistID(Integer.toString(json.getJSONObject("artist").getInt("id")));
		result.setArtistName((json.getJSONObject("artist").getString("name")));
		
		return result;
	}

}
