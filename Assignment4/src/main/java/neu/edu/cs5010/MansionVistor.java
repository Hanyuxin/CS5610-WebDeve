package neu.edu.cs5010;

public class MansionVistor implements CandyVisitor {

    private String mansion;

    public String getMansion() {
        return mansion;
    }

    @Override
    public void visit(TwixCandy twixCandy) {
        if (twixCandy.getSize().equals(superSize)) {
            mansion = twixCandy.getCandyType() +  ", Super Size, Mansion";
        }
    }

    @Override
    public void visit(SnickersCandy snickersCandy) {
        if (snickersCandy.getSize().equals(superSize)) {
            mansion = snickersCandy.getCandyType() + ", Super Size, Mansion";
        }
    }

    @Override
    public void visit(MarsCandy marsCandy) {
        if (marsCandy.getSize().equals(superSize)) {
            mansion = marsCandy.getCandyType() + ", Super Size, Mansion";
        }
    }

    @Override
    public void visit(KitKatCandy kitKatCandy) {
        if (kitKatCandy.getSize().equals(kingSize)) {
            mansion = kitKatCandy.getCandyType() + ", King Size, Mansion";
        }
    }

    @Override
    public void visit(WhoopersCandy whoopersCandy) {
        if (whoopersCandy.getSize().equals(kingSize)) {
            mansion = whoopersCandy.getCandyType() +  ", King Size, Mansion";
        }
    }

    @Override
    public void visit(MilkyWayCandy milkyWayCandy) {

    }

    @Override
    public void visit(TobleroneCandy tobleroneCandy) {
        if (tobleroneCandy.getSize().equals(funSize)) {
            mansion = tobleroneCandy.getCandyType() +  ", Fun Size, Mansion";
        }
    }

    @Override
    public void visit(CrunchCandy crunchCandy) {
        if (crunchCandy.getSize().equals(kingSize)) {
            mansion = crunchCandy.getCandyType() + ", King Size, Mansion";
        }
    }

    @Override
    public void visit(BabyRuthCandy babyRuthCandy) {
        if (babyRuthCandy.getSize().equals(funSize)) {
            mansion = babyRuthCandy.getCandyType() +  ", Fun Size, Mansion";
        }
    }

    @Override
    public void visit(AlmondJoyCandy almondJoyCandy) {
        if (almondJoyCandy.getSize().equals(funSize)) {
            mansion = almondJoyCandy.getCandyType() +  ", Fun Size, Mansion";
        }
    }
}
