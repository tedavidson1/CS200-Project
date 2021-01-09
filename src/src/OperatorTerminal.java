package src;
import java.util.Scanner;

/**
 * The OperatorTerminal class. This class handles managing providers and members
 * @author Jake Frances
 */

public class OperatorTerminal {
	
	Scanner in = new Scanner(System.in);
	ManageAccounts manageAccounts = new ManageAccounts(); //creates instance of manage accounts class

//	public OperatorTerminal() {
//		
//	}
	
	/**
	 * handles if user is managing providers or accounts
	 * loops until user is done performing operators
	 * user selects "1" for providers and "2" for members
	 */
	public void operatorTerminal() {
		while(true) {

//			Scanner in = new Scanner(System.in);
			
			//user first decides to manage providers or members with 1 or 2
			System.out.println("\nWhat would you like to do?");
			System.out.println("\t1. Manage Providers\n\t2. Manage Members");
			System.out.println("Enter 0 to logout");

			int selection = in.nextInt(); //users selection for managing providers or members
//			in.close();
			
			if(selection == 0) { //user selects to logout
				logout();
				break;
			}
			else if(selection == 1 || selection == 2) { //user selects to manage accounts
				manageAccounts(selection); //calls instance of function and sends over the users selection
			} else { //invalid selection
				System.out.println("Invalid selection");
				break;
			}
			
		}
	}
	
	/**
	 * prompts user what they would like to do
	 * selection parameter indicates whether user is managing provider or members
	 * providers are indicated by "1" and members "2"
	 * @param selection
	 */
	public void manageAccounts(int selection) {
		int subSelection; //users selection for how to manage either providers or members
		
		

		while(true) { //loops until user is done making changes

//			Scanner in = new Scanner(System.in);
		
			System.out.println("\nSelect from the following:");
			if(selection == 1) { //user selected providers in original function
				System.out.println("\t1. Add Provider\n\t2. Edit Provider\n\t3. Delete Provider");
				System.out.println("Enter 0 to go back");
				subSelection = in.nextInt();
//				in.close();
				
				
				//exit manageAccounts function
				if(subSelection == 0) break;
				
				
				if(subSelection == 1) {
					manageAccounts.addProvider(); //calls managerterminal to add provider
				} else if(subSelection == 2) {
					manageAccounts.editProvider(); //calls managerterminal to edit provider
				} else {
					manageAccounts.deleteProvider(); //calls managerterminal to delete provider
				}
				
				
			} else { //user selected member in original function
				System.out.println("\t1. Add Member\n\t2. Edit Member\n\t3. Delete Member");
				System.out.println("Enter 0 to go back");
				subSelection = in.nextInt();
//				in.close();
				
				//exit manageAccounts function
				if(subSelection == 0) break;
				
				
				if(subSelection == 1) {
					manageAccounts.addMember(); //calls managerterminal to add member
				} else if(subSelection == 2) {
					manageAccounts.editMember(); //calls managerterminal to edit member
				} else {
					manageAccounts.deleteMember(); //calls managerterminal to delete member
				}
				
	
				
	
			}
		}
		
		
	}
	
	
	/**
	 * confirms the user has logged out of the Operator Terminal
	 */
	public void logout() {
		System.out.println("\nOperator Terminal logout success\n");
	}
}
