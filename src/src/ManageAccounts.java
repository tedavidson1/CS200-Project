package src;

/**
 * The ManageAccounts class. This class acts as an interface between
 * the operator terminal and the two databases
 *
 * @author Jake Frances
 */

public class ManageAccounts {

	//interface class between the operator terminal and the two databases
	
	ProviderDatabase providerDatabase = new ProviderDatabase(); //instance of provider database to make modifications
	MemberDatabase memberDatabase = new MemberDatabase(); //instance of member database to make modifications
	
	/**
	 * adds provider
	 */
	public void addProvider() {
		providerDatabase.addProvider();
		
	}
	
	/**
	 * edits provider
	 */
	public void editProvider() {
		providerDatabase.editProvider();
	}
	
	/**
	 * deletes provider
	 */
	public void deleteProvider() {
		providerDatabase.deleteProvider();
	}
	
	/**
	 * adds member
	 */
	public void addMember() {
		memberDatabase.addMember();
	}
	
	/**
	 * edits member
	 */
	public void editMember() {
		memberDatabase.editMember();
	}
	
	/**
	 * deletes member
	 */
	public void deleteMember() {
		memberDatabase.deleteMember();
	}
}
