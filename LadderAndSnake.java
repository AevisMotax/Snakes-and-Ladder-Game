import java.util.Scanner;
import java.io.*;
import java.util.*;
/**
 * This main file class shall be the start-up point of the players: 
 *	they may create or restart a game of snake and ladder
 * 
 * @author Robert  CHEN and Alexandru Ilie
 * @version 1.0
 *
 *
 */
public class LadderAndSnake {
	Scanner keyb = new Scanner(System.in);
	private int maxPlayers; //next time, I'll implement a weird thing to make it look like the user can input more character.
	private String [] playersName;
	private int[] playersDice;
	private int[] selection;
	private int [] sortPlayers;
	private int [] playersLocation;
	private String[] details;
	private String [][] board = new String[10][10];
	private int[][] boardNum = new int[10][10];
	private boolean hasWon = false;

	/*
	 * Default constructor
	 */
	public LadderAndSnake() {

	}
	
	/**
	 * Copy constructor
	 * @param S, another game
	 */
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
/*	private void gameContinue() {
		List<String> temp  = new ArrayList<String>();
		
		int i = 0;
		System.out.print("Would you like to continue the ga"
				+ "me? If yes, press Y. If not, press N\n"
				+ "Note: All Players must agree.\n");

		for ( String name : playersName){
			System.out.printf("%f : ",name);
			temp.add(i++, keyb.nextLine());
		}
		
		//Put everything into lower case
		
		//Check if everyone said Yes (Y) or some random nonsense
		if (temp.stream().allMatch("")) {
			
		}

	} */ 
	
	//If users wish to look at the board and their position
	public void lookBoard() {	
			System.out.println("\nhere is a quick visualization of the current board.");
			keyb.nextLine();
			 //Display the board after modifications
			for(int i = 0; i<10;i+=2) {
				for(int j = 0; j<10;j++) {
					System.out.print("|\t"+board[i][j]+"\t");
					
					if (j== 9) {
						break;
					}
				}
				System.out.println("|\n-----------------------------------------------------------"
						+ "----------------------------------------------------------------------"
						+ "----------------------------");
	
				for(int j = 0; j<10;j++) {
					System.out.print("|\t"+board[i+1][j]+"\t");
					if (j == 9) {
						break;
						}
					}
				System.out.println("|\n-----------------------------------------------------------"
						+ "----------------------------------------------------------------------"
						+ "---------------------------");
				
	        }
			keyb.nextLine();
		}
	
	
	//The representation of the board
	private void board() {
		 board = new String[10][10];
		 boardNum = new int[10][10];
		 int counter =100;
		 
		 //Pause momentarily
		 keyb.nextLine();
		 
		//BoardNum
		for(int i = 0; i<10;i+=2) {	
			for(int j = 0; j<10;j++) {
				boardNum[i][j] = counter;
				board[i][j] = ""+counter;
				counter--;
				
				if (j== 9) {
					counter++;
					break;
				}
			}
			counter = counter-10;
			
			for(int j = 0; j<10;j++) {
				boardNum[i+1][j] = counter;
				board[i+1][j] = ""+counter;
				counter++;
				if (j == 9) {
					counter--;
					break;
					}
				}
			counter = counter-10;
		}	
	}

