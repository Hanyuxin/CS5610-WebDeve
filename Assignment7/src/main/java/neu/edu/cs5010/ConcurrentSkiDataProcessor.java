package neu.edu.cs5010;

import java.util.List;
import java.util.concurrent.*;

public class ConcurrentSkiDataProcessor {
    private BlockingQueue<int[]> skierQueue;
    private BlockingQueue<Integer> liftQueue;
    private BlockingQueue<int[]> hourQueue;
    private String inputFileName;
    private long time;

    public ConcurrentSkiDataProcessor() {
        skierQueue = new LinkedBlockingQueue<>();
        liftQueue = new LinkedBlockingQueue<>();
        hourQueue = new LinkedBlockingQueue<>();
    }


    /**
     * the main method to receive console input and fulfill the function to write 3 csv file in concurrent way
     * @param args input csv file name
     */
    public static void main( String[] args ) {
        ConcurrentSkiDataProcessor processor = new ConcurrentSkiDataProcessor();
        processor.checkArgument(args);
        processor.preprocess();
        processor.multiThreadsRun();
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
     * and then for each string, store information in 3 blocking queue,
     */
    private void preprocess() {
        List<String> input = IOLibrary.read(inputFileName);

        int skierPos = 0, liftPos = 0, hourPos = 0;
        if (input.size() == 0) {
            throw new RuntimeException("There is no content in input file");
        }

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
            skierQueue.offer(new int[]{skierID, liftID});
            liftQueue.offer(liftID);
            hourQueue.offer(new int[]{hourID, liftID});
        }

        multiThreadsRun();
    }

    /**
     * use Executors to manage multiple thread, use submit to run SkierRunnable, LiftRunnable and HourRunnable.
     * and use Future to see whether success
     */
    private void multiThreadsRun() {
        time = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Future skierFuture = executor.submit(new SkierRunnable(skierQueue));
        Future liftFuture = executor.submit(new LiftRunnable(liftQueue));
        Future hourFuture = executor.submit(new HourRunnable(hourQueue));

        try {
            if (skierFuture.get() == null) {
                System.out.println("Skier file generate success!");
            } else {
                skierFuture.cancel(true);
            }

            if (liftFuture.get() == null) {
                System.out.println("Lift file generate success!");
            } else {
                liftFuture.cancel(true);
            }

            if (hourFuture.get() == null) {
                System.out.println("Hour file generate success!");
            } else {
                hourFuture.cancel(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        time = System.currentTimeMillis() - time;
        System.out.println("Concurrent Solution Runtime: " + time);
    }
}
