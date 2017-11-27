package neu.edu.cs5010.dataProcess;


import java.util.HashMap;
import java.util.Map;

public class Hour {
    private int number;
    private Map<Integer, Lift> liftMap;

    public Hour(int number) {
        this.number = number;
        liftMap = new HashMap<>();
    }

    /**
     * get this hour's number
     * @return int number from 0-6
     */
    public int getNumber() {
        return number;
    }

    /**
     * get a map that contains all Lift that used in this hour
     * @return Map<Integer, Lift>
     */
    public Map<Integer, Lift> getLiftMap() {
        return liftMap;
    }

    /**
     *
     * @param liftMap Map<Integer, Lift>
     */
    public void setLiftMap(Map<Integer, Lift> liftMap) {
        this.liftMap = liftMap;
    }
}
