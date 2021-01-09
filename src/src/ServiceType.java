package src;

/**
 * The ServiceType class. This class is used in the arraylist in service record
 * @author Henry Schenck
 * 
 *
 */

public class ServiceType {
	String serviceName;
	int serviceCode;
	int fee;
	//constructor
	public ServiceType(String serviceName, int serviceCode, int fee) {
		this.serviceName = serviceName;
		this.serviceCode = serviceCode;
		this.fee = fee;
	}
	//gets
	 public String getName(){
		    return serviceName;
		  }
	 public int getCode(){
		    return serviceCode;
		  }
	 public int getfee(){
		    return fee;
		  }
	 
	//sets
	 public void setName (String newName){
		    this.serviceName = newName;
		  }
	 public void setCode (int newCode){
		    this.serviceCode = newCode;
		  }
	 public void setFee (int newFee){
		    this.fee = newFee;
		  }
	
	
	//not important
	public String toString() {
        return "Service[name=" + serviceName + ",Code=" + serviceCode + ",fee=" + fee + "]";
	}
}
