package neu.edu.cs5010;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private User[] users;


    /**
     * Set up for User class test; Created 4 User objects and store
     * them into a User array to test the methods in User Class.
     */
    @Before
    public void setUp(){
        users = new User[]{new User(33,"4/1/10","F",27,"Seattle"),
                new User(32,"4/1/10","F",27,"Seattle"),
                new User(33,"4/1/10","F",27,"Seattle"),
                new User(302,"11/1/17","F",27,"Seattle")};


    }

    /**
     * Test isNew method in User Class.
     * @throws Exception
     */
    @Test
    public void isNew() throws Exception {
        Assert.assertEquals(false,users[0].isNew());
        Assert.assertEquals(true,users[3].isNew());
    }

    /**
     * Test getID method in User class.
     * @throws Exception
     */
    @Test
    public void getID() throws Exception {
        Assert.assertEquals(32,users[1].getID());
    }

    /**
     * Test getNeighbor method in User class.
     * @throws Exception
     */
    @Test
    public void getNeighbor() throws Exception {
        for(int i=1; i<4; i++){
            users[0].getNeighbor().add(users[i]);
        }
        Assert.assertEquals(3,users[0].getNeighbor().size());

    }

    /**
     * Test getRecommendationTimes method in User class.
     * @throws Exception
     */
    @Test
    public void getRecommendationTimes() throws Exception {
        for (int i = 0; i < 3; i++) {
            users[2].increaseRecommendationTimes();
        }
        Assert.assertEquals(3,users[2].getRecommendationTimes());
    }

    /**
     * Test compareTo method in User class.
     * @throws Exception
     */

    @Test
    public void compareTo() throws Exception {
        for (int i = 0; i < 3; i++) {
            users[2].increaseRecommendationTimes();
        }
       Assert.assertEquals(-1, users[1].compareTo(users[2]));
    }

    /**
     * Test overriden equals and hashCode method in User class.
     */
    @Test
    public void equalsTest(){
        Assert.assertEquals(false, users[0].equals(users[1]));
        Assert.assertEquals(true,users[0].equals(users[2]));
        Assert.assertEquals(true,users[2].hashCode()!=users[3].hashCode());

    }

}