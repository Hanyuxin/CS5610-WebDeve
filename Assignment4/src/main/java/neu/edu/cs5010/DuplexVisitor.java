package neu.edu.cs5010;

public class DuplexVisitor implements CandyVisitor {

    private String duplex;

    public String getDuplex() {
        return duplex;
    }

    @Override
    public void visit(TwixCandy twixCandy) {
        if (twixCandy.getSize().equals(kingSize)) {
            duplex = twixCandy.getCandyType() +", King Size, Duplex";
        }
    }

    @Override
    public void visit(SnickersCandy snickersCandy) {
        if (snickersCandy.getSize().equals(kingSize)) {
            duplex = snickersCandy.getCandyType() + ", King Size, Duplex";
        }
    }

    @Override
    public void visit(MarsCandy marsCandy) {
        if (marsCandy.getSize().equals(kingSize)) {
            duplex = marsCandy.getCandyType() + ", King Size, Duplex";
        }
    }

    @Override
    public void visit(KitKatCandy kitKatCandy) {
        if (kitKatCandy.getSize().equals(funSize)) {
            duplex = kitKatCandy.getCandyType() + ", Fun Size, Duplex";
        }
    }

    @Override
    public void visit(WhoopersCandy whoopersCandy) {
        if (whoopersCandy.getSize().equals(funSize)) {
            duplex = whoopersCandy.getCandyType() + ", Fun Size, Duplex";
        }
    }

    @Override
    public void visit(MilkyWayCandy milkyWayCandy) {
        if (milkyWayCandy.getSize().equals(funSize)) {
            duplex = milkyWayCandy.getCandyType() + ", Fun Size, Duplex";
        }
    }

    @Override
    public void visit(TobleroneCandy tobleroneCandy) {
        if (tobleroneCandy.getSize().equals(superSize)) {
            duplex = tobleroneCandy.getCandyType() + ", Super Size, Duplex";
        }
    }

    @Override
    public void visit(CrunchCandy crunchCandy) {
        if (crunchCandy.getSize().equals(funSize)) {
            duplex = crunchCandy.getCandyType() + ", Fun Size, Duplex";
        }
    }

    @Override
    public void visit(BabyRuthCandy babyRuthCandy) {
        if (babyRuthCandy.getSize().equals(superSize)) {
            duplex = babyRuthCandy.getCandyType() + ", Super Size, Duplex";
        }
    }

    @Override
    public void visit(AlmondJoyCandy almondJoyCandy) {
        if (almondJoyCandy.getSize().equals(superSize)) {
            duplex = almondJoyCandy.getCandyType() + ", Super Size, Duplex";
        }
    }
}
