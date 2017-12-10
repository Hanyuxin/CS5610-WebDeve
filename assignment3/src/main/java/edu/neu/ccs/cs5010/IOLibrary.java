package edu.neu.ccs.cs5010;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class IOLibrary {

  public String readFile(File file) {
    BufferedReader bufferedReader = null;
    StringBuilder stringBuilder = new StringBuilder();
    try {
      InputStream inputStream = new FileInputStream(file);
      Reader rder = new InputStreamReader(inputStream, "UTF-8");
      bufferedReader = new BufferedReader(rder);
      String temp;
      while ((temp = bufferedReader.readLine()) != null) {
        stringBuilder.append(temp).append(System.lineSeparator());
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
    return stringBuilder.toString();
  }
}
