package edu.neu.ccs.cs5010.section2;

import org.junit.Before;
import org.junit.Test;

public class PatientGeneratorTest {
    private PatientGenerator patientGenerator;
    @Before
    public void setup(){
        patientGenerator = new PatientGenerator(10,12);
    }

    @Test
    public void TestNext(){
        Patient patient = patientGenerator.next();
        System.out.println(patient.getArriveTime());
        System.out.println(patient.getTreatment());
        System.out.println(patient.getUrgeLevel());
    }
}
