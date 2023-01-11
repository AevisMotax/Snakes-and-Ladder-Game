/*
 * ---------------------------
 * Assignment 1 Part I 
 * Written by: Robert CHEN 40241709 and Alexandru Ilie 40248696
 * COMP 249 Section S - Winter 2023
 * 3 February 2023
 * --------------------------- */





import java.util.Scanner;

public class PlayLadderAndSnake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyb = new Scanner(System.in);
		
		//Declare variables
	/*	int y = 1;
		int x = 11;
		int [][] lol = new int[10][10];
		
		for (int i = 0; i < lol.length; i++) {
			
			for (int z = 0; z< lol[i].length; z++) {
				lol[i][z] = y++;
			}
		} */
		
		System.out.println("Hello, welcome to the Snake And Ladder Game!");
		System.out.println("To begin, initialize the amount of players:");
		
		System.out.println(LadderAndSnake.flipDice());
		
		keyb.close();	
	}

}
