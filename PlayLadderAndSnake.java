/*
 * ---------------------------
 * Assignment 1 Part I 
 * Written by: Robert CHEN 40241709 and Alexandru Ilie 40248696
 * COMP 249 Section S - Winter 2023
 * 3 February 2023
 * --------------------------- */
/* The driver file is where many things are being played.
 * 
 * 
 * 
 */
import java.util.Scanner;

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyb = new Scanner(System.in);
		LadderAndSnake Game1 = new LadderAndSnake();
		
		System.out.println("Hello, welcome to the Snake And Ladder Game!");
		Game1.Rules();
		Game1.play();
		
		
		keyb.close();	
	}

}




