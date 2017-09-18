package Assignment1.test;

import Assignment1.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ScenicRoadTest {
    private static ScenicRoad sr;

    @BeforeClass
    public static void setupBeforeClass(){
        sr = new ScenicRoad(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException(){
        ScenicRoad scenicRoad = new ScenicRoad(-10);
    }

    @Test
    public void TestMethod(){
        Vehicle v1 = new Vehicle(6.5,2);
        Vehicle v2 = new Vehicle(7.8,2);
        assertEquals("Test Add:",true,sr.add(v1));
        assertEquals("Test reuse getVelocityWestbound",6.5,sr.getVelocityWestbound());
        assertEquals("Test Add:",true,sr.add(v2));
        assertEquals("Test overAdd",4.0,sr.getVelocityWestbound());
        assertEquals("Test Remove",true,sr.remove(v1));
        assertEquals("Test the velocity after Remove",7.8,sr.getVelocityWestbound());
    }

}