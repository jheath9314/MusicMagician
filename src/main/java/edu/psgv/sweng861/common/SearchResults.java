package edu.psgv.sweng861.common;

import java.util.List;

/**
 * @author Jeremy Heath this class holds the search results and the
 * URL to the next page of results
 *
 */
public class SearchResults {
	
	private List<SearchResult> results;
	private String nextResults;

	/**
	 * getNextResults() returns the URL for the next
	 * page of search results
	 * @return URL for next search results
	 */
	public String getNextResults() {
		return nextResults;
	}
	
	public void appendResults(List<SearchResult> results) {
		this.results.addAll(results);
	}

	/**
	 * setNextResults() sets the next page of search results
	 * @param nextResults is the next page of results
	 */
	public void setNextResults(String nextResults) {
		this.nextResults = nextResults;
	}

	/**
	 * getResults() returns the search results
	 * @return the search results
	 */
	public List<SearchResult> getResults() {
		return results;
	}

	/**
	 * getResults() sets the search results
	 * @param results
	 */
	public void setResults(List<SearchResult> results) {
		this.results = results;
	}

}
