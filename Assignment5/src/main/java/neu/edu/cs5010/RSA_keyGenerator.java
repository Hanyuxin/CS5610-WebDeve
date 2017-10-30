package neu.edu.cs5010;

import java.math.BigInteger;
import java.util.Random;

public class RSA_keyGenerator {

    private BigInteger n;
    private BigInteger a;
    private BigInteger d;
    private Random random;
    private int bitLength = 32;

    /**
     * first generate two  distinct primes p and q, then compute n = p*q and thi = (p-1)*(q-1), and then find
     * a that satisfy  gcd (a, n) = 1 and gcd (a, thi(n)) = 1, and then find b that satisfy  ab ≡ 1 mod φ(n)
     */
    public RSA_keyGenerator() {
        random = new Random();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = p;
        while(p==q) {
            q = BigInteger.probablePrime(bitLength, random);
        }
        n = p.multiply(q);
        BigInteger thi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        a = BigInteger.probablePrime(bitLength, random);
        while (thi.gcd(a).compareTo(BigInteger.ONE) > 0 && a.compareTo(thi) < 0
                && n.gcd(a).compareTo(BigInteger.ONE) > 0 && a.compareTo(n) < 0) {
            a.add(BigInteger.ONE);
        }
        d = a.modInverse(thi);
    }

    /**
     * get the public key
     * @return public Key
     */
    public BigInteger[] getPublicKey() {
        return new BigInteger[]{a,n};
    }

    /**
     * get the private Key
     * @return private Key
     */
    public BigInteger[] getPrivateKey() {
        return new BigInteger[]{d,n};
    }
}
