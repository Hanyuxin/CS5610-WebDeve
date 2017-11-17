package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sequential Solution
 */
public class SequentialSkiDataProcessor {

    private Map<Integer, Skier> skierMap;
    private Map<Integer, Lift> liftMap ;
    private Map<Integer, Hour> hourMap;
    private StringBuilder skierString;
    private StringBuilder liftString;
    private StringBuilder hourString;
    private String inputFileName;
    private long time;

    public SequentialSkiDataProcessor() {
        skierMap = new HashMap<>();
        liftMap = new HashMap<>();
        hourMap = new HashMap<>();
        skierString = new StringBuilder();
        liftString = new StringBuilder();
        hourString = new StringBuilder();
    }

    public static void main( String[] args ) {
        SequentialSkiDataProcessor processor = new SequentialSkiDataProcessor();
        processor.checkArgument(args);
        processor.run();
        processor.write();
    }

    /**
     * check whether input arguments are valid
     * @param args input arguments
     * @RuntimeException IllegalArgumentException when argument not exactly one, and not a csv file
     */
    private void checkArgument(String[] args) {
        if (args.length != 1)
            throw new IllegalArgumentException("Please check your argument");
        if (!args[0].contains(".csv"))
            throw new IllegalArgumentException("The input file should be a CSV file");
        inputFileName = args[0];
    }

    /**
     * read every line from input file name as a list of String, first find the different columns index,
     * and then for each string, get the skierID, liftId, hourId, and update the skierMap, liftMap, hourMap
     */
    private void run() {
        List<String> input = IOLibrary.read(inputFileName);

        time = System.currentTimeMillis();
        int skierPos = 0, liftPos = 0, hourPos = 0;
        if (input.size() == 0) {
            throw new RuntimeException("There is no content in input file");
        }

        //get the columns index
        String[] strs = input.get(0).split(",");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].contains("skier")) skierPos = i;
            else if (strs[i].contains("lift")) liftPos = i;
            else if (strs[i].contains("time")) hourPos = i;
        }

        for (int i = 1; i < input.size(); i++) {
            strs = input.get(i).split(",");
            int skierID = Integer.parseInt(strs[skierPos]);
            int liftID = Integer.parseInt(strs[liftPos]);
            int hourID = Integer.parseInt(strs[hourPos]) / 60 + 1;
            if (!skierMap.containsKey(skierID)) {
                skierMap.put(skierID, new Skier(skierID));
            }
            if (!liftMap.containsKey(liftID)) {
                liftMap.put(liftID, new Lift(liftID));
            }
            if(!hourMap.containsKey(hourID)) {
                hourMap.put(hourID,new Hour(hourID));
            }
            skierMap.get(skierID).increaseVerticalMetres(liftMap.get(liftID).getVertical());
            skierMap.get(skierID).increaseLiftRidesCount();

            liftMap.get(liftID).increaseRidesCount();

            Map<Integer, Lift> hourLiftMap = hourMap.get(hourID).getLiftMap();
            if(!hourLiftMap.containsKey(liftID)) {
                hourLiftMap.put(liftID,new Lift(liftID));
            }
            hourLiftMap.get(liftID).increaseRidesCount();
        }
    }

    private void write() {
        write2SkierFile();
        write2LiftFile();
        write2HourFile();
        toConsoleString();
    }

    /**
     * sort all Skier by vertical totals in descending order, and write the top 100 to skierSequential.csv file
     */
    private void write2SkierFile() {
        List<Skier> list = new ArrayList<>(skierMap.values());
        list.sort((a,b) -> (b.compareTo(a)));
        skierString.append("SkierID, Vertical").append(System.lineSeparator());

        for (int i = 0; i < 100; i++) {
            skierString.append(list.get(i).getID());
            skierString.append(", ");
            skierString.append(list.get(i).getVerticalMetres());
            skierString.append(System.lineSeparator());
        }

        IOLibrary.write("skierSequential.csv",skierString.toString());
        System.out.println("Skier file generate success!");
    }

    /**
     * sort all Lift in ascending order of LiftID, and write to liftSequential.csv file
     */
    private void write2LiftFile() {
        List<Lift> list = new ArrayList<>(liftMap.values());
        list.sort((a,b) -> (a.getLiftID() - b.getLiftID()));
        liftString.append("LiftID, Number of Rides").append(System.lineSeparator());

        for (int i = 0; i < list.size(); i++) {
            liftString.append(list.get(i).getLiftID());
            liftString.append(", ");
            liftString.append(list.get(i).getRidesCount());
            liftString.append(System.lineSeparator());
        }

        IOLibrary.write("liftSequential.csv", liftString.toString());
        System.out.println("Lift file generate success!");
    }

    /**
     * with 6 sections, one for each hour in the day in ascending order. Each section contain
     * the top 10 busiest lifts for that hour. and write to hourSequential.csv
     */
    private void write2HourFile() {
        List<Hour> list = new ArrayList<>(hourMap.values());
        list.sort((a,b) -> (a.getNumber() - b.getNumber()));

        for (int i = 0; i < list.size(); i++) {
            hourString.append("Hour ").append(list.get(i).getNumber());
            hourString.append(System.lineSeparator());
            hourString.append("LiftID, Number of Rides");
            hourString.append(System.lineSeparator());
            List<Lift> temp = new ArrayList<>(list.get(i).getLiftMap().values());
            temp.sort((a,b) -> (b.compareTo(a)));
            for (int j = 0; j < 10 && j < temp.size(); j++) {
                hourString.append(temp.get(j).getLiftID());
                hourString.append(", ");
                hourString.append(temp.get(j).getRidesCount());
                hourString.append(System.lineSeparator());
            }
        }

        IOLibrary.write("hourSequential.csv", hourString.toString());
        System.out.println("Hour file generate success!");
    }

    /**
     * print out time information
     */
    private void toConsoleString() {
        time = System.currentTimeMillis() - time;
        System.out.println("Sequential Solution Runtime: " + time);
    }
}
