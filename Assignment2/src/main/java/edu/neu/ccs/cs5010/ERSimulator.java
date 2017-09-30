package edu.neu.ccs.cs5010;

import java.util.Scanner;

public class ERSimulator {
    private int roomNumber;
    private Room[] rooms;
    private int arriveTimeLow;
    private int arriveTimeHigh;
    private long simulationLength;
    private MyPriorityQueue arriveQueue;
    private MyPriorityQueue examQueue;
    private PatientGenerator patientGenerator;
    private Patient[] preSetPatient;
    private int type;
    private static int RANDOM = 0;
    private static int PRESET = 1;

    public ERSimulator(long simulationLength,int arriveTimeLow,int arriveTimeHigh){
        UserInput();
        this.arriveTimeLow=arriveTimeLow;
        this.arriveTimeHigh=arriveTimeHigh;
        this.arriveQueue = new MyPriorityQueue();
        this.examQueue = new MyPriorityQueue();
        this.patientGenerator = new PatientGenerator(arriveTimeLow,arriveTimeHigh);
        this.simulationLength = simulationLength;
        rooms = new Room[roomNumber];
        for(int i = 0; i<roomNumber;i++){
            rooms[i] = new Room();
        }
        if(type == RANDOM) {
            Patient patient = patientGenerator.next();
            patient.setArriveTime(0);
            patient.setID(1);
            arriveQueue.insert(patient);
            System.out.println("patient 1 arrive at " + patient.getArriveTime() + ", Urge Level is " + patient.getUrgeLevel() + ", Treatment Time is " + patient.getTreatment());
        }else{
            arriveQueue.insert(preSetPatient[0]);
        }
    }

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
            System.out.println("Patient 1: Arrive At 4, Urge Level 8, Treatment Time 23");
            System.out.println("Patient 2: Arrive At 12, Urge Level 4, Treatment Time 6");
            System.out.println();
            preSetPatient = new Patient[3];
            preSetPatient[0] = new Patient(0,3,30);
            preSetPatient[0].setID(0);
            preSetPatient[1] = new Patient(4,8,23);
            preSetPatient[1].setID(1);
            preSetPatient[2] = new Patient(12,4,6);
            preSetPatient[2].setID(2);
        }else type = RANDOM;
    }

    private int nextAvailableRoom(){
        long t = rooms[0].FinishTreat;
        int roomID=0;
        for(int i = 1;i<roomNumber;i++)
            if(rooms[i].FinishTreat<t){
                t=rooms[i].FinishTreat;
                roomID = i;
            }
         return roomID;
    }

    public void runArriveSimulation(){

        int patientnumber = 1;
        int avaiableroom = roomNumber;
        while(!arriveQueue.isEmpty()) {
            Patient patient = (Patient) arriveQueue.remove();
            if(avaiableroom>0) Treat(patient);
            else{
                patient.setType(1);
                examQueue.insert(patient);
            }
            if (type == RANDOM) {
                Patient p = patientGenerator.next();
                if (p.getArriveTime() > simulationLength) continue;
                else {
                    patientnumber++;
                    p.setID(patientnumber);
                    arriveQueue.insert(p);
                    System.out.println("patient "+ p.getID()+ " arrive at " + p.getArriveTime() + ". Urge Level is " + patient.getUrgeLevel() + " Treatment Time is " + patient.getTreatment());
                }
            }else{
                int n = patient.getID();
                if(n<2) arriveQueue.insert(preSetPatient[n+1]);
            }
        }
    }

    private void Treat(Patient patient){
        int roomID = nextAvailableRoom();
        System.out.println("Room "+roomID+" Available At " +rooms[roomID].FinishTreat);
        int waitTime = 0; // 客户等待时间
        if (rooms[roomID].FinishTreat == 0) {
            rooms[roomID].FinishTreat = patient.getArriveTime();
        } else {
            waitTime = (int) (rooms[roomID].FinishTreat - patient.getArriveTime());
        }
        if(patient.getUrgeLevel()<5) {
            rooms[roomID].lowUrgePatient++;
            rooms[roomID].lowUrgeWait += waitTime;
        }
        else{
            rooms[roomID].highUrgePatient++;
            rooms[roomID].highUrgeWait += waitTime;
        }
        rooms[roomID].FinishTreat += patient.getTreatment();
        rooms[roomID].totalPatientCount++;
        rooms[roomID].totalPatientWait += waitTime;
        rooms[roomID].TotalService += patient.getTreatment();
        long departTime = rooms[roomID].FinishTreat>patient.getArriveTime()? rooms[roomID].FinishTreat:patient.getArriveTime()+rooms[roomID].FinishTreat;
        System.out.println("patient " + patient.getID() +" treated in Room "+ roomID+ " and depart in "+ departTime);
    }
    public void runDepartSimulation(){
        while(!examQueue.isEmpty()) {
            Patient patient = (Patient) examQueue.remove();
            Treat(patient);
//            rooms[roomID].FinishTreat=0;
        }
    }

    public void printResult(){
        int patientnumber=0;
        long totalwait=0;
        long totalLowUrgeWait=0;
        long totalLowUrgeNumber = 0;
        long totalHighUrgeWait=0;
        long totalHighUrgeNumber = 0;
        long totalTreatment = 0;
        for(int i = 0; i< roomNumber;i++){
            patientnumber += rooms[i].totalPatientCount;
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
        System.out.println("Simulation Time: "+simulationLength);
        System.out.println("Total Patient Number: "+ patientnumber);
        System.out.println("Total Wait Time: "+ totalwait);
        double averageLowUrgeWait = totalLowUrgeNumber==0? 0: totalLowUrgeWait/totalLowUrgeNumber;
        double averageHighUrgeWait = totalHighUrgeNumber==0? 0: totalHighUrgeWait/totalHighUrgeNumber;
        double averageTime = patientnumber==0? 0:totalwait/patientnumber;
        System.out.println("Average wait for patients with urgency levels(1-4): " + averageLowUrgeWait);
        System.out.println("Average wait for patients with urgency levels(5-9): " + averageHighUrgeWait);
        System.out.println("Average wait time: "+ averageTime);
        for(int i = 0; i<roomNumber; i++){
            double roomPercent = 100 * rooms[i].TotalService/totalTreatment;
            System.out.println();
            System.out.println("Examination Room " +i+" serve " + rooms[i].totalPatientCount+ " patients");
//            System.out.println("Examination Room " +i+" servetime "+ rooms[i].TotalService);
            System.out.println("Examination Room " +i+" used percent " + roomPercent);
        }
        System.out.println("Thank you!");
    }

}
