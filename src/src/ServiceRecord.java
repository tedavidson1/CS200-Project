package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ServiceRecord class. This class creates and manages the service record
 * and provides the necessary functions 
 * @author Henry Schenck
 * 
 *
 */

public class ServiceRecord {
	static ArrayList<Service> Database = new ArrayList<Service>(); //arraylist initializes
	static String fileLocation = "src/src/ServiceRecord.txt";
	ProviderDirectory Directory = new ProviderDirectory();
	
	static { //arraylist populates from txt
		File fileName = new File(fileLocation);
		Scanner s = null;
		try {
			s = new Scanner(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (s.hasNextLine()){
			String tempDateFilled;
			String tempDateProvided;
			int tempProviderNumber;
			int tempMemberNumber;
			int tempCode;
			int tempFee;
			String tempComments;
			String line = s.nextLine();
			String[] words = line.split(",");
			tempDateFilled = words[0];
			tempDateProvided = words[1];
			tempProviderNumber = Integer.parseInt(words[2]);
			tempMemberNumber = Integer.parseInt(words[3]);
			tempCode = Integer.parseInt(words[4]);
			tempFee = Integer.parseInt(words[5]);
			tempComments = words[6];
		    Database.add(new Service(tempDateFilled, tempDateProvided, tempProviderNumber, tempMemberNumber, tempCode, tempFee, tempComments));
		}
		s.close();
	}	
	
	public void addRecord() { // add one record from user input
		
		System.out.print("Enter Current Date in the form of MM-DD-YYYY HH:MM:SS :");
        Scanner in = new Scanner(System.in);
        	String tempDateFilled = in.nextLine();
		System.out.print("Enter Date of Service in the form of MM-DD-YYYY :");
			String tempDateProvided = in.nextLine();
		System.out.print("Enter Your Provider Number:");
			int tempProviderNumber = in.nextInt();
		System.out.print("Enter Their Member Number:");
			int tempMemberNumber = in.nextInt();
		System.out.print("Enter Code of Service:");
			int tempServiceCode = in.nextInt();
			if(Directory.search(tempServiceCode) == null) {
				System.out.print("Error: Invalid Code");
				return;
			}
			System.out.print("Code Entered for: " + Directory.search(tempServiceCode).getName() + "\r\n");
		System.out.print("Enter Fee:");
			int tempFee = in.nextInt();
			in.nextLine(); 
		System.out.print("Enter Any Comments:");
			String tempServiceDate = in.nextLine();
			//in.close();
		
		Service temp = new Service(tempDateFilled, tempDateProvided, tempProviderNumber, tempMemberNumber, tempServiceCode, tempFee, tempServiceDate);
		Database.add(temp);
		 String textToAppend = "\r\n" + temp.dateFilled + "," + temp.dateProvided + "," + temp.providerNumber + "," + temp.memberNumber + ","
		            + temp.serviceCode + "," + temp.fee + "," + temp.comments; //new line in content
		    Path path = Paths.get(fileLocation);
		    try {
				Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}  
		    System.out.print("The fee is: " + tempFee);
		
	}
	
	public ArrayList<Service> searchMemberRecord(int searchCode) { //returns ararylist of records
	ArrayList<Service> searchReturn = new ArrayList<Service>();
	int searchListLength = Database.size();
	for (int i = 0; i < searchListLength; i++) {
		if (Database.get(i).getMemberNumber() == searchCode) {
			Service searchResult = new Service(Database.get(i).getDateFilled(), Database.get(i).getDateProvided(), Database.get(i).getProviderNumber(), Database.get(i).getMemberNumber(),Database.get(i).getServiceCode(), Database.get(i).getFee(), Database.get(i).getComments());
			searchReturn.add(searchResult);
		}
	}
	return searchReturn;
		
	}
	
	
	public ArrayList<Service> searchProviderRecord(int searchCode) { //returns arraylist of records
		ArrayList<Service> searchReturn = new ArrayList<Service>();
		int searchListLength = Database.size();
		for (int i = 0; i < searchListLength; i++) {
			if (Database.get(i).getProviderNumber() == searchCode) {
				Service searchResult = new Service(Database.get(i).getDateFilled(), Database.get(i).getDateProvided(), Database.get(i).getProviderNumber(), Database.get(i).getMemberNumber(), Database.get(i).getServiceCode(), Database.get(i).getFee(), Database.get(i).getComments());
				searchReturn.add(searchResult);
			}
		}
		return searchReturn;
	}
	public void clearRecord() { //deletes file and clears list
		Database.clear();
		File fileName = new File(fileLocation);
		fileName.delete();
		try {
			fileName.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Service Record Deleted.");
	}
}
