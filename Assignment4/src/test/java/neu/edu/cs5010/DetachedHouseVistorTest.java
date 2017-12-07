package neu.edu.cs5010;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DetachedHouseVistorTest {
    String king = "king";
    String superSize = "super";
    String fun = "fun";
    List<Candy> candyList = Arrays.asList(new TwixCandy(fun), new SnickersCandy(fun), new MarsCandy(fun), new KitKatCandy(fun),
            new WhoopersCandy(superSize), new MilkyWayCandy(superSize), new TobleroneCandy(king), new CrunchCandy(superSize), new BabyRuthCandy(superSize),
            new AlmondJoyCandy(superSize));

    @Test
    public void testVisit(){
        for(Candy candy : candyList){
            DetachedHouseVistor visitor = new DetachedHouseVistor();
            candy.accept(visitor);
            System.out.println(visitor.getDetachedHouse());
        }
    }
}
