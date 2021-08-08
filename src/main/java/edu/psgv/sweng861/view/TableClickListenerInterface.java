package edu.psgv.sweng861.view;

/**
 * @author Jeremy Heath
 * This interface is used to alert a listener of a table click event
 *
 */
public interface TableClickListenerInterface {
	
	/**
	 * handleTableClick() is a interface called upon a table 
	 * click
	 * @param row is the clicked row
	 * @param col is the clicked column
	 */
	public void handleTableClick(int row, int col);

}
