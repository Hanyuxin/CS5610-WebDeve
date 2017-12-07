package neu.edu.cs5010;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    private static Message message;

    @BeforeClass
    public static void setUp() {
        message = new Message(0.8);
    }
    @Test
    public void getContent() throws Exception {
        System.out.println(message.getContent());
    }

    @Test
    public void isValid() throws Exception {
        System.out.println(message.isValid());
    }

    @Test
    public void getTransactionType() throws Exception {
        System.out.println(message.getTransactionType());
    }

    @Test
    public void getNumber() throws Exception {
        assertTrue(message.getContent()>message.getNumber());
    }

}