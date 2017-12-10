package edu.neu.ccs.cs5010;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
  private File file;
  private String to;
  private String sub;
  private String msg;

  public SendEmail(String fileName) {
    file = new File(fileName);
    readText();
  }

  private void readText() {
    BufferedReader bufferedReader = null;
    try {
      InputStream inputStream = new FileInputStream(file);
      Reader rder = new InputStreamReader(inputStream, "UTF-8");
      bufferedReader = new BufferedReader(rder);
      Pattern emailPattern = Pattern.compile("To:");
      Pattern subjectPattern = Pattern.compile("Subject:");
      String temp;
      StringBuilder content = new StringBuilder();
      while ((temp = bufferedReader.readLine()) != null) {
        Matcher emailMatch = emailPattern.matcher(temp);
        Matcher subjectMatch = subjectPattern.matcher(temp);
        if (temp.contains("2017")) {
          continue;
        }
        if (emailMatch.find()) {
          to = temp.substring(emailMatch.end()).trim();
        } else if (subjectMatch.find()) {
          sub = temp.substring(subjectMatch.end()).trim();
        } else {
          content.append(temp).append(System.lineSeparator());
        }
      }
      msg = content.toString();
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
  }

  public void send() {
    //Get properties object
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class",
            "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    String from = "hanyuxinbnu@gmail.com";
    String password = "070519961996yy";
    //get Session
    Session session = Session.getDefaultInstance(props,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
              }
            });
    //compose message
    try {
      MimeMessage message = new MimeMessage(session);
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(sub);
      message.setText(msg);
      //send message
      Transport.send(message);
      System.out.println("message sent successfully");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }


  public static void main(String[] args) {
    SendEmail sendEmail = new SendEmail("emails" + File.separator + "email0.txt");
    System.out.println(sendEmail.to);
    System.out.println(sendEmail.sub);
    System.out.println(sendEmail.msg);
  }
}