	//Modify a players location
	private void boardPosition(int xLocation , int yCurrentPlayers , int zPreviousLocation, int wPlayersTurn) {
		//Remove previous location
		String junk = String.format("X%d", yCurrentPlayers);

				
		//Insert player's new location
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <10; j++) {

				if (xLocation == boardNum[i][j]) {
					board[i][j] = ""+junk;
				}

				if (board[i][j].equals(junk) && zPreviousLocation == boardNum[i][j]) {
					board[i][j] = ""+boardNum[i][j];
				}
			}
		}

		//new nested for loops
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j <10; j++) {
				//HARDCODE. Change things if a player lands on another person's position
				if (playersLocation[wPlayersTurn] == playersLocation[getOpponent(wPlayersTurn)]) {
					if (board[i][j].equals("X1") && playersLocation[getOpponent(wPlayersTurn)] == boardNum[i][j]) {
						playersLocation[getOpponent(wPlayersTurn)] = 0;
					//	System.out.println(" Player 2 has been kicked out, restarting from the start(0)");
						details[wPlayersTurn]+= "\nOh no! Player 2 is on the same spot as Player 1!"
								+ " Bye Bye you've been kicked, restarting from the start(0)";
					}
					else if (board[i][j].equals("X2")&& playersLocation[getOpponent(wPlayersTurn)] == boardNum[i][j]) {
						playersLocation[getOpponent(wPlayersTurn)] = 0;
					//	System.out.println(" Player 1 has been kicked out, restarting from the start(0)");
						details[wPlayersTurn]+="\nOh no! Player 1 is on the same spot as Player 2!"
								+ " Bye Bye you've been kicked, restarting from the start(0)";
					}
				}
			}
		}
		
	}
	
	
	public void Rules() {
		//Initialize the starting game
		int counter = 1;
		System.out.println("\nTo begin, initialize the amount of players:");
		
		//loop until we get a correct input
		while (true) {
			try {
				maxPlayers = keyb.nextInt();
				break;
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid. Please enter a number:\n");
				keyb.nextLine();
			}
		}
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
		keyb.nextLine(); 
		sortPlayers[i] = i+1;
		}
		
		//Sorting out which players move first.
		System.out.println("Now deciding which player will start playing:");
			selection = new int[getPlayers()];
			//values for players in order to sort the order
			while(true) {
				for (int i = 0; i < sortPlayers.length; i++) {
					selection[i] = flipDice();	
					System.out.format("Player %d got a dice value of %d\n",sortPlayers[i], selection[i]);
					keyb.nextLine(); //pause the game momentarily
				}
				
				//In order to try and sort every one out [SELECTION SORTING NOT EFFICIENT]
				for (int i = 0; i < sortPlayers.length; i++) {
					for (int j = i+1; j <sortPlayers.length; j++) {
						//Reposition the start 
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
							System.out.printf("A tie was achieved between Player %d and Player %d."
							+ "Attempting to break the tie\n",sortPlayers[i],sortPlayers[j]);
							counter++;
							continue;
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
	}
	
	public void play() {
		//Apply temporary variables
		int counter2 = 0;
		int dice = 0;
		//Show the board
		board();
		lookBoard();
		Rules();

		//Declare new values to assign temporary values of a players dice rolls and its values
		//SHOULD PROBABLY MAKE A DICTIONARY FOR THIS ONE
		playersDice = new int[getPlayers()];
		playersLocation = new int[getPlayers()];
		
		//Just some strings to figure out where each players are and then.
		details = new String[getPlayers()];
		
		//How the snake and ladder game is played
		while (true) {
			keyb.nextLine();
			counter2++;
				//if players wish to continue
//				if (counter3 % 4 == 0) {
//					gameContinue();
//				} 
			
				//if players wish to look at the board
				if (counter2 % 4 == 0) { 
					lookBoard();	
				} 
				
			//We start the game: Each player gets a turn to roll a "virtual" dice	
			for (int i = 0; i < sortPlayers.length; i++) {
				int junk = playersLocation[i];
				dice = flipDice();
				playersLocation[i] += dice;
				//Starting to set the output
				details[i] = "Player "+sortPlayers[i]+" got a dice value of"+ 
				" "+dice+"; gone up to square "+playersLocation[i];
				//Methods to be implemented in order to change some things on the board	
				Victory(i); Ladder(i); snake(i);
				boardPosition(playersLocation[i], sortPlayers[i], junk, i);	
				
				//print the results
				System.out.println(details[i]);
			}
			
			//Tell that the game is not over
			System.out.println("Game not over; flipping again");
		}
	}

	
	//New methods used to adjust the game depending on the user's roll dice count.
	//The ladder method is used to help players if they land on the bottom of a ladder
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
		//Re-assign new value to the players Location.		
		playersLocation[x] = pLocation;		
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
