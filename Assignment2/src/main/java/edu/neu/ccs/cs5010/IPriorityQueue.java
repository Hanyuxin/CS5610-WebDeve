package edu.neu.ccs.cs5010;

import java.util.List;

public interface IPriorityQueue<E> {

    /**
     * Insert a new element to IPriorityQueue
     * @param e
     */
    void insert(E e);

    /**
     * remove and return the first Element
     * @return
     */
    E remove();

    /**
     * return the first Element without delete it
     * @return
     */
    E front();

    /**
     *return true when the IPriority is null
     * @return true when the IPriority is null
     */
    boolean isEmpty();

    /**
     * test methods to be used by the unit tests; used to make sure all of the links are
     correct (going forward and backward) by traversing the queue and constructing a
     list containing its contents.
     * @return
     */
     List testForwardTraversal();

     List testReverseTraversal();

}
