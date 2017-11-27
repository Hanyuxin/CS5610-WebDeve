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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WorkingThread extends java.lang.Thread {
    private int index;
    private QueryProcessor queryProcessor;
    private Map<Integer, List<Integer>> QueryInput;
    private String fileName;
    private ReadWriteLock readWriteLock;
    private CyclicBarrier cyclicBarrier;


    public WorkingThread(int index, Map<Integer, List<Integer>> QueryInput, CyclicBarrier cyclicBarrier){
        this.index=index;
        this.QueryInput = QueryInput;
        this.fileName = "Thread"+this.index;
        this.cyclicBarrier = cyclicBarrier;
        readWriteLock = new ReadWriteLock();

    }

    /**
     * Overriden run method of each WorkingThread object.
     */
    @Override
    public void run(){
        try{
            cyclicBarrier.await();
        } catch(InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
        for(int key : QueryInput.keySet()){
            for(int parameterID: QueryInput.get(key)){
                    parseQuery(key,parameterID);
            }
        }
        try{
            cyclicBarrier.await();
        } catch(InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }

    /**
     * Parse different Query type and add wirtelock/readlock to current thread.
     * @param key Type of Query(Integer);
     * @param parameterID ParameterID read from input Query data file;
     */
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

    /**
     * Read query1 and generate String data that would be written into output
     * ThreadN.txt files.
     * @param parameterID ParameterID read from input Query data file;
     * @param fileName Source data(.dat file) file name ;
     * @return String data that would be written into output
     */
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
    /**
     * Read query2 and generate String data that would be written into output
     * ThreadN.txt files.
     * @param parameterID ParameterID read from input Query data file;
     * @param fileName Source data(.dat file) file name ;
     * @return String data that would be written into output
     */
    public String readQuery2(int parameterID, String fileName){
        StringBuilder sb = new StringBuilder();
        SkierDataBase skierDataBase = new SkierDataBase(fileName);
        Skier skier = skierDataBase.getSkier(parameterID);
        Map<Integer, Lift> liftMap = skier.getLiftMap().getMap();
        for(int Time: liftMap.keySet()){
            sb.append("Time:").append(Time).append("  ").append("LiftID:").
                    append(liftMap.get(Time).getLiftID()).append(System.lineSeparator());

        }
        skierDataBase.close();

       return sb.toString();
    }
    /**
     * Read query3 and generate String data that would be written into output
     * ThreadN.txt files.
     * @param parameterID ParameterID read from input Query data file;
     * @param fileName Source data(.dat file) file name ;
     * @return String data that would be written into output
     */

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
    /**
     * Read query4 and generate String data that would be written into output
     * ThreadN.txt files.
     * @param parameterID ParameterID read from input Query data file;
     * @param fileName Source data(.dat file) file name ;
     * @return String data that would be written into output
     */
    public String readQuery4(int parameterID, String fileName){
        LiftDataBase liftDataBase = new LiftDataBase(fileName);
       String data = parameterID +":"+String.valueOf(liftDataBase.getLift(parameterID).getRidesCount())
                +System.lineSeparator();
       return data;
    }

    /**
     * Write the String data into
     * @param data
     */
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

