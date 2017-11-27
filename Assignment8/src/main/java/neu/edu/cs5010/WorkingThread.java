package neu.edu.cs5010;

import neu.edu.cs5010.IO.Reader;
import neu.edu.cs5010.IO.Writer;
import neu.edu.cs5010.ReadWriteLock.ReadWriteLock;
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

    public void parseQuery(int key, int parameterID){
        String data="";
        if(key==1){

            if(key==1) {
                try {
                    readWriteLock.lockWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    data = readQuery1(parameterID, "skier.dat");
                } finally {
                    try {
                        readWriteLock.unlockWrite();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if(key==2){
            try {
                readWriteLock.lockRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                data = readQuery2(parameterID, "skier.dat");
            } finally {
                readWriteLock.unlockRead();
            }
        } else if(key==3){
            try {
                readWriteLock.lockRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                data = readQuery3(parameterID, "hour.dat");
            } finally {
                readWriteLock.unlockRead();
            }
        } else{
            try {
                readWriteLock.lockRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                data = readQuery4(parameterID, "lift.dat");
            } finally {
                readWriteLock.unlockRead();
            }
        }
        write(data);
    }


    public String readQuery1(int parameterID, String fileName){
        String data;
        SkierDataBase skierDataBase = new SkierDataBase(fileName);
        Skier skier = skierDataBase.getSkier(parameterID);
        skierDataBase.updateSkierNumOfView(skier);

        data=skier.getID()+","+skier.getLiftRidesCount()+","+skier.getVerticalMetres()+""+
                skier.getNumberOfViews()+System.lineSeparator();

        skierDataBase.close();
        return data;
    }

    public String readQuery2(int parameterID, String fileName){
        StringBuilder sb = new StringBuilder();
        SkierDataBase skierDataBase = new SkierDataBase(fileName);
        Skier skier = skierDataBase.getSkier(parameterID);

        for(Map.Entry entry: skier.getLiftMap().getMap().entrySet()){
            sb.append(entry.getKey()+":"+entry.getValue());
            sb.append(System.lineSeparator());
        }
        skierDataBase.close();
       String data = sb.toString();
       return data;
    }

    public String readQuery3(int parameterID, String fileName){
        StringBuilder sb = new StringBuilder();
        HourDataBase hourDataBase = new HourDataBase(fileName);
        for(Map.Entry entry: hourDataBase.getHour(parameterID).getLiftMap().entrySet()){
            sb.append(entry.getKey()+":"+entry.getValue());
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
    }

    /**
     * Return the index of current threads;
     * @return THe index of current threads;
     */
    public int getIndex(){
        return this.index;
    }
}

