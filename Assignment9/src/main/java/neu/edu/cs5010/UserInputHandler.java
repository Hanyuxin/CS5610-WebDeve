package neu.edu.cs5010;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class UserInputHandler {

  private BufferedReader input;
  private int DICE_END_POS = 5;

  public UserInputHandler(BufferedReader input) {
    this.input = input;
  }

  /**
   * handle the Server input information, and return the user input information
   * @param input String
   * @return User input String
   * @throws IOException because the inner call method
   */
  public String handler(String input) throws IOException {
    String[] inputArray = input.split(": ");
    String title = inputArray[0];
    switch (title) {
      case "CHOOSE_DICE" :
        return chooseDice(inputArray[1]);
      case "CHOOSE_SCORE" :
        return chooseScore(inputArray[1]);
      default:
        System.out.println(input);
        return "";
    }
  }

  /**
   * tell user the dice number, and transfer user input information to the frame the Serve can receive
   * @param str Server information
   * @return user input information
   * @throws IOException the readLine method in BufferedReader throws
   */
  private String chooseDice(String str) throws IOException {
    System.out.println("Your dice numbers are " + str);
    System.out.println("Please write the dice's index(1-based) that you want to keep, if you want to change all, press enter," +
            " remember to add blank between numbers");
    StringBuilder result = new StringBuilder();
    result.append("KEEP_DICE: " + str);
    String diceIndexToKeepString = input.readLine();
    System.out.println(diceIndexToKeepString);
    if (diceIndexToKeepString.length() == 0) {
      for (int i = 0; i < DICE_END_POS; i++) {
        result.append(" 0");
      }
      return result.toString();
    }

    String[] diceIndexToKeep = diceIndexToKeepString.split(" ");
    Arrays.sort(diceIndexToKeep);
    int index = 0;
    for (int i = 1; i <= DICE_END_POS; i++) {
      if (index < diceIndexToKeep.length && i == Integer.parseInt(diceIndexToKeep[index])) {
        result.append(" " + 1);
        index++;
      }
      else {
        result.append(" " + 0);
      }
    }
    return result.toString();
  }

  /**
   * tell user the Score information, and transfer user input information to the frame the Serve can receive
   * @param str Server information
   * @return User input information
   * @throws IOException the readLine method in BufferedReader throws
   */
  private String chooseScore(String str) throws IOException {
    String[] inputs = str.split(" ");
    StringBuilder currentDice = new StringBuilder();
    StringBuilder score = new StringBuilder();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i <= DICE_END_POS; i++) {
      currentDice.append( " " + inputs[i]);
    }
    System.out.println("Current Dices are : " + currentDice.toString());
    for (int i = DICE_END_POS + 1; i < inputs.length; i++) {
      score.append(" " + inputs[i]);
    }
    System.out.println("Please choose your score : " + score.toString());

    String chosenScore = input.readLine();
    result.append("SCORE_CHOICE:" + chosenScore);
    return result.toString();
  }
}
