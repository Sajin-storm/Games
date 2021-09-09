package com.cg.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	//fields
	String[] defaultBoard = {"0","1","2","3","4","5","6","7","8","9"};
	String[] board = {"0","1","2","3","4","5","6","7","8","9"};
	
	int player1Points = 0;
	int player2Points = 0;
	
	int position = 0;
	
	List<Integer> list = new ArrayList<>();
	List<Integer> emptyList = new ArrayList<>();
	
	Scanner input = new Scanner (System.in);
	
	public void table() {
		System.out.println(" === === === ");
		System.out.println("| "+board[7]+" | "+board[8]+" | "+board[9]+" |");
		System.out.println(" === === === ");
		System.out.println("| "+board[4]+" | "+board[5]+" | "+board[6]+" |");
		System.out.println(" === === === ");
		System.out.println("| "+board[1]+" | "+board[2]+" | "+board[3]+" |");
		System.out.println(" === === === ");
	}
	
	public void clearTable() {
		board = defaultBoard;
	}
	
	public void player1Input() {
		boolean b = true;
		while(b) {
			System.out.println("Player 1 choose: ");
			position = input.nextInt();
			b = validate(position);
		}
		board[position] = "X";
	}
	
	public void player2Input() {
		boolean b = true;
		while(b) {
			System.out.println("Player 2 choose: ");
			position = input.nextInt();
			b = validate(position);
		}
		board[position] = "O";
	}
	
	public boolean validate(int num) {
		boolean b = false;
		if(num > 9) {
			System.out.println("Please provide number less than 10");
			b = true;
		} else if (num < 0) {
			System.out.println("Please provide number greater than 0");
			b = true;
		} else {
			for(int n : list ) {
				if(num == n) {
					b = true;
					System.out.println("Repeated number!!!");
					System.out.println("Please provide a different number");
				} 
			}
			if(!list.contains(num)) {
				list.add((int)num);
				System.out.println("adding "+num +" number to list");
			}
			
		}
		return b;
	}
	
	public void points(String s) {
		if (s == "X") {
			System.out.println("Player 1 wins!!!");
			player1Points ++;
		} else if (s == "O") {
			System.out.println("Player 2 wins!!!");
			player2Points ++;
		}
	}
	
	public void playerPoints() {
		System.out.println("Player 1 points: "+player1Points);
		System.out.println("Player 2 points: "+player2Points);
		System.out.println("-----------------------------");
		if(player1Points > player2Points) {
			System.out.println("Player1 in the lead");
		} else if (player1Points < player2Points) {
			System.out.println("Player2 is in the lead");
		} else {
			System.out.println("Both are tied up");
		}
		System.out.println("-----------------------------");
	}
	
	public boolean stopGame() {
		String end = "";
		boolean b = true;
		System.out.println("Would you like to stop the game?");
		System.out.println("Type yes to stop game");
		System.out.println("type anything else to continue game");
		end = input.next().toLowerCase();
		
		if(end.equals("yes") ) {
			b = false;
		}
		
		return b;
	}
	
	public boolean winLogic() {
		boolean b = true;
		//horizontal
		if (board[1]==board[2]) {
			if(board[2]==board[3]) {
				points(board[1]);
				b = false;
			}
		}
		if (board[4]==board[5]) {
			if(board[5]==board[6]) {
				points(board[4]);
				b = false;
			}
		}
		if (board[7]==board[8]) {
			if(board[8]==board[9]) {
				points(board[7]);
				b = false;
			}
		}
		
		//vertical
		if (board[1]==board[4]) {
			if(board[4]==board[7]) {
				points(board[1]);
				b = false;
			}
		}
		if (board[2]==board[5]) {
			if(board[5]==board[8]) {
				points(board[2]);
				b = false;
			}
		}
		if (board[3]==board[6]) {
			if(board[6]==board[9]) {
				points(board[3]);
				b = false;
			}
		}
		
		//diagonal
		if (board[1]==board[5]) {
			if(board[5]==board[9]) {
				points(board[1]);
				b = false;
			}
		}
		if (board[3]==board[5]) {
			if(board[5]==board[7]) {
				points(board[3]);
				b = false;
			}
		}
		return b;
	}
	
	public void infoScreen() {
		System.out.println("Tic Tac Toe");
		System.out.println("Game by : Sajin S");
		System.out.println();
	}
	
	public void finalScreen() {
		System.out.println("-----------------");
		if(player1Points > player2Points) {
			System.out.println("Player1 is the winner");
		} else if (player1Points < player2Points) {
			System.out.println("Player2 is the winner");
		} else {
			System.out.println("Both are winners");
		}
		System.out.println("-----------------");
		System.out.println();
		System.out.println("|---------------------------|");
		System.out.println("|===Thank you for playing===|");
		System.out.println("|---------------------------|");
	}
	
	public void gameplay() {
		list.add(0);
		boolean inner = true;
		boolean outer = true;
		infoScreen();
		while(outer) {
			while(inner) {
				table();
				player1Input();
				inner = winLogic();
				if(inner == true) {
					table();
					player2Input();
					inner = winLogic();
				}
			}
			clearTable();
			playerPoints();
			outer = stopGame();
			inner = outer;
			list = emptyList;
			list.add(0);
		}
		
		finalScreen();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.gameplay();
		
	}

}
