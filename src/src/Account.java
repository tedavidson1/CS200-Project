package src;

/**
 * 
 * @author Thomas Davidson
 *
 */

public class Account {
  //apparently class vars need to be protected if inherited
  protected String name;
  protected int number;
  protected String streetAddress;
  protected String city;
  protected String state;
  protected int zip;
  protected int age;
  protected String email;

  //Constructor: Wrote like this so it isn't a crazy line
  public Account (String name, int number,
  String streetAddress, String city, String state,
  int zip, int age, String email){
    this.name = name;
    this.number = number;
    this.streetAddress = streetAddress;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.age = age;
    this.email = email;
  } //end of Constructor

  //start of gets
  public String getName(){
    return name;
  }

  public int getNumber(){
    return number;
  }

  public String getstreetAddress(){
    return streetAddress;
  }

  public String getCity(){
    return city;
  }

  public String getState(){
    return state;
  }

  public int getZip(){
    return zip;
  }
  public int getAge(){
	    return age;
	  }

  public String getEmail(){
    return email;
  }
  //end of gets
  //start of sets

  public void setName (String newName){
    this.name = newName;
  }

  public void setNumber (int newNumber){
    this.number = newNumber;
  }

  public void setstreetAddress (String newstreetAddress){
    this.streetAddress = newstreetAddress;
  }

  public void setCity (String newCity){
    this.city = newCity;
  }

  public void setState (String newState){
    this.state = newState;
  }

  public void setZip (int newZip){
    this.zip = newZip;
  }

  public void setAge (int newAge){
    this.age = newAge;
  }

  public void setEmail (String newEmail){
    this.email = newEmail;
  }
  //end of sets
}//end of class
