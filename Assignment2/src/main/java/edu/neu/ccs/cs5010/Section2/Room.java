package edu.neu.ccs.cs5010.Section2;

public class Room{
    private long finishTreat=0; //the time when the room finish a treatment
    private int totalPatientCount=0; //the total number of patient who use this room
    private int totalPatientWait=0;//the total time patient wait of this room
    private int highUrgeWait=0;//the high urgency level patient wait of this room
    private int lowUrgeWait = 0;//the low urgency level patient wait of this room
    private int highUrgePatient=0; //the total number of high urge Patient
    private int lowUrgePatient=0; //the total number of low urge Patient
    private long TotalService=0; //the total time this room in service

    long getFinishTreat(){
        return finishTreat;
    }

    void setFinishTreat(long finishTreat){
        this.finishTreat = finishTreat;
    }

    int getTotalPatientCount(){
        return totalPatientCount;
    }

    void setTotalPatientCount(int count){
        this.totalPatientCount = count;
    }

    int getTotalPatientWait(){
        return totalPatientWait;
    }

    void setTotalPatientWait(int time){
        totalPatientWait = time;
    }

    int getHighUrgeWait(){
        return highUrgeWait;
    }

    void setHighUrgeWait(int time){
        highUrgeWait = time;
    }

    int getLowUrgeWait() {
        return lowUrgeWait;
    }

    void setLowUrgeWait(int lowUrgeWait) {
        this.lowUrgeWait = lowUrgeWait;
    }

    int getHighUrgePatient() {
        return highUrgePatient;
    }

    void setHighUrgePatient(int highUrgePatient) {
        this.highUrgePatient = highUrgePatient;
    }

    int getLowUrgePatient() {
        return lowUrgePatient;
    }

    void setLowUrgePatient(int lowUrgePatient) {
        this.lowUrgePatient = lowUrgePatient;
    }

    long getTotalService() {
        return TotalService;
    }

    void setTotalService(long totalService) {
        TotalService = totalService;
    }
}
