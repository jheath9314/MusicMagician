package edu.psgv.sweng861.view;

import edu.psgv.sweng861.common.Album;
import edu.psgv.sweng861.common.Artist;
import edu.psgv.sweng861.common.SearchResult;
import edu.psgv.sweng861.common.Song;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * @author Jeremy Heath This class handles the main search and display view for
 *         the application
 *
 */
public class MainFrame {

	private JFrame mainFrame = new JFrame("Music Magician");
	private JButton searchButton = new JButton("Search");
	private JComboBox<String> searchCategoryBox;
	private JPanel searchPanel = new JPanel();
	private JTextField searchTextField = new JTextField(20);
	private JTextField searchSongTextField = new JTextField(20);
	private JLabel searchLabel = new JLabel("Search: ");
	private JPanel resultsPanel = new JPanel();
	private JTable resultsTable;
	private JLabel searchSongLabel = new JLabel("Search Song: ");
	private JLabel searchArtistLabel = new JLabel("Search Artist: ");
	private ArtistDetailFrame artistDetailFrame = new ArtistDetailFrame();
	private AlbumDetailFrame albumDetailFrame = new AlbumDetailFrame();
	private SongDetailFrame songDetailFrame = new SongDetailFrame();
	private List<TableClickListenerInterface> tableClickListeners = new ArrayList<TableClickListenerInterface>();
	
	private final int ArtistAndSongIndex = 3;
	

	/**
	 * registerTableClickListener() allows registering for table click events
	 * 
	 * @param listener is the object that is called when a click event occurs
	 */
	public void registerTableClickListener(TableClickListenerInterface listener) {
		tableClickListeners.add(listener);
	}

	/**
	 * updateTableWithResults() sets the source data of the table
	 * 
	 * @param resultsList is the source data to display int he table
	 */
	public void updateTableWithResults(List<SearchResult> resultsList) {

		if (resultsList.size() == 0) {
			displayErrorMessage("No Results Found");
		} else {
			DefaultTableModel tableModel = (DefaultTableModel) resultsTable.getModel();

			//populate the table data
			for (int i = 0; i < resultsList.size(); i++) {
				String[] data = new String[3];

				data[0] = resultsList.get(i).getArtistName();
				data[1] = resultsList.get(i).getSongName();
				data[2] = resultsList.get(i).getAlbumName();

				tableModel.addRow(data);
			}
			//alert the table that the data has changed
			resultsTable.setModel(tableModel);
			tableModel.fireTableDataChanged();
		}
	}
	
