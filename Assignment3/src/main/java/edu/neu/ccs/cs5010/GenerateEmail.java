package edu.neu.ccs.cs5010;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateEmail {

    private static String SEPARATOR = System.lineSeparator();
    private File file;
    private String contents;
    private List<String> informations;
    private String event;
    private String departure;
    private String destination;
    private String dir;


    public GenerateEmail( List<String> informations,String template, String event, String departure, String destination,String dir){
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
    private void createDir(){
        File dirFile = new File(dir);
        if(dirFile.exists()) return;
        if(!dir.endsWith(File.separator))
            dir = dir+File.separator;
        dirFile.mkdirs();
    }

    /**
     * get the content of template email text
     */
    private void getContents(){
        BufferedReader bufferedReader = null;
        StringBuilder sb = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String temp = null;
            while((temp = bufferedReader.readLine()) != null){
                sb.append(temp).append(SEPARATOR);
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        contents = sb.toString();
    }

    /**
     * writer to different files
     */
    public void writer(){
        int count = 0;
        for(int k = 1;k< informations.size();k++){
            Information i = new Information(informations.get(k));
            String fileName = dir+File.separator+"email"+count+".txt";
            writer(fileName,i);
            count++;
        }
    }

    /**
     * Write one file with the given fileName and information
     * @param fileName
     * @param i Information
     */
    private void writer(String fileName, Information i) {
        FileWriter writer = null;
        BufferedWriter bufferedWriter = null;
        try {
                writer = new FileWriter(fileName);
                bufferedWriter = new BufferedWriter(writer);
                String res = getReplaceContent(i.getFirstName(),i.getLastName(),i.getEmail(),i.getRewards());
                bufferedWriter.write(res);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param rewards
     * @return
     */
    public String getReplaceContent(String firstName, String lastName, String email, String rewards){
        String regex = "\\[\\[[a-zA-Z]+(_|\\-)?[a-zA-Z]+\\]\\]";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(contents);
        StringBuffer sb = new StringBuffer();
        StringBuffer tail = new StringBuffer();

        while(matcher.find()){
            String placeholder = matcher.group();
            switch (placeholder){
                case "[[Date]]":matcher.appendReplacement(sb,getCurrentTime()); break;
                case "[[email]]":matcher.appendReplacement(sb,email);break;
                case "[[event]]":matcher.appendReplacement(sb,event);break;
                case "[[first_name]]":matcher.appendReplacement(sb,firstName);break;
                case "[[last_name]]":matcher.appendReplacement(sb,lastName);break;
                case "[[departure-city]]":matcher.appendReplacement(sb,departure);break;
                case "[[destination-city]]":matcher.appendReplacement(sb,destination);break;
                case "[[rewards]]":matcher.appendReplacement(sb,rewards);
            }
        }
        matcher.appendTail(tail);
        return sb.append(tail.toString()).toString();
    }

    /**
     * get Current time with the hour:minutes:seconds Month-Day-Years format
     * @return String time
     */
    private String getCurrentTime(){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss MM-dd-yyyy");
        return format.format(date);
    }
}
