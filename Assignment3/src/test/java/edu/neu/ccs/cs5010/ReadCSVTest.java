package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReadCSVTest {
    ReadCSV readCSV ;

    @Before
    public void setUp(){
        readCSV = new ReadCSV("Flight363FromSeattleToBoston.csv");
    }
    @Test
    public void TestGetInformation(){
        List list = readCSV.getInformation();
        System.out.println(list.get(0));
    }
    @Test
    public void TestGetDeparture(){
        assertEquals("Seattle",readCSV.getDeparture());
    }
    @Test
    public void TestGetDestination(){
        assertEquals("Boston",readCSV.getDestination());
    }
}
