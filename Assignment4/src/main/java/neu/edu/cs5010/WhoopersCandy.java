package neu.edu.cs5010;

public class WhoopersCandy implements Candy {

    private String size;

    public WhoopersCandy(String size) {
        this.size = size;
    }
    /**
     * accept the visitor
     *
     * @param candyVisitor the special visitor
     */
    @Override
    public void accept(CandyVisitor candyVisitor) {
        candyVisitor.visit(this);
    }

    /**
     * get the size of this Candy
     *
     * @return String size
     */
    @Override
    public String getSize() {
        return size;
    }

    /**
     * get this Candy Type
     *
     * @return  Candy Type
     */
    @Override
    public String getCandyType() {
        return "Whoopers";
    }
}
