package neu.edu.cs5010;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class NeighborhoodTest {

    private static Neighborhood neighborhood;

    @BeforeClass
    public static void setUp(){
        neighborhood = new Neighborhood("nodes_small.csv","edges_small.csv");
    }




}