	/**
	 * displayErrorMessage() is used to display an error
	 * @param message is the message to display
	 */
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(mainFrame, message);
	}

	/**
	 * buildResultsPanel() handles constructing the panel and table for displaying
	 * search results
	 */
	@SuppressWarnings("serial")
	private void buildResultsPanel() {

		String[] columnNames = { "Artist", "Song", "Album" };
		int numRows = 0;
		DefaultTableModel model = new DefaultTableModel(numRows, columnNames.length);
		model.setColumnIdentifiers(columnNames);
		//Make sure that the user cannot edit the table data
		resultsTable = new JTable(model) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		//Handle the click event
		resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = resultsTable.rowAtPoint(evt.getPoint());
				int col = resultsTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {

					for (int i = 0; i < tableClickListeners.size(); i++) {
						tableClickListeners.get(i).handleTableClick(row, col);
					}
				}
			}
		});

		JScrollPane tableContainer = new JScrollPane(resultsTable);
		resultsPanel.add(tableContainer);
		resultsPanel.setVisible(true);
	}

	/**
	 * setArtistFrameData() sets the artist display data for the artist detail frame
	 * 
	 * @param artist is the artist source data
	 */
	public void setArtistFrameData(Artist artist) {
		artistDetailFrame.setArtistFrameData(artist);
	}

	/**
	 * setAlbumFrameData() sets the album display data for the album detail frame
	 * 
	 * @param album is the artist source data
	 */
	public void setAlbumFrameData(Album album) {
		albumDetailFrame.setAlbumFrameData(album);
	}

	/**
	 * setAlbumFrameData() sets the album display data for the album detail frame
	 * 
	 * @param album is the artist source data
	 */
	public void setSongFrameData(Song song) {
		songDetailFrame.setSongFrameData(song);
	}

	/**
	 * getSelectedSearchCategory() returns the search category selected in the view
	 * 
	 * @return the selected search cateogy
	 */
	public String getSelectedSearchCategory() {
		return searchCategoryBox.getSelectedItem().toString();
	}

	/**
	 * handleComboBoxChange() handles UI updates when the search category combo box
	 * changes
	 */
	private void handleComboBoxChange() {
		int selectedIndex = searchCategoryBox.getSelectedIndex();

		// the index of the song and artist value, so we need both fields for
		// searching
		if (selectedIndex == ArtistAndSongIndex) {
			searchPanel.removeAll();
			buldSearchPanelForArtistAndSong();
			searchPanel.validate();
		} else {
			searchPanel.removeAll();
			buildSearchPanelForSearching();
			searchPanel.validate();
		}
	}

	/**
	 * buildSearchBox() constructs the search combo box
	 */
	private void buildSearchBox() {
		String[] searchArray = { "Song", "Artist", "Album", "Artist and Song", "All" };
		searchCategoryBox = new JComboBox<String>(searchArray);
		searchCategoryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				handleComboBoxChange();
				mainFrame.repaint();
			}
		});
	}

	/**
	 * buildFrame() sets the layout and adds components to the main frame
	 */
	private void buildFrame() {

		mainFrame.setLayout(new BorderLayout());

		mainFrame.add(searchPanel, BorderLayout.PAGE_START);
		mainFrame.add(resultsPanel, BorderLayout.CENTER);
		mainFrame.setSize(850, 600);
	}

	/**
	 * buildSearchPanel() handles constructing the layout and adding components for
	 * the search panel
	 */
	private void buildSearchPanel() {

		searchPanel.setLayout(new FlowLayout());
		buildSearchPanelForSearching();
		searchPanel.setVisible(true);
	}

	/**
	 * buildSearchPanelForSearching() handles reconstructing the search panel based
	 * on the combo box selection
	 */
	private void buildSearchPanelForSearching() {
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(searchLabel);
		searchPanel.add(searchTextField);
		searchPanel.add(searchCategoryBox);
		searchPanel.add(searchButton);
		searchPanel.setVisible(true);
	}

	/**
	 * buildSearchPanelForSearching() handles reconstructing the search panel based
	 * on the combo box selection
	 */
	private void buldSearchPanelForArtistAndSong() {
		searchPanel.setLayout(new FlowLayout());
		searchPanel.add(searchArtistLabel);
		searchPanel.add(searchTextField);
		searchPanel.add(searchSongLabel);
		searchPanel.add(searchSongTextField);
		searchPanel.add(searchCategoryBox);
		searchPanel.add(searchButton);
		searchPanel.setVisible(true);
	}

	/**
	 * setSearchPanelVisible() sets the search panel visible
	 * 
	 * @param visible is used to determine whether or not to show the search panel
	 */
	public void setSearchPanelVisible(boolean visible) {

		searchPanel.setVisible(visible);
	}

	/**
	 * setFrameVisible() sets the main frame visible
	 * 
	 * @param visible is used to determine whether or not to show the main frame
	 */
	public void setFrameVisible(boolean visible) {
		mainFrame.setVisible(visible);
	}

	/**
	 * MainFrame() constructs the main frame
	 */
	public MainFrame() {
		buildSearchBox();
		buildSearchPanel();
		buildResultsPanel();
		buildFrame();
		setSearchPanelVisible(true);
		mainFrame.getRootPane().setDefaultButton(searchButton);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		resultsTable.getTableHeader().setReorderingAllowed(false);
	}

	/**
	 * addSearchActionListener() adds a listener to the search button event
	 * 
	 * @param listener is the listener to handle the search button event
	 */
	public void addSearchActionListener(ActionListener listener) {
		searchButton.addActionListener(listener);
	}

	/**
	 * getSearchValue returns the value of the search text field
	 * 
	 * @return the value of the search text field
	 */
	public String getSearchValue() {
		return searchTextField.getText();
	}

	/**
	 * getSearchSongValue returns the value of the song search text field
	 * 
	 * @return the value of the song search text field
	 */
	public String getSearchSongValue() {
		return searchSongTextField.getText();
	}

	/**
	 * clearTable() clears the table of all data
	 */
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
		model.setRowCount(0);
	}

	/**
	 * setArtistDetailVisible() displays the artist detail frame
	 * 
	 * @param visible is used to determine whether or not to show the artist detail
	 *                frame
	 */
	public void setArtistDetailVisible(boolean visible) {
		artistDetailFrame.setVisible(visible);
	}

	/**
	 * setAlbumDetailVisible() displays the album detail frame
	 * 
	 * @param visible is used to determine whether or not to show the album detail
	 *                frame
	 */
	public void setAlbumDetailVisible(boolean visible) {
		albumDetailFrame.setVisible(visible);
	}

	/**
	 * setSongDetailVisible() displays the song detail frame
	 * 
	 * @param visible is used to determine whether or not to show the song detail
	 *                frame
	 */
	public void setSongDetailVisible(boolean visible) {
		songDetailFrame.setVisible(visible);
	}
	
	/**
	 * setSearchButtonDelay() prohibit the user from rapidly clicking the button
	 * @param delay is how long to delay in milliseconds
	 */
	public void setSearchButtonDelay(int delay) {
		Timer timer = new Timer (delay, new ActionListener() {
			//enable the button after the timer signals
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButton.setEnabled(true);
			}
		});
		
		timer.setRepeats(false);
		//disable the button after a click until the timer expires
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchButton.setEnabled(false);
				timer.start();
			}
			
		});
	}
}
