package com.tictactoe.gameelements;

/**
 * This class represents each cell of the game board.
 * @author arjunb
 *
 */
public class Cell {
	private CellMarker cellMarker;
	
	/**
	 * This is the constructor for the class. It initializes
	 * each cell with an EMPTY marker
	 */
	public Cell() {
		this.cellMarker = CellMarker.EMPTY;
	}
	
	/**
	 * This method sets the marker for a cell object
	 * @param cellMarker Marker for the cell
	 */
	public void setCellMarker(CellMarker cellMarker){
		this.cellMarker = cellMarker;
	}
	
	/**
	 * This method returns the marker for a cell object
	 * @return Marker for a cell
	 */
	public CellMarker getCellMarker() {
		return this.cellMarker;
	}
	
	/**
	 * This method checks if the cell has not yet been used
	 * in the game
	 * @return True if the cell is empty else false
	 */
	public boolean isCellEmpty() {
		return this.cellMarker == CellMarker.EMPTY ;
	}
}
