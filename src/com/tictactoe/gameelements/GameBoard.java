package com.tictactoe.gameelements;

import java.util.ArrayList;

import com.tictactoe.utils.PrintUtils;

/**
 * This class represents a game board that contains
 * a m x n grid of cells
 * @author arjunb
 *
 */
public class GameBoard {
	private Cell[][] gameBoard;
	
	/**
	 * This is the constructor of the class which initializes
	 * the grid with EMPTY cells
	 * @param rowsInBoard Number of rows in the board
	 * @param columnsInBoard Number of columns in the board
	 */
	public GameBoard(int rowsInBoard, int columnsInBoard) {
		gameBoard = new Cell[rowsInBoard][columnsInBoard];
		for(int x=0; x<gameBoard.length; x++){
			for(int y=0; y<gameBoard[x].length; y++){
				gameBoard[x][y] = new Cell();
			}
		}
	}
	
	/**
	 * This method returns an array representing the board
	 * @return An array of cell objects
	 */
	public Cell[][] getGameBoard() {
		return gameBoard;
	}
	
	/**
	 * This method sets the grid with an array of cells
	 * @param gameBoard Array of cell objects
	 */
	public void setGameBoard(Cell[][] gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	/**
	 * This method checks if a move is valid or not
	 * @param cellNum The cell number that represents the move
	 * @return True if the move is valid else false
	 */
	public boolean isValidMove(int cellNum) {
		if(cellNum>=1 && cellNum<=gameBoard.length*gameBoard[0].length) {
			int rowNum = (cellNum-1)/gameBoard[0].length;
			int colNum = (cellNum-1)%gameBoard[0].length;
			if(this.gameBoard[rowNum][colNum].isCellEmpty()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method returns a list of valid moves for the current
	 * board state
	 * @return List of valid moves
	 */
	public ArrayList<Integer> getValidMoves() {
		ArrayList<Integer> validMoves = new ArrayList<>();
		for(int x=0; x<gameBoard.length; x++) {
			for(int y=0; y<gameBoard[x].length; y++) {
				if(gameBoard[x][y].isCellEmpty()){
					validMoves.add((x*gameBoard.length)+y+1);
				}
			}
		}
		return validMoves;
	}
	
	/**
	 * This method finds out whether the current state of the board
	 * results in a victory for the player
	 * @param currentPlayer Player who has made the last move
	 * @param k The number of contiguous markers needed for victory
	 * @return True if the last move results in a victory else false
	 */
	public boolean isWinningState(Player currentPlayer, int k){
		int row, col;
		int rowCount = gameBoard.length;
		int columnCount = gameBoard[0].length;
		boolean gameWon = false;

		//Checking each row
		for(row=0; row<rowCount; row++){
			int count = 1;
			for(col=0; col<columnCount-1; col++){
				if(!gameBoard[row][col].isCellEmpty() 
						&& gameBoard[row][col].getCellMarker()==gameBoard[row][col+1].getCellMarker())
					count++;
				else count = 1;
				if(count==k) gameWon = true;
			}
		}

		//Checking each column
		for(col=0; col<columnCount; col++){
			int count = 1;
			for(row=0; row<rowCount-1; row++){
				if(!gameBoard[row][col].isCellEmpty() 
						&& gameBoard[row][col].getCellMarker()==gameBoard[row+1][col].getCellMarker())
					count++; 
				else count = 1;
				if(count==k) gameWon = true;
			}
		}

		//Checking left bottom to right top diagonal
		for (int i = 0; i < rowCount; i++) {
			int count = 1;
			CellMarker curr = CellMarker.ILLEGAL, prev = CellMarker.ILLEGAL;
			for (row = i, col = 0; row >= 0 && col < columnCount; row--, col++) {
				curr = gameBoard[row][col].getCellMarker();
				if(curr!=CellMarker.EMPTY && curr==prev) count++;
				else count = 1;
				if(count==k) gameWon = true;
				prev = curr;
			}
		}
		for (int j = 1; j < columnCount; j++) {
			int count = 1;
			CellMarker curr = CellMarker.ILLEGAL, prev = CellMarker.ILLEGAL;
			for (row = rowCount - 1, col = j; row >= 0 && col < columnCount; row--, col++) {
				curr = gameBoard[row][col].getCellMarker();
				if(curr!=CellMarker.EMPTY && curr==prev) count++;
				else count = 1;
				if(count==k) gameWon = true;
				prev = curr;
			}
		}

		//Checking right bottom to left top diagonal
		for (int i=0; i<rowCount; i++) {
			int count = 1;
			CellMarker curr = CellMarker.ILLEGAL, prev = CellMarker.ILLEGAL;
			for (row=i, col=columnCount-1; row>=0 && col>=0; row--, col--) {
				curr = gameBoard[row][col].getCellMarker();
				if(curr!=CellMarker.EMPTY && curr==prev) count++;
				else count = 1;
				if(count==k) gameWon = true;
				prev = curr;
			}
		}
		for (int j=columnCount-2; j>=0; j--) {
			int count = 1;
			CellMarker curr = CellMarker.ILLEGAL, prev = CellMarker.ILLEGAL;
			for (row=rowCount-1, col=j; row>=0 && col>=0; row--, col--) {
				curr = gameBoard[row][col].getCellMarker();
				if(curr!=CellMarker.EMPTY && curr==prev) count++;
				else count = 1;
				if(count==k) gameWon = true;
				prev = curr;
			}
		}
		if(gameWon){
			PrintUtils.printGameResult(currentPlayer, true);
		}
		return gameWon;
	}
	
	/**
	 * This method finds out if the current state of the board results
	 * in a drawn match
	 * @param currentPlayer The player who made the last move
	 * @param k The number of contiguous markers needed for victory
	 * @return True if game reults in a draw else false
	 */
	public boolean isDrawnState(Player currentPlayer, int k) {
		boolean gameDrawn = true;
		if(isWinningState(currentPlayer, k)){
			return false;
		}
		for(int x=0; x<gameBoard.length; x++) {
			for(int y=0; y<gameBoard[x].length; y++) {
				if(gameBoard[x][y].isCellEmpty()){
					gameDrawn = false;
					break;
				}
			}
		}
		if(gameDrawn){
			PrintUtils.printGameResult(currentPlayer, false);
		}
		return gameDrawn;
	}
	
	/**
	 * This is a wrapper method for the winning state and
	 * drawn state methods
	 * @param currentPlayer The player who made the last move
	 * @param k The number of contiguous markers needed for victory
	 * @return True if current state results in a win or a draw
	 * else false
	 */
	public boolean isGameOver(Player currentPlayer, int k) {
		return this.isWinningState(currentPlayer, k) || this.isDrawnState(currentPlayer, k);
	}
}
