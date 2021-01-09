package src;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * The MemberDatabase class. This class creates and manages the provider directory
 * and provides the necessary functions 
 * @author Henry Schenck
 * 
 *
 */

public class ProviderDirectory {
	static String fileLocation = "src/src/ProviderDirectory.txt";
	static ArrayList<ServiceType> Directory = new ArrayList<ServiceType>();
	static {
		File fileName = new File(fileLocation);
		Scanner s = null;
		try {
			s = new Scanner(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (s.hasNextLine()){
			String tempName;
			int tempCode;
			int tempFee;
			String line = s.nextLine();
			String[] words = line.split(" ");
			tempName = words[0];
			tempCode = Integer.parseInt(words[1]);
			tempFee = Integer.parseInt(words[2]);
		    Directory.add(new ServiceType(tempName, tempCode, tempFee));
		}
		s.close();
	}
	
	public ServiceType search(int searchNumber) {
		int searchListLength = Directory.size();
		for (int i = 0; i < searchListLength; i++) {
			if (Directory.get(i).getCode() == searchNumber) {
				ServiceType searchResult = new ServiceType(Directory.get(i).getName(), Directory.get(i).getCode(), Directory.get(i).getfee());
				return searchResult;
			}
		}
		return null;
	}
	
	public String setAndGetDirectory() {
		return fileLocation;
	}
}
