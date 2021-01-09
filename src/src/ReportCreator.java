package src;

import java.util.ArrayList;

/**
 * The ReportCreator class. This class provides all of the functions necessary to
 * produce the multiple report types for the system. 
 * @author Thomas Davidson
 * 
 *
 */
public class ReportCreator {
//objects of databases pre-declared to make operation smoother
//private ProviderDirectory directory;
//private MemberDatabase members;
//private ProviderDatabase providers;
//private ServiceRecord services;
	
	//FIX THIS. MAKE CONSTRUCTOR MAKE THESE DATABASES PREMADE

/*	public ReportCreator(){ //default constructor that will just pre-make the databases for me
		ProviderDirectory directory = new ProviderDirectory();
		MemberDatabase members = new MemberDatabase();
		ProviderDatabase providers = new ProviderDatabase();
		ServiceRecord services = new ServiceRecord();
		//called to make objects of each database, all functions need them
	}
*/	
	/**
	 * makeManagerReport is responsible for making the manager report when called.
	 * It first has to determine all of that week's providers
	 * It then lists the provider number, # of consultations, and their overall fee
	 * 
	 * Once every provider has had the above done, the overall # of providers, consultations, and fee is displayed.
	 */
	public void makeManagerReport() {
		/* make the report
		 * every provider to pay that week
		 * how many consultations they had
		 * overall fee for each
		 * then totals:
		 * # of providers
		 * # of consultations
		 * overall total fee
		 */
		
		//I'm about to write something really inefficient
		ServiceRecord services = new ServiceRecord(); //new service record object, all of the weekly stuff
		int overallProviders = 0; //for the very end
		int overallConsultations = 0; // 
		int overallFee = 0; //
		
		ArrayList<Integer> weeklyProviders = new ArrayList<Integer>(); //will use this to know every provider for the week
		//Integer because it requires the wrapper class in ArrayList definition
		
		for (int i = 0; i < services.Database.size(); i++) { //go through all entries, can access since not private and no public method to do this
			//get a warning for this: "The static field ServiceRecord.Database should be accessed in a static way"
			
				int providerCode = services.Database.get(i).getProviderNumber(); //got a provider code
				//System.out.println("Provider Number: " + providerCode);
				boolean flag = false;
			
				for (int j = 0; j < weeklyProviders.size(); j++) { //search ArrayList of codes for if it is a duplicate
					if (providerCode == weeklyProviders.get(j)) flag = true; //as long as it hits once
				}//end of inner loop
			
			if (flag == false) weeklyProviders.add(providerCode); //since the code is not already in the list, add it
		}//end of outer loop
		//System.out.println("Survived the gathering of unique providers");
		//now have every single provider, can now get the data for each one
		
		if (weeklyProviders.size() == 0) { //in case
			System.out.println("There are currently no records of service for the week.");
			System.out.println("Total # of Providers: " + overallProviders);
			System.out.println("Total # of Consultations: " + overallConsultations);
			System.out.println("Overall Fee: " + overallFee);
			return;
		} //if it triggers, it won't continue execution, so no need for else case
		
		System.out.println("Every Provider for the Week:");
		
		for (int i = 0; i < weeklyProviders.size(); i++) {//begin looping for every provider
			int totalFee = 0;
			int totalConsultations = 0;
			System.out.print("Number: " + weeklyProviders.get(i) + " "); //the number of the provider
			
			ArrayList<Service> hasProvider = services.searchProviderRecord(weeklyProviders.get(i));
			
			for (int j = 0; j < hasProvider.size(); j++) {
			totalFee = totalFee + hasProvider.get(j).getFee(); //gets the fee
			totalConsultations = totalConsultations + 1; //+1 since each one is 1 consultation
		}//end of inner loop
			
		System.out.print("Total Fee: " + totalFee);
		System.out.print(" # of Consultations: " + totalConsultations);
		System.out.println(" ");
		
		overallProviders++;
		overallConsultations = overallConsultations + totalConsultations;
		overallFee = overallFee + totalFee;
		}//end of outer loop
		
		System.out.println(" ");
		System.out.println("Total # of Providers: " + overallProviders);
		System.out.println("Total # of Consultations: " + overallConsultations);
		System.out.println("Overall Fee: " + overallFee);
	}//end of makeManagerReport
	
