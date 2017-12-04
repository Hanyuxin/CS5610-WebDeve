package neu.edu.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class UserInputHandlerTest {
  private static UserInputHandler inputHandler;

  @Before
  public void setUp() throws FileNotFoundException {
    BufferedReader in = new BufferedReader(new FileReader("test"));
    inputHandler = new UserInputHandler(in);
  }

  @Test
  public void handler() throws Exception {
    String s1 = "CHOOSE_DICE:  3 6 3 4 4";
    inputHandler.handler(s1);
    String s2 = "CHOOSE_DICE:  3 3 5 3 4";
    inputHandler.handler(s2);
    String s3 = "CHOOSE_SCORE:  3 6 6 2 1 Twos Fours SmallStraight FullHouse Yahtzee Fives LargeStraight Aces Chance Threes FourOfKind Sixes ThreeOfKind";
    inputHandler.handler(s3);
    String s4 = "INFO: Joining the game";
    inputHandler.handler(s4);
  }

}