package edu.neu.ccs.cs5010.section1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StackTest {

    private  IStack stack;

    @Before
    public void setup(){
        stack = new MyStack();
    }

    @Test
    public void testPush(){
        IStack stack1 = stack.push(1).push(2);
        assertEquals("After push two element, the top should be the last one",2,stack1.top());
        IStack stack2 = stack1.pop();
        assertEquals(1,stack2.top());
    }

    @Test
    public void testPop(){
        IStack stack1 = stack.push(1).push(2).pop();
        assertEquals("After pop one element, the top should be 1",1,stack1.top());
        IStack stack2 = stack1.pop();
        assertTrue(stack2.isEmpty());
    }

    @Test
    public void testTop(){
        IStack stack1 = stack.push(1).push(2);
        assertEquals("After push two element, the top should be the last one",2, stack1.top());
        IStack stack2 = stack1.pop();
        assertEquals("After pop one element, the top should be 1",1,stack2.top());
    }

    @Test
    public void testIsEmpty(){
        assertTrue(stack.isEmpty());
        IStack stack1 = stack.push(1);
        assertFalse(stack1.isEmpty());
    }
}
