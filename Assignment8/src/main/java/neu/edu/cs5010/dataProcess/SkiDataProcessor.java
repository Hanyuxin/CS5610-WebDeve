package neu.edu.cs5010.dataProcess;

import neu.edu.cs5010.IO.Reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * generate 4 dat files
 *
 */
public class SkiDataProcessor {
    private BlockingQueue<int[]> skierQueue;
    private BlockingQueue<Integer> liftQueue;
    private BlockingQueue<int[]> hourQueue;
    private Map<Integer, Skier> skierMap ;
    private Map<Integer, Lift> liftMap;
    private Map<Integer, Hour> hourMap;
    private String inputFileName;
    private List<String> input;

    public SkiDataProcessor() {
        skierQueue = new LinkedBlockingQueue<>();
        liftQueue = new LinkedBlockingQueue<>();
        hourQueue = new LinkedBlockingQueue<>();
        skierMap = new HashMap<>();
        liftMap = new HashMap<>();
        hourMap = new HashMap<>();
    }
    public static void main( String[] args ) {
        SkiDataProcessor processor = new SkiDataProcessor();
        processor.checkArgument(args);
        processor.multiThreadsRun();
        processor.write();

    }

    /**
     * check whether input arguments are valid, throw IllegalArgumentException when argument not exactly one,
     * and not a csv file
     * @param args input arguments
     */
    public void checkArgument(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("Please check your argument");
        if (!args[0].contains(".csv"))
            throw new IllegalArgumentException("The input file should be a CSV file");
        inputFileName = args[0];
    }

    /**
     * use Executors to manage multiple thread, use consumer and producer to deal with blocking Queue.
     */
    public void multiThreadsRun() {
        Reader reader = new Reader();
        input = reader.read(inputFileName);

        CsvInfo csvInfo = new CsvInfo(input.get(0));
        ExecutorService executor = Executors.newCachedThreadPool();

        SkierDataProducer skierDataProducer = new SkierDataProducer(skierQueue, input);
        SkierDataConsumer skierDataConsumer = new SkierDataConsumer(skierQueue,skierMap);

        LiftDataProducer liftDataProducer = new LiftDataProducer(liftQueue,input);
        LiftDataConsumer liftDataConsumer = new LiftDataConsumer(liftQueue,liftMap);

        HourDataProducer hourDataProducer = new HourDataProducer(hourQueue,input);
        HourDataConsumer hourDataConsumer = new HourDataConsumer(hourQueue,hourMap);

        executor.submit(skierDataProducer);
        executor.submit(skierDataConsumer);

        executor.submit(liftDataProducer);
        executor.submit(liftDataConsumer);

        executor.submit(hourDataConsumer);
        executor.submit(hourDataProducer);

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    /**
     * write result to different dat file
     */
    private void write() {
        write2SkierFile();
        write2LiftFile();
        write2HourFile();
//        write2RawFile();
    }

    /**
     * sort all Skier by ID in ascending order, and write to skier.dat file
     */
    private void write2SkierFile(){

        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("skier.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Skier> list = new ArrayList<>( skierMap.values());
        list.sort((a,b) -> (a.getID() - b.getID()));

        for (Skier skier : list) {
            try {
                file.writeInt(skier.getID());
                file.writeInt(skier.getVerticalMetres());
                file.writeInt(skier.getLiftRidesCount());
                file.writeInt(skier.getNumberOfViews());

                StringBuffer sb = new StringBuffer(skier.getLiftMap().toDatFileString());
                sb.setLength(SkierLiftMap.SIGNLE_LENGTH * SkierLiftMap.NUMBER);
                file.writeChars(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * sort all Lift by ID in ascending order, and write to lift.dat file
     */
    private void write2LiftFile() {

        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("lift.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Lift> list = new ArrayList<>( liftMap.values());
        list.sort((a,b) -> (a.getLiftID() - b.getLiftID()));

        for (Lift lift : list) {
            try {
                file.writeInt(lift.getLiftID());
                file.writeInt(lift.getRidesCount());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * with 6 sections, one for each hour in the day in ascending order. Each section contain
     * the top 10 busiest lifts for that hour. and write to hour.dat
     */
    private void write2HourFile() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("hour.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Hour> list = new ArrayList<>(hourMap.values());
        list.sort((a,b) -> (a.getNumber() - b.getNumber()));

        for (Hour aList : list) {
            try {
                file.writeInt(aList.getNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<Lift> temp = new ArrayList<>(aList.getLiftMap().values());
            temp.sort((a, b) -> (b.compareTo(a)));
            for (int j = 0; j < 10 && j < temp.size(); j++) {
                try {
                    file.writeInt(temp.get(j).getLiftID());
                    file.writeInt(temp.get(j).getRidesCount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * write to liftRides.dat
     */
    private void write2RawFile() {
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("liftRides.dat", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<int[]> rawData = preprecessRawData();
        for (int[] data : rawData) {
            try {
                file.writeInt(data[0]);
                file.writeInt(data[1]);
                file.writeInt(data[2]);
            } catch (IOException e) {
                e.printStackTrace();
                }
        }
    }

    /**
     * only select the SkierID, LiftID and timestamp, and sort first by skierID, if same, sort by liftId
     * @return List<int[]>
     */
    private  List<int[]> preprecessRawData() {
        List<int[]> rawData = new ArrayList<>();
        for (int i = 1; i < input.size(); i++) {
            String[] strs = input.get(i).split(",");
            int[] data = new int[] {
                    Integer.parseInt(strs[CsvInfo.getSkierPos()]),
                    Integer.parseInt(strs[CsvInfo.getLiftPos()]),
                    Integer.parseInt(strs[CsvInfo.getHourPos()])};
            rawData.add(data);
        }
        rawData.sort((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b [0]));
        return rawData;
    }

}
