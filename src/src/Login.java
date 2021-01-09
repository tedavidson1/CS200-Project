package src;

import java.util.Scanner;

/**
 * The Login class. Acts as a gateway to all other terminals for program functions
 * @author Jake Frances
 */


public class Login {
	
	/**
	 * main function
	 * prompts user what they would like to login as
	 * @param args
	 */
	public static void main(String args[]) {
		
		Scanner in = new Scanner(System.in); //input stream for user input
		String selection; //stores users selection for the respective terminal

		while(true) {

//			Scanner in = new Scanner(System.in);
//			String selection;

			System.out.println("For login please enter \"O\" for Operator, \"P\" for Provider, \"M\" for Manager, or \"Q\" to quit");
			selection = in.next(); //user terminal selection
//			in.close();

			selection = selection.toLowerCase(); //makes all inputs lowercase for simplicity

			if(selection.equals("q")) { //user selects to quit
			    System.out.println("\nShutting down...");
				in.close();
				return;
			}
			else if(selection.equals("o")) { //user selects operator terminal
				launchOperatorTerminal();
			}
			else if (selection.equals("p")) { //user selects provider terminal
				launchProviderTerminal();
			}
			else if(selection.equals("m")) { //user selects member terminal
				launchManagerTerminal();
			} else { //user enters invalid input
				System.out.println("Invalid selection, please try again");

			}
			
		}	
		
	}
	
	//for all these functions, creates instance of the terminal, and then continuously calls respective function until necessary to logout
	
	
	/**
	 * launches operator terminal
	 */
	private static void launchOperatorTerminal() { //launches instance of operator terminal
		OperatorTerminal terminal = new OperatorTerminal();		
		terminal.operatorTerminal();
	}
	
	/**
	 * launches provider terminal
	 */
	private static void launchProviderTerminal() { //launches instances of provider terminal
		ProviderTerminal terminal = new ProviderTerminal();
		terminal.providerTerminal();
		
	}
	
	/**
	 * launches manager terminal
	 */
	private static void launchManagerTerminal() { //launches instance of manager terminal
		ManagerTerminal terminal = new ManagerTerminal();
		terminal.managerTerminal();
	}

}
