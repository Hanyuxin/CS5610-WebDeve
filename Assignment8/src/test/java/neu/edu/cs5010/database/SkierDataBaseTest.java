package neu.edu.cs5010.database;


import neu.edu.cs5010.dataProcess.Skier;
import neu.edu.cs5010.dataProcess.SkierLiftMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkierDataBaseTest {
    SkierDataBase skierDataBase;
    @Before
    public void setUp() throws Exception {
        skierDataBase = new SkierDataBase("skier.dat");
    }

    @Test
    public void getSkier() throws Exception {
        Skier skier = skierDataBase.getSkier(2000);
        System.out.println(skier.getID());
        System.out.println(skier.getVerticalMetres());
        System.out.println(skier.getLiftRidesCount());
        SkierLiftMap map = skier.getLiftMap();
        System.out.println(map);
    }

    @After
    public void close() throws Exception {
        skierDataBase.close();
    }


}