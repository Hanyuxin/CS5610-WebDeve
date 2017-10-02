package edu.neu.ccs.cs5010.Section2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PatientGenerator implements Generator {
    private  Random random = new Random();
    private long arriveTimeLow;
    private long arriveTimeHigh;
    public PatientGenerator(long low, long high){
        arriveTimeLow=low;
        arriveTimeHigh= high;
    }

    /**
     * randomly generator new Patient and return Patient
     * @return Patient
     */
    public Patient next() {
        try {
            Patient patient = Patient.class.newInstance();
            patient.setType(0);
            patient.setArriveTime(arriveTimeLow+random.nextInt((int)(arriveTimeHigh-arriveTimeLow+1)));
            patient.setUrgeLevel(1+random.nextInt(10));
            patient.setTreatment(random.nextInt(180));
            return patient;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
