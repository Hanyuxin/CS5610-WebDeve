package edu.neu.ccs.cs5010.section2;

import java.util.Random;

public class PatientGenerator implements Generator {
  private Random random = new Random();
  private long arriveTimeLow;
  private long arriveTimeHigh;

  PatientGenerator(long low, long high) {
    arriveTimeLow = low;
    arriveTimeHigh = high;
  }

  /**
   * randomly generator new Patient and return Patient
   * @return Patient
   */
  public Patient next() {
    try {
      Patient patient = Patient.class.newInstance();
      patient.setType(0);
      patient.setArriveTime(arriveTimeLow
              + random.nextInt((int) (arriveTimeHigh - arriveTimeLow + 1)));
      patient.setUrgeLevel(1 + random.nextInt(10));
      patient.setTreatment(random.nextInt(180));
      return patient;
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }
}
