package edu.neu.ccs.cs5010.Section2;

import java.util.Scanner;

public class ERSimulator {
    private int roomNumber;
    private Room[] rooms;
    private int arriveTimeLow;
    private int arriveTimeHigh;
    private long simulationLength;
    private MyPriorityQueue Pqueue;
    private PatientGenerator patientGenerator;
    private Patient[] preSetPatient;
    private int type;
    private static int RANDOM = 0;
    private static int PRESET = 1;

    public ERSimulator(long simulationLength,int arriveTimeLow,int arriveTimeHigh){
        UserInput();
        this.arriveTimeLow=arriveTimeLow;
        this.arriveTimeHigh=arriveTimeHigh;
        this.Pqueue = new MyPriorityQueue();
        this.patientGenerator = new PatientGenerator(arriveTimeLow,arriveTimeHigh);
        this.simulationLength = simulationLength;
        rooms = new Room[roomNumber];
        for(int i = 0; i<roomNumber;i++){
            rooms[i] = new Room();
        }
        if(type == RANDOM) {
            Patient patient = patientGenerator.next();
            patient.setArriveTime(0);
            patient.setID(0);
            Pqueue.insert(patient);
            System.out.println("Patient " + patient.getID() + ", Urge Level is " + patient.getUrgeLevel() + ", Treatment Time is " + patient.getTreatment() + ". Arrive in " + patient.getArriveTime());
        }else{
            Pqueue.insert(preSetPatient[0]);
        }
    }

    /**
     * Interact with User, get the room number and the type of execution
     */
    public void UserInput(){
        System.out.println("Please input the room number:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        while(!s.matches("[1-9][0-9]*")) {
            System.out.println("Please check your input and input again!");
            s = scanner.next();
        }
        roomNumber = Integer.valueOf(s);
        System.out.println("Please choose the Type of simulation");
        System.out.println("[A].Random    [B].PreSet");
        while(s.matches("(A|B|a|b)")){
            System.out.println("Please check your input and input again!");
            s = scanner.next();
        }
        String choice = scanner.next();
        if(choice.toLowerCase().equals("b")){
            type = PRESET;
            System.out.println("Here is the preset patients:");
            System.out.println("Patient 0: Arrive At 0, Urge Level 3, Treatment Time 30");
            System.out.println("Patient 1: Arrive At 3, Urge Level 8, Treatment Time 23");
            System.out.println("Patient 2: Arrive At 8, Urge Level 4, Treatment Time 6");
            System.out.println();
            preSetPatient = new Patient[3];
            preSetPatient[0] = new Patient(0,3,30);
            preSetPatient[0].setID(0);
            preSetPatient[1] = new Patient(3,8,23);
            preSetPatient[1].setID(1);
            preSetPatient[2] = new Patient(8,4,6);
            preSetPatient[2].setID(2);
        }else type = RANDOM;
    }

    /**
     * Get the next available Room
     * @return the room number
     */
    private int nextAvailableRoom(){
        long t = simulationLength;
        int roomID=0;
        for(int i = 0;i<roomNumber;i++)
            if(rooms[i].finishTreat<t){
                t=rooms[i].finishTreat;
                roomID = i;
            }
         return roomID;
    }

    /**
     * Get the next arrive Patient based on last patient information
     * @param patient last patient
     * @return new patient
     */
    private Patient nextArrivePatient(Patient patient){
        Patient p = patientGenerator.next();
        p.setArriveTime(patient.getArriveTime() + p.getArriveTime());
        return p;
    }

    /**
     * Run the Simulation 
     */
    public void runSimulation(){
        int availableRoom = roomNumber-1;
        int patientNumber = 0;
        boolean isAdd = false;
        while(!Pqueue.isEmpty()) {
            Patient patient = (Patient) Pqueue.remove();
            if (patient.getType() == 1) {
                System.out.println("Patient " + patient.getID() + " depart in " + patient.getArriveTime());
            } else {
                System.out.println("Patient " + patient.getID() + " now to be treat");
                Treat(patient);
                if (type == RANDOM) {
                    Patient p = nextArrivePatient(patient);
                    if (p.getArriveTime() > simulationLength) continue;
                    else {
                        patientNumber++;
                        p.setID(patientNumber);
                        while (availableRoom > 0) {
                            System.out.println("Patient " + p.getID() + ", Urge Level is " + p.getUrgeLevel() + ", Treatment Time is " + p.getTreatment() + ". Arrive in " + p.getArriveTime());
                            System.out.println("Patient " + p.getID() + " now to be treat");
                            Treat(p);
                            availableRoom--;
                            p = nextArrivePatient(p);
                            patientNumber++;
                            p.setID(patientNumber);
                        }
                        System.out.println("Patient " + p.getID() + ", Urge Level is " + p.getUrgeLevel() + ", Treatment Time is " + p.getTreatment() + ". Arrive in " + p.getArriveTime());
                        Pqueue.insert(p);
                    }
                } else {
                    if (patient.getType() == 0) {
                        int n = patient.getID();
                        if (n < preSetPatient.length-1) {
                            n++;
                            while(availableRoom > 0) {
                                Patient p = preSetPatient[n];
                                System.out.println("Patient " + p.getID() + ", Urge Level is " + p.getUrgeLevel() + ", Treatment Time is " + p.getTreatment() + ". Arrive in " + p.getArriveTime());
                                Treat(p);
                                n++;
                                availableRoom--;
                            }
                            if(!isAdd) {
                                while (n < preSetPatient.length) {
                                    Pqueue.insert(preSetPatient[n]);
                                    n++;
                                }
                                isAdd = true;
                                }
                        }
                    }
                }
            }
        }
    }

