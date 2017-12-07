package neu.edu.cs5010;

import java.math.BigInteger;
import java.util.Random;

public class RSA_signature {

    private Random random;
    private BigInteger signature;
    private Message message;

    public RSA_signature(Client client) {
        random = new Random();
        this.message = client.getMessage();
        signature = message.isValid()? calSignature(client) : BigInteger.valueOf(random.nextInt());
    }

    /**
     * calculate signature according to message and private key
     * @param client
     * @return the Encode Signature
     */
    private BigInteger calSignature(Client client) {
        BigInteger[] privateKey = client.getRsAgenerator().getPrivateKey();
        return new BigInteger(Integer.toString(message.getContent())).modPow(privateKey[0],privateKey[1]);
    }

    /**
     * get the signature
     * @return the Encode Signature
     */
    public BigInteger getSignature() {
        return signature;
    }

    /**
     * get the message
     * @return the message
     */
    public Message getMessage() {
        return message;
    }
}
