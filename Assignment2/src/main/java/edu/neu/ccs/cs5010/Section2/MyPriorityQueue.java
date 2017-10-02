package edu.neu.ccs.cs5010.Section2;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E>{

    private List<E> list;

    public MyPriorityQueue() {
        list = new LinkedList<>();
    }

    /**
     * every time insert a new element, it will adjust the sequence to sort it in descending order
     */
    private void adjust(){
        int size = list.size();
        if(size==1) return;
        E temp = list.remove(size-1);
        int i;
        for(i = size-2;i>=0;i--){
            if(list.get(i).compareTo(temp)>0) break;
        }
        list.add(i+1,temp);
    }

    /**
     * insert a new element
     * @param e the element insert
     */
    public void insert(E e) {
        list.add(e);
        adjust();
    }

    /**
     * remove the front of this queue
     * @return the largest element
     */
    public E remove() {
        if(isEmpty()) throw new NoSuchElementException();
        E e = list.get(0);
        list.remove(0);
        return e;
    }

    /**
     * return the front of the queue without remove it
     * @return the largest element
     */
    public E front() {
        if(isEmpty()) throw new NoSuchElementException();
        return list.get(0);
    }

    /**
     *return true when the queue is null
     * @return true when the queue is null
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * the size of this queue
     * @return
     */
    public int size(){
        return list.size();
    }

    /**
     * test methods to be used by the unit tests; used to make sure all of the links are
     correct (going forward and backward) by traversing the queue and constructing a
     list containing its contents.
     * @return
     */
    public List testForwardTraversal() {
        return list;
    }

    public List testReverseTraversal() {
        List<E> reverseList = new LinkedList<>();
        for(E e: list){
            reverseList.add(0,e);
        }
        return reverseList;
    }
}
