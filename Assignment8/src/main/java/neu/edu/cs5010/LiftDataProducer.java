package neu.edu.cs5010;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class LiftDataProducer implements Runnable{

    private BlockingQueue<Integer> skierQueue;
    private List<String> input;


    public LiftDataProducer(BlockingQueue<Integer> skierQueue, List<String> input) {
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
        int liftPos = CsvInfo.getLiftPos();

        if (input.size() == 0) {
            throw new RuntimeException("There is no content in input file");
        }

        for (int i = 1; i < input.size(); i++) {
            String[] strs = input.get(i).split(",");
            int liftID = Integer.parseInt(strs[liftPos]);
            try {
                skierQueue.put(liftID);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            skierQueue.put(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
