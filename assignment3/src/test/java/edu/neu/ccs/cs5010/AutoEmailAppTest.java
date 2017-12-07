package edu.neu.ccs.cs5010;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AutoEmailAppTest {

  private AutoEmailApp app;
  private String[] args = new String[]{"--email-template", "--output-dir", "--csv-file Flight363FromSeattleToBoston.csv", "–-event arrival"};

  //     @Before
  public void setUp() {
    app = new AutoEmailApp();

  }


  @Test
  public void testMain() {
    String[] args = new String[]{"--email-template", "--output-dir", "--csv-file", "Flight363FromSeattleToBoston.csv", "--event"};
    app.main(args);
    String[] args1 = new String[]{"--email-template", "--output-dir", "--csv-file", "Flight363.csv", "--event", "arrival"};
    app.main(args1);
    String[] args2 = new String[]{"--email-template", "--output-dir", "--csv-file", "--event", "arrival"};
    app.main(args2);
    String[] args3 = new String[]{"--email-template email-template.txt --output-dir emails --csv-file Flight666FromSeattleToBeijing.csv --event arrival"};
    app.main(args3);
  }
}
