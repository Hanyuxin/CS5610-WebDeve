package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateEmailTest {
  private GenerateEmail generateEmail;
  private List<String> information = new ArrayList<>();

  private String event = "arrival";
  private String departure = "Seattle";
  private String destination = "Beijing";
  private String dir = "emails";
  private Information i;

  @Before
  public void setUp() {
    generateEmail = new GenerateEmail(information, "email-template.txt", event, departure, destination, dir);
    information.add("\"first_name\",\"last_name\",\"address\",\"city\",\"county\",\"state\",\"zip\",\"phone\",\"email\",\"rewards\"");
    information.add("\"Art\",\"Venere\",\"8 W Cerritos Ave #54\",\"Bridgeport\",\"Gloucester\",\"NJ\",\"8014\",\"856-636-8749\",\"art@venere.org\",\"bronze\"");

    i = new Information(information.get(0), 1, 3, 17, 19);
  }

  @Test
  public void TestGetContents() {
    System.out.println(generateEmail.getReplaceContent(i.getFirstName(), i.getLastName(), i.getEmail(), i.getRewards()));
  }

  @Test
  public void TestWrite() {
    generateEmail.writer();
  }
}
