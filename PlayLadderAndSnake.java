//Import Scanner 
import java.util.Scanner;

/**
 * This main file class shall be the start-up point of the players: 
 *	they may create or restart a game of snake and ladder
 * 
 * @author Robert  CHEN and ALexandru Ilie
 * @version 1.0
 * 
 *
 */
public class PlayLadderAndSnake {
	/** Program's entry point
	 * 
	 * @param args an array of command-line arguments for the application
	 */
	public static void main(String[] args) {

				
		/**
		 * Declare the object required to start a game:
		 */
		LadderAndSnake game = new LadderAndSnake();
		
		System.out.println("Hello, welcome to the Snake And Ladder Game!");
		
		//Invoke play() method to simulate the game
		game.play();
		

	}

}
