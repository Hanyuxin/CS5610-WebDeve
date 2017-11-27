package neu.edu.cs5010.dataProcess;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SkierDataProducer implements Runnable{
    private BlockingQueue<int[]> skierQueue;
    private List<String> input;


    public SkierDataProducer(BlockingQueue skierQueue, List<String> input) {
        this.skierQueue = skierQueue;
        this.input = input;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        int skierPos = CsvInfo.getSkierPos();
        int liftPos = CsvInfo.getLiftPos();
        int hourPos = CsvInfo.getHourPos();

        if (input.size() == 0) {
            throw new RuntimeException("There is no content in input file");
        }

        for (int i = 1; i < input.size(); i++) {
            String[] strs = input.get(i).split(",");
            int skierID = Integer.parseInt(strs[skierPos]);
            int liftID = Integer.parseInt(strs[liftPos]);
            int hourID = Integer.parseInt(strs[hourPos]);
            try {
                skierQueue.put(new int[]{skierID, liftID, hourID});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            skierQueue.put(new int[]{});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
