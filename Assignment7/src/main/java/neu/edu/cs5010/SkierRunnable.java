package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SkierRunnable implements Runnable {

    private Map<Integer, Skier> map;
    private BlockingQueue<int[]> srcQueue;
    private static int LEVEL_1 = 10;
    private static int LEVEL_2 = 20;
    private static int LEVEL_3 = 30;

    public SkierRunnable(BlockingQueue<int[]> skierQueue) {
        srcQueue = skierQueue;
        map = new HashMap<>();
    }

    private int getVertical(int liftID) {
        if (liftID <= LEVEL_1) {
            return 200;
        } else if (liftID <= LEVEL_2) {
            return 300;
        } else if(liftID <=LEVEL_3) {
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
        while (!srcQueue.isEmpty()) {
            int[] pairs = null;
//            try {
//                pairs =  srcQueue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            pairs = srcQueue.poll();
            map.putIfAbsent(pairs[0],new Skier(pairs[0]));
            map.get(pairs[0]).increaseVerticalMetres(getVertical(pairs[1]));
        }
        List<Skier> list = new ArrayList<>( map.values());
        list.sort((a,b) -> (b.compareTo(a)));
        StringBuilder output = new StringBuilder("SkierID, Vertical");
        output.append(System.lineSeparator());
        for (int i = 0; i < 100; i++) {
            output.append(list.get(i).getID()).
                    append(",").
                    append(list.get(i).getVerticalMetres()).
                    append(System.lineSeparator());
        }
        IOLibrary.write("skierConcurrent.csv",output.toString());
    }


}
