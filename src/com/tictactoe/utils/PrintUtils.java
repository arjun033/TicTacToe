package com.tictactoe.utils;

import com.tictactoe.gameelements.Cell;
import com.tictactoe.gameelements.GameBoard;
import com.tictactoe.gameelements.Player;

/**
 * This is a utility class that contains different methods
 * to print game data on the console
 * @author arjunb
 *
 */
public class PrintUtils {
	
	/**
	 * This method prints the game instructions at the beginning of the game
	 * @param k The number of contiguous markers needed for victory
	 */
	public static void printGameInstructions(int k) {
		System.out.println();
		System.out.println("*********** TIC-TAC-TOE GAME ***********");
		System.out.println("**");
		System.out.println("** Directions:");
	    System.out.println("** Enter numbers 1-"+k+" to make a move");
	    System.out.println("** X starts the game");
	    System.out.println("**");
	    System.out.println("** _1_|_2_|_3_|");
	    System.out.println("** _4_|_5_|_6_|");
	    System.out.println("** _7_|_8_|_9_|");
	    System.out.println("**");
	    System.out.println("********* LET THE GAME BEGIN !! ********");
	    System.out.println();
	}
	
	/**
	 * This method prints the current state of the game board
	 * @param gameBoard Current state of the game board object
	 */
	public static void printGameBoard(GameBoard gameBoard){
		Cell[][] board = gameBoard.getGameBoard();
		for(int x=0; x<board.length; x++){
			for(int y=0; y<board[0].length; y++){
				System.out.print("_"+board[x][y].getCellMarker().getCellMarkerValue()+"_|");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * This method prints the result of the game
	 * @param currentPlayer This is the player who made the last move
	 * @param gameWon This is a boolean variable which is true when the
	 * game has been won by the current player or false when the game
	 * has been drawn
	 */
	public static void printGameResult(Player currentPlayer, boolean gameWon) {
		if(!gameWon){
			System.out.println("Your computer says - \"I was in a good mood, so I let you draw the game !!\"");
		} else {
			if(currentPlayer.isHuman()){
				System.out.println("Your computer says - \"You won this time, but I will avenge this defeat !!\"");
			} else {
				System.out.println("Your computer says - \"You may be a Jedi, but tic-tac-toe ain't your thing !!\"");
			}
		}
	}
}
