package neu.edu.cs5010;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;


public class NeighborhoodTest {
    private  Map<Integer, User> userList;

    private  Neighborhood neighborhood;

    /**
     * Set up the neighborhood object.
     */
    @Before
    public  void setUp(){
        neighborhood = new Neighborhood("nodes_small.csv","edges_small.csv");

    }

    /**
     * Test if the user's information and social networking was constructed correctly.
     */
    @Test
    public void GraphConstructionCheck(){
        userList = neighborhood.getUserList();
        Assert.assertEquals("F", userList.get(1).getGender());
        Assert.assertEquals(22, userList.get(1).getAge());
        Assert.assertEquals("Seattle", userList.get(1).getCity());
        Assert.assertEquals(false, userList.get(1).isNew());


        Assert.assertEquals(5, userList.get(1).getNeighbor().size());
        Assert.assertEquals(21, userList.get(1).getFollwers());
        Assert.assertEquals(27, userList.get(10).getFollwers());

    }




}