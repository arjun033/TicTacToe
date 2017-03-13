package com.tictactoe.gameelements;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the class that represents each player playing
 * the game
 * @author arjunb
 *
 */
public class Player {
	private boolean isHuman;
	private CellMarker playerMarker;
	
	/**
	 * This is the constructor for a player object
	 * @param isHuman This is a boolean variable which is true if the 
	 * player is playing the game through the console
	 * @param playerMarker This denotes whether the player is playing
	 * with a CROSS or a NOUGHT
	 */
	public Player(boolean isHuman, CellMarker playerMarker) {
		this.isHuman = isHuman;
		this.playerMarker = playerMarker;
	}
	
	/**
	 * This method returns true if the player object is a human player
	 * else returns false
	 * @return If human, true, else false
	 */
	public boolean isHuman() {
		return isHuman;
	}
	
	/**
	 * This method sets the property of the object which
	 * denotes if the player is a human player or not
	 * @param isHuman Boolean variable which is true if the
	 * player is a human player
	 */
	public void setHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}
	
	/**
	 * This method returns the marker for the player object
	 * @return Marker for the player object
	 */
	public CellMarker getPlayerMarker() {
		return playerMarker;
	}
	
	/**
	 * This method sets the marker for the player object
	 * @param playerMarker The marker for the player object
	 */
	public void setPlayerMarker(CellMarker playerMarker) {
		this.playerMarker = playerMarker;
	}
	
	/**
	 * This method makes a move on the board for the human player
	 * @param gameBoard This is the current status of the board
	 * @param cellNum This is the choice of move that the human
	 * player has made
	 * @return The updated game board after making the move
	 */
	public GameBoard makeMove(GameBoard gameBoard, int cellNum) {
		Cell[][] currentBoard = gameBoard.getGameBoard();
		int rowNum = (cellNum-1)/currentBoard[0].length;
		int colNum = (cellNum-1)%currentBoard[0].length;
		currentBoard[rowNum][colNum].setCellMarker(this.playerMarker);
		gameBoard.setGameBoard(currentBoard);
		return gameBoard;
	}
	
	/**
	 * This method makes a move on the board for the computer
	 * @param gameBoard This is the current status of the board
	 * @return The updated game board after making the move
	 */
	public GameBoard makeMove(GameBoard gameBoard) {
		Cell[][] currentBoard = gameBoard.getGameBoard();
		ArrayList<Integer> validMoves = gameBoard.getValidMoves();
		int randomMove = this.getRandomNumber(validMoves.size());
		int cellNum = validMoves.get(randomMove);
		int rowNum = (cellNum-1)/currentBoard[0].length;
		int colNum = (cellNum-1)%currentBoard[0].length;
		currentBoard[rowNum][colNum].setCellMarker(this.playerMarker);
		gameBoard.setGameBoard(currentBoard);
		return gameBoard;
	}
	
	/**
	 * This is a utility method for generating a random number between
	 * zero and max
	 * @param max The generated random number will be smaller than max
	 * @return The generated random number
	 */
	public int getRandomNumber(int max) {
		return ThreadLocalRandom.current().nextInt(0, max);
	}
}
