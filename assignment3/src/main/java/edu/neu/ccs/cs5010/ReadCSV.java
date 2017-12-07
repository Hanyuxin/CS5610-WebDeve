package edu.neu.ccs.cs5010;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCSV {

  private List<String> res;
  private String CsvName;

  public ReadCSV(String name) {
    res = new ArrayList<>();
    CsvName = name;
  }

  /**
   * Use regular expression to find String have format "FromCityNameTo" ,and use substring to return return the City name.
   *
   * @return return the Departure City
   */
  public String getDeparture() {
    String regex = "(From)[A-Z][a-z]+To";
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(CsvName);
    matcher.find();
    int start = matcher.start();
    int end = matcher.end();
    return CsvName.substring(start + 4, end - 2);
  }

  /**
   * Use regular expression to find String have format "ToCityname." ,and use substring to return return the City name.
   *
   * @return return the Destination City
   */
  public String getDestination() {
    String regex = "(To)[A-Z][a-z]+.";
    Pattern p = Pattern.compile(regex);
    Matcher matcher = p.matcher(CsvName);
    matcher.find();
    int start = matcher.start();
    int end = matcher.end();
    return CsvName.substring(start + 2, end - 1);
  }

  /**
   * Use BufferReader to read line and store them in a List
   *
   * @return the list of information string
   */
  public List<String> getInformation() {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new FileReader(new File(CsvName)));
      String temp;
      while ((temp = bufferedReader.readLine()) != null) {
        res.add(temp);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (bufferedReader != null) {
        try {
          bufferedReader.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return res;
  }


}