	/**
	 * makeProviderReport creates a provider report for a single provider, specified by their number.
	 * It first retrieves the personal data of that provider
	 * It acquires a list of every service in the service record that the provider provided.
	 * It then goes through each one, printing the service specific info.
	 * Finally, the total # of consultations and total fee to be paid are displayed.
	 * @param providerNumber, the provider we want to make a report for
	 */
	public void makeProviderReport(int providerNumber) {
		/* given a providerNumber, make a report
		 * provider name
		 * provider number
		 * provider street address
		 * provider city
		 * provider state
		 * provider zip
		 *CAN GET ALL FROM SEARCHING PROVIDER DATASEBASE
		 *For each service provided:
		 *date of service
		 *date and time data was received by computer
		 *member name
		 *member number
		 *service code
		 *fee to be paid
		 *
		 *total # of consultations given
		 *total fee for week (sum of fee to be paid)
		 */

		MemberDatabase members = new MemberDatabase();
		ProviderDatabase providers = new ProviderDatabase();
		ServiceRecord services = new ServiceRecord();
		int totalFee = 0; //will sum all of the fees
		
		//check to make sure that the requested provider actually is in there
		Provider target = providers.searchAutomatic(providerNumber);
		
		if (target == null) { //if the provider is not in the database
			System.out.println("Error: Provider is not in the provider database");
			System.out.println(" ");
			return; //no need to continue execution
		}
		
		else { //it does exist, make the report
			System.out.println("Report for: " + target.getName());
			System.out.println("Provider Number: " + target.getNumber());
			System.out.println("Address: " + target.getstreetAddress());
			System.out.println("City: " + target.getCity());
			System.out.println("State: " + target.getState());
			System.out.println("Zip: " + target.getZip());
			System.out.println(" ");
			
			//now get the service dependent stuff
			ArrayList<Service> hasProvider = services.searchProviderRecord(providerNumber);
			//now have a list of every service that includes the target member
			int searchListLength = hasProvider.size();
			for (int i = 0; i < searchListLength; i++) {
				System.out.println("Date the Service was Provided: " + hasProvider.get(i).getDateProvided());
				System.out.println("Date Received by Computer: " + hasProvider.get(i).getDateFilled());
				
				/* Get member code
				 * then search member database for that code
				 * then get the name of the member
				 */
				
				int code = hasProvider.get(i).getMemberNumber(); //gets the member number
				Member member = members.searchAutomatic(code); //gets the member by searching with member number
				System.out.println("Member Name: " + member.getName()); //prints the member number
				System.out.println("Member Number: " + member.getNumber()); //prints the member name
				
				//now just the fee and service code
				System.out.println("Service Code: " + hasProvider.get(i).getServiceCode()); //prints the service code
				System.out.println("Fee for Service: " + hasProvider.get(i).getFee()); //prints the fee
				totalFee = totalFee + hasProvider.get(i).getFee(); //adds the fee to the running total
				System.out.println(" ");
			}
			System.out.println("Total # of Consultations for this Week: " + searchListLength);
			System.out.println("Total Fee to be Paid: " + totalFee);	
	}
} //end of makeProviderReport
	
	/**
	 * makeMemberReport creates a member report for a single member, specified by their number.
	 * It first retrieves the personal data of that provider.
	 * It acquires a list of every service in the service record that the provider provided.
	 * It then goes through each one, printing the service specific info.
	 * @param memberNumber, the member we want to make a report for
	 */
	public void makeMemberReport(int memberNumber) {
		/* given a providerNumber, make a report
		 * Member name
		 * street address
		 * city
		 * state
		 * zip
		 *CAN GET ALL ABOVE FROM SEARCHING MEMBER DATABASE
		 * For each service provided:
		 * date of service
		 * provider name
		 * service name
		 */
		
		ProviderDirectory directory = new ProviderDirectory();
		MemberDatabase members = new MemberDatabase();
		ProviderDatabase providers = new ProviderDatabase();
		ServiceRecord services = new ServiceRecord();
		
		//check to make sure that the requested member actually is in there
		Member target = members.searchAutomatic(memberNumber);
		
		if (target == null) { //if the member is not in the database
			System.out.println("Error: Member is not in the member database");
			System.out.println(" ");
			return;
		}
		
		else { //it does exist, make the report
			System.out.println("Report for: " + target.getName());
			System.out.println("Number: " + target.getNumber());
			System.out.println("Address: " + target.getstreetAddress());
			System.out.println("City: " + target.getCity());
			System.out.println("State: " + target.getState());
			System.out.println("Zip: " + target.getZip());
			
			//now get the service dependent stuff
			ArrayList<Service> hasMember = services.searchMemberRecord(memberNumber);
			//now have a list of every service that includes the target member
			int searchListLength = hasMember.size();
			for (int i = 0; i < searchListLength; i++) {
				System.out.println("Date Service was Provided: " + hasMember.get(i).getDateProvided()); //prints the date provided
				
				/* Get provider code
				 * then search provider database for that code
				 * then get the name of the provider
				 */
				
				int code = hasMember.get(i).getProviderNumber(); //gets the provider number
				Provider provider = providers.searchAutomatic(code); //gets the provider by searching with provider number
				if (provider == null) {
					System.out.println("We are sorry, but that provider is either no longer in service or has been issued a new number.");
				}
				else {
				System.out.println("Provider Name: " + provider.getName()); //prints the provider name
				}
				/* Get service code
				 * then search provider directory for that code
				 * then get the name of that service
				 */
				
				int serviceCode = hasMember.get(i).getServiceCode(); //gets the service code
				ServiceType type = directory.search(serviceCode); //gets the servicetype by searching with the service code
				System.out.println("Service provided: " + type.getName()); //prints the service name
				System.out.println(" ");
			}//end of loop
		}//end of existence case
	}//end of makeMemberReport
	
