package neu.edu.cs5010;


import neu.edu.cs5010.IO.Writer;
import neu.edu.cs5010.ReadWriteLock.ReadWriteLock;
import neu.edu.cs5010.dataProcess.Lift;
import neu.edu.cs5010.dataProcess.Skier;
import neu.edu.cs5010.database.HourDataBase;
import neu.edu.cs5010.database.LiftDataBase;
import neu.edu.cs5010.database.SkierDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkingThread extends java.lang.Thread {
    private int index;
    private QueryProcessor queryProcessor;
    private Map<Integer, List<Integer>> QueryInput;
    private Map<Integer, String> fileNameParser;
    private String fileName;
    private ReadWriteLock readWriteLock;

    public WorkingThread(int index, Map<Integer, List<Integer>> QueryInput){
        this.index=index;
        this.QueryInput = QueryInput;
        this.fileName = "Thread"+this.index;
        queryProcessor = new QueryProcessor();
        fileNameParser = new HashMap<>();
        fileNameParser.put(1,"skier.dat");
        fileNameParser.put(2,"skier.dat");
        fileNameParser.put(3,"hour.dat");
        fileNameParser.put(4,"lift.dat");
        readWriteLock = new ReadWriteLock();

    }

    @Override
    public void run(){
        for(int key : QueryInput.keySet()){
            for(int parameterID: QueryInput.get(key)){
                parseQuery(key,parameterID);
            }
        }
    }

    public void parseQuery(int key, int parameterID) {
        String data = "";
        if (key == 1) {
            readWriteLock.getWriteLock().lock();
            try {
                data = readQuery1(parameterID, "skier.dat");
            } finally {
                readWriteLock.getWriteLock().unlock();

            }

        } else if(key==2){
           readWriteLock.getReadLock().lock();
            try {
                data = readQuery2(parameterID, "skier.dat");
            } finally {
                readWriteLock.getReadLock().unlock();

            }
        }else if (key == 3) {
            readWriteLock.getReadLock().lock();

            try {
                data = readQuery3(parameterID, "hour.dat");
            } finally {
                readWriteLock.getReadLock().unlock();

            }
        } else{
                readWriteLock.getReadLock().lock();
            try {
                data = readQuery4(parameterID, "lift.dat");
            } finally {
                readWriteLock.getReadLock().unlock();

            }
        }
            write(data);
        }




    public String readQuery1(int parameterID, String fileName){
        String data;
        SkierDataBase skierDataBase = new SkierDataBase(fileName);
        Skier skier = skierDataBase.getSkier(parameterID);
        skierDataBase.updateSkierNumOfView(skier);

        data=parameterID+","+skier.getLiftRidesCount()+","+skier.getVerticalMetres()+","+
                skier.getNumberOfViews()+System.lineSeparator();
        skierDataBase.close();
        return data;
    }

    public String readQuery2(int parameterID, String fileName){
        StringBuilder sb = new StringBuilder();
        SkierDataBase skierDataBase = new SkierDataBase(fileName);
        Skier skier = skierDataBase.getSkier(parameterID);

         String data = skier.getLiftMap().toDatFileString();
        skierDataBase.close();

       return data;
    }

    public String readQuery3(int parameterID, String fileName){
        StringBuilder sb = new StringBuilder();

        HourDataBase hourDataBase = new HourDataBase(fileName);
        Map<Integer,Lift> liftMap = hourDataBase.getHour(parameterID).getLiftMap();
        for(int LiftID: liftMap.keySet()){

            sb.append(LiftID+":"+liftMap.get(LiftID).getRidesCount());
            sb.append(System.lineSeparator());
        }
        hourDataBase.close();
       String data = sb.toString();
       return data;
    }

    public String readQuery4(int parameterID, String fileName){
        LiftDataBase liftDataBase = new LiftDataBase(fileName);
       String data = parameterID +":"+String.valueOf(liftDataBase.getLift(parameterID).getRidesCount())
                +System.lineSeparator();
       return data;
    }


    public void write(String data){
        Writer.AppendWrite(fileName, data);
       // Writer.OverriddenWrite(fileName,data);
    }

    /**
     * Return the index of current threads;
     * @return The index of current threads;
     */
    public int getIndex(){
        return this.index;
    }
}

