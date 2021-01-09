package src;

/**
 * 
 * @author Thomas Davidson
 *
 */
public class Provider extends Account{
  //it doesn't have anything extra
  //just needs to be a unique class for differentiating

  //Constructor
  public Provider (String name, int number,
  String streetAddress, String city, String state,
  int zip, int age, String email){
    super (name, number, streetAddress, city, state, zip, age, email);
    //calls the super class constructor
  }//end of constructor

}//end of class
