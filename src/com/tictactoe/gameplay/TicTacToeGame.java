package com.tictactoe.gameplay;

import java.util.Scanner;

import com.tictactoe.gameelements.CellMarker;
import com.tictactoe.gameelements.GameBoard;
import com.tictactoe.gameelements.Player;
import com.tictactoe.utils.PrintUtils;

/**
 * This is the class from where the execution of the program begins.
 * The configuration parameters of the game board can be set in the 
 * beginning.
 * @author arjunb
 *
 */
public class TicTacToeGame {
	
	//Game elements
	private static GameBoard gameBoard;
	private static Player firstPlayer;
	private static Player secondPlayer;
	
	//Board configuration
	private static final int k = 3;
	private static final int rowsInBoard = 3;
	private static final int columnsInBoard = 3;
	
	private static Scanner sc;
	
	/**
	 * This is the main method which is the entry point of
	 * the program. It calls other methods to perform the 
	 * necessary actions of the game
	 * @param args
	 */
	public static void main(String[] args) {
		String replayGame = "Y";
		sc = new Scanner(System.in);
		
		do {
			//Printing game instructions
			PrintUtils.printGameInstructions(rowsInBoard*columnsInBoard);
			//Initializing game elements
			try {
				initGameElements();
			} catch (IllegalArgumentException e) {
				System.out.println("ERROR : "+e.getMessage());
				continue;
			}
			//Play game until game is won/lost/drawn
			playGame();
			//Providing replay option
			System.out.println("Up for another game? (Y/N)");
			replayGame = sc.next();
		} while (replayGame.equalsIgnoreCase("Y"));
		sc.close();
	}
	
	/**
	 * This method instantiates the different game elements like
	 * the game board and the players
	 */
	public static void initGameElements () throws IllegalArgumentException {
		//Initializing game board object
		gameBoard = new GameBoard(rowsInBoard, columnsInBoard);
		
		//Initializing player objects
		System.out.println("Choose your marker: (X/O)");
		String marker = sc.nextLine().trim();
		if(marker.equalsIgnoreCase(CellMarker.CROSS.getCellMarkerValue())){
			firstPlayer = new Player(true, CellMarker.CROSS);
			secondPlayer = new Player(false, CellMarker.NOUGHT);
		} else if (marker.equalsIgnoreCase(CellMarker.NOUGHT.getCellMarkerValue())){
			firstPlayer = new Player(false, CellMarker.CROSS);
			secondPlayer = new Player(true, CellMarker.NOUGHT);
		} else {
			throw new IllegalArgumentException("Only X or O can be markers.");
		}
	}
	
	/**
	 * This method executes the gameplay. It continues running 
	 * until the user chooses to quit the game.
	 */
	public static void playGame() {
		PrintUtils.printGameBoard(gameBoard);
		int cellNum = 0;
		while(true){
			if(firstPlayer.isHuman()){
				System.out.println("Make your move: ");
				try{
					cellNum = Integer.parseInt(sc.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println("That's not a valid number !!");
					continue;
				}
				if(!gameBoard.isValidMove(cellNum)){
					System.out.println("I can't let you make that move !!");
					continue;
				}
				gameBoard = firstPlayer.makeMove(gameBoard, cellNum);
				PrintUtils.printGameBoard(gameBoard);
				if(gameBoard.isGameOver(firstPlayer, k)) break;
				System.out.println("Computer's turn:");
				gameBoard = secondPlayer.makeMove(gameBoard);
				PrintUtils.printGameBoard(gameBoard);
				if(gameBoard.isGameOver(secondPlayer, k)) break;
			} else {
				System.out.println("Computer's turn:");
				gameBoard = firstPlayer.makeMove(gameBoard);
				PrintUtils.printGameBoard(gameBoard);
				if(gameBoard.isGameOver(firstPlayer, k)) break;
				System.out.println("Make your move: ");
				try{
					cellNum = Integer.parseInt(sc.nextLine().trim());
				} catch (NumberFormatException e) {
					System.out.println("That's not a valid number !!");
					swapPlayerTurns();
					continue;
				}
				if(!gameBoard.isValidMove(cellNum)){
					System.out.println("I can't let you make that move !!");
					swapPlayerTurns();
					continue;
				}
				gameBoard = secondPlayer.makeMove(gameBoard, cellNum);
				PrintUtils.printGameBoard(gameBoard);
				if(gameBoard.isGameOver(secondPlayer, k)) break;
			}
		}
	}
	
	/**
	 * This method swaps the player turns.
	 */
	public static void swapPlayerTurns(){
		Player tempPlayer = firstPlayer;
		firstPlayer = secondPlayer;
		secondPlayer = tempPlayer;
	}

}
