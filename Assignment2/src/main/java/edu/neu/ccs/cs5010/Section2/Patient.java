package edu.neu.ccs.cs5010.Section2;


public class Patient implements Comparable<Patient> {
    private int ID;
    private long arriveTime;
    private int urgeLevel;
    private long treatment;
    private int type;//type of arrive or left
    static int arrive = 0;
    static int left = 1;


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

    /**
     * Compare the patient with a given patient, if their urgeLevel is same, return the differ in arrive time, else return the differ in urgeLevel
     * @param p
     * @return
     */
    @Override
    public int compareTo(Patient p){
            if (this.urgeLevel == p.getUrgeLevel()) {
                return (int) (p.getArriveTime() - arriveTime);
            }
            else return  p.getUrgeLevel()-urgeLevel;
    }

    /**
     *  Set the ID of patient
     * @param i
     */
    void setID(int i){
        ID=i;
    }

    /**
     * get the ID of patient
     * @return ID
     */
    int getID() {
        return ID;
    }

    /**
     * set the type of patient
     * @param type
     */
    void setType(int type){
        this.type = type;
    }

    /**
     * return the type of patient
     * @return int type
     */
    int getType(){
        return type;
    }


//    public void setUseRoomID(int i){
//        UseRoomID = i;
//    }
//    public int getUseRoomID(){
//        return UseRoomID;
//    }
    /**
     * Set the Arrive time
     * @param time
     */
    void setArriveTime(long time){
        arriveTime = time;
    }

    /**
     * Set the UrgeLevel
     * @param level
     */
    void setUrgeLevel(int level){
        urgeLevel = level;
    }

    /**
     * Set the treatment
     * @param treatment treatment
     */
    void setTreatment(long treatment){
        this.treatment = treatment;
    }

    /**
     * get ArriveTime
     * @return arriveTime
     */
    long getArriveTime(){
        return arriveTime;
    }

    /**
     * get UrgeLevel
     * @return UrgeLevel
     */
    int getUrgeLevel(){
        return urgeLevel;
    }

    /**
     * get Treatment
     * @return Treatment
     */
    long getTreatment(){
        return treatment;
    }

}