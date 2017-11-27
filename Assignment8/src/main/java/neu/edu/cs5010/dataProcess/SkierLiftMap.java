package neu.edu.cs5010.dataProcess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkierLiftMap {
    private Map<Integer, Lift> map;
    public static final int SIGNLE_LENGTH = 5;
    public static final int NUMBER =  20;


    public SkierLiftMap() {
        map = new HashMap<>();
    }

    /**
     * set the inner Map<Integer, Lift>
     * @param map Map<Integer, Lift>
     */
    public SkierLiftMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    /**
     * get the inner Map<Integer, Lift>
     * @return Map<Integer, Lift>
     */
    public Map<Integer, Lift> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    /**
     * Override the toString method, by add all entry to a string, time use 3 characters, and liftID use 2 character
     * @return String
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        for (int time : map.keySet()) {
            sb.append(String.format("%03d",time));
            sb.append(String.format("%02d",map.get(time).getLiftID()));
        }
        return sb.toString();
    }

    /**
     * override the hashCode method
     * @return int
     */
    @Override
    public int hashCode() {
        return map.hashCode();
    }

    /**
     * Override the equals method
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return map.equals(obj);
    }
}