    /**
     * Treat patient and update the properties of examination rooms
     * @param patient
     */
    private void Treat(Patient patient){
        int roomID = nextAvailableRoom();
        System.out.println("Room "+roomID+" now treat patient "+ patient.getID());
        int waitTime = 0; // 客户等待时间
        if (rooms[roomID].finishTreat == 0) {
            rooms[roomID].finishTreat = patient.getArriveTime();
        } else {
            rooms[roomID].finishTreat=patient.getArriveTime()>rooms[roomID].finishTreat? patient.getArriveTime():rooms[roomID].finishTreat;
            waitTime = (int) (rooms[roomID].finishTreat - patient.getArriveTime());
        }
        if(patient.getUrgeLevel()>8) {
            rooms[roomID].lowUrgePatient++;
            rooms[roomID].lowUrgeWait += waitTime;
        }
        else if(patient.getUrgeLevel()<5){
            rooms[roomID].highUrgePatient++;
            rooms[roomID].highUrgeWait += waitTime;
        }
        rooms[roomID].finishTreat += patient.getTreatment();
        rooms[roomID].totalPatientCount++;
        rooms[roomID].totalPatientWait += waitTime;
        rooms[roomID].TotalService += patient.getTreatment();

        Patient newPatient = new Patient(patient.getID(), rooms[roomID].finishTreat, 0, patient.getTreatment());
        newPatient.setType(1);
        Pqueue.insert(newPatient);

    }

    /**
     *  Print the summary result
     */
    public void printResult(){
        int patientNumber=0;
        long totalwait=0;
        long totalLowUrgeWait=0;
        long totalLowUrgeNumber = 0;
        long totalHighUrgeWait=0;
        long totalHighUrgeNumber = 0;
        long totalTreatment = 0;
        for(int i = 0; i< roomNumber;i++){
            patientNumber += rooms[i].totalPatientCount;
            totalwait += rooms[i].totalPatientWait;
            totalTreatment += rooms[i].TotalService;
            totalLowUrgeWait += rooms[i].lowUrgeWait;
            totalHighUrgeWait += rooms[i].highUrgeWait;
            totalLowUrgeNumber += rooms[i].lowUrgePatient;
            totalLowUrgeNumber += rooms[i].lowUrgePatient;
        }
        System.out.println();
        System.out.println("*******Summary*******");
        System.out.println("Total examination rooms are in the system: " + roomNumber);
        System.out.println("Simulation Time: "+((double)simulationLength)/60+" hours");
        System.out.println("Total Patient Number: "+ patientNumber);
        System.out.println("Total Wait Time: "+ totalwait + " minutes");
        double averageLowUrgeWait = totalLowUrgeNumber==0? 0: totalLowUrgeWait/totalLowUrgeNumber;
        double averageHighUrgeWait = totalHighUrgeNumber==0? 0: totalHighUrgeWait/totalHighUrgeNumber;
        double averageTime = patientNumber==0? 0:totalwait/patientNumber;
        System.out.println("Average wait for patients with urgency levels(1-4): " + averageHighUrgeWait);
        System.out.println("Average wait for patients with urgency levels(8-9): " + averageLowUrgeWait);
        System.out.println("Average wait time: "+ averageTime+ " minutes");
        System.out.println("Average duration of treatment: "+ totalTreatment/patientNumber +" minutes");
        for(int i = 0; i<roomNumber; i++){
            double roomPercent = 100 * rooms[i].TotalService/totalTreatment;
            System.out.println();
            System.out.println("Examination Room " +i+" serve " + rooms[i].totalPatientCount+ " patients");
            System.out.println("Examination Room " +i+" serve time "+ rooms[i].TotalService);
            System.out.println("Examination Room " +i+" used percent " + roomPercent);
        }
        System.out.println("Thank you!");
    }

}
