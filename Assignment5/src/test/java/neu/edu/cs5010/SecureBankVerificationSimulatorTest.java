package neu.edu.cs5010;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SecureBankVerificationSimulatorTest {
    private SecureBankVerificationSimulator simulator;
    @Before
    public void setUp() {
        simulator = new SecureBankVerificationSimulator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void main() throws Exception {
        String[] args = new String[]{"60000", "10000","0.3","test.ext"};
        SecureBankVerificationSimulator.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void main1() throws Exception {
        String[] args = new String[]{"40000", "100000","0.3","test.ext"};
        SecureBankVerificationSimulator.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void main2() throws Exception {
        String[] args = new String[]{"60000", "10000","1.3","test.ext"};
        SecureBankVerificationSimulator.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void main3() throws Exception {
        String[] args = new String[]{"60000", "10000","0.3",""};
        SecureBankVerificationSimulator.main(args);
    }

    @Test(expected = IllegalArgumentException.class)
    public void main4() throws Exception {
        String[] args = new String[]{};
        SecureBankVerificationSimulator.main(args);
    }

    @Test
    public void main5() throws Exception {
        String[] args = new String[]{"5000", "1000","0.9","test.txt"};
        SecureBankVerificationSimulator.main(args);
    }
}