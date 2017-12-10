package edu.neu.ccs.cs5010;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadCSV {

  private List<String> res;
  private String csvName;

  public ReadCSV(String name) {
    res = new ArrayList<>();
    csvName = name;
  }

  /**
   * Use regular expression to find String have format "FromCityNameTo" ,and use substring to return return the City name.
   * @return return the Departure City
   */
  public String getDeparture() {
    String regex = "(From)[A-Z][a-z]+To";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(csvName);
    matcher.find();
    int start = matcher.start();
    int end = matcher.end();
    return csvName.substring(start + 4, end - 2);
  }

  /**
   * Use regular expression to find String have format "ToCityname." ,and use substring to return return the City name.
   * @return return the Destination City
   */
  public String getDestination() {
    String regex = "(To)[A-Z][a-z]+.";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(csvName);
    matcher.find();
    int start = matcher.start();
    int end = matcher.end();
    return csvName.substring(start + 2, end - 1);
  }

  /**
   * Use BufferReader to read line and store them in a List
   * @return the list of information string
   */
  public List<String> getInformation() {
    BufferedReader bufferedReader = null;
    try {
      InputStream inputStream = new FileInputStream(csvName);
      Reader rder = new InputStreamReader(inputStream, "UTF-8");
      bufferedReader = new BufferedReader(rder);
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
