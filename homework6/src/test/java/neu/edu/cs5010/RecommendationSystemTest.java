package neu.edu.cs5010;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class RecommendationSystemTest {
    private String[] arguments;
    private String[] arguments2;
    private RecommendationSystem recommendationSystem;
    private RecommendationSystem recommendationSystem2;

    /**
     * Set up the arguments and recommendationSystem objects for test.
     */
    @Before
    public void setUp(){
        arguments = new String[]{"nodes_small.csv", "edges_small.csv","small.csv","99","r","20"};
        arguments2 = new String[]{"nodes_small.csv", "edges_small.csv","small.csv","40","s","17"};

        recommendationSystem = new RecommendationSystem();
        recommendationSystem2 = new RecommendationSystem();
    }

    /**
     * Test readArgument method in RecommendationSystem class.
     */
    @Test
    public void readArgumentTest(){
        try{
            recommendationSystem.checkArgument(arguments);
            recommendationSystem2.checkArgument(arguments2);
        } catch(IllegalArgumentException ex){
            fail("Threw IllegalArgumentException for:"
                    + ex);
        }

    }

    /**
     * Test ovveriden equals and hashCode method in RecommendationSystem class.
     */
    @Test
    public void recomendTest(){
        recommendationSystem.checkArgument(arguments);
        recommendationSystem.run();
        recommendationSystem.write();

        recommendationSystem2.checkArgument(arguments2);
        recommendationSystem2.run();
        recommendationSystem2.write();

        Assert.assertEquals(false, recommendationSystem.equals(recommendationSystem2));
        Assert.assertEquals(true, recommendationSystem.hashCode()!=recommendationSystem2.hashCode());
    }
}
