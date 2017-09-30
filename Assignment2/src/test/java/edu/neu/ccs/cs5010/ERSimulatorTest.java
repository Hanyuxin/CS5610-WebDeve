package edu.neu.ccs.cs5010;

import org.junit.Before;
import org.junit.Test;

public class ERSimulatorTest {
    private ERSimulator erSimulator;

    @Before
    public void SetUp(){
        erSimulator = new ERSimulator(20,8,18);
    }

    @Test
    public void TestUserInput(){
        erSimulator.UserInput();
    }
}
