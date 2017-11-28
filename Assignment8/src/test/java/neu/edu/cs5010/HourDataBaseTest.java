package neu.edu.cs5010;


import neu.edu.cs5010.dataProcess.Hour;
import neu.edu.cs5010.database.HourDataBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HourDataBaseTest {
    private HourDataBase hourDataBase;

    @Before
    public void setUp() {
        hourDataBase = new HourDataBase("hour.dat");
    }

    @After
    public void close() {
        hourDataBase.close();
    }
    @Test
    public void getHour() {
        Hour hour = hourDataBase.getHour(3);
        System.out.println(hour.getLiftMap());
    }

}