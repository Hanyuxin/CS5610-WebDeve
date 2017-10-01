package edu.neu.ccs.cs5010;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Patient implements Comparable<Patient> {
    private int ID;
    private long arriveTime;
    private int urgeLevel;
    private long treatment;
    private int type;
    private int UseRoomID;
    static int arrive = 0;
    static int left = 1;
//    private DateFormat dateFormat = new SimpleDateFormat("HH:mm MM-dd-yyyy");

    public Patient(){

    }

    public Patient(long arriveTime, int urgeLevel, long treatment){
        this.arriveTime = arriveTime;
        this.urgeLevel = urgeLevel;
        this.treatment = treatment;
    }

    public Patient(int ID,long arriveTime, int urgeLevel, long treatment){
        this.ID = ID;
        this.arriveTime = arriveTime;
        this.urgeLevel = urgeLevel;
        this.treatment = treatment;
    }
    @Override
    public int compareTo(Patient p){
            if (this.urgeLevel == p.getUrgeLevel()) {
                return (int) (p.getArriveTime() - arriveTime);
            }
            else return urgeLevel - p.getUrgeLevel();
    }

    public void setID(int i){
        ID=i;
    }

    public int getID() {
        return ID;
    }

    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return type;
    }

    public void setUseRoomID(int i){
        UseRoomID = i;
    }
    public int getUseRoomID(){
        return UseRoomID;
    }
    /**
     * Set the Arrive time
     * @param time
     */
    public void setArriveTime(long time){
        arriveTime = time;
    }

    /**
     * Set the UrgeLevel
     * @param level
     */
    public void setUrgeLevel(int level){
        urgeLevel = level;
    }

    /**
     * Set the treatment
     * @param treatment
     */
    public void setTreatment(long treatment){
        this.treatment = treatment;
    }

    /**
     * get ArriveTime
     * @return arriveTime
     */
    public long getArriveTime(){
        return arriveTime;
    }

    /**
     * get UrgeLevel
     * @return UrgeLevel
     */
    public int getUrgeLevel(){
        return urgeLevel;
    }

    /**
     * get Treatment
     * @return Treatment
     */
    public long getTreatment(){
        return treatment;
    }

}
