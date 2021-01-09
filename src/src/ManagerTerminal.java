package src;

import java.util.Scanner;

/**
 * The ManagerTerminal class. This class is the terminal that the manager would see when using the system. In this terminal they
 * will be asked to either logout of the terminal, or produce one or all of the following reports: manager report, provider report, 
 * member report, or of all of the reports.  
 * @author Bre Layton
 * 
 *
 */

	public class ManagerTerminal {
		
		Scanner in = new Scanner(System.in);
		
		public void managerTerminal(){
		
			while(true) {
				
				/**
				   * Prompts the user to figure out which of the following options they would like.
				   */
				
				System.out.println("\n What would you like to do?");
				System.out.println("\t1. Produce Report");	
				System.out.println("\t2. It is Friday at Midnight?");
				System.out.println("Enter 0 to logout");
				int option = in.nextInt();
				
				/**
				   * Switch cases that run through the options available to the user.
				   * If the user enters 0 then the program will log them out.
				   */
				
				if(option == 0) {
					logout();
					break;
				}
				
				/**
				   * If the user enters 1 then the code calls to the produceReport function in order to see which report to produce.
				   */
				
				else if(option == 1) {
					produceReport();
				}
				
				else if (option == 2) {
					Timer v = new Timer();
					v.isFridayMidnight();
				}
				
				/**
				   * If the number entered is not 0 or 1 then the switch default will be activated and the switches will break.
				   * Prints message to let the user know that another option is not available. 
				   */
				
				else {
					System.out.println("The option selected in unavailable\n");
				}
			}
		}
		/**
		   * Allows user to log out of manager terminal.
		   */
		
		
		public void logout(){
			System.out.println("\n Manager Terminal Logout Successful\n");
		}
		
		/**
		   * Goes to ReportCreator and produces the correct report(s).
		   */
		
		public void produceReport(){
			
			/**
			   * User is prompted to see which report(s) they want to be produced.
			   */
			
			System.out.println("\n Which report(s) would you like to produce?\n");
			System.out.println("\t1. Manager Report\n\t2. Provider Report\n\t3. Member Report\n\t4. All of the reports above\n");
			
			int choice = in.nextInt();
			
			/**
			   * The Switch runs through the options of report types.
			   */
			
			switch (choice){
			
			/**
			   * Entering 0 doesn't affect the code at all.
			   */
			
			case 0:
				break;
				
				/**
				 * If the user enters 1 then the manager report will be produced.
				*/
				
			case 1:
				ReportCreator x = new ReportCreator();
				x.makeManagerReport();
				break;
				
				/**
				 * If the user enters 2 then the provider report will be produced.
				*/
				
			case 2:
				System.out.println("\n Enter the provider number: ");
				ReportCreator y = new ReportCreator();
				Scanner pNum =  new Scanner(System.in);
				int providerNumber = pNum.nextInt();
				y.makeProviderReport(providerNumber);
				break;
				
				/**
				 * If the user enters 3 then the member report will be produced.
				*/
				
			case 3:
				System.out.println("\n Enter the member number: ");
				ReportCreator z = new ReportCreator();
				Scanner mNum =  new Scanner(System.in);
				int memberNumber = mNum.nextInt();
				z.makeMemberReport(memberNumber);
				break;
				
				/**
				 * If the user enters 4 then all of the reports will be produced.
				*/
				
			case 4:
				ReportCreator a = new ReportCreator();
				a.makeAllReports();
				break;
				
				/**
				 * If the user enters anything else the default will issue an error.
				*/
				
			default:
				System.out.println("\n ERROR \n");
				break;
			}
		}
	}

