package edu.psgv.sweng861.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import edu.psgv.sweng861.API.*;
import edu.psgv.sweng861.common.SearchResults;
import edu.psgv.sweng861.view.MainFrame;
import edu.psgv.sweng861.view.TableClickListenerInterface;

/**
 * @author Jeremy Heath This class is the controller linking the model and the
 *         view together;
 */
public class MusicMagicianController implements ActionListener, TableClickListenerInterface {

	private DeezerAPI model;
	private MainFrame view;
	private SearchResults musicList;
	final int songSearchIndex = 1;
	Thread t = null;

	/**
	 * main() performs the setup
	 * 
	 * @param args None
	 */
	public static void main(String[] args) {
		DeezerAPI model = new DeezerAPI();
		MainFrame view = new MainFrame();
		@SuppressWarnings("unused")
		//bind the controller to the model and the view
		MusicMagicianController controller = new MusicMagicianController(model, view);
		view.setFrameVisible(true);
	}

	/**
	 * MusicMagicianController() is the constructor for associating a model and a
	 * view to the controller
	 * 
	 * @param model is the model for the source data
	 * @param view  is the graphical interface
	 */
	MusicMagicianController(DeezerAPI model, MainFrame view) {
		this.model = model;
		this.view = view;
		this.view.addSearchActionListener(this);
		this.view.registerTableClickListener(this);
	}

	/**
	 * actionPerformed() handles the search action from the view
	 * 
	 * @param e is the action taken
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();
		//set a two second delay on the button
		view.setSearchButtonDelay(2000);

		if (command == "Search") {
			try {

				// ensure that the thread is stopped
				// before starting a new one or an exception will
				// be thrown upon interrupting the thread if it is currently
				// executing, but there is no reason to handle the exception as the next
				// search cleans up already
				if (t != null) {
					t.interrupt();
				}

				if (view.getSelectedSearchCategory() == "Song") {
					musicList = model.searchSongs(view.getSearchValue());
				} else if (view.getSelectedSearchCategory() == "Artist") {
					musicList = model.searchArtists(view.getSearchValue());
				} else if (view.getSelectedSearchCategory() == "Album") {
					musicList = model.searchAlbums(view.getSearchValue());
				} else if (view.getSelectedSearchCategory() == "Artist and Song") {
					musicList = model.searchArtistAndSong(view.getSearchValue(), view.getSearchSongValue());
				} else if (view.getSelectedSearchCategory() == "All") {
					musicList = model.searchAll(view.getSearchValue());
				}

				view.clearTable();
				view.updateTableWithResults(musicList.getResults());
				ResultsThread run = new ResultsThread();
				t = new Thread(run);
				t.start();

			} catch (IOException e1) {
				view.displayErrorMessage("Unable to perform search. Connection error.");
				e1.printStackTrace();
			}
			catch (org.json.JSONException e1) {
				view.displayErrorMessage("Unable to perform search. Data formatting error.");
				e1.printStackTrace();
			}
		}
	}

	/**
	 * handleTableClick() handles the search a table event click and is fired from
	 * the view
	 * 
	 * @param row is the row of the table that was clicked
	 * @param col is the column of the table that was clicked
	 */
	@Override
	public void handleTableClick(int row, int col) {
		if (col == 0) {
			String artistID = musicList.getResults().get(row).getArtistID();
			try {
				view.setArtistFrameData(model.getArtistInformation(artistID));
				view.setArtistDetailVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving artist information. Connection error.");
			} catch (org.json.JSONException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving artist information. Data formatting error.");
			}

		} else if (col == 1) {
			String songID = musicList.getResults().get(row).getSongID();
			try {
				view.setSongFrameData(model.getSongInformation(songID));
				view.setSongDetailVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving song information. Connection error.");
			} catch (org.json.JSONException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving song information. Data formatting error.");
			}
		} else if (col == 2) {
			try {
				String albumID = musicList.getResults().get(row).getAlbumID();
				view.setAlbumFrameData(model.getAlbumInformation(albumID));
				view.setAlbumDetailVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving album information. Connection error.");
			} catch(org.json.JSONException e) {
				e.printStackTrace();
				view.displayErrorMessage("Problem retrieving album information. Data formatting error.");
				
			}
		}
	}

	/**
	 * @author Jeremy Heath This is a helper class that retrieves additional data
	 *         from the API using a separate thread. The run() methods retrieves
	 *         a page of results and then appends those results to the displayed list.
	 *         The table is then updated.
	 *
	 */
	private class ResultsThread implements Runnable {

		@Override
		public void run() {
			while (musicList.getNextResults().length() > 0) {
				SearchResults tempList = null;
				try {
					tempList = model.getNextSearchResults(musicList.getNextResults());
				} catch (Exception e) {
					e.printStackTrace();
					//The thread was interrupted, but this doesn't cause any issues
				}
				//update the list and the table
				musicList.appendResults(tempList.getResults());
				musicList.setNextResults(tempList.getNextResults());
				view.updateTableWithResults(tempList.getResults());
			}
		}
	}
}
