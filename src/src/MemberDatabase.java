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
 * The MemberDatabase class. This class creates and manages the member database
 * and provides the necessary functions 
 * @author Henry Schenck
 * 
 *
 */

public class MemberDatabase {

  static ArrayList<Member> Database = new ArrayList<Member>(); //Arraylist of Member objects
  static String fileLocation = "src/src/MemberDatabase.txt";   // may need to change
  
  static { //runs every time MemberDatabase object is initialized+creates arraylist from txt file
    File fileName = new File(fileLocation); 
    Scanner s = null;
    try {
      s = new Scanner(fileName);
    } catch (FileNotFoundException e) {
      e.printStackTrace(); 
    }
    while (s.hasNextLine()) { //get arraylist from txt
      String line = s.nextLine();//get line from txt
      String[] words = line.split(",");//split based on ',' char
      String tempName = words[0];
      int tempNumber = Integer.parseInt(words[1]);
      String tempAddress = words[2];
      String tempCity = words[3];
      String tempState = words[4];
      int tempZip = Integer.parseInt(words[5]);
      int tempAge = Integer.parseInt(words[6]);
      String tempEmail = words[7];
      int tempStanding = Integer.parseInt(words[8]);
      Database.add(new Member(tempName, tempNumber, tempAddress, tempCity, //add to list
          tempState, tempZip, tempAge, tempEmail, tempStanding));
    }
    s.close();
  }

  
  /**
   * adds member to arraylist and to txt from user input.
   * @return bool
   */
  
