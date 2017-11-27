package neu.edu.cs5010;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class HourDataProducer implements Runnable {
    private BlockingQueue<int[]> hourQueue;
    private List<String> input;

    public HourDataProducer(BlockingQueue<int[]> hourQueue, List<String> input) {
        this.hourQueue = hourQueue;
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

        int hourPos = CsvInfo.getHourPos();
        int liftPos = CsvInfo.getLiftPos();
        if (input.size() == 0) {
            throw new RuntimeException("There is no content in input file");
        }

        for (int i = 1; i < input.size(); i++) {
            String[] strs = input.get(i).split(",");
            int hourID = Integer.parseInt(strs[hourPos]) / 60 + 1;
            int liftID = Integer.parseInt(strs[liftPos]);
            try {
                hourQueue.put(new int[]{hourID, liftID});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            hourQueue.put(new int[]{});
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
