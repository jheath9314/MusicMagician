package edu.psgv.sweng861.API;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import edu.psgv.sweng861.common.Album;
import edu.psgv.sweng861.common.Artist;
import edu.psgv.sweng861.common.SearchResult;
import edu.psgv.sweng861.common.Song;
import edu.psgv.sweng861.common.SearchResults;
/**
 * @author Jeremy Heath 
 * This class handles interfacing with the Deezer API
 */
public class DeezerAPI {

	private final String host = "deezerdevs-deezer.p.rapidapi.com";
	private final String key = "";
	private final String urlBase = "https://deezerdevs-deezer.p.rapidapi.com";
	private final String searchURL = "/search?q=";
	private final String getArtistURL = "/artist/";
	private final String getAlbumURL = "/album/";
	private final String getSongURL = "/track/";
	private final String deezerLyricsBaseURL = "https://www.deezer.com/en/track/";
	private final String artistBioBaseURL = "https://www.deezer.com/en/artist";

	private OkHttpClient client = new OkHttpClient();

	private DeezerJSONParser searchJSONParser = new DeezerJSONParser();

	/**
	 * search() interfaces with the Deezer search API and returns the search data
	 * 
	 * @param searchParameters the parameters to search by
	 * @return the JSON object representing the results of the search
	 * @throws IOException if the search cannot be performed
	 */
	private JSONObject search(String searchParameters) throws IOException {

		Request request = new Request.Builder().url(urlBase + searchURL + searchParameters).get()
				.addHeader("x-rapidapi-host", host).addHeader("x-rapidapi-key", key).build();

		return executeRequest(request);
	}
	
	
	/**
	 * getNextSearchResults() handles returning the next page of search
	 * results as a SearchResults object
	 * @param URL is the URL for the next page of results
	 * @return the list of the next search results and a URL for the next
	 * page to search, encapsulated in a SearchResults object
	 * @throws IOException if an exception occurs during data retrieval
	 */
	public SearchResults getNextSearchResults(String URL) throws IOException, org.json.JSONException {
		List<SearchResult> list = new ArrayList<SearchResult>();
		SearchResults searchResults = new SearchResults();

		JSONObject results = search(URL);
		JSONArray resultsArray = results.getJSONArray("data");

		for (int i = 0; i < resultsArray.length(); i++) {

			SearchResult result = searchJSONParser.getSearchResultFromJSON(resultsArray.getJSONObject(i));
			list.add(result);
		}
		
		searchResults.setResults(list);
		//if the results have a "next" item, store that off for future
		//processing, otherwise, store an empty string so the future
		//processing can be ignored
		if(results.has("next")) {
			searchResults.setNextResults(results.getString("next"));
		} else {
			searchResults.setNextResults("");
		}
		
		return searchResults;
	}
	
	/**
	 * searchSongs() searches for songs 
	 * @param parameters is the search query for a song
	 * @return the search results
	 * @throws IOException is an exception that occurs while retrieving the data
	 */
	public SearchResults searchSongs(String parameters) throws IOException {
		String searchString = "track:";
		searchString = searchString + "\"" + parameters + "\"";
		
		return getSearchResults(searchString);
	}
	
	/**
	 * searchArtists() searches for artists 
	 * @param parameters is the search query for an artist
	 * @return the search results
	 * @throws IOException is an that exception occurs while retrieving the data
	 */
	public SearchResults searchArtists(String parameters) throws IOException {
		String searchString = "artist:";
		searchString = searchString + "\"" +  parameters + "\"";
		
		return getSearchResults(searchString);	
	}
	
	/**
	 * searchAlbums() searches for album 
	 * @param parameters is the search query for an album
	 * @return the search results
	 * @throws IOException is an exception that occurs while retrieving the data
	 */
	public SearchResults searchAlbums(String parameters) throws IOException {
		String searchString = "album:";
		searchString = searchString + "\"" +  parameters + "\"";
		
		return getSearchResults(searchString);	
	}
	
	/**
	 * searchAll() searches for all categories, allowing the API
	 * to determine the most relevant results
	 * @param parameters is the search query
	 * @return the search results
	 * @throws IOException is an exception that occurs while retrieving the data
	 */
	public SearchResults searchAll(String parameters) throws IOException {
		String searchString = "album:";
		searchString = searchString + "\"" +  parameters + "\"";
		
		return getSearchResults(searchString);	
	}
	

	/**
	 * searchAlbumAndSong() searches for an artist and a song
	 * @param artist is the artist of the song
	 * @param song is the song to search for
	 * @return the search results of the query
	 * @throws IOException is an exception that occurs while retrieving the data.
	 */
	public SearchResults searchArtistAndSong(String artist, String song) throws IOException {
		String searchString = "artist:" + "\"" + artist + "\"" +" track:" + "\"" + song + "\"";
		
		return getSearchResults(searchString);	
	}
	

