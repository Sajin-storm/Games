package com.cg.basic;

import java.util.Scanner;

public class Game {
	
	//fields
	int playerPoints = 0;
	int systemPoints = 0;
	String name = "Player";
	
	//arrays
	String[] choiceList = {"rock","paper","scissor"};
	String[] closeList = {"stop","exit","close"};
	String[] completeList = {"rock","paper","scissor","stop","exit","close"};

	//input from user
	Scanner input = new Scanner (System.in);
	
	//method = system choice
	public String systemChoice() {
		int max = 2;
		int min = 0;
		double randomNumber = Math.random()*(max - min + 1) + min;
		return choiceList[(int)randomNumber];
	}
	
	//method = player choice
	public String playerChoice() {
		boolean b = true;
		String result = "";
		String[] completeList = this.completeList;
		while(b) {
			System.out.println("Enter your choice:");
			result = input.nextLine().toLowerCase();
			
			for(String s : completeList) {
				if(result.equals(s)) {
					b = false;
				}
			}
			
			if(b == true) {
				System.out.println("Provide a valid input");
			}
		}
		
		return result;
	}

	// method = points / score
	public void points() {
		System.out.println("Player points: "+this.playerPoints);
		System.out.println("System points: "+this.systemPoints);
	}
	
	//method = gameplay code 
	public String gameplay() {
		String result = "play";
		String[] list = this.closeList;
		
		String systemChoice = systemChoice();
		String playerChoice = playerChoice();
		
		System.out.println("-------------------------");
		System.out.println("You chose:"+ playerChoice);
		System.out.println("AI chose:"+ systemChoice);
		System.out.println("-------------------------");
		
		for(String s : list) {
			if (playerChoice.equals(s)) {
				result = "close";
			}
		}
		
		if(result.equals("play")) {
			if(systemChoice.equals(playerChoice)) {
				System.out.println("Same no points");
			}
			else if (systemChoice.equals("rock")) {
				if (playerChoice.equals("paper")) {
					System.out.println("player wins");
					this.playerPoints ++;
				} else {
					System.out.println("AI wins");
					this.systemPoints ++;
				}
			}
			else if (systemChoice.equals("paper")) {
				if (playerChoice.equals("scissor")) {
					System.out.println("player wins");
					this.playerPoints ++;
				} else {
					System.out.println("AI wins");
					this.systemPoints ++;
				}
			}
			else if (systemChoice.equals("scissor")) {
				if (playerChoice.equals("rock")) {
					System.out.println("player wins");
					this.playerPoints ++;
				} else {
					System.out.println("AI wins");
					this.systemPoints ++;
				}
			}
			else {
				System.out.println("Ending game");
			}
		}
		System.out.println();
		return result;
	}
	
	//method = inital information / main screen
	public void info() {
		System.out.println("===========================");
		System.out.println("|   Rock, paper, Scissor  |");
		System.out.println("|    Game by : Sajin S    |");
		System.out.println("|    Coded using Java     |");
		System.out.println("===========================");
		System.out.println();
		System.out.println("|-------------------------------------------------------|");
		System.out.println("|                        Rules:                         |");
		System.out.println("|      The console will ask for your choice             |");
		System.out.println("|  Fill in with your choice: rock or paper or scissor   |");
		System.out.println("|  You can write in capital letters or small letters    |");
		System.out.println("|to stop the game you can type in: stop or close or exit|");
		System.out.println("|-------------------------------------------------------|");
		System.out.println("|       warnings will be shown for other words          |");
		System.out.println("|               That is it!!! Have fun                  |");
		System.out.println("|-------------------------------------------------------|");
		System.out.println();
	}
	
	//method = final screen / end screen
	public void finalScreen() {
		System.out.println("-----------------");
		points();
		System.out.println("-----------------");
		System.out.println();
		
		if (this.playerPoints > this.systemPoints) {
			System.out.println("You have won!!! with "+this.playerPoints+" points");
		}
		else if(this.playerPoints < this.systemPoints) {
			System.out.println("AI won with "+ this.systemPoints +" points");
			System.out.println("You have lost this time, but you can win next time");
		} 
		else {
			System.out.println("You both took the same points : "+ this.playerPoints);
			System.out.println("Try to win next time");
		}
		
		System.out.println("===Thank you for playing===");
	}
	
	//method = run game
	public void runGame() {
		String exit = "play";
		info();
		while(exit == "play") {
			exit = gameplay();
		}
		finalScreen();
		input.close();
	}
	
	//main method
	public static void main (String[] args) {
	
		Game game = new Game();

		game.runGame();
	}

}
