package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InformationTest {
  Information i;

  @Before
  public void setUp() {
    i = new Information("\"James\",\"Butt\",\"6649 N Blue Gum St\",\"New Orleans\",\"Orleans\",\"LA\",\"70116\",\"504-621-8927\",\"jbutt@gmail.com\",\"gold \"", 1, 3, 17, 19);
  }

  @Test
  public void testGetFirstName() {
    assertEquals("James", i.getFirstName());
  }

  @Test
  public void testGetLastName() {
    assertEquals("Butt", i.getLastName());
  }

  @Test
  public void testGetEmail() {
    assertEquals("jbutt@gmail.com", i.getEmail());
  }

  public void testGetRewards() {
    assertEquals("gold", i.getRewards());
  }
}
