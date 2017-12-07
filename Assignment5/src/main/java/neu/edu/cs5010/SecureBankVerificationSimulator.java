package neu.edu.cs5010;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SecureBankVerificationSimulator {

    private Random random;
    private static int transitionNumber;
    private String fileContent;
    private PriorityQueue<Client> queue;
    private Set<Integer> depositRej;
    private Set<Integer> withdrawalRej;
    private Map<BigInteger[],List<Integer>> map;
    private List<List<Integer>> sameKey;
    private Client[] clientArray;
    private int clientUpperBound;
    private int  verifications;
    private double fraction;
    private String outputFileName;

    /**
     * Constructor, initial all the data structure
     */
    public SecureBankVerificationSimulator(){
        transitionNumber = 0;
        random = new Random();
        queue = new PriorityQueue<>(11,
                (a, b) -> (a.getTransitions() - b.getTransitions()));
        depositRej = new HashSet<>();
        withdrawalRej = new HashSet<>();
        map = new HashMap<>();
        sameKey = new ArrayList<>();

    }

    public static void main(String[] args) {
        SecureBankVerificationSimulator simulator = new SecureBankVerificationSimulator();
        simulator.checkArgument(args);
        simulator.simulate();
        simulator.summary();
    }

    /**
     * Check whether the input arguments are vaild
     * @param args input arguments
     */
    private void checkArgument(String[] args) {
        if(args.length != 4) {
            throw new IllegalArgumentException("Please input correct argument");
        }

        clientUpperBound = Integer.parseInt(args[0]);
        verifications = Integer.parseInt(args[1]);
        fraction = Double.parseDouble(args[2]);
        outputFileName = args[3];

        if(clientUpperBound > 50000) {
            throw new IllegalArgumentException("Number of unique bank clients should be less than 50000");
        } if(verifications > 10000) {
            throw new IllegalArgumentException("Number of unique verifications should be less than 10000");
        } if(fraction > 1 || fraction < 0) {
            throw  new IllegalArgumentException("Fraction of invalid messages should be in the range [0-1]");
        } if(outputFileName.length() == 0) {
            throw new IllegalArgumentException("Output file name is not valid");
        }
    }

    /**
     * The main way to simulate. First initial all the client in a array, and for every verification, randomly get
     * a client, generate RSA_signature and RSA_verification, and print out the information in the console
     */
    private void simulate(){

        clientArray = new Client[clientUpperBound];
        for(int i = 0; i < clientUpperBound; i++) {
            Client client = new Client(clientUpperBound, fraction);
            clientArray[i] = client;
        }

        fileContent = "Transaction number, Date, Time, Client ID, Message, Digital signature, " +
                "Verified, Transactions status.";

        for (transitionNumber = 0; transitionNumber < verifications; transitionNumber++) {
            Client client = clientArray[random.nextInt(clientUpperBound)];
            client.setTransitions(client.getTransitions() + 1);
            RSA_signature signature = new RSA_signature(client);
            RSA_verification rsa_verification = new RSA_verification(signature,client);
            fileContent += toString(client,signature,rsa_verification) + System.lineSeparator();

            if (getTransactionsStatus(client,rsa_verification).equals("deposit rejected")) {
                depositRej.add(client.getClientID());
            } else if (getTransactionsStatus(client,rsa_verification).equals("withdrawal rejected")) {
                withdrawalRej.add(client.getClientID());
            }

            BigInteger[] publicKey = client.getRsAgenerator().getPublicKey();
            if (!map.containsKey(publicKey)) {
                map.put(publicKey, new ArrayList<>());
            }
            if (!map.get(publicKey).contains(client.getClientID()))
                map.get(publicKey).add(client.getClientID());
            if (map.get(publicKey).size() > 1)
                sameKey.add(map.get(publicKey));


            queue.offer(client);
            if (queue.size() > 10) {
                queue.poll();
            }

        }
        IOLibrary.write(outputFileName,fileContent);
    }

    /**
     * Print out the summary information
     */
    public void summary() {
        System.out.println("***********************************");
        System.out.println("The number of distinct transactions with different IDs, but the same public key:");
        for (List<Integer> list : sameKey) {
            System.out.println(list);
        }

        System.out.println("Top ten unique users with the largest number of transactions during the simulation");
        for (Client i : queue) {
            System.out.println(i.getClientID());
        }
        System.out.println("List of all IDs with rejected deposit transactions");
        for (int i : depositRej) {
            System.out.println(i);
        }
        System.out.println("List of all IDs with rejected withdrawal transactions");
        for (int i : withdrawalRej) {
            System.out.println(i);
        }
    }
    /**
     * Return Current date in format Month-Day-Year
     * @return Current date
     */
    private String getDate() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        return format.format(date);
    }

    /**
     * get current time in format Hour: Minute: Second
     * @return Current time
     */
    private String getTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    /**
     * First judge weather verification is true, if not, return N/A. Then Compare the number in message
     * and client's limit, return accept or reject
     * @param client Client
     * @param verification verification
     * @return transaction status
     */
    private String getTransactionsStatus(Client client, RSA_verification verification) {
        int number = client.getMessage().getNumber();
        if (!verification.isVerified()) return "N/A";
        if (client.getMessage().getTransactionType() == TransactionType.Deposit) {
            if (number > client.getDepositLimit()) {
                return "deposit rejected";
            } else return "deposit accepted";
        } else {
            if(number > client.getWithdrawLimit()) return "withdrawal rejected";
            else return "withdrawal accepted";
        }

    }

    /**
     * print out the Transaction number, Date, Time, Client ID, Message, Digital signature, Verified,
     * Transactions status, and return them in string
     * @param client Client
     * @param signature RSA_signature
     * @param verification RSA_verification
     * @return a string line contain all information
     */
    public String toString(Client client, RSA_signature signature, RSA_verification verification) {
        StringBuilder sb = new StringBuilder();
        sb.append(transitionNumber).append(", ").append(getDate()).append(", ").append(getTime()).append(", ")
                .append(client.getClientID()).append(", ").append(client.getMessage().getContent()).append(", ")
                .append(signature.getSignature().toString()).append(", ").append(verification.isVerified())
                .append(", ").append(getTransactionsStatus(client,verification));
        System.out.println(sb.toString());
        return sb.toString();
    }
}
