package neu.edu.cs5010;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int ID;
    private String date;
    private int recommendationTimes;
    private List<User> neighbor;
    private DateFormat format = new SimpleDateFormat("MM/dd/yy");
    private long BASETIME;

    public User(int ID, String date) throws ParseException {
        this.ID = ID;
        this.date = date;
        recommendationTimes = 0;
        neighbor = new ArrayList<>();
        BASETIME = format.parse("10/01/17").getTime();
    }

    public boolean isNew(String time) throws ParseException {
        long dateTime = format.parse(time).getTime();
        return dateTime > BASETIME ? true : false;
    }


    public int getID() {
        return ID;
    }

    public List<User> getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(List<User> neighbor) {
        this.neighbor = neighbor;
    }

    public int getRecommendationTimes() {
        return recommendationTimes;
    }

    public void increaseRecommendationTimes() {
        recommendationTimes++;
    }

    public int compareTo(User user) {
        if (user.getRecommendationTimes() > recommendationTimes) {
            return -1;
        } else if (user.getRecommendationTimes() < recommendationTimes) {
            return 1;
        } else return 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
