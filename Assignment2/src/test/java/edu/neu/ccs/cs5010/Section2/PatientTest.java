package edu.neu.ccs.cs5010.Section2;

import org.junit.Before;
import org.junit.Test;

public class PatientTest {
    Patient patient;
    PatientGenerator patientGenerator = new PatientGenerator(10,20);
    @Before
    public void setUp(){
        patient = patientGenerator.next();
        System.out.println("UrgeLevel-" + patient.getUrgeLevel());
        System.out.println("ArriveTime-"+patient.getArriveTime());
        System.out.println("ArriveTime-"+patient.getTreatment());
    }

    @Test
    public void testCompareTo(){
        Patient p = patientGenerator.next();
        System.out.println("UrgeLevel-" + p.getUrgeLevel());
        System.out.println("ArriveTime-"+p.getArriveTime());
        System.out.println("ArriveTime-"+p.getTreatment());
        System.out.println(patient.compareTo(p));
    }
}