  public boolean addMember() {
    System.out.print("New Member Name:"); //reads in user inputs for new member
    Scanner in = new Scanner(System.in);
    String tempName = in.nextLine();
    System.out.print("New Member Number:");
    int tempNumber = in.nextInt();
    in.nextLine();
    if(checkExists(tempNumber) == true) {
    	System.out.print("Member Number is already in use, aborting.\n");
    	return false;
    }
    System.out.print("New Member Address:");
    String tempAddress = in.nextLine();
    System.out.print("New Member City:");
    String tempCity = in.nextLine();
    System.out.print("New Member State:");
    String tempState = in.nextLine();
    System.out.print("New Member Zip:");
    int tempZip = in.nextInt();
    System.out.print("New Member Age:");
    int tempAge = in.nextInt();
    in.nextLine();
    System.out.print("New Member Email:");
    String tempEmail = in.nextLine();
    System.out.print("New Member Standing(Enter '1' for good standing and '2' for suspended):");
    int tempStanding = in.nextInt();
    //in.close();
    
    Member temp = new Member(tempName, //takes user inputs and adds them to the arraylist
         tempNumber, tempAddress, tempCity, tempState, tempZip, tempAge, tempEmail, tempStanding);
    Database.add(temp);

    String textToAppend = temp.name + "," + temp.number + "," + temp.streetAddress + "," + temp.city + "," //string to add to txt
            + temp.state + "," + temp.zip + "," + temp.age + "," + temp.email + "," + temp.getStanding() + "\r\n"; //new line in content
    Path path = Paths.get(fileLocation);
    try {
		Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND); //adds string
	} catch (IOException e) {
		e.printStackTrace();
	}  
    return true;
  }
  
  /**
    * removes member from arraylist and from txt.
    * @return bool
    */
	
  public boolean deleteMember() {
	    Scanner in = new Scanner(System.in);
	    int edittingMember = -1;
	    String oldMember = null;
	    System.out.print("Enter Number of Member to delete:");
	    int tempNumber = in.nextInt(); //get member number from user
	    //in.close();
	    int searchListLength = Database.size();
	    for (int i = 0; i < searchListLength; i++) { //parse arraylist to find number
	    	int curNum = Database.get(i).getNumber();
	      if (tempNumber == curNum) { //string of line to delete from file
	        oldMember = (Database.get(i).getName() + "," + Database.get(i).getNumber() + "," + Database.get(i).getstreetAddress() + "," + Database.get(i).getCity() + "," + Database.get(i).getState()
	                + "," + Database.get(i).getZip() + "," + Database.get(i).getAge()
	                + "," + Database.get(i).getEmail()+ "," + Database.get(i).getStanding());
	       edittingMember = i;
	       Database.remove(i); //remove from list
	       searchListLength = Database.size();
	        System.out.print("Member Found\n");
	        }
	      }
	      if (edittingMember == -1) {
	    	  System.out.print("Member not Found\n");
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
	    	 String line = sc.nextLine(); //string of file contents
	    	 if(!line.equals(oldMember)) {
	         buffer.append(line+System.lineSeparator()); //add line only if it does not equal line to remove
	    	 }
	      }
	      String fileContents = buffer.toString();
	      System.out.print("Member deleted\n");
	      
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
  
  /**
    * finds member from user input and edits any data user wants to.
    */
	
  public void editMember() {
	    Scanner in = new Scanner(System.in);
	    int edittingMember = -1;
	    String oldMember = null;
	    System.out.print("Enter Number of Member to edit:");
	    int tempNumber = in.nextInt(); //user input of member number
	    int searchListLength = Database.size();
	    for (int i = 0; i < searchListLength; i++) { //search arraylist to find member.
	    	int curNum = Database.get(i).getNumber();
	      if (tempNumber == curNum) {
	    	  
	        oldMember = (Database.get(i).getName() + "," + Database.get(i).getNumber() + "," + Database.get(i).getstreetAddress() + "," + Database.get(i).getCity() + "," + Database.get(i).getState()
	                + "," + Database.get(i).getZip() + "," + Database.get(i).getAge()
	                + "," + Database.get(i).getEmail() + "," + Database.get(i).getStanding()); //string to replace in file
	       edittingMember = i;
	        System.out.print("Member Found\n");
	}
  }
	      if (edittingMember == -1) {
	    	  System.out.print("Member not Found\n");
	    	  in.close();
	    	  return;
	      }
	    System.out.print("Enter 1 to edit Name, 2 to edit Number, 3 to edit Address, 4 to edit City,"
	        + " 5 to edit State, 6 to edit ZIP, 7 to edit Age, 8 to edit email, 9 to edit Standing"); //get input from user for var to edit
	    int editNumber = in.nextInt();
	    in.nextLine();
	    switch (editNumber) {
	      case 1:
	        System.out.print("New Member Name:");
	        String newName = in.nextLine();
	        Database.get(edittingMember).setName(newName);
	        break;
	      case 2:
	        System.out.print("New Member Number:");
	        int newNumber = in.nextInt();
	        if(checkExists(newNumber) == true) {
	        	System.out.print("Member Number is already in use, aborting.\n");
	        	return;
	        }
	        Database.get(edittingMember).setNumber(newNumber);
	        break;
	      case 3:
	        System.out.print("New Member Address:");
	        String newAddress = in.nextLine();
	        Database.get(edittingMember).setstreetAddress(newAddress);
	        break;
	      case 4:
	        System.out.print("New Member City:");
	        String newCity = in.nextLine();
	        Database.get(edittingMember).setCity(newCity);
	        break;
	      case 5:
	        System.out.print("New Member State:");
	        String newState = in.nextLine();
	        Database.get(edittingMember).setState(newState);
	        break;
	      case 6:
	        System.out.print("New Member ZIP:");
	        int newZip = in.nextInt();
	        Database.get(edittingMember).setZip(newZip);
	        break;
	      case 7:
	        System.out.print("New Member Age:");
	        int newAge = in.nextInt();
	        Database.get(edittingMember).setAge(newAge);
	        break;
	      case 8:
	        System.out.print("New Member Email:");
	        String newEmail = in.nextLine();
	        Database.get(edittingMember).setEmail(newEmail);
	        break;
	      case 9:
		    System.out.print("New Member Standing(Enter '1' for good standing and '2' for suspended):");
		    int newStanding = in.nextInt();
		    Database.get(edittingMember).setStanding(newStanding);
		    break;
	      default:
	        System.out.print("Error, not a number 1-9"); //don't do this
	        break;
	    }
	    //in.close();
	    String NewMember = (Database.get(edittingMember).getName() + ","  + Database.get(edittingMember).getNumber() + ","+ Database.get(edittingMember).getstreetAddress() + "," + Database.get(edittingMember).getCity() + "," + Database.get(edittingMember).getState()
	            + "," + Database.get(edittingMember).getZip() + "," + Database.get(edittingMember).getAge()
	            + "," + Database.get(edittingMember).getEmail() + "," + Database.get(edittingMember).getStanding()); //new line to replace old line
	      Scanner sc = null;
		try {
			sc = new Scanner(new File(fileLocation));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	      StringBuffer buffer = new StringBuffer();
	      while (sc.hasNextLine()) {
	         buffer.append(sc.nextLine()+System.lineSeparator()); //build string from file
	      }
	      String fileContents = buffer.toString();
	      
	      sc.close();
	      fileContents = fileContents.replaceAll(oldMember, NewMember); //replace old with new in string
	      FileWriter writer = null;
		try {
			writer = new FileWriter(fileLocation); 
		} catch (IOException e) {
			e.printStackTrace();
		}

	      try {
			writer.append(fileContents); //add to new file
		} catch (IOException e) {
			e.printStackTrace();
		}
	      try {
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	   }
	  
  
  /**
    * search for member using member ID.
    * @return member 
    */
  
  public Member searchManual() {
    System.out.print("Enter ID to search for:");
    Scanner in = new Scanner(System.in);
    int tempNumber = in.nextInt(); //member number to search for
    //in.close();
    Member searchResult = null;
    int searchListLength = Database.size();
    for (int i = 0; i < searchListLength; i++) { //search for it
      if (Database.get(i).getNumber() == tempNumber) {
        searchResult = new Member(Database.get(i).getName(),
            Database.get(i).getNumber(), Database.get(i).getstreetAddress(),
            Database.get(i).getCity(), Database.get(i).getState(),
            Database.get(i).getZip(), Database.get(i).getAge(),
            Database.get(i).getEmail(), Database.get(i).getStanding());
        return searchResult;
      }
    }
    //in.close();
    return null;
  }
  
  /**
   * searched for member in list using number in param.
   * @param searchNumber member number to search with
   * @return member
   */

  public Member searchAutomatic(int searchNumber) {
    int searchListLength = Database.size();
    Member searchResult = null;
    for (int i = 0; i < searchListLength; i++) {
      if (Database.get(i).getNumber() == searchNumber) {
        searchResult = new Member(Database.get(i).getName(),
            Database.get(i).getNumber(), Database.get(i).getstreetAddress(),
            Database.get(i).getCity(), Database.get(i).getState(),
            Database.get(i).getZip(), Database.get(i).getAge(),
            Database.get(i).getEmail(), Database.get(i).getStanding());
        return searchResult;
      }
    }
    return null;
  }
  
  /**
   * unused, checks to see if member exists.
   * @param number number to search
   * @return bool
   */
  
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