	/**
	 * makeAllReports acquires two lists: one for every provider from that week and one for every member from that week
	 * It then calls to make member reports for every member and provider reports for every provider
	 * It then calls to make the manager report
	 */
	public void makeAllReports() {
		//get every provider number
		//get every member number
		//make every member report
		//make every provider report
		//make the manager report
		
		//COULD MAKE A PRIVATE METHOD STUB THAT RECEIVES THE PROVIDER ARRAYLIST TO SAVE COMPUTATION TIME
		
		ServiceRecord services = new ServiceRecord(); //new service record object, all of the weekly stuff
		
		ArrayList<Integer> weeklyProviders = new ArrayList<Integer>(); //will use this to know every provider for the week
		//Integer because it requires the wrapper class in ArrayList definition
		for (int i = 0; i < services.Database.size(); i++) { //go through all entries, can access since not private and no public method to do this
			//get a warning for this: "The static field ServiceRecord.Database should be accessed in a static way"
			
			int providerCode = services.Database.get(i).getProviderNumber(); //got a provider code
			boolean flag = false;
			
			for (int j = 0; j < weeklyProviders.size(); j++) { //search ArrayList of codes for if it is a duplicate
				if (providerCode == weeklyProviders.get(j)) flag = true; //as long as it hits once
			}//end of inner loop
			
			if (flag == false) weeklyProviders.add(providerCode); //since the code is not already in the list, add it
		}//end of outer loop
		
		//now get the members
		
		ArrayList<Integer> weeklyMembers = new ArrayList<Integer>(); //will use this to know every member for the week
		//Integer because it requires the wrapper class in ArrayList definition
		
		for (int i = 0; i < services.Database.size(); i++) { //go through all entries, can access since not private and no public method to do this
			//get a warning for this: "The static field ServiceRecord.Database should be accessed in a static way"
			
			int memberNumber = services.Database.get(i).getMemberNumber(); //got a member number
			boolean flag = false;
			
			for (int j = 0; j < weeklyMembers.size(); j++) { //search ArrayList of codes for if it is a duplicate
				if (memberNumber == weeklyMembers.get(j)) flag = true; //as long as it hits once
			}//end of inner loop
			
			if (flag == false) weeklyMembers.add(memberNumber); //since the number is not already in the list, add it
		}//end of outer loop
		
		//make every member report
		for (int i = 0; i < weeklyMembers.size(); i++) {
			makeMemberReport(weeklyMembers.get(i));
		}
		
		//make every provider report
		for (int i = 0; i < weeklyProviders.size(); i++) {
			makeProviderReport(weeklyProviders.get(i));
		}
		
		//make manager report
		//REPLACE THIS WITH A METHOD STUB FOR FASTER COMP TIME
		makeManagerReport();
		
		
	}//end of makeAllReports
	
	/**
	 * clearDatabase is used to clear the persistent database of services for that week
	 * This function exists to be called by the timer class, since the week for these services has expired.
	 */
	public void clearDatabase() {
		ServiceRecord services = new ServiceRecord();
		services.clearRecord();
	}
}
