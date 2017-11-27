package neu.edu.cs5010;

import neu.edu.cs5010.IO.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class HourDataConsumer implements Runnable{

    private BlockingQueue<int[]> hourQueue;
    private Map<Integer, Hour> map;

    public HourDataConsumer(BlockingQueue<int[]> hourQueue,Map<Integer, Hour> map) {
        this.hourQueue = hourQueue;
        this.map = map;
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

        while (true) {
            int[] pairs = null;
            try {
                pairs = hourQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(pairs.length == 0) break;
            map.putIfAbsent(pairs[0],new Hour(pairs[0]));
            Map<Integer, Lift> hourLiftMap = map.get(pairs[0]).getLiftMap();
            hourLiftMap.putIfAbsent(pairs[1],new Lift(pairs[1]));
            hourLiftMap.get(pairs[1]).increaseRidesCount();
        }

    }
}
