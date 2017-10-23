package neu.edu.cs5010;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CandyTest {
    String king = "king";
    List<Candy> candyList = Arrays.asList(new TwixCandy(king), new SnickersCandy(king), new MarsCandy(king), new KitKatCandy(king),
            new WhoopersCandy(king), new MilkyWayCandy(king), new TobleroneCandy(king), new CrunchCandy(king), new BabyRuthCandy(king),
            new AlmondJoyCandy(king));

    @Test
    public void testgetSize(){
        for(Candy candy :candyList){
            assertEquals("king",candy.getSize());
        }
    }

    @Test
    public void testgetCandyType(){
        for(Candy candy:candyList)
            System.out.println(candy.getCandyType());
    }

}
