package neu.edu.cs5010.IO;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reader {

    public Reader(){


    }
    /**
     * use bufferedReader to read from the file that named fileName
     * @param fileName the fileName to read
     * @return the content in this fileName
     */
    public  List<String> read(String fileName) {
        File file = new File(fileName);
        BufferedReader bufferedReader = null;
        List<String> list = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                list.add(temp);
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
        return list;
    }

    /**
     * Read the data from database to retrieve the reuslt information that need
     * to be generated in output ThreadN.txt files.
     * @param queryID Type of Queries(Integer);
     * @param parameterID parameterID that is being queried;
     * @param fileName Input .dat data flies;
     * @return Result String that need to be appended in result txt files;
     */

//    public static String readData(int queryID, int parameterID, String fileName ){
//        String data;
//        StringBuilder sb = new StringBuilder();
//
//        if(queryID==4){
//          LiftDataBase liftDataBase = new LiftDataBase(fileName);
//          data = parameterID +":"+String.valueOf(liftDataBase.getLift(parameterID).getRidesCount())
//                  +System.lineSeparator();
//        } else if(queryID==3){
//            HourDataBase hourDataBase = new HourDataBase(fileName);
//            for(Map.Entry entry: hourDataBase.getHour(parameterID).getLiftMap().entrySet()){
//               sb.append(entry.getKey()+":"+entry.getValue());
//               sb.append(System.lineSeparator());
//            }
//            hourDataBase.close();
//            data = sb.toString();
//        } else if(queryID==2){
//            SkierDataBase skierDataBase = new SkierDataBase(fileName);
//            Skier skier = skierDataBase.getSkier(parameterID);
//
//            for(Map.Entry entry: skier.getLiftMap().getMap().entrySet()){
//                sb.append(entry.getKey()+":"+entry.getValue());
//                sb.append(System.lineSeparator());
//            }
//            skierDataBase.close();
//            data = sb.toString();
//        } else{
//            SkierDataBase skierDataBase = new SkierDataBase(fileName);
//            Skier skier = skierDataBase.getSkier(parameterID);
//            skierDataBase.updateSkierNumOfView(skier);
//
//            data=skier.getID()+","+skier.getLiftRidesCount()+","+skier.getVerticalMetres()+""+
//                    skier.getNumberOfViews()+System.lineSeparator();
//
//            skierDataBase.close();
//        }
//        return data;
//    }

}
