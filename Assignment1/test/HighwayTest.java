package Assignment1.test;

import Assignment1.Highway;
import Assignment1.Vehicle;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class HighwayTest {

    private static Highway highway ;
    @BeforeClass
    public static void setupBeforeClass(){
        highway = new Highway();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentException1(){
        highway.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentException2(){
        highway.remove(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectedIllegalArgumentException3(){
        highway.contains(null);
    }

    @Test
    public void testMethod(){
        Vehicle v1 = new Vehicle(5.5,1);
        Vehicle v2 = new Vehicle(12.8,1);
        assertEquals("Test Add case 1:",true,highway.add(v1));
        assertEquals("Test Add case 1:",true,highway.add(v2));
        assertEquals("Test numberVehiclesEastbound",2,highway.numberVehiclesEastbound());
        assertEquals("Test numberVehiclesWestbound",0,highway.numberVehiclesWestbound());
        assertEquals("Test getVelocityEastbound",5.5,highway.getVelocityEastbound());
        assertEquals("Test getVelocityWestbound",0.0,highway.getVelocityWestbound());
        assertEquals("Test contains case 1",true,highway.contains(v1));
        assertEquals("Test Add case 2:",false,highway.add(v1));
        assertEquals("Test Remove case 1:",true,highway.remove(v1));
        assertEquals("Test Remove case 2:",false,highway.remove(v1));
        assertEquals("Test contains case 2:",false,highway.contains(v1));
    }

}