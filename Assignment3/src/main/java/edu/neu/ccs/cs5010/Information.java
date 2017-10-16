package edu.neu.ccs.cs5010;

public class Information {
    private String[] informations;
    private int firstNameIndex;
    private int lastNameIndex;
    private int emailIndex;
    private int rewardIndex;


    public Information(String line,int firstNameIndex, int lastNameIndex, int emailIndex, int rewardIndex){
        informations = line.split("\"");
        this.firstNameIndex = firstNameIndex;
        this.lastNameIndex = lastNameIndex;
        this.emailIndex = emailIndex;
        this.rewardIndex = rewardIndex;
    }


    public String getFirstName(){
        return informations[firstNameIndex];
    }

    public String getLastName(){
        return informations[lastNameIndex];
    }

    public String getEmail(){
        return informations[emailIndex];
    }

    public String getRewards() {
        return informations[rewardIndex];
    }
}
