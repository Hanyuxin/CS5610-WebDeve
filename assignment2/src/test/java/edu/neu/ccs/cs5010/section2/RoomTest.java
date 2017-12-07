package edu.neu.ccs.cs5010.section2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoomTest {
    private Room room;

    @Before
    public void setUp(){
        room = new Room();
    }

    @Test
    public void testFinishTreat(){
        room.setFinishTreat(100);
        assertEquals(100,room.getFinishTreat());
    }

    @Test
    public void testTotalPatientCount(){
        room.setTotalPatientCount(10);
        assertEquals(10,room.getTotalPatientCount());
    }

    @Test
    public void testTotalPatientWait(){
        room.setTotalPatientWait(80);
        assertEquals(80,room.getTotalPatientWait());
    }

    @Test
    public void testHighUrgeWait(){
        room.setHighUrgeWait(10);
        assertEquals(10,room.getHighUrgeWait());
    }

    @Test
    public void testLowUrgeWait(){
        room.setLowUrgeWait(10);
        assertEquals(10,room.getLowUrgeWait());
    }

    @Test
    public void testHighUrgePatient(){
        room.setHighUrgePatient(8);
        assertEquals(8,room.getHighUrgePatient());
    }

    @Test
    public void testLowUrgePatient(){
        room.setLowUrgePatient(8);
        assertEquals(8,room.getLowUrgePatient());
    }

    @Test
    public void testTotalService(){
        room.setTotalService(100);
        assertEquals(100,room.getTotalService());
    }

}
