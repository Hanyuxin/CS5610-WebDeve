package neu.edu.cs5010;

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

    public SkierLiftMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    public Map<Integer, Lift> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Lift> map) {
        this.map = map;
    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer();
        for (int time : map.keySet()) {
            sb.append(String.format("%03d",time));
            sb.append(String.format("%02d",map.get(time).getLiftID()));
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return map.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return map.equals(obj);
    }
}
