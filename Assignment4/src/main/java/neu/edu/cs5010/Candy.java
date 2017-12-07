package neu.edu.cs5010;

public interface Candy {
    String size = null;

    /**
     * accept the visitor
     * @param candyVisitor the special visitor
     */
    void accept(CandyVisitor candyVisitor);

    /**
     * get the size of this Candy
     * @return String size
     */
    String getSize();

    /**
     * get this Candy Type
     * @return  Candy Type
     */
    String getCandyType();
}
