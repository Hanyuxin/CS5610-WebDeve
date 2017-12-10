package edu.neu.ccs.cs5010.section2;

import java.util.Scanner;

class ERSimulator {
  private int roomNumber;
  private Room[] rooms;
  private int arriveTimeLow;
  private int arriveTimeHigh;
  private long simulationLength;
  private MyPriorityQueue<Patient> pQueue;
  private PatientGenerator patientGenerator;
  private Patient[] preSetPatient;
  private int type;
  private int availableRoom;
  private static int patientNumber;
  private static final int RANDOM = 0;
  private static final int PRESET = 1;


  public ERSimulator(long simulationLength, int arriveTimeLow, int arriveTimeHigh) {
    userInput();
    this.arriveTimeLow = arriveTimeLow;
    this.arriveTimeHigh = arriveTimeHigh;
    this.pQueue = new MyPriorityQueue<Patient>();
    this.patientGenerator = new PatientGenerator(arriveTimeLow, arriveTimeHigh);
    this.simulationLength = simulationLength;
    rooms = new Room[roomNumber];
    availableRoom = roomNumber - 1;
    for (int i = 0; i < roomNumber; i++) {
      rooms[i] = new Room();
    }
    if (type == RANDOM) {
      Patient patient = patientGenerator.next();
      patient.setArriveTime(0);
      patient.setID(0);
      pQueue.insert(patient);
      System.out.println("Patient " + patient.getID() + ", Urge Level is " + patient.getUrgeLevel()
              + ", Treatment Time is " + patient.getTreatment()
              + ". Arrive in " + patient.getArriveTime());
    } else {
      pQueue.insert(preSetPatient[0]);
    }
  }


  /**
   * Interact with User, get the room number and the type of execution
   */
  private void userInput() {
    System.out.println("Please input the room number:");
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    while (!str.matches("[1-9][0-9]*")) {
      System.out.println("Please check your input and input again!");
      str = scanner.next();
    }
    roomNumber = Integer.parseInt(str);
    System.out.println("Please choose the Type of simulation");
    System.out.println("[A].Random    [B].PreSet");
    while (str.matches("([ABab])")) {
      System.out.println("Please check your input and input again!");
      str = scanner.next();
    }
    String choice = scanner.next();
    if (choice.toLowerCase().equals("b")) {
      type = PRESET;
      System.out.println("Here is the preset patients:");
      System.out.println("Patient 0: Arrive At 0, Urge Level 3, Treatment Time 30");
      System.out.println("Patient 1: Arrive At 3, Urge Level 8, Treatment Time 23");
      System.out.println("Patient 2: Arrive At 8, Urge Level 4, Treatment Time 6");
      System.out.println();
      preSetPatient = new Patient[3];
      preSetPatient[0] = new Patient(0, 3, 30);
      preSetPatient[0].setID(0);
      preSetPatient[1] = new Patient(3, 8, 23);
      preSetPatient[1].setID(1);
      preSetPatient[2] = new Patient(8, 4, 6);
      preSetPatient[2].setID(2);
    } else {
      type = RANDOM;
    }
  }

  /**Get the next available Room
   * @return the room number
   */
  private int nextAvailableRoom() {
    long temp = simulationLength;
    int roomID = 0;
    for (int i = 0; i < roomNumber; i++) {
      if (rooms[i].getFinishTreat() < temp) {
        temp = rooms[i].getFinishTreat();
        roomID = i;
      }
    }
    return roomID;
  }

  /**
   * Get the next arrive Patient based on last patient information
   * @param patient last patient
   * @return new patient
   */
  private Patient nextArrivePatient(Patient patient) {
    Patient pat = patientGenerator.next();
    pat.setArriveTime(patient.getArriveTime() + pat.getArriveTime());
    return pat;
  }

  /**
   * Output depart information
   * @param patient the depart patient
   */
  private void toDepartString(Patient patient) {
    System.out.println("Patient " + patient.getID() + " depart in " + patient.getArriveTime());
  }

  /**
   * Output ready to Treat information
   * @param patient
   */
  private void toReadyTreatString(Patient patient) {
    System.out.println("Patient " + patient.getID() + " now to be treat");
  }

  /**
   * Output treat information
   *
   * @param patient Patient
   */
  private void toNowTreatString(Patient patient) {
    System.out.println("Patient " + patient.getID() + ", Urge Level is " + patient.getUrgeLevel()
            +", Treatment Time is " + patient.getTreatment()
            + ". Arrive in " + patient.getArriveTime());
    System.out.println("Patient " + patient.getID() + " now to be treat");
  }

  /**
   * Add new patient to priority queue
   * @param patient
   */
  private void addNewPatient(Patient patient) {
    Patient nextArrivePatient = nextArrivePatient(patient);
    if (nextArrivePatient.getArriveTime() < simulationLength) {
      patientNumber++;
    }
    nextArrivePatient.setID(patientNumber);
    while (availableRoom > 0) {
      toNowTreatString(nextArrivePatient);
      Treat(nextArrivePatient);
      availableRoom--;
      nextArrivePatient = nextArrivePatient(nextArrivePatient);
      patientNumber++;
      nextArrivePatient.setID(patientNumber);
    }
    toNowTreatString(nextArrivePatient);
    pQueue.insert(nextArrivePatient);
  }

