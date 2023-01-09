//--------------------------
//Assignment 2 Part 2
// Written by: Robert CHEN 40241709
//COMP 248 Section R - Fall 2022
//-------------------------------

/* October 21, 2022
 * Program detailing the price Index used to set the retail price
 * , according to the percentage of fruit harvest that are domestic or international
 * in our farm's distribution center and  according to the month. 
 */

//Allow usage of scanner
import java.util.Scanner;

public class A2_Q2
{
	public static void main(String[] args)
	{
		//Declare Scanner
		Scanner keyboard = new Scanner(System.in);
		
		//Declare variables needed
		String month = "";
		String market = "";
		String month_market = "";
		double index = 0.00;
		final double  W = 1.4672;
		//Welcome message and debut of the program
		System.out.println("Welcome to Farms Distribution Center Price Index Program:");
		System.out.println("=========================================================");
		System.out.print("\nPlease enter a Month and Market, respectively: ");
		
		//user enters data 
		month = keyboard.next();
		market = keyboard.next();
		
		
		//make all cases insensitive
		month = month.toLowerCase();
		market = market.toLowerCase();
		
		//if user entered invalid input for the market variable.
		switch (market)
		{
		case "domestic":
			//merge both strings together
			month_market = month +" "+market;
			break;
		case "international":
			//merge both strings together
			month_market = month +" " + market;
			break;
			
		default:
			System.out.println("Error: An invalid value has been entered for the 'Market' variable. Please retry again!");
		}

		//Switch statements to decide the Index program
			switch (month_market)
			{
			case "":
				index = 0.0;
				break;
			case "january domestic":
				index = 1.0/20.0*(75.25*W);
				break;
			case "january international":
				index = 1.0/20.0*(24.75*W);
				break;
			case "february domestic":
				index = 1.0/20.0*(62.86*W);
				break;
			case "february international":
				index = 1.0/20.0*(37.14*W);
				break;
			case "march domestic":
				index = 1.0/20.0*(54.78*W);
				break;
			case "march international":
				index = 1.0/20.0*(45.22*W);
				break;
			case "april domestic":
				index = 1.0/20.0*(68.46*W);
				break;
			case "april international":
				index = 1.0/20.0*(31.54*W);
				break;
			case "may domestic":
				index = 1.0/20.0*(35.89*W);
				break;
			case "may international":
				index = 1.0/20.0*(64.11*W);
				break;
			case "june domestic":
				index = 1.0/20.0*(28.94*W);
				break;
			case "june international":
				index = 1.0/20.0*(71.06*W);
				break;
			case "july domestic":
				index = 1.0/20.0*(42.50*W);
				break;
			case "july international":
				index = 1.0/20.0*(57.50*W);
				break;
			case "august domestic":
				index = 1.0/20.0*(86.10*W);
				break;
			case "august international":
				index = 1.0/20*(13.90*W);
				break;
			case "september domestic":
				index = 1.0/20*(69.70*W);
				break;
			case "september international":
				index = 1.0/20*(30.30*W);
				break;
			case "october domestic":
				index = 1.0/20*(85.40*W);
				break;
			case "october international":
				index = 1.0/20*(14.60*W);
				break;
			case "november domestic":
				index = 1.0/20*(75.90*W);
				break;
			case "november international":
				index = 1.0/20*(24.10*W);
				break;
			case "december domestic":
				index = 1.0/20*(76.80*W);
				break;
			case "december international":
				index = 1.0/20*(23.20*W);
				break;
			default: 
				System.out.println("Error: An invalid value has been entered for the 'Month' variable. Please retry again!");
			}
		
		//Round the Index result to X.XX (only 2 decimals)
		index = Math.round(index*100.0);
		index /= 100.0;
		
		//Display confirmation message
		System.out.println("\nThe value of the Price Index, 'I' is: " + index);
			
		//Close message
		System.out.println("Thank you for using Farms Distribution Center Price Index program!");
		
		//Close Scanner
		keyboard.close();
	}

}
