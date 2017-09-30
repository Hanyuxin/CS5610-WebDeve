package edu.neu.ccs.cs5010;

public interface Generator<T> {

    /**
     * Used to generate new Object
     * @return
     */
    public T next();
}
