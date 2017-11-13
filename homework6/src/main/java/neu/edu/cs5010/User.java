package neu.edu.cs5010;

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
    private int follwers;
    private String gender;
    private int age;
    private String city;
    private DateFormat format = new SimpleDateFormat("MM/dd/yy");
    private long BASETIME;

    public User(int ID, String date, String gender, int age, String city) {
        this.ID = ID;
        this.date = date;
        this.gender = gender;
        this.age = age;
        this.city = city;
        follwers = 0;
        recommendationTimes = 0;
        neighbor = new ArrayList<>();
        try {
            BASETIME = format.parse("10/01/17").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * compare this user's register time with base time(Oct 1st)
     * @return true if the user is register after Oct 1st, false if the user is register before Oct 1st
     */
    public boolean isNew() {
        long dateTime = 0;
        try {
            dateTime = format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime > BASETIME ? true : false;
    }


    /**
     * get this user's ID
     * @return int ID
     */
    public int getID() {
        return ID;
    }

    /**
     * get Neighbor
     * @return List<User> neighbor
     */
    public List<User> getNeighbor() {
        return neighbor;
    }


    /**
     * get the number of people that follow this user
     * @return the number of people that follow this user
     */
    public int getFollwers() {
        return follwers;
    }

    /**
     * set the number of people that follow this user
     * @param follwers the number of people that follow this user
     */
    public void setFollwers(int follwers) {
        this.follwers = follwers;
    }

    /**
     * get the RecommendationTimes
     * @return int RecommendationTimes
     */
    public int getRecommendationTimes() {
        return recommendationTimes;
    }

    /**
     *  increase recommendation times by 1;
     */
    public void increaseRecommendationTimes() {
        recommendationTimes++;
    }

    /**
     * compare this user with another user, if this user's  recommendation times bigger than another user, return 1;
     * if this user's  recommendation times smaller than another user, return -1, if same, return 0;
     * @param user the user that to compare with
     * @return 1, -1, 0
     */
    public int compareTo(User user) {
        if (recommendationTimes < user.getRecommendationTimes()) {
            return -1;
        } else if (recommendationTimes > user.getRecommendationTimes()) {
            return 1;
        } else return 0;
    }

    /**
     * this user's ID and how many time this user been recommend
     * @return this user's ID and how many time this user been recommend
     */
    public String toConsoleString() {
        String s = "User " + getID() + " is been recommend " + getRecommendationTimes() + " times";
        return s;
    }

    /**
     * get this user's age
     * @return int age
     */
    public int getAge() {
        return age;
    }


    /**
     * get this user's city
     * @return String city name
     */
    public String getCity() {
        return city;
    }



    /**
     * get this user's gender
     * @return String gender
     */
    public String getGender() {
        return gender;
    }



    @Override
    public String toString() {
        return String.valueOf(getID());
    }


    /**
     * Override the equals method of User object.
     * @param o The User object.
     * @return If two User objects are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        if (ID != user.ID) return false;
        if (recommendationTimes != user.recommendationTimes) return false;
        if (follwers != user.follwers) return false;
        if (age != user.age) return false;
        if (date != null ? !date.equals(user.date) : user.date != null) return false;
        if (gender != null ? !gender.equals(user.gender) : user.gender != null) return false;
        return city != null ? city.equals(user.city) : user.city == null;
    }

    /**
     * Overrides hashCode method in User class.
     * @return The hashCode of User objects;
     */
    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + follwers;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
