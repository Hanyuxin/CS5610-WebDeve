package neu.edu.cs5010;


import neu.edu.cs5010.dataProcess.SkiDataProcessor;
import org.junit.Test;


public class SkiDataProcessorTest {
    @Test(expected = NullPointerException.class)
    public void mainTest1() {
        SkiDataProcessor.main(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mainTest2(){
        String[] s = new String[]{"abc"};
        SkiDataProcessor.main(s);
    }

    @Test
    public void mianTest2(){
        String [] s = new String[]{"PDPAssignment.csv"};
        SkiDataProcessor.main(s);
    }
}