	/**
	 * getSearchResults() executes a search and parses through the JSON data
	 * returned and returns a SearchResult object
	 * 
	 * @param searchParameters the search parameters
	 * @return the results of the search
	 * @throws IOException if an IO exception occurs during a search
	 */
	public SearchResults getSearchResults(String searchParameters) throws IOException, org.json.JSONException {

		List<SearchResult> list = new ArrayList<SearchResult>();
		SearchResults searchResults = new SearchResults();

		JSONObject results = search(searchParameters);
		JSONArray resultsArray = results.getJSONArray("data");

		for (int i = 0; i < resultsArray.length(); i++) {

			SearchResult result = searchJSONParser.getSearchResultFromJSON(resultsArray.getJSONObject(i));
			list.add(result);
		}
		
		searchResults.setResults(list);
		
		//store off the next page of results
		if(results.has("next")) {
			searchResults.setNextResults(results.getString("next"));
		}
		else {
			searchResults.setNextResults("");
		}
		

		return searchResults;
	}

	/**
	 * executeRequest()
	 * 
	 * @param request the request to be executed
	 * @return the JSONObject received from the Deezer API
	 * @throws IOException if an IO exception occurs
	 */
	public JSONObject executeRequest(Request request) throws IOException {

		Response response = client.newCall(request).execute();
		String rawResponseString = response.body().string();
		JSONObject responseJSON = new JSONObject(rawResponseString);
		return responseJSON;
	}

	/**
	 * getArtistInformation() executes a lookup of an Artist using the Deezer API
	 * and returns an artist object
	 * 
	 * @param artistID is the ID used by the deezer API for the artist
	 * @return the API search results as an Artist Object
	 * @throws IOException if an IO exception occurs
	 */
	public Artist getArtistInformation(String artistID) throws IOException, org.json.JSONException {

		Request request = new Request.Builder()
				.url("https://deezerdevs-deezer.p.rapidapi.com" + getArtistURL + artistID).get()
				.addHeader("x-rapidapi-host", host).addHeader("x-rapidapi-key", key).build();

		JSONObject artistJSON = executeRequest(request);

		Artist artist = searchJSONParser.getArtistFromJSON(artistJSON);
		String artistBio = scrapeBiography(artistBioBaseURL + "/" + artist.getArtistID() + "/biography");
		artist.setArtistBio(artistBio);
		return artist;

	}

	/**
	 * getAlbumInformation() executes a lookup of an album using the Deezer API and
	 * returns an album object
	 * 
	 * @param albumID is the ID used by the deezer API for the album
	 * @return the API search results as an album Object
	 * @throws IOException if an IO exception occurs
	 * @throws org.json.JSONException is a JSON exception occurs
	 */
	public Song getSongInformation(String songID) throws IOException, org.json.JSONException {

		Request request = new Request.Builder().url("https://deezerdevs-deezer.p.rapidapi.com" + getSongURL + songID)
				.get().addHeader("x-rapidapi-host", host).addHeader("x-rapidapi-key", key).build();

		JSONObject songJSON = executeRequest(request);

		Song song = searchJSONParser.getSongFromJSON(songJSON);
		String lyrics = scrapeLyrics(deezerLyricsBaseURL + song.getSongID());
		song.setLyrics(lyrics);
		return song;
	}
	
	/**
	 * getAlbumInformation() executes a lookup of an album using the Deezer API and
	 * returns an album object
	 * 
	 * @param albumID is the ID used by the deezer API for the album
	 * @return the API search results as an album Object
	 * @throws IOException if an IO exception occurs
	 */
	public Album getAlbumInformation(String albumID) throws IOException, org.json.JSONException {

		Request request = new Request.Builder().url("https://deezerdevs-deezer.p.rapidapi.com" + getAlbumURL + albumID)
				.get().addHeader("x-rapidapi-host", host).addHeader("x-rapidapi-key", key).build();

		JSONObject albumJSON = executeRequest(request);

		Album album = searchJSONParser.getAlbumFromJSON(albumJSON);
		return album;
	}

	/**
	 * scrapeLyrics() scrapes lyrics from a given URL of a deezer song
	 * 
	 * @param lyricsURL the URL to scrape from
	 * @return the lyrics, cleaned of all HTML
	 * @throws IOException if an IO exception occurs
	 */
	public String scrapeLyrics(String lyricsURL) throws IOException {

		Document doc = Jsoup.connect(lyricsURL)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com").get();

		// select the lyrics based on the tag number
		//based on the tag containing "Lyrics." Note that this is screen
		//scraping and may not always be reliable Additionally,
		//not all songs have lyrics
		Elements lyricsHTML = doc.getElementsContainingOwnText("Lyrics");
		
		if (lyricsHTML.size() > 0) {
			Element lyricHTML = lyricsHTML.get(0);
			String lyrics = lyricHTML.nextElementSibling().toString();

			// remove HTML tags
			return Jsoup.parse(lyrics).text();
			
		} else {
			return "";
		}
	}
	
	/**
	 * scrapeBiography() scrapes the HTML for the given artist's biography.
	 * @param bioURL is the URL of the Biography to scrape
	 * @return a string contain the artists biography
	 * @throws IOException is the biography cannot be found
	 */
	public String scrapeBiography(String bioURL) throws IOException {
		Document doc = Jsoup.connect(bioURL)
				.userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
				.referrer("http://www.google.com").get();
		
		//select the biography based on the tag. Note that this is screen scraping and may
		//not always be reliable. Additionally, not all artists have biographies.
		String bioHTML = doc.select("div#naboo_artist_biography_resume_premium").toString();
		
		return Jsoup.parse(bioHTML).text();
	}
}
