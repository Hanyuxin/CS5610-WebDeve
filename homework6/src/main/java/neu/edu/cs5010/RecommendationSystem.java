package neu.edu.cs5010;

import java.util.*;
import java.util.regex.Pattern;

public class RecommendationSystem {

    private String nodeFileName;
    private String edgeFileName;
    private String outputFileName;
    private String processingFlag = "s";
    private int numberOfUsersToProcess = 100;
    private int numberOfRecommendations = 15;
    private RecommendationCriteria[] criteria;
    private Random random;
    private Map<User, List<User>> recommend;
    private Set<User> recommendedUser;
    private StringBuilder outputString;
    private int limit;


    /**
     * Constructor, initial recommendation criteria array and the output map and the user be recommended
     */
    public RecommendationSystem(){
        random = new Random();
        recommend = new HashMap<>();
        recommendedUser = new HashSet<>();
        outputString = new StringBuilder();
    }

    /**
     * the main function to accept argument and run
     * @param args node csv  file name(mandatory), edge csv file name(mandatory), output file name(mandatory),
     *            processingFlag, numberOfUsersToProcess, numberOfRecommendations
     */
    public static void main( String[] args ) {
        RecommendationSystem system = new RecommendationSystem();
        system.checkArgument(args);
        system.run();
        system.write();
    }

    private void checkArgument(String[] args) {
        if (args.length < 3) throw new IllegalArgumentException("Not Enough Argument");

        if(!args[0].contains(".csv") || !args[1].contains(".csv") || !args[2].contains(".csv")) {
            throw new IllegalArgumentException("Please check your Argument");
        } else {
            nodeFileName = args[0];
            edgeFileName = args[1];
            outputFileName = args[2];
        }

        if(args[0].contains("small")) limit = 25;
        else limit = 250;

        Pattern flagPattern = Pattern.compile("[s|e|r]");
        Pattern  intergerPattern = Pattern.compile("[0-9]+");

        if (flagPattern.matcher(args[3]).matches()) {
            processingFlag = args[3];
            if (args.length == 5) numberOfUsersToProcess = Integer.parseInt(args[4]);
            else if (args.length == 6) {
                numberOfUsersToProcess = Integer.parseInt(args[4]);
                numberOfRecommendations = Integer.parseInt(args[5]);
            }
        } else if(intergerPattern.matcher(args[3]).matches()) {
            numberOfUsersToProcess = Integer.parseInt(args[3]);
            if(args.length == 5) {
                numberOfRecommendations = Integer.parseInt(args[4]);
            }
        }
    }

    /**
     * according to processingFlag, if it is "s", start from the smallest one, if it is "end", start from the largest
     * one, if it is "r", randomly pick one index.
     */
    private void run() {
        criteria = new RecommendationCriteria[] {new NewbiesMimic(), new FriendOfFriend(),
                new FollowInfluencer(limit), new BranchOut()};
//        Bonus part
//        criteria = new RecommendationCriteria[] {new NewbiesMimic(), new FriendOfFriend(),
//                new FollowInfluencer(limit), new SameCityDiffGenderNearAge()};
        Neighborhood neighborhood = new Neighborhood(nodeFileName, edgeFileName);
        int size = Neighborhood.getUserList().size();
        switch (processingFlag) {
            case "s" :
                for (int i = 1; i <= numberOfUsersToProcess; i++) {
                    recommendation(Neighborhood.getUserList().get(i));
                    output(Neighborhood.getUserList().get(i));
                }
                break;
            case "e":
                for (int i =  size; i > size - numberOfUsersToProcess; i--) {
                    recommendation(Neighborhood.getUserList().get(i));
                    output(Neighborhood.getUserList().get(i));
                }
                break;
            case "r":
                List<Integer> used = new ArrayList<>();
                for(int i = 0; i < numberOfUsersToProcess; i++) {
                    int index = randomIndex(used,size);
                    User user = Neighborhood.getUserList().get(index);
                    if(user == null) {
                        i--;
                        continue;
                    }
                    recommendation(user);
                    used.add(user.getID());
                    output(user);
                }
        }
        frequentlyRecommended();
    }

    /**
     * in the order of  NewbiesMimic, FriendOfFriend, FollowInfluencer, BranchOut to recommend friend.
     * @param user the user to recommend
     */
    private void recommendation(User user) {
        int remain = numberOfRecommendations;
        int index = 0;
        if (!user.isNew()) {
            index = 1;
        }
        while (remain != 0) {
            remain = criteria[index].recommendFriends(remain, user, recommend, recommendedUser);
            index++;
        }
    }

    /**
     * use reservoid sampling to pick the random index
     * @param used the list of user that already recommend
     * @param n the total number of user
     * @return the random index of user
     */
    private int randomIndex(List<Integer> used, int n) {
        int res = 0;
        int count = 0;
        for(int i = 1; i<= n ;i++) {
            if(!used.contains(i)) {
                count++;
                if(random.nextInt(count) == 0)
                    res = i;
            }
        }
        return res;
    }

    /**
     * sort all the user that been recommend by their recommend times in descdending ways. And output to the
     * console the top ten one
     */
    private void frequentlyRecommended() {
        List<User> list = new ArrayList<>(recommendedUser);
        list.sort((a,b) -> (b.compareTo(a)));
        for (int i = 0; i < 10 && i< list.size(); i++) {
            System.out.println("No." + i + " " + list.get(i).toConsoleString());
        }
    }

    /**
     * add line to the output file
     * @param user the User that we recommend friends to
     */
    private void output(User user) {

        outputString.append(user.getID());
        outputString.append(", ");
        outputString.append(recommend.get(user));
        outputString.append(System.lineSeparator());
    }

    /**
     * Write information to the output file
     */
    private void write() {
        IOLibrary.write(outputFileName,outputString.toString());
    }
}
