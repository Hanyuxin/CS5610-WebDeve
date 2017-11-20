package neu.edu.cs5010;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ConcurrentSkiDataProcessorTest {

   private String [] args;
   private ConcurrentSkiDataProcessor concurrentSkiDataProcessor;
   private SequentialSkiDataProcessor sequentialSkiDataProcessor;
   private BufferedReader br_hourSequential;
   private BufferedReader br_hourConcurrent;
    private BufferedReader br_liftSequential;
    private BufferedReader br_liftConcurrent;
    private BufferedReader br_skiSequential;
    private BufferedReader br_skiConcurrent;



    /**
     * Create ConcurrentSkiDataProcessor object and get three data blocking queues.
     */
    @Before
    public void setUp(){
        args = new String []{"PDPAssignment.csv"};
        concurrentSkiDataProcessor = new ConcurrentSkiDataProcessor();
        sequentialSkiDataProcessor = new SequentialSkiDataProcessor();



    }

    /**
     * Test if the results calculated from concurrent and sequential methods are the same.
     */
    @Test
    public void ResultCompareTest(){
        sequentialSkiDataProcessor.main(args);
        concurrentSkiDataProcessor.main(args);
        try {
            br_hourSequential = new BufferedReader(new FileReader("hourSequential.csv"));
            br_hourConcurrent = new BufferedReader(new FileReader("hourConcurrent.csv"));

            br_liftSequential =new BufferedReader(new FileReader("liftSequential.csv"));
            br_liftConcurrent =new BufferedReader(new FileReader("liftConcurrent.csv"));

            br_skiSequential = new BufferedReader(new FileReader("skierSequential.csv"));
            br_skiConcurrent =new BufferedReader(new FileReader("skierConcurrent.csv"));

            String line_hourSequential =br_hourSequential.readLine();
            String line_hourConcurrent = br_hourConcurrent.readLine();

            String line_liftSequential =br_liftSequential.readLine();
            String line_liftConcurrent = br_liftConcurrent.readLine();

            String line_skierSequential =br_skiSequential.readLine();
            String line_skierConcurrent = br_skiConcurrent.readLine();

            while(line_hourConcurrent!=null || line_hourSequential!=null){
                Assert.assertEquals(true, line_hourConcurrent.equals(line_hourSequential));

                 line_hourSequential = br_hourSequential.readLine();
                 line_hourConcurrent = br_hourConcurrent.readLine();


            }

            while(line_liftConcurrent!=null || line_liftSequential!=null){
                Assert.assertEquals(true, line_liftConcurrent.equals(line_liftSequential));
                line_liftSequential =br_liftSequential.readLine();
                line_liftConcurrent = br_liftConcurrent.readLine();


            }
            while(line_skierConcurrent!=null || line_skierSequential!=null) {
                Assert.assertEquals(true, line_skierConcurrent.equals(line_skierConcurrent));
                line_skierSequential = br_skiSequential.readLine();
                line_skierConcurrent = br_skiConcurrent.readLine();
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
