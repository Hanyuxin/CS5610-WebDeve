package neu.edu.cs5010;

import java.util.*;

public class BranchOut extends RecommendationCriteria {

    /**
     * randomly proposing some nodes from the network as potential friends, until the required number
     * of recommendations for a user is reached.
     * @param number the number of recommendation
     * @param user the user
     * @param recommend the recommend information, the key is user, the value is a list of users that we recommend
     *                  to the key user
     * @param recommendedUser a set that contains all users have been recommended
     * @return the left number of recommendation
     */
    public int  recommendFriends(int number, User user, Map<User, List<User>> recommend, Set<User> recommendedUser) {
        Random random = new Random();
        Map<Integer, User> map = Neighborhood.getUserList();
        int size = map.size();
        if (recommend.containsKey(user)) {
            recommend.put(user, new ArrayList<>());
        }
        for (int i = 0; i < number; i++) {
            User randomPick = map.get(1 + random.nextInt(size));
            if(random == null) continue;
            if (!recommend.get(user).contains(randomPick)) {
                recommend.get(user).add(randomPick);
                randomPick.increaseRecommendationTimes();
                recommendedUser.add(randomPick);
            }
        }

        return 0;
    }
}