  /**
   * If the type is preSet, add the patient from the preSet array
   * @param patient Patient
   * @param isAdd   Check whether all element have inset in priority queue
   * @return boolean
   */
  private boolean addPreSet(Patient patient, boolean isAdd) {
    int number = patient.getID();
    if (number < preSetPatient.length - 1) {
      number++;
      while (availableRoom > 0) {
        Patient p = preSetPatient[number];
        System.out.println("Patient " + p.getID() + ", Urge Level is " + p.getUrgeLevel() + ""
                +", Treatment Time is " + p.getTreatment() + ". Arrive in " + p.getArriveTime());
        Treat(p);
        number++;
        availableRoom--;
      }
      if (!isAdd) {
        while (number < preSetPatient.length) {
          pQueue.insert(preSetPatient[number]);
          number++;
        }
        isAdd = true;
      }
    }
    return isAdd;
  }

  /**
   * the main function to run the Simulation
   */
  public void runSimulation() {
    boolean isAdd = false;
    while (!pQueue.isEmpty()) {
      Patient patient = pQueue.remove();
      int DEPART = 1;
      if (patient.getType() == DEPART) {
        toDepartString(patient);
        if (type == RANDOM) {
          addNewPatient(patient);
        }
      } else {
        toReadyTreatString(patient);
        Treat(patient);
        if (type == RANDOM) {
          addNewPatient(patient);
        }
        if (type == PRESET) {
          isAdd = addPreSet(patient, isAdd);
        }
      }
    }
  }

  /**
   * Treat patient and update the properties of examination rooms
   * @param patient
   */
  private void Treat(Patient patient) {
    int roomID = nextAvailableRoom();
    System.out.println("Room " + roomID + " now treat patient " + patient.getID());
    int waitTime = 0;
    if (rooms[roomID].getFinishTreat() == 0) {
      rooms[roomID].setFinishTreat(patient.getArriveTime());
    } else {
      long time = patient.getArriveTime() > rooms[roomID].getFinishTreat()
              ? patient.getArriveTime() : rooms[roomID].getFinishTreat();
      rooms[roomID].setFinishTreat(time);
      waitTime = (int) (rooms[roomID].getFinishTreat() - patient.getArriveTime());
    }
    if (patient.getUrgeLevel() > 8) {
      rooms[roomID].setLowUrgePatient(rooms[roomID].getLowUrgePatient() + 1);
      rooms[roomID].setLowUrgeWait(rooms[roomID].getLowUrgeWait() + waitTime);
    } else if (patient.getUrgeLevel() < 5) {
      rooms[roomID].setHighUrgePatient(rooms[roomID].getHighUrgePatient() + 1);
      rooms[roomID].setHighUrgeWait(rooms[roomID].getHighUrgeWait() + waitTime);
    }
    rooms[roomID].setFinishTreat(rooms[roomID].getFinishTreat() + patient.getTreatment());
    rooms[roomID].setTotalPatientCount(rooms[roomID].getTotalPatientCount() + 1);
    rooms[roomID].setTotalPatientWait(rooms[roomID].getTotalPatientWait() + waitTime);
    rooms[roomID].setTotalService(rooms[roomID].getTotalService() + patient.getTreatment());

    Patient newPatient = new Patient(patient.getID(), rooms[roomID].getFinishTreat(),
            0, patient.getTreatment());
    newPatient.setType(1);
    pQueue.insert(newPatient);

  }

  /**
   * Print the summary result
   */
  public void printResult() {
    int patientNumber = 0;
    long totalWait = 0;
    long totalLowUrgeWait = 0;
    long totalLowUrgeNumber = 0;
    long totalHighUrgeWait = 0;
    long totalHighUrgeNumber = 0;
    long totalTreatment = 0;
    for (int i = 0; i < roomNumber; i++) {
      patientNumber += rooms[i].getTotalPatientCount();
      totalWait += rooms[i].getTotalPatientWait();
      totalTreatment += rooms[i].getTotalService();
      totalLowUrgeWait += rooms[i].getLowUrgeWait();
      totalHighUrgeWait += rooms[i].getHighUrgeWait();
      totalLowUrgeNumber += rooms[i].getLowUrgePatient();
      totalLowUrgeNumber += rooms[i].getLowUrgePatient();
    }
    System.out.println();
    System.out.println("*******Summary*******");
    System.out.println("Total examination rooms are in the system: " + roomNumber);
    System.out.println("Simulation Time: " + ((double) simulationLength) / 60 + " hours");
    System.out.println("Total Patient Number: " + patientNumber);
    System.out.println("Total Wait Time: " + totalWait + " minutes");
    double averageLowUrgeWait = totalLowUrgeNumber == 0
            ? 0.0 : (double)totalLowUrgeWait / totalLowUrgeNumber;
    double averageHighUrgeWait = totalHighUrgeNumber == 0
            ? 0 : totalHighUrgeWait / totalHighUrgeNumber;
    double averageTime = patientNumber == 0 ? 0 : totalWait / patientNumber;
    System.out.println("Average wait for patients with urgency levels(1-4): "
            + averageHighUrgeWait);
    System.out.println("Average wait for patients with urgency levels(8-9): "
            + averageLowUrgeWait);
    System.out.println("Average wait time: " + averageTime + " minutes");
    System.out.println("Average duration of treatment: "
            + totalTreatment / patientNumber + " minutes");
    for (int i = 0; i < roomNumber; i++) {
      double roomPercent = 100 * rooms[i].getTotalService() / totalTreatment;
      System.out.println();
      System.out.println("Examination Room " + i + " serve "
              + rooms[i].getTotalPatientCount() + " patients");
      System.out.println("Examination Room " + i + " serve time " + rooms[i].getTotalService());
      System.out.println("Examination Room " + i + " used percent " + roomPercent);
    }
    System.out.println("Thank you!");
  }

}
