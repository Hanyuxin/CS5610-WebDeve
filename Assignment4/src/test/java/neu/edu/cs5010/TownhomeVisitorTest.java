package neu.edu.cs5010;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TownhomeVisitorTest {

    String regular = "regular";
    List<Candy> candyList = Arrays.asList(new TwixCandy(regular), new SnickersCandy(regular), new MarsCandy(regular), new KitKatCandy(regular),
            new WhoopersCandy(regular), new MilkyWayCandy(regular), new TobleroneCandy(regular), new CrunchCandy(regular), new BabyRuthCandy(regular),
            new AlmondJoyCandy(regular));

    @Test
    public void testVisit(){
        for(Candy candy : candyList){
            TownhomeVisitor visitor = new TownhomeVisitor();
            candy.accept(visitor);
            System.out.println(visitor.getTownhome());
        }
    }

}
