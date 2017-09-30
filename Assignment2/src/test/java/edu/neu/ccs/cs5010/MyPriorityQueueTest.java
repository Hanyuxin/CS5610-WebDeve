package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyPriorityQueueTest {
    private MyPriorityQueue myPriorityQueue;

    @Before
    public void setUp(){
        myPriorityQueue = new MyPriorityQueue();
    }

    @Test
    public void TestInsert(){
        myPriorityQueue.insert(2);
        myPriorityQueue.insert(3);
        assertEquals(2,myPriorityQueue.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void expectedSuchElementException1(){
        myPriorityQueue.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void expectedSuchElementException2(){
        myPriorityQueue.front();
    }

    @Test
    public void TestRemove(){
        myPriorityQueue.insert(3);
        myPriorityQueue.insert(8);
        assertEquals("The bigger one should return:",8,myPriorityQueue.remove());
        assertEquals(3,myPriorityQueue.front());
        assertEquals(3,myPriorityQueue.remove());
        assertTrue(myPriorityQueue.isEmpty());
    }

    @Test
    public void TestFront(){
        myPriorityQueue.insert(5);
        myPriorityQueue.insert(8);
        assertEquals("The bigger one should return:",8,myPriorityQueue.front());
    }

    @Test
    public void TestIsEmpty(){
        assertTrue("Is Empty",myPriorityQueue.isEmpty());
        myPriorityQueue.insert(5);
        assertFalse("Not Empty",myPriorityQueue.isEmpty());
    }

    @Test
    public void TestForwardTraversal(){
        myPriorityQueue.insert('b');
        myPriorityQueue.insert('a');
        myPriorityQueue.insert('r');
        myPriorityQueue.insert('f');
        List<Character> list = myPriorityQueue.testForwardTraversal();
        System.out.println(list.get(0));
    }
}
