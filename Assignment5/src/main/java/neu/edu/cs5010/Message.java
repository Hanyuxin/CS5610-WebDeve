package neu.edu.cs5010;

import java.util.Random;

public class Message {

    private static int MESSAGE_BOUND = 30001;

    private Random random;
    private int content;
    private boolean isValid;

    public Message(double fraction) {
        random = new Random();
        content = random.nextInt(MESSAGE_BOUND);
        isValid = random.nextDouble()<fraction ? true : false;
    }

    /**
     * get the value of the message
     * @return Message value
     */
    public int getContent() {
        return content;
    }

    /**
     * get this message is valid or not
     * @return whether this message is valid
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * get the transaction type
     * @return TransactionType
     */
    public TransactionType getTransactionType() {
        if(content%10 < 5) return TransactionType.Deposit;
        else return TransactionType.Withdrawal;
    }

    /**
     * get the number of transaction
     * @return number
     */
    public int getNumber() {
        if(!isValid) return -1;
        return content/10;
    }
}
