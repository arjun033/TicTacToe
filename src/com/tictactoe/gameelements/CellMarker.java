package com.tictactoe.gameelements;

/**
 * This is an enum containing the possible values
 * of the markers that are allowed on the board
 * @author arjunb
 *
 */
public enum CellMarker {
	CROSS("X"), NOUGHT("O"), EMPTY("."), ILLEGAL("");
	
	private final String identifier;
	
	/**
	 * This is the constructor for the enum that initializes
	 * the members with their values
	 * @param identifier
	 */
    private CellMarker (String identifier) { 
    	this.identifier = identifier; 
    }
    
    /**
     * This method returns the values associated with the
     * enum constants
     * @return String value of the enum constant
     */
    public String getCellMarkerValue() { 
    	return this.identifier; 
    }
}
