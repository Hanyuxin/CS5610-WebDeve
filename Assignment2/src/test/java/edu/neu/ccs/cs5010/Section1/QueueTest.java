//package edu.neu.ccs.cs5010.Section1;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//
//public class QueueTest {
//    private IQueue queue;
//
//    @Before
//    public void setup(){
//        queue = new MyQueue();
//    }
//    @Test
//    public void testEnqueue(){
//        IQueue queue1 = queue.enqueue(1).enqueue(2);
//        assertEquals("After enqueue two number, the front of queue should be 1",1,queue1.front());
//    }
//    @Test
//    public void testDequeue(){
//        IQueue queue1 = queue.enqueue(1).enqueue(2).dequeue();
//        assertEquals("After dequeue one number, the front of queue should be 2",2,queue1.front());
//        IQueue queue2 = queue1.dequeue();
//        assertTrue(queue2.isEmpty());
//    }
//
//    @Test
//    public void testFront(){
//        IQueue queue1 = queue.enqueue(1).enqueue(2);
//        assertEquals("After enqueue two number, the front of queue should be 1",1,queue1.front());
//        IQueue queue2 = queue1.dequeue();
//        assertEquals("After dequeue one number, the front of the queue should be 2",2,queue2.front());
//    }
//
//    @Test
//    public void testIsEmpty(){
//        assertTrue("Nothing enqueue,isEmpty() should return true",queue.isEmpty());
//        IQueue queue1 = queue.enqueue(1);
//        assertFalse("After enqueue a number, isEmpty() should return false",queue1.isEmpty());
//    }
//
//}
