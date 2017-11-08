package neu.edu.cs5010;

import java.util.regex.Pattern;

public class RecommendationSystem {

    private String nodeFileName;
    private String edgeFileName;
    private String outputFileName;
    private String processingFlag;
    private int numberOfUsersToProcess = 100;
    private int numberOfRecommendations = 15;


    public static void main( String[] args ) {

    }

    private boolean checkArgument(String[] args) {
        if (args.length < 3) throw new IllegalArgumentException("Not Enough Argument");

        if(!args[0].contains(".csv") || !args[1].contains(".csv") || !args[2].contains(".csv")) {
            throw new IllegalArgumentException("Please check your Argument");
        }

        Pattern flagPattern = Pattern.compile("[s|e|r]");
        Pattern
    }
}
