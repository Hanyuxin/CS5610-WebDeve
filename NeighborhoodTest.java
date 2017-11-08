package neu.edu.cs5010;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class NeighborhoodTest {

    private static Neighborhood neighborhood;

    @BeforeClass
    public static void setUp(){
        neighborhood = new Neighborhood("edges_small.csv");
    }

    @Test
    public void getNeighborhood() throws Exception {
        System.out.println(Neighborhood.getNeighborhood());
    }


}