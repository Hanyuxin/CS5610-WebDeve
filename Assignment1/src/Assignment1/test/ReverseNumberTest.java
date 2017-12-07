package Assignment1.test;

import Assignment1.ReverseNumber;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.io.StringBufferInputStream;

import static org.junit.Assert.*;

public class ReverseNumberTest {
    private  static ReverseNumber rn;
    @BeforeClass
    public static void setupBeforeClass(){
        rn = new ReverseNumber();
    }

    @Test(expected = IllegalArgumentException.class)
    public void expectIllegalArgumentException(){
        String s = "";
        InputStream is = new StringBufferInputStream(s);
        System.setIn(is);
        rn.reverseNumber();
    }


}