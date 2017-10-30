package neu.edu.cs5010;

import java.math.BigInteger;

public class RSA_verification {

    private boolean verified;

    public RSA_verification(RSA_signature signature, Client client) {
        verified = verify(signature,client);
    }

    /**
     * use the public key of this client to decode the signature, and compare it with message, return same or not
     * @param signature RSA_signature
     * @param client Client
     * @return boolean
     */
    private boolean verify(RSA_signature signature, Client client) {
        BigInteger[] publicKey = client.getRsAgenerator().getPublicKey();
        BigInteger decode = signature.getSignature().modPow(publicKey[0],publicKey[1]);
        BigInteger message = new BigInteger(Integer.toString(client.getMessage().getContent()));
        return decode.compareTo(message) == 0;
    }

    /**
     * get whether verified
     * @return boolean
     */
    public boolean isVerified() {
        return verified;
    }
}
