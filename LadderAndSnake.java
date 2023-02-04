/*
 * ---------------------------
 * Assignment 1  Part I 
 * Written by: Robert CHEN 40241709 and Alexandru Ilie 40248696
 * COMP 249 Section S - Winter 2023
 * 3 February 2023
 * --------------------------- */


/*COMMENT: This main file has everything.
*/
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.*;
import java.lang.Thread;

public class LadderAndSnake {
	Scanner keyb = new Scanner(System.in);
	private int maxPlayers;
	private String [] playersName;
	private int[] playersDice;
	private int[] selection;
	private int [] sortPlayers;
	private int [] playersLocation;
	private String[] details;
	private String [][] board = new String[10][10];
	private int[][] boardNum = new int[10][10];
	private boolean hasWon = false;

	
	public LadderAndSnake() {

	}
	
	public LadderAndSnake(LadderAndSnake S) {
		
		this.maxPlayers = S.maxPlayers;
		this.playersName = S.playersName;
		this.sortPlayers = S.sortPlayers;
		this.playersLocation = S.playersLocation;
		this.board = S.board;
		this.boardNum = S.boardNum;
		this.hasWon = S.hasWon;
	}
	
	
	//Access values
	public int getPlayers() {
		return maxPlayers;
	}
	//obtain values for sorting out players
	public int [] getSelection() {
		return selection;
	}
	
	//obtain values from the opponent NOte: the return values are actually for the position on the array
	public int getOpponent(int y) {

			if (y == 1) {
				return 0;	
			}

			else {
				return 1;
			}
	}
	
	//Mutators
	public void setPlayersLocation(int i, int x) {
		playersLocation[i] = x;
	}
	
	
	
	//METHOD used to simulate a dice from 1 to 6
	public static int flipDice() {
		int x = (int)(Math.random()*6 + 1);	
		return x;	
	}
	
	//Whether the users wish to continue or quit the game
	private void gameContinue() {
	//	Scanner keyb2 = new Scanner(System.in);
		System.out.print("Would you like to continue the ga"
				+ "me? If yes, press Y. If not, press N\n"
				+ "Note: Both Players must agree.\n"+playersName[0]+": ");

		String s1= keyb.nextLine();
		//s1+=keyb2.nextLine();
		s1 =s1.toLowerCase();	
		System.out.print(playersName[1]+": ");
		String s2 = keyb.nextLine();
		//s2+=keyb2.nextLine();
		s2 =s2.toLowerCase();	
	//	keyb.close();
		if (s1.equals("n") && s2.equals("n")) {
			System.out.println("THANK YOU FOR PLAYING THE GAME!!");
			System.exit(0);
		}

	}
	
	//If users wish to look at the board and their position
	public void lookBoard() {		
	/*	Scanner keyb= new Scanner(System.in);
		String S = "";
		System.out.print("Would you like to currently look at the board and view the standings?: Press Y for Yes, N for No\n");
		/* hasNext() is used to verify if there are any more data to read, since.next() is used continously
		 * By that, it will cause a logic error and terminate program. using a while loop will end that
		 */ 
		/*System.out.print("Would you like to currently look at the board and view the standings?: Press Y for Yes, N for No\n");
		String s= keyb.next();
		s = s.toLowerCase();
		if (s.equals("y")) {*/
			 //Display the board after modifications
			for(int i = 0; i<10;i+=2) {
				for(int j = 0; j<10;j++) {
					board[i][j] = ""+board[i][j];
					System.out.print("|\t"+board[i][j]+"\t");
					
					if (j== 9) {
					break;
					}
				}
				System.out.println("|\n-----------------------------------------------------------"
						+ "----------------------------------------------------------------------"
						+ "----------------------------");
	
				for(int j = 0; j<10;j++) {
					board[i+1][j] = ""+board[i+1][j];
	
					System.out.print("|\t"+board[i+1][j]+"\t");
					if (j == 9) {
						break;
						}
					}
				System.out.println("|\n-----------------------------------------------------------"
						+ "----------------------------------------------------------------------"
						+ "---------------------------");
				//} 
		
	        }
		}
	
	
	//The representation of the board
	private void board() {
		 board = new String[10][10];
		 boardNum = new int[10][10];
		 int counter =100;
			//BoardNum
			for(int i = 0; i<10;i+=2) {	
					for(int j = 0; j<10;j++) {
					boardNum[i][j] = counter;
					counter--;
					
					if (j== 9) {
					counter++;
					break;
					}
				}
				counter = counter-10;
				
				for(int j = 0; j<10;j++) {
					boardNum[i+1][j] = counter;
					counter++;
					if (j == 9) {
						counter--;
						break;
						}
					}
				counter = counter-10;
			}
		 
		 //Display the board at the beginning			
		for(int i = 0; i<10;i+=2) {
			
			for(int j = 0; j<10;j++) {
				board[i][j] = ""+boardNum[i][j];
				counter--;
				System.out.print("|\t"+board[i][j]+"\t");
				if (j== 9) {
				counter++;
				break;
				}
			}
			System.out.println("|\n-----------------------------------------------------------"
					+ "----------------------------------------------------------------------"
					+ "----------------------------");
			counter = counter-10;
		
			for(int j = 0; j<10;j++) {
				board[i+1][j] = ""+boardNum[i+1][j];
				counter++;
				System.out.print("|\t"+board[i+1][j]+"\t");
				if (j == 9) {
					counter--;
					break;
					}
				}
			counter = counter-10;
			System.out.println("|\n-----------------------------------------------------------"
					+ "----------------------------------------------------------------------"
					+ "---------------------------");
			}	
	}

