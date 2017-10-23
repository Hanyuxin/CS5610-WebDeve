package neu.edu.cs5010;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class DuplexVisitorTest {

    String king = "king";
    String superSize = "super";
    String fun = "fun";
    List<Candy> candyList = Arrays.asList(new TwixCandy(king), new SnickersCandy(king), new MarsCandy(king), new KitKatCandy(fun),
            new WhoopersCandy(fun), new MilkyWayCandy(fun), new TobleroneCandy(superSize), new CrunchCandy(fun), new BabyRuthCandy(superSize),
            new AlmondJoyCandy(superSize));

    @Test
    public void testVisit(){
        for(Candy candy : candyList){
            DuplexVisitor visitor = new DuplexVisitor();
            candy.accept(visitor);
            System.out.println(visitor.getDuplex());
        }
    }

}
