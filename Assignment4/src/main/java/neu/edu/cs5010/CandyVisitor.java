package neu.edu.cs5010;

public interface CandyVisitor {

    String superSize = "super";
    String kingSize = "king";
    String funSize = "fun";
    String regularSize = "regular";

    void visit(TwixCandy twixCandy);

    void visit(SnickersCandy snickersCandy);

    void visit(MarsCandy marsCandy);

    void visit(KitKatCandy kitKatCandy);

    void visit(WhoopersCandy whoopersCandy);

    void visit(MilkyWayCandy milkyWayCandy);

    void visit(TobleroneCandy tobleroneCandy);

    void visit(CrunchCandy crunchCandy);

    void visit(BabyRuthCandy babyRuthCandy);

    void visit(AlmondJoyCandy almondJoyCandy);
}
