package neu.edu.cs5010;

public class Lift {
    private int liftID;
    private int ridesCount;
    private int vertical;
    private static int LEVEL_1 = 10;
    private static int LEVEL_2 = 20;
    private static int LEVEL_3 = 30;

    public Lift(int ID) {
        this.liftID = ID;
        ridesCount = 0;
        initialVertical();
    }

    /**
     * according to liftID, to determine the vertical
     */
    private void initialVertical() {

        if (liftID <= LEVEL_1) {
            vertical = 200;
        } else if (liftID <= LEVEL_2) {
            vertical = 300;
        } else if(liftID <=LEVEL_3) {
            vertical = 400;
        } else {
            vertical = 500;
        }
    }

    /**
     * compare this Lift with another Lift, if this Lift's  rides count bigger than another user, return 1;
     * if this user's  rides count  smaller than another user, return -1; if same, return 0;
     * @param lift the Lift to compare with
     * @return -1, 1, 0
     */
    public int compareTo(Lift lift) {

        if (ridesCount > lift.getRidesCount()) {
            return 1;
        } else if (ridesCount < lift.getRidesCount()) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * return this lift's vertical high
     * @return int vertical
     */
    public int getVertical() {
        return vertical;
    }

    /**
     * get this lift ID
     * @return int ID
     */
    public int getLiftID() {
        return liftID;
    }

    /**
     * get the total number of lift rides per day
     * @return int ridesCount
     */
    public int getRidesCount() {
        return ridesCount;
    }

    /**
     * increase the total number of lift rides per day by 1
     */
    public void increaseRidesCount() {
        this.ridesCount += 1;
    }

    /**
     * Override the equals method of Lift object.
     * @param o The Lift object.
     * @return If two Lift objects are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lift lift = (Lift) o;

        if (liftID != lift.liftID) return false;
        if (ridesCount != lift.ridesCount) return false;
        return vertical == lift.vertical;
    }
    /**
     * Override the hashCode method of Lift object.
     * @return integer hashcode
     */
    @Override
    public int hashCode() {
        int result = liftID;
        result = 31 * result + ridesCount;
        result = 31 * result + vertical;
        return result;
    }
}
