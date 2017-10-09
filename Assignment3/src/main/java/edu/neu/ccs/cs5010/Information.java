package edu.neu.ccs.cs5010;

public class Information {
    String[] informations;

    public Information(String line){
        informations = line.split("\"");
    }

    public String getFirstName(){
        return informations[1];
    }

    public String getLastName(){
        return informations[3];
    }

    public String getEmail(){
        return informations[17];
    }

    public String getRewards() {
        return informations[19];
    }
}
