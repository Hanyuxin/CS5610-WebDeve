package edu.neu.ccs.cs5010.section2;

public interface Generator<T> {

  /**
   * Used to generate new Object
   *
   * @return next
   */
  T next();
}
