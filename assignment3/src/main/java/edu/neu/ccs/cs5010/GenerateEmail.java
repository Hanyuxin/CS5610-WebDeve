package edu.neu.ccs.cs5010;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateEmail {

  private File file;
  private String contents;
  private List<String> informations;
  private String event;
  private String departure;
  private String destination;
  private String dir;


  public GenerateEmail(List<String> informations, String template, String event, String departure, String destination, String dir) {
    file = new File(template);
    this.informations = informations;
    this.event = event;
    this.departure = departure;
    this.destination = destination;
    this.dir = dir;
    createDir();
    getContents();
  }

  /**
   * create the dictionary, if already exists, do noting
   */
  private void createDir() {
    File dirFile = new File(dir);
    if (dirFile.exists()) {
      return;
    }
    if (!dir.endsWith(File.separator)) {
      dir = dir + File.separator;
    }
    dirFile.mkdirs();
  }

  /**
   * get the content of template email text
   */
  private void getContents() {
    IOLibrary ioLibrary = new IOLibrary();
    contents = ioLibrary.readFile(file);
  }

  /**
   * writer to different files
   */
  public void writer() {
    int count = 0;
    CSVParser csvParser = new CSVParser(informations.get(0));
    for (int k = 1; k < informations.size(); k++) {
      Information information = new Information(informations.get(k), csvParser.getFirstNameIndex(), csvParser.getLastNameIndex(),
              csvParser.getEmailIndex(), csvParser.getRewardIndex());
      String fileName = dir + File.separator + "email" + count + ".txt";
      writer(fileName, information);
      count++;
    }
  }

  /**
   * Write one file with the given fileName and information
   * @param fileName        String
   * @param information     Information
   */
  private void writer(String fileName, Information information) {
    BufferedWriter bufferedWriter = null;
    try {
      OutputStream inputStream = new FileOutputStream(fileName);
      Writer rder = new OutputStreamWriter(inputStream, "UTF-8");
      bufferedWriter = new BufferedWriter(rder);
      String res = getReplaceContent(information.getFirstName(), information.getLastName(),
              information.getEmail(), information.getRewards());
      bufferedWriter.write(res);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bufferedWriter != null) {
          bufferedWriter.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }

  /**
   * use regular expression to replace content
   * @param firstName String
   * @param lastName String
   * @param email String
   * @param rewards String
   * @return String replaced
   */
  public String getReplaceContent(String firstName, String lastName, String email, String rewards) {
    String regex = "\\[\\[[a-zA-Z]+([_-])?[a-zA-Z]+]]";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(contents);
    StringBuffer sb = new StringBuffer();
    StringBuffer tail = new StringBuffer();

    while (matcher.find()) {
      String placeholder = matcher.group();
      switch (placeholder) {
        case "[[Date]]":
          matcher.appendReplacement(sb, getCurrentTime());
          break;
        case "[[email]]":
          matcher.appendReplacement(sb, email);
          break;
        case "[[event]]":
          matcher.appendReplacement(sb, event);
          break;
        case "[[first_name]]":
          matcher.appendReplacement(sb, firstName);
          break;
        case "[[last_name]]":
          matcher.appendReplacement(sb, lastName);
          break;
        case "[[departure-city]]":
          matcher.appendReplacement(sb, departure);
          break;
        case "[[destination-city]]":
          matcher.appendReplacement(sb, destination);
          break;
        case "[[rewards]]":
          matcher.appendReplacement(sb, rewards);
          break;
        default:
      }
    }
    matcher.appendTail(tail);
    return sb.append(tail.toString()).toString();
  }

  /**
   * get Current time with the hour:minutes:seconds Month-Day-Years format
   * @return String time
   */
  private String getCurrentTime() {
    Date date = new Date();
    DateFormat format = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
    return format.format(date);
  }
}