	//Modify a players location
	private void boardPosition(int x , int y , int z, int w) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <10; j++) {

				if (x == boardNum[i][j]) {
					board[i][j] = String.format("X%d", y);
				}
				if (z == boardNum[i][j]) {
					board[i][j] = ""+boardNum[i][j];
				}
			}
		}

		//new nested for loops
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <10; j++) {
				//HARDCODE. Change things if a player lands on another person's position
				if (playersLocation[w] == playersLocation[getOpponent(w)]) {
					if (board[i][j].equals("X1") && boardNum[i][j] == playersLocation[getOpponent(w)]) {
						playersLocation[getOpponent(w)] = 0;
					//	System.out.println(" Player 2 has been kicked out, restarting from the start(0)");
						details[w]+= "\nOh no! Player 2 has landed on the same spot as Player 1!"
								+ " Bye Bye you've been kicked, restarting from the start(0)";
					}
					else if (board[i][j].equals("X2")&& boardNum[i][j] == playersLocation[getOpponent(w)]) {
						playersLocation[getOpponent(w)] = 0;
					//	System.out.println(" Player 1 has been kicked out, restarting from the start(0)");
						details[w]+="\nOh no! Player 1 has landed on the same spot as Player 2!"
								+ " Bye Bye you've been kicked, restarting from the start(0)";
					}
				}
			}
		}
		
	}
	
	public void Rules() {
		//Declare Scanner and variables
	//	Scanner keyb = new Scanner(System.in);
		//int numPlayers;
		int counter = 1;
		System.out.println("To begin, initialize the amount of players:");
		maxPlayers = keyb.nextInt();
		//initialize an array to sort out players
		 sortPlayers = new int[getPlayers()];
		
		if (maxPlayers > 2) {
			System.out.println("Initialization was attempted for 2 member of players; however, this is only" 
				+	" expected for an extended version the game.\nValue will be set to 2");
			maxPlayers = 2;
			sortPlayers = new int[maxPlayers];
		}
		else if (maxPlayers < 2) {
			System.out.println("Error: Cannot execute the game with less than 2 players! Will exit.");
			System.exit(0);
		}
		
		//Set for the maximum amount of players and storing their names
		playersName = new String[sortPlayers.length];
		
		//Ask players to write their name
		for (int i = 0; i < sortPlayers.length; i++) {
		System.out.format("May Player %d enter his name: ", i+1);
		playersName[i] = keyb.next();
		keyb.nextLine(); //System.out.println();
		sortPlayers[i] = i+1;
		}
		
		//Sorting out which players move first.
		System.out.println("Now deciding which player will start playing:");
			selection = new int[getPlayers()];
			//values for players in order to sort the order
			while(true) {
				for (int i = 0; i < sortPlayers.length; i++) {
					//for (int j=0; j <=0; )
					selection[i] = flipDice();	
					System.out.format("Player %d got a dice value of %d\n",sortPlayers[i], selection[i]);
				}
				//In order to try and sort every one out
				for (int i = 0; i < sortPlayers.length; i++) {
					for (int j = i+1; j <sortPlayers.length; j++) {
						if (selection[i] < selection[j]) {
							int garbage = sortPlayers[i];
							sortPlayers[i] = sortPlayers[j];
							sortPlayers[j] = garbage;
							
							String junk = playersName[i];
							playersName[i] = playersName[j];
							playersName[i] = junk;
						}
						//Redo if two  values are the same
						else if (selection[i] == selection[j]) {
							System.out.println("A tie was achieved between Player 1 and Player 2. "
							+ "Attempting to break the tie");
							counter++;
							break;
						}
					}
				}
				//Be able to exit the while loop MUS CHANGE MUST CHANGE MUST CHANGE
				if (selection[0] != selection[1] ) {
					break;
				}
			}	

			//Detail the playing order
		System.out.printf("Reached Final decision on order of playing: Player %d, then Player %d."
				+ " It took %d attempts before a decision could be made.\n\n",sortPlayers[0],sortPlayers[1],counter);
		//CLose Scanner
	//	keyb.close();
	}
	
	public void play() {
		//Apply temporary variables
		int counter2 = 0;
		int counter3 = 0;
		//Show the board
		//System.out.println("Here is a representation of the board:");
		//Thread.sleep(3000);
		board();
		lookBoard();

		//Declare new values to assign temporary values of a players dice rolls and its values
		playersDice = new int[getPlayers()];
		playersLocation = new int[getPlayers()];
		
		
		details = new String[getPlayers()];
		//How the snake and ladder game is played
		while (true) {
			counter2++;
			counter3++;
				//if players wish to continue
				if (counter3 % 4 == 0) {
					gameContinue();
				} 
			
				//if players wish to look at the board
				if (counter2 % 4 == 0) { 
						lookBoard();	
				} 
				
				
			for (int i = 0; i < sortPlayers.length; i++) {
				playersDice[i] = flipDice();
				playersLocation[i] += playersDice[i];
				//Starting to set the output
				details[i] = "Player "+sortPlayers[i]+" got a dice value of"+ 
				" "+playersDice[i]+"; gone up to square "+playersLocation[i];
				//Methods to be implemented in order to change some things on the board	
				Victory(i); Ladder(i); snake(i);
				boardPosition(playersLocation[i], sortPlayers[i], playersLocation[i]-playersDice[i], i);	

				
				//print the results
				System.out.println(details[i]);
			}
			
			//Tell that the game is not over
			System.out.println("Game not over; flipping again");
		}
	}

	
	//New methods used to adjust the game depending on the user's roll dice count.
	//The ladder method is used to help players if they land on the bottom of a ladder
	//New methods used to adjust the game depending on the user's roll dice count.
	private void Ladder(int x) {
		//temporary variables, 
		int pLocation = playersLocation[x];
		
		switch(pLocation) {
			case 1: 
				pLocation = 38;
				details[x] +=" then up to square 38";
				break;
			case 4:
				pLocation = 14;
				details[x] +=" then up to square 14";
				break;
			case 9:
				pLocation = 31;
				details[x] +=" then up to square 31";
				break;
			case 21:
				pLocation = 42;
				details[x] +=" then up to square 42";
				break;	
			case 28:
				pLocation = 84;
				details[x] +=" then up to square 84";
				break;
			case 36:
				pLocation = 44;
				details[x] +=" then up to square 44";
				break;
			case 51:
				pLocation = 67;
				details[x] +=" then up to square 67";
				break;
			case 71:
				pLocation = 91;
				details[x] +=" then up to square 91";
				break;
			case 80:
				pLocation = 100;
				details[x] +=" then up to square 100";
				break;
		}
		
		playersLocation[x] = pLocation;
		//Tell that the game is not over
		
		
	}
	
	private void snake(int x) {
		//temporary variables
		int pLocation = playersLocation[x];
		
		switch(pLocation) {
		case 16: 
			pLocation = 6;
			details[x] +=" then down to square 6";
			break;
		case 48:
			pLocation = 30;
			details[x] +=" then down to square 30";
			break;
		case 64:
			pLocation = 60;
			details[x] +=" then down to square 60";
			break;
		case 79:
			pLocation = 19;
			details[x] +=" then down to square 19";
			break;	
		case 93:
			pLocation = 68;
			details[x] +=" then down to square 68";
			break;
		case 95:
			pLocation = 24;
			details[x] +=" then down to square 24";
			break;
		case 97:
			pLocation = 76;
			details[x] +=" then down to square 76";
			break;
		case 98:
			pLocation = 78;
			details[x] +=" then down to square 78";
			break;
		
		}
		//Re-assign new value to the players Location.
		playersLocation[x] = pLocation;				
	}
		
	//When someone reaches 100.
	private void Victory(int x) {
			if (playersLocation[x] ==100) {
				//Output methods to detail that the player has won
				board[0][0] = "X1";
				System.out.println(details[x]);
				System.out.printf("CONGRATULATIONS!! "+playersName[x]+" HAS WON!! \nC-E-L-E-B-R-A-T-E GOOD TIMES, COME ON!!");
				keyb.close();
				System.exit(0);
			}
			else if (playersLocation[x]> 100) {	
				/*temporary variables to modify 
				 * if players go overboard 100. */
				
				int y;
				int y2 = playersLocation[x];
				y = playersLocation[x]-100;
				playersLocation[x] =100-y;
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j <10; j++) {
						//Move the player backwards if they move over 100
						if (playersLocation[x]== boardNum[i][j]) {
							board[i][j] =String.format("X%d",sortPlayers[x]);
							details[x] += String.format(" , which is over 100. Moving backwards by %d steps.....gone to square %d",y,playersLocation[x]);
						}
						/*Replace the old position that was marked for either player 1 or 2
						with a number to avoid confusion */ 
						if (y2 == boardNum[i][j]) {
							board[i][j] = ""+boardNum[i][j];
						}
					}
				}		
			}
	}
}
