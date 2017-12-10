package edu.neu.ccs.cs5010.section2;

public class Room {
  private long finishTreat = 0; //the time when the room finish a treatment
  private int totalPatientCount = 0; //the total number of patient who use this room
  private int totalPatientWait = 0;//the total time patient wait of this room
  private int highUrgeWait = 0;//the high urgency level patient wait of this room
  private int lowUrgeWait = 0;//the low urgency level patient wait of this room
  private int highUrgePatient = 0; //the total number of high urge Patient
  private int lowUrgePatient = 0; //the total number of low urge Patient
  private long totalService = 0; //the total time this room in service


  /**get FinishTreat
   * @return finishTreat
   */
  long getFinishTreat() {
    return finishTreat;
  }


  /**set the time when the room finish a treatment
   * @param finishTreat Long
   */
  void setFinishTreat(long finishTreat) {
    this.finishTreat = finishTreat;
  }

  /**get the total number of patient who use this room
   * @return totalPatientCount
   */
  int getTotalPatientCount() {
    return totalPatientCount;
  }

  /**set the total number of patient who use this room
   * @param count int
   */
  void setTotalPatientCount(int count) {
    this.totalPatientCount = count;
  }

  /**get the total time patient wait of this room
   * @return totalPatientWait
   */
  int getTotalPatientWait() {
    return totalPatientWait;
  }

  /**set the total time patient wait of this room
   * @param time Int
   */
  void setTotalPatientWait(int time) {
    totalPatientWait = time;
  }

  /**get the high urgency level patient wait of this room
   * @return the high urgency level patient wait of this room
   */
  int getHighUrgeWait() {
    return highUrgeWait;
  }

  /**set the high urgency level patient wait of this room
   * @param time Int
   */
  void setHighUrgeWait(int time) {
    highUrgeWait = time;
  }

  /**the low urgency level patient wait of this room
   * @return the low urgency level patient wait of this room
   */
  int getLowUrgeWait() {
    return lowUrgeWait;
  }

  /**the low urgency level patient wait of this room
   * @param lowUrgeWait the low urgency level patient wait of this room
   */
  void setLowUrgeWait(int lowUrgeWait) {
    this.lowUrgeWait = lowUrgeWait;
  }

  /** the total number of high urge Patient
   * @return the total number of high urge Patient
   */
  int getHighUrgePatient() {
    return highUrgePatient;
  }

  /** the total number of high urge Patient
   * @param highUrgePatient the total number of high urge Patient
   */
  void setHighUrgePatient(int highUrgePatient) {
    this.highUrgePatient = highUrgePatient;
  }

  /**the total number of low urge Patient
   * @return the total number of low urge Patient
   */
  int getLowUrgePatient() {
    return lowUrgePatient;
  }

  /** the total number of low urge Patient
   * @param lowUrgePatient the total number of low urge Patient
   */
  void setLowUrgePatient(int lowUrgePatient) {
    this.lowUrgePatient = lowUrgePatient;
  }

  /**the total time this room in service
   * @return the total time this room in service
   */
  long getTotalService() {
    return totalService;
  }

  /**the total time this room in service
   * @param totalService the total time this room in service
   */
  void setTotalService(long totalService) {
    this.totalService = totalService;
  }
}
