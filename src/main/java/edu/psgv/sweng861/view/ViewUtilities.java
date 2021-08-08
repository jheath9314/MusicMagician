package edu.psgv.sweng861.view;

import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @author Jeremy Heath
 * This class handles the utilities for formatting
 * display elements
 *
 */
public class ViewUtilities {
	
	private static JLabel defaultLabel = new JLabel();

	/**
	 * formatTime() handles formatting time for display
	 * @param duration is the duration in seconds
	 * @return a string representing a duration formatted
	 * as minutes:seconds
	 */
	public static  String formatTime(int duration) {
		int minutes = duration / 60;
		int seconds = duration % 60;
		String formattedSeconds;
		
		if(seconds < 10) {
			formattedSeconds = "0" + Integer.toString(seconds);
		} else {
			formattedSeconds = Integer.toString(seconds);
		}
		
		String time = Integer.toString(minutes) + ":" + formattedSeconds;
		
		return time;
	}
	
	/**
	 * configureJTextArea() handles common configuration
	 * of the JTextArea for display
	 * @param text is the JTextArea to configure
	 */
	public static void configureJTextArea(JTextArea text) {
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setFont(defaultLabel.getFont());
		text.setBackground(defaultLabel.getBackground());
	}
}
