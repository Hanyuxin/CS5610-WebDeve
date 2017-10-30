package neu.edu.cs5010;

import java.util.Random;

public class Client {

    private int DEPOSIT_BOUND = 2001;
    private int WITHDRAW_BOUND = 3001;
    private Random random;
    private int clientID;
    private int depositLimit;
    private int withdrawLimit;
    private RSA_keyGenerator rsAgenerator;
    private Message message;
    private int transitions;

    public Client(int clientUpperBound, double fraction) {
        random = new Random();
        this.clientID = random.nextInt(clientUpperBound+1);
        rsAgenerator = new RSA_keyGenerator();
        this.clientID = random.nextInt(clientUpperBound);
        this.depositLimit = random.nextInt(DEPOSIT_BOUND);
        this.withdrawLimit = random.nextInt(WITHDRAW_BOUND);
        this.message = new Message(fraction);
        this.transitions = 0;
    }

    public int getClientID() {
        return clientID;
    }

    public int getDepositLimit() {
        return depositLimit;
    }

    public int getWithdrawLimit() {
        return withdrawLimit;
    }

    public RSA_keyGenerator getRsAgenerator() {
        return rsAgenerator;
    }

    public Message getMessage() {
        return message;
    }

    public int getTransitions() {
        return transitions;
    }

    public void setTransitions(int transitions) {
        this.transitions = transitions;
    }
}
