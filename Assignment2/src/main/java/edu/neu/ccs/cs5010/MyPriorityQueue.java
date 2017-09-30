package edu.neu.ccs.cs5010;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyPriorityQueue<E extends Comparable<E>> implements IPriorityQueue<E>{

    private List<E> list;

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
    public MyPriorityQueue() {
        list = new LinkedList<>();
    }

    public void insert(E e) {
        list.add(e);
        adjust();
    }

    public E remove() {
        if(isEmpty()) throw new NoSuchElementException();
        E e = list.get(0);
        list.remove(0);
        return e;
    }

    public E front() {
        if(isEmpty()) throw new NoSuchElementException();
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size(){
        return list.size();
    }

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
