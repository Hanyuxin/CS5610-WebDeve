package neu.edu.cs5010;


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

    /**
     * Override the equals method of Hour object.
     * @param o The Hour object.
     * @return If two Hour objects are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hour hour = (Hour) o;

        if (number != hour.number) return false;
        return liftMap != null ? liftMap.equals(hour.liftMap) : hour.liftMap == null;
    }

    /**
     * Override the hashCode method of Hour object.
     * @return integer hashcode
     */
    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (liftMap != null ? liftMap.hashCode() : 0);
        return result;
    }
}
