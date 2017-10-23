package neu.edu.cs5010;

public class CrunchCandy implements Candy {

    private String size;

    public CrunchCandy(String size) {
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
        return "Crunch";
    }
}
