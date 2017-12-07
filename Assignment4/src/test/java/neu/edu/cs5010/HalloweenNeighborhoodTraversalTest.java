package neu.edu.cs5010;

import org.junit.Before;
import org.junit.Test;

public class HalloweenNeighborhoodTraversalTest {
    HalloweenNeighborhoodTraversal traversal;

    @Before
    public void setUp(){
        traversal = new HalloweenNeighborhoodTraversal();
    }

    /**
     * Test none input
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestMain1(){
        String[] args = new String[]{};
        HalloweenNeighborhoodTraversal.main(args);
    }

    /**
     * Test not enough argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestMain2(){
        String[] args = new String[]{"1"};
        HalloweenNeighborhoodTraversal.main(args);
    }

    /**
     * Test wrong input
     */
    @Test(expected = IllegalArgumentException.class)
    public void TestMain3(){
        String[] args = new String[]{"a"};
        HalloweenNeighborhoodTraversal.main(args);
    }

    /**
     * Test correct input
     */
    @Test
    public void TestMain4(){
        String[] args = new String[]{"1","DreamCandy1.csv"};
        HalloweenNeighborhoodTraversal.main(args);
    }

    /**
     * Test multiply arguments that one of them is not valid
     */
    @Test
    public void TestMain5(){
        String[] args = new String[]{"2","DreamCandy2.csv","DreamCandy1.csv"};
        HalloweenNeighborhoodTraversal.main(args);
    }
}
