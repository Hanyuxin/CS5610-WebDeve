package edu.neu.ccs.cs5010;

public class CSVParser {

  private int firstNameIndex;
  private int lastNameIndex;
  private int emailIndex;
  private int rewardIndex;

  /** Constructor
   * @param line String
   */
  public CSVParser(String line) {
    String[] placeHolder = line.split("\"");
    getIndex(placeHolder);
  }

  /**get Index of diffent place holder
   * @param placeHolder String[]
   */
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

  /**emailIndex
   * @return emailIndex
   */
  public int getEmailIndex() {
    return emailIndex;
  }

  /**firstNameIndex
   * @return firstNameIndex
   */
  public int getFirstNameIndex() {
    return firstNameIndex;
  }

  /**lastNameIndex
   * @return lastNameIndex
   */
  public int getLastNameIndex() {
    return lastNameIndex;
  }

  /**rewardIndex
   * @return rewardIndex
   */
  public int getRewardIndex() {
    return rewardIndex;
  }
}
