package edu.neu.ccs.cs5010.Section2;

public class Room{
    public long finishTreat=0; //the time when the room finish a treatment
    public int totalPatientCount=0; //the total number of patient who use this room
    public int totalPatientWait=0;//the total time patient wait of this room
    public int highUrgeWait=0;//the high urgency level patient wait of this room
    public int lowUrgeWait = 0;//the low urgency level patient wait of this room
    public int highUrgePatient=0; //the total number of high urge Patient
    public int lowUrgePatient=0; //the total number of low urge Patient
    public long TotalService=0; //the total time this room in service
}
