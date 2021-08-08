package edu.psgv.sweng861.view;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import edu.psgv.sweng861.common.Song;

public class SongDetailFrame {
	
	@SuppressWarnings("unused")
	private Song song;
	private final Dimension size = new Dimension(500, 500);
	
	private JPanel mainPanel = new JPanel();
	private JFrame mainFrame = new JFrame("Song Details");
	private JLabel titleLabel = new JLabel("Song Title: ");
	private JLabel titleTextLabel = new JLabel();
	private JLabel albumLabel = new JLabel("Album: ");
	private JLabel albumText = new JLabel();
	private JLabel releaseDateLabel = new JLabel("Release Date: ");
	private JLabel releaseDateText = new JLabel();
	private JTextArea lyricsLabel = new JTextArea("Lyrics: ");
	private JTextArea lyricsText = new JTextArea();
	private JLabel artistLabel = new JLabel("Artist: ");
	private JLabel artistText = new JLabel();
	private JLabel durationLabel = new JLabel("Duration: ");
	private JLabel durationText = new JLabel();
	private JScrollPane scrollPane;
	
	/**
	 * SongDetailFrame() handles coordinating the assemble of the
	 * display
	 */
	public SongDetailFrame() {
		buildMainPanel();
		buildMainFrame();
	}
	
	/**
	 * buildMainFrame() constructs the main frame in the display
	 */
	private void buildMainFrame() {
		mainFrame.add(scrollPane);
		mainFrame.setSize(500, 500);
		mainFrame.setResizable(false);
	}
	
	/**
	 * buildMainPanel() constructs the main panel for the display,
	 * adding the display components and handling the layout
	 */
	private void buildMainPanel() {
		SpringLayout layout = new SpringLayout();
		
		ViewUtilities.configureJTextArea(lyricsText);
		ViewUtilities.configureJTextArea(lyricsLabel);
		mainPanel.setLayout(layout);
		mainPanel.add(titleLabel);
		mainPanel.add(titleTextLabel);
		mainPanel.add(releaseDateLabel);
		mainPanel.add(releaseDateText);;
		mainPanel.add(artistLabel);
		mainPanel.add(artistText);
		mainPanel.add(albumLabel);
		mainPanel.add(albumText);
		mainPanel.add(durationLabel);
		mainPanel.add(durationText);
		mainPanel.add(lyricsLabel);
		mainPanel.add(lyricsText);
		
		SpringUtilities.makeCompactGrid(mainPanel, // parent
				6, 2, // rows, cols
				5, 5, // initx, inity
				5, 5); // xpad, y pad
		
		scrollPane = new JScrollPane(mainPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.revalidate();
	}
	
	/**
	 * setVisible() sets the frame and panels visible
	 * @param show is whether or not to show the display
	 */
	public void setVisible(boolean show) {
		mainPanel.setVisible(show);
		mainFrame.setVisible(show);
		mainPanel.setVisible(show);
		mainFrame.setVisible(show);
		mainFrame.pack();
		mainFrame.setSize(size);
		mainFrame.revalidate();
		mainFrame.repaint();
		scrollPane.setVisible(show);
		//This is required to ensure that the scroll bar is set to the
		//top after the view has been rendered
		SwingUtilities.invokeLater(new Runnable()
		{
		    public void run()
		    {
		        scrollPane.getViewport().setViewPosition(new Point(0, 0));
		    }
		});
	}
	
	/**
	 * setSongFrameData() sets the song data for the
	 * view to display
	 * @param song is the song data
	 */
	public void setSongFrameData(Song song) {
		this.song = song;
		titleTextLabel.setText(song.getSongName());
		releaseDateText.setText(song.getReleaseDate());
		
		String lyrics = song.getLyrics();
		//The scraper was unable to find lyrics,
		//so let the user know
		if(lyrics.length() == 0) {
			lyrics = "Lyrics not found.";
		}
		
		lyricsText.setText(lyrics);
		artistText.setText(song.getArtistName());
		albumText.setText(song.getAlbumName());
		String songLength = ViewUtilities.formatTime(song.getDuration());
		durationText.setText(songLength);
		mainPanel.revalidate();
	}
}
