package edu.neu.ccs.cs5010;


import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AutoEmailAppTest
{

    private AutoEmailApp app ;
    private String[] args = new String[]{"--email-template", "--output-dir", "--csv-file Flight363FromSeattleToBoston.csv","–-event arrival"};
//     @Before
     public void setUp(){
         app = new AutoEmailApp();

     }

     @Test
     public void testPrintUsage()
    {
        app.printUsage();
    }

    @Test
    public void testMain(){
         String[] args = new String[]{"--email-template","--output-dir", "--csv-file", "Flight363FromSeattleToBoston.csv","--event","arrival"};
        app.main(args);
    }
}
