package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class HourRunnable implements Runnable {

    private Map<Integer, Hour> map;
    private BlockingQueue<int[]> srcQueue;


    public HourRunnable(BlockingQueue<int[]> srcQueue) {
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
        while (!srcQueue.isEmpty()) {
            int[] pairs = null;
//            try {
//                pairs = srcQueue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            pairs = srcQueue.poll();
            map.putIfAbsent(pairs[0],new Hour(pairs[0]));
            Map<Integer, Lift> hourLiftMap = map.get(pairs[0]).getLiftMap();
            hourLiftMap.putIfAbsent(pairs[1],new Lift(pairs[1]));
            hourLiftMap.get(pairs[1]).increaseRidesCount();
        }
        List<Hour> list = new ArrayList<>(map.values());
        list.sort((a,b) -> (a.getNumber() - b.getNumber()));

        StringBuilder hourString = new StringBuilder();
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

        IOLibrary.write("hourConcurrent.csv", hourString.toString());

    }

}
