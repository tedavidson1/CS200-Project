package src;

/**
 * 
 * @author Thomas Davidson
 *
 */
public class Member extends Account{
  private int standing;
  
  //Constructor
  public Member (String name, int number,
  String streetAddress, String city, String state,
  int zip, int age, String email, int standing){
	//calls the super class constructor
	 super (name, number, streetAddress, city, state, zip, age, email);
	 
	 //do the added attribute manually
    this.standing = standing;
 
  }//end of constructor

//gets and sets for unique attribute

  public int getStanding(){
    return standing;
  }

  public void setStanding (int newStanding){
    this.standing = newStanding;
  }
	public String toString() {
        return "member[name=" + name + ",num=" + number + ",streetad=" + streetAddress + ",city=" + city + ",state=" + state +",zip=" + zip +",age=" + age +",email=" + email +",standing=" + standing +"]";
	}

}//end of member
