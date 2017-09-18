package Assignment1.test;

import Assignment1.ReverseNumber;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.junit.Assert.assertEquals;

public class ReverseNumberTest {
    private  static ReverseNumber rn;
    @BeforeClass
    public static void setupBeforeClass(){
        rn = new ReverseNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException1(){
        String s = "";
        InputStream is = new StringBufferInputStream(s);
        System.setIn(is);
        rn.reverseNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException2(){
        String s = "ab 2";
        InputStream is = new StringBufferInputStream(s);
        System.setIn(is);
        rn.reverseNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException3(){
        rn.reverse(Integer.MIN_VALUE);
    }

    @Test
    public void testExamples(){
        assertEquals("Test positive input",15,rn.reverse(5100));
        assertEquals("Test negetive input",-1,rn.reverse(-10));
        assertEquals("Test zero",0,rn.reverse(0));
    }


}