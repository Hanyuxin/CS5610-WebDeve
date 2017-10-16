package edu.neu.ccs.cs5010.Section2;

import org.junit.Before;
import org.junit.Test;

public class PatientTest {
    private Patient patient;
    private PatientGenerator patientGenerator = new PatientGenerator(10,20);
    @Before
    public void setUp(){
        patient = patientGenerator.next();
        System.out.println("UrgeLevel-" + patient.getUrgeLevel());
        System.out.println("ArriveTime-"+patient.getArriveTime());
        System.out.println("ArriveTime-"+patient.getTreatment());
        System.out.println("Type-"+patient.getType());
        System.out.println("ID-"+patient.getID());
    }

    @Test
    public void testCompareTo(){
        Patient p = patientGenerator.next();
        System.out.println("UrgeLevel-" + p.getUrgeLevel());
        System.out.println("ArriveTime-"+p.getArriveTime());
        System.out.println("ArriveTime-"+p.getTreatment());
        System.out.println(patient.compareTo(p));
        p.setUrgeLevel(4);
        Patient p1 = new Patient(0, 4, 40);
        System.out.println(patient.compareTo(p1));
    }
}
