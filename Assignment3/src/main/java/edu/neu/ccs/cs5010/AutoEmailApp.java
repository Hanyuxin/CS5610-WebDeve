package edu.neu.ccs.cs5010;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AutoEmailApp
{
    static String CsvName=null,dir=null,template=null,event=null;

    public static void main( String[] args ) {
        AutoEmailApp app = new AutoEmailApp();
        boolean check = app.checkArgument(args);
        if(check){
            ReadCSV readCSV = new ReadCSV(CsvName);
            GenerateEmail g = new GenerateEmail(readCSV.getInformation(),template,event,readCSV.getDeparture(),readCSV.getDestination(),dir);
            g.writer();
            System.out.println("Success! Please check "+ dir+" dictionary!");
            SendEmail sendEmail = new SendEmail(dir+File.separator+"email0.txt");
            sendEmail.send();
        }
    }

    private boolean checkArgument(String[] args){
        if(args.length==0) {
            System.out.println("Please insert argument!" );
            printUsage();
            return false;
        }
        Pattern emailPattern = Pattern.compile("\\w*\\W*\\w+(.txt)");
        Pattern csvPattern = Pattern.compile("(Flight)\\d+(From)[A-Z][a-z]+(To)[A-Z][a-z]+(.csv)");
        Pattern csvWrongPattern = Pattern.compile("(Flight)\\d+(.csv)");
        Pattern dirPattern = Pattern.compile("\\w+");
        StringBuilder lostInforamtion = new StringBuilder();
        boolean isError = false;
        for(int i = 0; i< args.length;i++){
            if(i==args.length-1 && args[i].indexOf("-")!=-1){
                printError(lostInforamtion.toString());
                printUsage();
                return false;
            }
            if(args[i].equals("--csv-file") ){
                if(csvPattern.matcher(args[i+1]).matches())
                    CsvName = args[++i];
                else if(csvWrongPattern.matcher(args[i+1]).matches())
                    printCsvError();
                else {
                    lostInforamtion.append(args[i]).append(" ");
                    isError = true;
                }
            }else if(args[i].equals("--email-template")){
                if(emailPattern.matcher(args[i+1]).matches())
                    template = args[++i];
                else {
                    lostInforamtion.append(args[i]).append(" ");
                    isError = true;
                }
            }else if(args[i].equals("--event")){
                if(!args[i+1].equals("arrival") && !args[i+1].equals("departure")){
                    lostInforamtion.append(args[i]).append(" ");
                    isError = true;
                }
                else event = args[++i];
            }else if(args[i].equals("--output-dir")){
                if(dirPattern.matcher(args[i+1]).matches()){
                    dir = args[++i];
                }else{
                    lostInforamtion.append(args[i]).append(" ");
                    isError = true;
                }
            }
        }
        if(isError){
            printError(lostInforamtion.toString());
            printUsage();
            return false;
        }
        return true;
    }

    private void printError(String error){
        System.out.println("Error: " + error +" were given without providing appropriate arguments:");
    }

    private void printCsvError(){
        System.out.println("Error: --csv-file argument does not contain departure-city and destination-city");
    }

    public void printUsage() {
        File file = new File("usage.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                System.out.println(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}