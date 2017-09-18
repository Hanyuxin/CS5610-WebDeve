package Assignment1.test;

import Assignment1.Vehicle;
import org.junit.Test;

public class VehicleTest {

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException(){
        Vehicle v = new Vehicle(5.5, 8);
    }

    @Test(expected = NumberFormatException.class)
    public void expectNumberFormatException(){
        String s ="";
        Vehicle v = new Vehicle(new Double(s), new Integer(s));
    }

}