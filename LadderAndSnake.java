/*
 * ---------------------------
 * Assignment 1 Part I 
 * Written by: Robert CHEN 40241709 and Alexandru Ilie 40248696
 * COMP 249 Section S - Winter 2023
 * 3 February 2023
 * --------------------------- */
import java.util.Scanner;
public class LadderAndSnake {

	private int maxPlayers = 2;
	private String player1;
	private String player2;
	
	private int [][] board ;
	
	
	
	public LadderAndSnake() {
		
	}
	
	
	public LadderAndSnake(LadderAndSnake S) {
		S.player1 = player1;
		S.player2 = player2;
	}
	
	
	public static int flipDice() {
		int x = (int)(Math.random()*6 + 1);
		
		return x;	
	}
	
	
	
	
	
	public boolean play() {
		boolean isOver = false;
		//Declare Scanner and variables
		Scanner keyb = new Scanner(System.in);
		int numPlayers;
		int p1Dice;
		int p2Dice;
		int counter = 0;
		int p1location = 0;
		int p2location = 0;
		
		System.out.println("To begin, initialize the amount of players:");
		numPlayers = keyb.nextInt();
		if (numPlayers > maxPlayers) {
			System.out.println("Initialization was attempted for 2 member of players; however, this is only" 
				+	"expected for an extended version the game. Value will be set to 2");
			numPlayers = 2;
		}
		else if (numPlayers < maxPlayers) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		}
		
		System.out.println("Now deciding which player will start playing:");
		
		while (true) {
			
			p1Dice = LadderAndSnake.flipDice();
			System.out.println("Player 1 got a dice value of "+p1Dice );
			p2Dice = LadderAndSnake.flipDice();
			System.out.println("Player 2 got a dice value of "+p2Dice );
		
			if (p1Dice == p2Dice) {
				System.out.println("A tie was achieved between Player 1 and Player 2. "
						+ "Attempting to break the tie");
				counter++;
			}
		
			else if (p1Dice > p2Dice) {
				System.out.println(" Reached final decision on order of playing: "
						+ "Player 1 then Player 2. It took "+ counter + "attempts before a"
						+ "decision could be made. ");
				break;
			}
			
			else if (p1Dice < p2Dice) {
				System.out.println(" Reached final decision on order of playing: "
						+ "Player 2 then Player 1. It took "+ counter + "attempts before a"
						+ "decision could be made. ");
				break;
			}
		}
		
		
		if (p1Dice > p2Dice) {
			
		}
		
		else if (p2Dice > p1Dice) {
			
		}
		
		
		//Close Scanner
		keyb.close();
		
		return isOver;
	}
	
	
	
	
	
	public String toString() {
		return "";
	}
	
}
