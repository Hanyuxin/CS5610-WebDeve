package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LiftRunnable implements Runnable{

    private Map<Integer, Lift> map;
    private BlockingQueue<Integer> srcQueue;

    public LiftRunnable(BlockingQueue<Integer> srcQueue) {
        this.srcQueue = srcQueue;
        map = new HashMap<>();
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
        while (!srcQueue.isEmpty()){
            int liftID = 0;
//            try {
//                liftID = srcQueue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            liftID = srcQueue.poll();
            map.putIfAbsent(liftID, new Lift(liftID));
            map.get(liftID).increaseRidesCount();
        }

        List<Lift> list = new ArrayList<>( map.values());
        list.sort((a,b) -> (a.getLiftID() - b.getLiftID()));
        StringBuilder liftString = new StringBuilder();
        liftString.append("LiftID, Number of Rides").append(System.lineSeparator());

        for (int i = 0; i < list.size(); i++) {
            liftString.append(list.get(i).getLiftID());
            liftString.append(", ");
            liftString.append(list.get(i).getRidesCount());
            liftString.append(System.lineSeparator());
        }

        IOLibrary.write("liftConcurrent.csv", liftString.toString());
    }
}
