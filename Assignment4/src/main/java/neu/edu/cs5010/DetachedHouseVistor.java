package neu.edu.cs5010;

public class DetachedHouseVistor implements CandyVisitor {

    private String detachedHouse;

    public String getDetachedHouse() {
        return detachedHouse;
    }

    @Override
    public void visit(TwixCandy twixCandy) {
        if (twixCandy.getSize().equals(funSize)) {
            detachedHouse = twixCandy.getCandyType() + ", Fun Size, Detached House";
        }
    }

    @Override
    public void visit(SnickersCandy snickersCandy) {
        if (snickersCandy.getSize().equals(funSize)) {
            detachedHouse = snickersCandy.getCandyType() + ", Fun Size, Detached House";
        }
    }

    @Override
    public void visit(MarsCandy marsCandy) {
        if (marsCandy.getSize().equals(funSize)) {
            detachedHouse = marsCandy.getCandyType() + ", Fun Size, Detached House";
        }
    }

    @Override
    public void visit(KitKatCandy kitKatCandy) {
        if (kitKatCandy.getSize().equals(superSize)) {
            detachedHouse = kitKatCandy.getCandyType() + ", Super Size, Detached House";
        }
    }

    @Override
    public void visit(WhoopersCandy whoopersCandy) {
        if (whoopersCandy.getSize().equals(superSize)) {
            detachedHouse = whoopersCandy.getCandyType() + ", Super Size, Detached House";
        }
    }

    @Override
    public void visit(MilkyWayCandy milkyWayCandy) {
        if (milkyWayCandy.getSize().equals(superSize)) {
            detachedHouse = milkyWayCandy.getCandyType() + ", Super Size, Detached House";
        }
    }

    @Override
    public void visit(TobleroneCandy tobleroneCandy) {
        if (tobleroneCandy.getSize().equals(kingSize)) {
            detachedHouse = tobleroneCandy.getCandyType() + ", King Size, Detached House";
        }
    }

    @Override
    public void visit(CrunchCandy crunchCandy) {
        if (crunchCandy.getSize().equals(superSize)) {
            detachedHouse = crunchCandy.getCandyType() + ", Super Size, Detached House";
        }
    }

    @Override
    public void visit(BabyRuthCandy babyRuthCandy) {

    }

    @Override
    public void visit(AlmondJoyCandy almondJoyCandy) {

    }
}
