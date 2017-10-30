package neu.edu.cs5010;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {

    private Client client;
    @Before
    public void setUp(){
        client = new Client(100000,0.9);
    }
    @Test
    public void testGetClientID() throws Exception {
        System.out.println(client.getClientID());
    }

    @Test
    public void testGetDepositLimit() throws Exception {
        System.out.println(client.getDepositLimit());
    }

    @Test
    public void getWithdrawLimit() throws Exception {
        System.out.println(client.getDepositLimit());
    }

    @Test
    public void getRsAgenerator() throws Exception {
    }

    @Test
    public void getMessage() throws Exception {
    }

    @Test
    public void getTransitions() throws Exception {
    }

    @Test
    public void setTransitions() throws Exception {
    }

}
