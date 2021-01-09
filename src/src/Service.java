package src;

public class Service {

    protected String dateFilled;
    protected String dateProvided;
    protected int providerNumber;
    protected int memberNumber;
    protected int serviceCode;
    protected int fee;
    protected String comments;

    public Service (String dateFilled, String dateProvided, int providerNumber, int memberNumber, int serviceCode, int fee, String comments) {
        this.dateFilled = dateFilled;
        this.dateProvided = dateProvided;
        this.providerNumber = providerNumber;
        this.memberNumber = memberNumber;
        this.serviceCode = serviceCode;
        this.fee = fee;
        this.comments = comments;
    }

    public String getDateFilled() {
        return dateFilled;
    }    

    public void setDateFilled(String newDate) {
        dateFilled = newDate;
    }

    public String getDateProvided() {
        return dateProvided;
    }

    public void setDateProvided(String newDateProvided) {
        dateProvided = newDateProvided;
    }

    public int getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int newProviderNumber) {
        providerNumber = newProviderNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int newMemberNumber) {
        memberNumber = newMemberNumber;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode (int newServiceCode) {
        serviceCode = newServiceCode;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int newFee) {
        fee = newFee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String newComments) {
        comments = newComments;
    }
    
}