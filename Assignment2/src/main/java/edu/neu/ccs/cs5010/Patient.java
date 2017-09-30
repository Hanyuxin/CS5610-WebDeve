package edu.neu.ccs.cs5010;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Patient implements Comparable<Patient> {
    public int ID;
    private long arriveTime;
    private int urgeLevel;
    private long treatment;
    private int type;
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
    @Override
    public int compareTo(Patient p){
        if(type != p.getType()) return p.getType()-type;
        else if(type==arrive) {
            if (this.urgeLevel == p.getUrgeLevel()) {
                return (int) (p.getArriveTime() - arriveTime);
            } else return urgeLevel - p.getUrgeLevel();
        }
        else{
            return (int)(p.getArriveTime()+p.getTreatment()-arriveTime-treatment);
        }
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
