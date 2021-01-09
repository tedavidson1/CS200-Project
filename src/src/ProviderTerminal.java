/**
* Provides a hub for the provider to enact all needed methods.
* @author Daniel Braun
*/

package src;

import java.util.Scanner;

public class ProviderTerminal {
	
	
	/**
	 * Offers the provider a menu to enact methods.
	 */

	//added to fix scanner
	Scanner in = new Scanner(System.in);


	public void providerTerminal() {

		while(true) {
			
			System.out.println("\nWhat would you like to do?");
			System.out.println("\t1. Request Provider Directory\n\t2. Verify Member\n\t3. Bill ChocAn");
            System.out.println("Enter 0 to logout");
//            Scanner in = new Scanner(System.in);
			int selection = in.nextInt();
//            in.close();
			
			switch (selection) {
				case 0:
					logout();
					return;
				case 1:
					requestProviderDirectory();
					break;
				case 2:
					int memberValue = verifyMember();
					if (memberValue == 0) {
						System.out.println("Invalid Number");
						break;
					}
					if (memberValue == 1) {
						System.out.println("Validated");
						break;
					}
					if (memberValue == 2) {
						System.out.println("Member suspended");
						break;
					}
				case 3:
					billChocAn();
					break;
				default: 
					System.out.print("Invalid selection");
					break;
			}
		}
	}
   
    /**
     * Returns to login() function
     */

    public void logout() {
        System.out.println("\nOperator Terminal logout success\n");
    }
    
    /**
     * Outputs information in provider directory
     */

    public void requestProviderDirectory() {
        RequestingProviderDirectory x = new RequestingProviderDirectory();
        x.fetchandemailDirectory();
    }
    
    /**
     * Verifies member number: either valid, invalid, or suspended
     */

    public int verifyMember() { 
        VerifyMember member = new VerifyMember(); 
        
        System.out.println("Enter Member Number: ");
//        Scanner in = new Scanner(System.in);
        int memberNumber = in.nextInt();
//        in.close();
        int memberStanding = member.verifyMember(memberNumber);
        if (memberStanding == 0) return 0;
        if (memberStanding == 1) return 1;
        if (memberStanding == 2) return 2;
        
        return -1;
    }
    /**
     * Completes billing process by checking member number then adding member.
     */

    
    public void billChocAn() {
        int memberStanding = verifyMember();
        
        switch (memberStanding) {
        	case 0:
        		System.out.println("Invalid number");
        		System.out.println("Cannot Bill ChocAn");
        		break;
        	case 1:
                BillChocAn bill = new BillChocAn();
                bill.addRecord();
                break;
        	case 2:
        		System.out.println("Member suspended");
        		System.out.println("Cannot Bill ChocAn");
        		break;
        	default:
        		System.out.println("Error");
        		break;
        }
    }
}