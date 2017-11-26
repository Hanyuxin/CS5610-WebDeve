package neu.edu.cs5010;

public class Skier {
    private int ID;
    private int liftRidesCount;
    private int verticalMetres;

    public Skier(int ID) {
        this.ID = ID;
        liftRidesCount = 0;
        verticalMetres = 0;
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
     * get the number of lift rides they do each day
     * @return int liftRidesCount
     */
    public int getLiftRidesCount() {
        return liftRidesCount;
    }

    /**
     * set the number of lift rides they do each day
     *
     */
    public void increaseLiftRidesCount() {
        this.liftRidesCount += 1;
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
     * Override the equals method of Skier object.
     * @param o the Skier Object
     * @return true If two Skier objects are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skier skier = (Skier) o;

        if (ID != skier.ID) return false;
        if (liftRidesCount != skier.liftRidesCount) return false;
        return verticalMetres == skier.verticalMetres;
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
        return result;
    }
}
