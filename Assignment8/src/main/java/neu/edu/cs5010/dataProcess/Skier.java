package neu.edu.cs5010.dataProcess;


public class Skier {
    private int ID;
    private int liftRidesCount;
    private int verticalMetres;
    private int numberOfViews;
    private SkierLiftMap liftMap;


    public Skier(int ID) {
        this.ID = ID;
        liftRidesCount = 0;
        verticalMetres = 0;
        numberOfViews = 0;
        liftMap= new SkierLiftMap();
    }

    /**
     * compare this skier with another skier, if this user's  vertical metres bigger than another user, return 1;
     * if this user's   vertical metres smaller than another user, return -1; if same, return 0;
     * @param skier the Skier to compare with
     * @return 1, -1, 0
     */
    public int compareTo(Skier skier) {
        if (verticalMetres > skier.getVerticalMetres()) {
            return 1;
        } else if(verticalMetres < skier.getVerticalMetres()) {
            return -1;
        } else {
            return 0;
        }
    }
    /**
     * get this skier's unique ID
     * @return int ID
     */
    public int getID() {
        return ID;
    }

    /**
     * set LiftMap by the giving param
     * @param liftMap SkierLiftMap
     */
    public void setLiftMap(SkierLiftMap liftMap) {
        this.liftMap = liftMap;
    }

    /**
     * get the LiftMap
     * @return SkierLiftMap
     */
    public SkierLiftMap getLiftMap() {
        return liftMap;
    }

    /**
     * set the number of views
     * @param numberOfViews Number of views of current Skier Object.
     */
    public void setNumberOfViews(int numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    /**
     * get the number of lift rides they do each day
     * @return int liftRidesCount
     */
    public int getLiftRidesCount() {
        return liftRidesCount;
    }

    /**
     * set the number of lift rides they do each day
     */
    public void increaseLiftRidesCount() {
        this.liftRidesCount += 1;
    }

    /**
     * set the number of lift rides they do each day
     * @param liftRidesCount Lift Rides count of current Skier Object.
     */
    public void setLiftRidesCount(int liftRidesCount) {
        this.liftRidesCount = liftRidesCount;
    }

    /**
     * get the total amount of vertical metres ski (equal to the total vertical rise of all lifts they ride)
     * @return int verticalMetres
     */
    public int getVerticalMetres() {
        return verticalMetres;
    }

    /**
     * increase the total amount of vertical metres ski by metres
     * @param metres int
     */
    public void increaseVerticalMetres(int metres) {
        this.verticalMetres += metres;
    }

    /**
     * set the total amount of vertical metres ski by metres
     * @param verticalMetres int
     */
    public void setVerticalMetres(int verticalMetres) {
        this.verticalMetres = verticalMetres;
    }

    /**
     * get the time of number of views
     * @return int numberOfViews;
     */
    public int getNumberOfViews() {
        return numberOfViews;
    }

    /**
     * increase the time of number of views by 1
     */
    public void increaseNumberOfViews() {
        this.numberOfViews += 1;
    }


    /**
     * Override the equals method of Skier object.
     * @return true If two Skier objects are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skier skier = (Skier) o;

        if (ID != skier.ID) return false;
        if (liftRidesCount != skier.liftRidesCount) return false;
        if (verticalMetres != skier.verticalMetres) return false;
        if (numberOfViews != skier.numberOfViews) return false;
        return liftMap != null ? liftMap.equals(skier.liftMap) : skier.liftMap == null;
    }
    /**
     * Override the hashCode method of Skier object.
     * @return integer hashcode
     */
    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + liftRidesCount;
        result = 31 * result + verticalMetres;
        result = 31 * result + numberOfViews;
        result = 31 * result + (liftMap != null ? liftMap.hashCode() : 0);
        return result;
    }
}
