package neu.edu.cs5010.dataProcess;

import neu.edu.cs5010.IO.Writer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class LiftDataConsumer implements Runnable{

    private BlockingQueue<Integer> liftQueue;
    private Map<Integer, Lift> map;

    public LiftDataConsumer(BlockingQueue<Integer> liftQueue, Map<Integer, Lift> map) {
        this.liftQueue = liftQueue;
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
        while (true){
            int liftID = 0;
            try {
                liftID = liftQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(liftID == -1) break;
            map.putIfAbsent(liftID, new Lift(liftID));
            map.get(liftID).increaseRidesCount();
        }

    }
}
