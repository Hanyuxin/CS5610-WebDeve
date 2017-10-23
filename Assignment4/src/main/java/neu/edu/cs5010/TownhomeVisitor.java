package neu.edu.cs5010;

public class TownhomeVisitor implements CandyVisitor {

    private String townhome;

    public String getTownhome() {
        return townhome;
    }

    public void setTownhome(String candyType) {
        townhome = candyType + ", Regular Size, Townhome";
    }

    @Override
    public void visit(TwixCandy twixCandy) {
        if (twixCandy.getSize().equals(regularSize)) {
            setTownhome(twixCandy.getCandyType());
        }
    }

    @Override
    public void visit(SnickersCandy snickersCandy) {
        if (snickersCandy.getSize().equals(regularSize)) {
            setTownhome(snickersCandy.getCandyType());
        }
    }

    @Override
    public void visit(MarsCandy marsCandy) {
        if (marsCandy.getSize().equals(regularSize)) {
            setTownhome(marsCandy.getCandyType());
        }
    }

    @Override
    public void visit(KitKatCandy kitKatCandy) {
        if (kitKatCandy.getSize().equals(regularSize)) {
            setTownhome(kitKatCandy.getCandyType());
        }
    }

    @Override
    public void visit(WhoopersCandy whoopersCandy) {
        if (whoopersCandy.getSize().equals(regularSize)) {
            setTownhome(whoopersCandy.getCandyType());
        }
    }

    @Override
    public void visit(MilkyWayCandy milkyWayCandy) {

    }

    @Override
    public void visit(TobleroneCandy tobleroneCandy) {
        if (tobleroneCandy.getSize().equals(regularSize)) {
            setTownhome(tobleroneCandy.getCandyType());
        }
    }

    @Override
    public void visit(CrunchCandy crunchCandy) {

    }

    @Override
    public void visit(BabyRuthCandy babyRuthCandy) {
        if (babyRuthCandy.getSize().equals(regularSize)) {
            setTownhome(babyRuthCandy.getCandyType());
        }
    }

    @Override
    public void visit(AlmondJoyCandy almondJoyCandy) {
        if (almondJoyCandy.getSize().equals(regularSize)) {
            setTownhome(almondJoyCandy.getCandyType());
        }
    }
}
