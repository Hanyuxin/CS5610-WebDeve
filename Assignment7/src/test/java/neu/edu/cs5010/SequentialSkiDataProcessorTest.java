package neu.edu.cs5010;


import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;

public class SequentialSkiDataProcessorTest {
  String [] args;
  SequentialSkiDataProcessor sequentialSkiDataProcessor;
  BufferedReader br;
  String line;

  /**
   * Set up the parameters for test.
   */
  @Before
  public void setUp(){
    args = new String []{"PDPAssignment.csv"};
    sequentialSkiDataProcessor = new SequentialSkiDataProcessor();

  }

  /**
   * Test is main() can produce correct result based on correct argument
   */
  @Test
  public void main() throws Exception{
    SequentialSkiDataProcessor.main(args);
    br = new BufferedReader(new FileReader("hourSequential.csv"));
    line = br.readLine();
    line = br.readLine();
    line = br.readLine();
    assertEquals("Not the Sequential Case","29, 3408", line);
  }

}