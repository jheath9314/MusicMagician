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

import edu.psgv.sweng861.common.Artist;

/**
 * @author Jeremy Heath
 *
 * This class handles the display logic for showing
 * artist details
 */
public class ArtistDetailFrame {

	@SuppressWarnings("unused")
	private Artist artist;
	private final Dimension size = new Dimension(500, 500);

	private JPanel mainPanel = new JPanel();
	private JFrame mainFrame = new JFrame("Artist Details");
	private JLabel artistLabel = new JLabel("Artist: ");
	private JLabel artistNameLabel = new JLabel();
	private JLabel artistImageLabel = new JLabel("Image: ");
	private JLabel artistImage = new JLabel();
	private JTextArea artistBioLabel = new JTextArea("Biography: ");
	private JTextArea artistBioText = new JTextArea();
	private JScrollPane scrollPane;

	/**
	 * ArtistDetailFrame() handles coordinating the assemble of the
	 * display
	 */
	public ArtistDetailFrame() {
		buildMainPanel();
		buildMainFrame();
	}

	/**
	 * buildMainFrame() constructs the main frame in the display
	 */
	private void buildMainFrame() {
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		mainFrame.add(scrollPane);
		mainFrame.setSize(size);
		mainFrame.setResizable(false);
	}

	/**
	 * buildMainPanel() constructs the main panel for the display,
	 * adding the display components and handling the layout
	 */
	private void buildMainPanel() {
		artistBioText.setLineWrap(true);
		artistBioText.setWrapStyleWord(true);
		artistBioText.setEditable(false);
		ViewUtilities.configureJTextArea(artistBioText);
		ViewUtilities.configureJTextArea(artistBioLabel);
		mainPanel.setLayout(new SpringLayout());
		mainPanel.add(artistLabel);
		mainPanel.add(artistNameLabel);
		mainPanel.add(artistImageLabel);
		mainPanel.add(artistImage);
		mainPanel.add(artistBioLabel);
		mainPanel.add(artistBioText);
		SpringUtilities.makeCompactGrid(mainPanel, // parent
				3, 2, // rows, cols
				5, 5, // initx, inity
				5, 5); // xpad, y pad
		scrollPane = new JScrollPane(mainPanel);
		mainPanel.revalidate();
	}

	/**
	 * setVisible() sets the frame and panels visible
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
	 * setArtistFrameData() sets the data to be displayed
	 * @param artist is the source data
	 */
	public void setArtistFrameData(Artist artist) {
		this.artist = artist;
		artistNameLabel.setText(artist.getName());
		String artistBio = artist.getArtistBio();
		
		if(artistBio.length() == 0) {
			artistBio = "No biography found.";
		} 
		
		artistBioText.setText(artistBio);

		try {
			String path = artist.getArtistImageURL();
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			artistImage.setIcon(new ImageIcon(image));
		} catch (IOException e) {
			e.printStackTrace();
			//If no image is found then a default image is
			//shown. No reason to handle this.
		}
		
	}
	
}
