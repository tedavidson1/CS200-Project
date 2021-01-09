package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The ProviderDatabase class. This class creates and manages the provider database
 * and provides the necessary functions 
 * @author Henry Schenck
 * 
 *
 */

public class ProviderDatabase { 
	
  static ArrayList<Provider> Database = new ArrayList<Provider>(); //arraylist of provider
  static String fileLocation = "src/src/ProviderDatabase.txt";
	
  static { //runs every time class is initilized to set up list from txt
    File fileName = new File(fileLocation);
    Scanner s = null;
    try {
      s = new Scanner(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    while (s.hasNextLine()) {
      String line = s.nextLine();
      String[] words = line.split(",");
      String tempName = words[0];
      int tempNumber = Integer.parseInt(words[1]);
      String tempAddress = words[2];
      String tempCity = words[3];
      String tempState = words[4];
      int tempZip = Integer.parseInt(words[5]);
      int tempAge = Integer.parseInt(words[6]);
      String tempEmail = words[7];
      Database.add(new Provider(tempName, tempNumber, tempAddress,
           tempCity, tempState, tempZip, tempAge, tempEmail));
    }
    s.close();
  }

  /**
   * adds provider to arraylist and txt.
   * @return bool
   */
  
  public boolean addProvider() {

    System.out.print("New Provider Name:"); //user input for data
    Scanner in = new Scanner(System.in);
    String tempName = in.nextLine();
    System.out.print("New Provider Number:");
    int tempNumber = in.nextInt();
    in.nextLine();
    if(checkExists(tempNumber) == true) {
    	System.out.print("Provider Number is already in use, aborting.\n");
    	return false;
    }
    System.out.print("New Provider Address:");
    String tempAddress = in.nextLine();
    System.out.print("New Provider City:");
    String tempCity = in.nextLine();
    System.out.print("New Provider State:");
    String tempState = in.nextLine();
    System.out.print("New Provider Zip:");
    int tempZip = in.nextInt();
    System.out.print("New Provider Age:");
    int tempAge = in.nextInt();
    in.nextLine();
    System.out.print("New Provider Email:");
    String tempEmail = in.nextLine();
    //in.close();
    Provider temp = new Provider(tempName, tempNumber, tempAddress,
        tempCity, tempState, tempZip, tempAge, tempEmail);
    Database.add(temp);
    
    String textToAppend = temp.name + "," + temp.number + "," + temp.streetAddress + "," + temp.city + ","
            + temp.state + "," + temp.zip + "," + temp.age + "," + temp.email + "\r\n"; //new line in content
    Path path = Paths.get(fileLocation);
    try {
		Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
	} catch (IOException e) {
		e.printStackTrace();
	}  
    
    return true;
  }
  
  /**
   * deletes provider from arraylist and txt.
   * @return bool
   */
  
  public boolean deleteProvider() {
	    Scanner in = new Scanner(System.in);
	    int edittingProvider = -1;
	    String oldProvider = null;
	    System.out.print("Enter Number of Provider to delete:");
	    int tempNumber = in.nextInt();
	    //in.close();
	    //System.out.print(tempNumber);
	    int searchListLength = Database.size();
	    for (int i = 0; i < searchListLength; i++) {
	    	int curNum = Database.get(i).getNumber();
	      if (tempNumber == curNum) {
	        oldProvider = (Database.get(i).getName() + "," + Database.get(i).getNumber() + "," + Database.get(i).getstreetAddress() + "," + Database.get(i).getCity() + "," + Database.get(i).getState()
	                + "," + Database.get(i).getZip() + "," + Database.get(i).getAge()
	                + "," + Database.get(i).getEmail());
	       edittingProvider = i;
	       Database.remove(i); //remove from list
	       searchListLength = Database.size();
	        System.out.print("Provider Found\n");
	        }
	      }
	      if (edittingProvider == -1) {
	    	  System.out.print("Provider not Found\n");
	    	  return false;
	    	  }
	      Scanner sc = null;
		try {
			sc = new Scanner(new File(fileLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      StringBuffer buffer = new StringBuffer();
	      while (sc.hasNextLine()) {
	    	 String line = sc.nextLine();
	    	 if(!line.equals(oldProvider)) {
	         buffer.append(line+System.lineSeparator());
	    	 }
	      }
	      String fileContents = buffer.toString();
	      System.out.print("Provider deleted\n");
	      
	      sc.close();
	      FileWriter writer = null;
		try {
			writer = new FileWriter(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

	      try {
			writer.append(fileContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	      try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
  }
  
 /**edits provider based on user input.
  * 
  */
  
  public void editProvider() {
	    Scanner in = new Scanner(System.in);
	    int edittingProvider = -1;
	    String oldProvider = null;
	    System.out.print("Enter Number of Provider to edit:");
	    int tempNumber = in.nextInt();
	    //System.out.print(tempNumber);
	    int searchListLength = Database.size();
	    for (int i = 0; i < searchListLength; i++) {
	    	int curNum = Database.get(i).getNumber();
	      if (tempNumber == curNum) {
	        oldProvider = (Database.get(i).getName() + "," + Database.get(i).getNumber() + "," + Database.get(i).getstreetAddress() + "," + Database.get(i).getCity() + "," + Database.get(i).getState()
	                + "," + Database.get(i).getZip() + "," + Database.get(i).getAge()
	                + "," + Database.get(i).getEmail());
	       edittingProvider = i;
	        System.out.print("Member Found\n");
	}
  }
	      if (edittingProvider == -1) {
	    	  System.out.print("Member not Found\n");
	    	  //in.close();
	    	  return;
	      }
	    System.out.print("Enter 1 to edit Name, 2 to edit Number, 3 to edit Address, 4 to edit City,"
	        + " 5 to edit State, 6 to edit ZIP, 7 to edit Age, 8 to edit email");
	    int editNumber = in.nextInt();
	    in.nextLine();
	    switch (editNumber) {
	      case 1:
	        System.out.print("New Provider Name:");
	        String newName = in.nextLine();
	        Database.get(edittingProvider).setName(newName);
	        break;
	      case 2:
	        System.out.print("New Provider Number:");
	        int newNumber = in.nextInt();
	        if(checkExists(newNumber) == true) {
	        	System.out.print("Provider Number is already in use, aborting.\n");
	        	return;
	        }
	        Database.get(edittingProvider).setNumber(newNumber);
	        break;
	      case 3:
	        System.out.print("New Provider Address:");
	        String newAddress = in.nextLine();
	        Database.get(edittingProvider).setstreetAddress(newAddress);
	        break;
	      case 4:
	        System.out.print("New Provider City:");
	        String newCity = in.nextLine();
	        Database.get(edittingProvider).setCity(newCity);
	        break;
	      case 5:
	        System.out.print("New Provider State:");
	        String newState = in.nextLine();
	        Database.get(edittingProvider).setState(newState);
	        break;
	      case 6:
	        System.out.print("New Provider ZIP:");
	        int newZip = in.nextInt();
	        Database.get(edittingProvider).setZip(newZip);
	        break;
	      case 7:
	        System.out.print("New Provider Age:");
	        int newAge = in.nextInt();
	        Database.get(edittingProvider).setAge(newAge);
	        break;
	      case 8:
	        System.out.print("New Provider Email:");
	        String newEmail = in.nextLine();
	        Database.get(edittingProvider).setEmail(newEmail);
	        break;
	      default:
	        System.out.print("Error, not a number 1-8");
	        break;
	    }
	    //in.close();
	    String NewProvider = (Database.get(edittingProvider).getName() + ","  + Database.get(edittingProvider).getNumber() + "," + Database.get(edittingProvider).getstreetAddress() + "," + Database.get(edittingProvider).getCity() + "," + Database.get(edittingProvider).getState()
	            + "," + Database.get(edittingProvider).getZip() + "," + Database.get(edittingProvider).getAge()
	            + "," + Database.get(edittingProvider).getEmail());

	    Scanner sc = null;
		try {
			sc = new Scanner(new File(fileLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      StringBuffer buffer = new StringBuffer();
	      while (sc.hasNextLine()) {
	         buffer.append(sc.nextLine()+System.lineSeparator());
	      }
	      String fileContents = buffer.toString();
	      //System.out.print(fileContents);
	      sc.close();
	      fileContents = fileContents.replaceAll(oldProvider, NewProvider);
	      FileWriter writer = null;
		try {
			writer = new FileWriter(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}

	      try {
			writer.append(fileContents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	      try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      
	   }
	  
	
	public Provider searchManual() {
		System.out.print("Enter ID to search for:");
		Scanner in = new Scanner(System.in);
		Provider searchResult = null;
		int tempNumber = in.nextInt();
		int searchListLength = Database.size();
		for (int i = 0; i < searchListLength; i++) {
			if (Database.get(i).getNumber() == tempNumber) {
				searchResult = new Provider(Database.get(i).getName(), Database.get(i).getNumber(), Database.get(i).getstreetAddress(), Database.get(i).getCity(), Database.get(i).getState(), Database.get(i).getZip(), Database.get(i).getAge(), Database.get(i).getEmail());
				//in.close();
				return searchResult;
			}
		}
		//in.close();
		return null;
	}
	public Provider searchAutomatic(int searchNumber) {
		int searchListLength = Database.size();
		Provider searchResult = null;
		for (int i = 0; i < searchListLength; i++) {
			if (Database.get(i).getNumber() == searchNumber) {
				//System.out.print("\n" + Database.get(i).toString());
				searchResult = new Provider(Database.get(i).getName(), Database.get(i).getNumber(), Database.get(i).getstreetAddress(), Database.get(i).getCity(), Database.get(i).getState(), Database.get(i).getZip(), Database.get(i).getAge(), Database.get(i).getEmail());
				return searchResult;
			}
		}
		return null;
	}
	
	private boolean checkExists(int number) {
		int searchListLength = Database.size();
		for (int i = 0; i < searchListLength; i++) {
			if (Database.get(i).getNumber() == number) {
				return true;
			}

		}
		return false;
	}
		
}
	

