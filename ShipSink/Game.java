package com.hpe.basic;

import java.util.Scanner;

public class Game {
	
	SystemPlayer systemPlayer = new SystemPlayer();
	UserPlayer userPlayer = new UserPlayer();
	
	int moves = 0;
	int ships = 9;
	
	private String[][] userBoard = { { " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, };
	
	private String[][] systemBoard = { { " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, };
	
	public void userGrid() {
		char c = 'G';
		for (int i = 6; i >= 0; i--) {
			System.out.println("   === === === === === === ===");
			System.out.print(c--);
			for (int j = 0; j <= 6; j++) {
				System.out.print(" | " + userBoard[i][j]);
			}
			System.out.print(" |");
			System.out.println();
		}
		System.out.println("   === === === === === === ===");
		System.out.println("    0   1   2   3   4   5   6");
	}
	
	
	public void gameplay() {
		
		infoScreen();
		
		systemPlayer.shipPlaces();
//		systemPlayer.grid(); //to see system output
		
		boolean loop = true;
		while(loop) {
			userGrid();
			userPlayer.userInput();
			validate(userPlayer.position());
			
			loop = shipsDestroyed();
		}
		userGrid();
		finalScreen();
		
	}
	
	public void validate(String position) {
		int x = Integer.parseInt(position.split(":")[0]);
		int y = Integer.parseInt(position.split(":")[1]);
		
		systemBoard = systemPlayer.getBoard();
		if(userBoard[x][y] == " ") {
			if(systemBoard[x][y] == "X") {
				userBoard[x][y] = "X";
				System.out.println("Hit!!!");
				ships--;
				this.moves++;
			} else {
				userBoard[x][y] = "O";
				System.out.println("Miss!!!");
				this.moves++;
			}
		} else if(userBoard[x][y] == "X" || userBoard[x][y] == "O") {
			System.out.println("Already given");
		}
		
	}
	
	public boolean shipsDestroyed() {
		boolean result = true;
		if(ships == 0) {
			result = false;
		}
		return result;
	}
	
	public void finalScreen() {
		System.out.println();
		System.out.print("Calculating score");
		for(int i = 0 ; i< 5 ; i++) {
			System.out.print(".");
			waitTime(5);
		}
		System.out.println();
		System.out.println("Number of moves: "+moves);
		System.out.print("Experience : ");
		if (moves <= 9) {
			System.out.println("God level");
		} else if (moves <= 12) {
			System.out.println("Master level");
		} else if (moves <= 15) {
			System.out.println("Experienced level");
		} else if (moves <= 21) {
			System.out.println("Intermediate level");
		} else if (moves <= 27) {
			System.out.println("Begineer level");
		} else {
			System.out.println("Noob level");
			System.out.println("Try focusing harder to get a good score");
		}
		waitTime(20);
		System.out.println();
		System.out.println("Thank you for playing!!!");
		
	}
	
	
	public void waitTime(int num) {
		//delay code copied from stackoverflow
		try
		{
		    Thread.sleep(num*100);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
	}
	
	public void infoScreen() {
		System.out.print("Loading");
		for(int i = 0 ; i< 5 ; i++) {
			System.out.print(".");
			waitTime(5);
		}
		System.out.println();
		System.out.println("Welcome to Shipsink");
		System.out.println("Game by - Sajin S");
		System.out.println();
		waitTime(10);
		System.out.println("A game were you get to guess ship locations in a 7x7 grid");
		System.out.println("Type your guess between A0 to G6 when console asks for your input to select your location");
		System.out.println();
		waitTime(10);
		System.out.println("There are total of 3 ships and 3 blocks in size");
		System.out.println("If you guess it correctly the gird will have X mark in it");
		System.out.println("If you guessed in wrongly the grid will have O mark in it");
		System.out.println();
		waitTime(10);
		System.out.println("Have fun Playing!!!");
		
		System.out.println();
		System.out.print("Creating system board");
		for(int i = 0 ; i< 5 ; i++) {
			System.out.print(".");
			waitTime(6);
		}
		System.out.println();
		System.out.print("Creating user empty board");
		for(int i = 0 ; i< 5 ; i++) {
			System.out.print(".");
			waitTime(2);
		}
		System.out.println();
		System.out.print("Starting gamePlay");
		for(int i = 0 ; i< 5 ; i++) {
			System.out.print(".");
			waitTime(3);
		}
		System.out.println();
		System.out.println();
		
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.gameplay();
	}
}

class UserPlayer {

	char positionX;
	int positionY;
	
	public void userInput() {
		boolean loop = true;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String userPosition = "";
		
		while(loop) {
			System.out.println("Enter the position that you want to guess: ");
			userPosition = input.nextLine();
			loop = validateLength(userPosition);
		}	
		
	}

	public boolean validateLength(String userPosition) {
		boolean result = true;
		if(userPosition.length() > 2) {
			System.out.println("Please provide a proper input between A0 - G6");
		} else {
			result = validatePosition(userPosition);
		}
		
		return result;
	}

	public boolean validatePosition(String userPosition) {
		boolean resultX = true;

		try {
			int y = Integer.parseInt(userPosition.split("")[1]);
			positionY = y;
			if(positionY >=0 && positionY <= 6) {
				resultX = false;
			} else {
				System.out.println("Please provide number between 0 and 6");
				resultX = true;
			}
		} 
		catch (NumberFormatException e) {
			System.out.println("Provide a proper format A0 - G6");
			resultX = true;
		}
		
		boolean resultY = true;
		positionX = userPosition.split("")[0].charAt(0);
		if(positionX >= 'A' && positionX <= 'G') {
			resultY = false;
		} else {
			System.out.println("Provide a proper format A0 - G6");
			resultY = true;
		}
		
		boolean result = true;
		if(resultX == false && resultY == false) {
			result = false;
		}
		return result;
	}
	
	public String position() {
		String result = String.valueOf(positionX-65)+":"+String.valueOf(positionY);
		return result;
		
	}

	
	
	
}

class SystemPlayer {

	private String[][] board = { { " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, { " ", " ", " ", " ", " ", " ", " " },
			{ " ", " ", " ", " ", " ", " ", " " }, };

	public String[][] getBoard() {
		return board;
	}

	public void setBoard(String[][] board) {
		this.board = board;
	}


	public void grid() {
		char c = 'G';
		for (int i = 6; i >= 0; i--) {
			System.out.println("   === === === === === === ===");
			System.out.print(c--);
			for (int j = 0; j <= 6; j++) {
				System.out.print(" | " + board[i][j]);
			}
			System.out.print(" |");
			System.out.println();
		}
		System.out.println("   === === === === === === ===");
		System.out.println("    0   1   2   3   4   5   6");
	}
	

	public void shipPlaces() {
		int min = 0;
		int max = 6;

		int locationX = 0;
		int locationY = 0;

		
		int num = 2;
		for (int i = 0; i <= 2; i++) {
			boolean loop = true;
			while (loop) {
				locationX = (int) (Math.random() * (max - min + 1) + min);
				locationY = (int) (Math.random() * (max - min + 1) + min);
//				System.out.println(locationX + ":" + locationY);
				if (num % 2 == 0) {
					loop = validateX(locationX, locationY);
				} else {
					loop = validateY(locationX, locationY);
				}
				num++;
			}
		}

	}

	public boolean validateX(int x, int y) {
		boolean result = true;
		if (x <= 4) {
			if (board[x][y] == " " && board[x + 1][y] == " " && board[x + 2][y] == " ") {
				board[x][y] = "X";
				board[x + 1][y] = "X";
				board[x + 2][y] = "X";
				result = false;
			}
		} else if (x >= 2) {
			if (board[x][y] == " " && board[x - 1][y] == " " && board[x - 2][y] == " ") {
				board[x][y] = "X";
				board[x - 1][y] = "X";
				board[x - 2][y] = "X";
				result = false;
			}
		}
		return result;
	}

	public boolean validateY(int x, int y) {
		boolean result = true;
		if (y <= 4) {
			if (board[x][y] == " " && board[x][y + 1] == " " && board[x][y + 2] == " ") {
				board[x][y] = "X";
				board[x][y + 1] = "X";
				board[x][y + 2] = "X";
				result = false;
			}
		} else if (y >= 2) {
			if (board[x][y] == " " && board[x][y - 1] == " " && board[x][y - 2] == " ") {
				board[x][y] = "X";
				board[x][y - 1] = "X";
				board[x][y - 2] = "X";
				result = false;
			}
		}
		return result;
	}

	

}
