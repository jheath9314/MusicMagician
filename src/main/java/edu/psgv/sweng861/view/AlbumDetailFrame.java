package edu.psgv.sweng861.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import edu.psgv.sweng861.common.Album;

/**
 * @author Jeremy Heath This class handles the display logic for showing album
 *         details
 *
 */
public class AlbumDetailFrame {

	@SuppressWarnings("unused")
	private Album album;
	private final Dimension size = new Dimension(500, 500);

	private JPanel mainPanel = new JPanel();
	private JFrame mainFrame = new JFrame("Album Details");
	private JScrollPane scrollPane;

	private JLabel titleLabel = new JLabel("Title: ");
	private JLabel title = new JLabel();
	private JLabel albumImageLabel = new JLabel("Image: ");
	private JLabel albumImage = new JLabel();
	private JLabel numberOfTracksLabel = new JLabel("Number of Tracks: ");
	private JLabel numberOfTracks = new JLabel();
	private JLabel releaseDateLabel = new JLabel("Release Date: ");
	private JLabel releaseDate = new JLabel();
	private JLabel durationLabel = new JLabel("Duration: ");
	private JLabel duration = new JLabel();
	private JLabel artistLabel = new JLabel("Artist: ");
	private JLabel artist = new JLabel();
	private JTextArea tracksLabel = new JTextArea("Tracks: ");
	private JTextArea tracks = new JTextArea();

	/**
	 * AlbumDetailFrame() is the constructor that coordinates setting up the main
	 * frame
	 */
	public AlbumDetailFrame() {
		buildMainPanel();
		buildMainFrame();
	}

	/**
	 * buildMainFrame() handles the actual building of the main frame
	 */
	private void buildMainFrame() {
		mainFrame.add(scrollPane);
		mainFrame.setSize(500, 500);
		mainFrame.setResizable(false);

	}

	/**
	 * buildMainPanel() adds all of the components and handles the layout of the
	 * main panel
	 */
	private void buildMainPanel() {

		mainPanel.setLayout(new SpringLayout());
		configureJTextArea(tracks);
		configureJTextArea(tracksLabel);
		mainPanel.add(titleLabel);
		mainPanel.add(title);
		mainPanel.add(albumImageLabel);
		mainPanel.add(albumImage);
		mainPanel.add(durationLabel);
		mainPanel.add(duration);
		mainPanel.add(releaseDateLabel);
		mainPanel.add(releaseDate);
		mainPanel.add(artistLabel);
		mainPanel.add(artist);
		mainPanel.add(numberOfTracksLabel);
		mainPanel.add(numberOfTracks);
		mainPanel.add(tracksLabel);
		mainPanel.add(tracks);
		SpringUtilities.makeCompactGrid(mainPanel, // parent
				7, 2, // rows, cols
				5, 5, // initx, inity
				5, 5); // xpad, y pad
		
		scrollPane = new JScrollPane(mainPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.revalidate();
	}

	/**
	 * setVisible() sets the frame and panels visible
	 * 
	 * @param show is whether or not to show the display
	 */
	public void setVisible(boolean show) {
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
	 * setAlbumFrameData() sets the data to be displayed
	 * 
	 * @param album is the source data
	 */
	public void setAlbumFrameData(Album album) {
		this.album = album;
		artist.setText(album.getArtistName());
		title.setText(album.getAlbumTitle());
		numberOfTracks.setText(album.getNumberOfTracks());
		releaseDate.setText(album.getReleaseDate());
		String albumLength = ViewUtilities.formatTime(Integer.parseInt(album.getDuration()));
		duration.setText(albumLength);

		String tracksText = "";

		//Add the tracks to the text will a new line character
		for (int i = 0; i < album.getTracks().size(); i++) {
			tracksText = tracksText + album.getTracks().get(i);
			if (i != album.getTracks().size() - 1) {
				tracksText = tracksText + System.lineSeparator();
			}
		}

		tracks.setText(tracksText);
		try {
			String path = album.getAlbumImageURL();
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			albumImage.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			//If no image is found, a default
			//image is shown automatically,
			//no reason to handle this
			e.printStackTrace();
		}
	}

	/**
	 * configureJTextArea() handles common configuration of the JTextArea for
	 * display
	 * 
	 * @param text is the JTextArea to configure
	 */
	private void configureJTextArea(JTextArea text) {
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setFont(artistLabel.getFont());
		text.setBackground(artistLabel.getBackground());
	}

}
