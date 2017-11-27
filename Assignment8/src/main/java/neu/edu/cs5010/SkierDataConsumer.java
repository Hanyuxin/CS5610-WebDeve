package neu.edu.cs5010;

import neu.edu.cs5010.IO.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class SkierDataConsumer implements Runnable{
    private BlockingQueue<int[]> skierQueue;
    private Map<Integer, Skier> map;
    private int LEVEL_LOW = 10;
    private int LEVEL_MID = 20;
    private int LEVEL_HIGH = 30;

    public SkierDataConsumer(BlockingQueue<int[]> skierQueue, Map<Integer, Skier> map) {
        this.skierQueue = skierQueue;
        this.map = map;
    }

    private int getVertical(int liftID) {
        if (liftID <= LEVEL_LOW) {
            return 200;
        } else if (liftID <= LEVEL_MID) {
            return 300;
        } else if(liftID <= LEVEL_HIGH) {
            return 400;
        } else {
            return 500;
        }
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
                pairs =  skierQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(pairs.length == 0) break;
            map.putIfAbsent(pairs[0],new Skier(pairs[0]));
            map.get(pairs[0]).increaseVerticalMetres(getVertical(pairs[1]));
            map.get(pairs[0]).increaseLiftRidesCount();

            SkierLiftMap skierLiftMap = map.get(pairs[0]).getLiftMap();
            skierLiftMap.getMap().putIfAbsent(pairs[1], new Lift(pairs[1]));
            skierLiftMap.getMap().get(pairs[1]).increaseRidesCount();
        }

    }
}
