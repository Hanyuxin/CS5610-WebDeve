package edu.neu.ccs.cs5010;

public class CSVParser {

  private int firstNameIndex;
  private int lastNameIndex;
  private int emailIndex;
  private int rewardIndex;

  public CSVParser(String line) {
    String[] placeHolder = line.split("\"");
    getIndex(placeHolder);
  }

  private void getIndex(String[] placeHolder) {
    for (int i = 0; i < placeHolder.length; i++) {
      switch (placeHolder[i]) {
        case "first_name":
          firstNameIndex = i;
          break;
        case "last_name":
          lastNameIndex = i;
          break;
        case "email":
          emailIndex = i;
          break;
        case "rewards":
          rewardIndex = i;
          break;
        default:
          break;
      }
    }
  }

  public int getEmailIndex() {
    return emailIndex;
  }

  public int getFirstNameIndex() {
    return firstNameIndex;
  }

  public int getLastNameIndex() {
    return lastNameIndex;
  }

  public int getRewardIndex() {
    return rewardIndex;
  }
}
