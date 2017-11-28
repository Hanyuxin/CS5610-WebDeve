package neu.edu.cs5010.dataProcess;

import java.util.HashMap;

import java.util.Map;

public class SkierLiftMap {
    private Map<Integer, Lift> map;
    public static final int SIGNLE_LENGTH = 5;
    public static final int NUMBER =  20;


    public SkierLiftMap() {
        map = new HashMap<>();
    }


    public SkierLiftMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    /**
     * Get the Map of LiftID and Lift object.
     * @return  The Map of LiftID and Lift object.
     */
    public Map<Integer, Lift> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    public String toDatFileString(){
        StringBuffer sb = new StringBuffer();
        for (int time : map.keySet()) {
            sb.append(String.format("%03d",time));
            sb.append(String.format("%02d",map.get(time).getLiftID()));
        }
        return sb.toString();
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
        return map != null ? map.hashCode() : 0;
    }

    /**
     * Override the equals method
     * @return if two SkierLiftMap objects are actually equals.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkierLiftMap that = (SkierLiftMap) o;

        return map != null ? map.equals(that.map) : that.map == null;
    }
}
