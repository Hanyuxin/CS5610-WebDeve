package neu.edu.cs5010;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MansionVistorTest {

    String king = "king";
    String superSize = "super";
    String fun = "fun";
    List<Candy> candyList = Arrays.asList(new TwixCandy(superSize), new SnickersCandy(superSize), new MarsCandy(superSize), new KitKatCandy(king),
            new WhoopersCandy(king), new MilkyWayCandy(king), new TobleroneCandy(fun), new CrunchCandy(king), new BabyRuthCandy(fun),
            new AlmondJoyCandy(fun));

    @Test
    public void testVisit(){
        for(Candy candy : candyList){
            MansionVistor visitor = new MansionVistor();
            candy.accept(visitor);
            System.out.println(visitor.getMansion());
        }
    }
}
