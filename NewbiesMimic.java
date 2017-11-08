package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;

public class NewbiesMimic extends RecommendationCriteria{

    public NewbiesMimic() {

    }

    /**
     * find that user's friend with the largest number of friends, max. Then recommend as friends of user all of the
     * friends of max, that are not already friends of A
     * @param number the number of recommendation
     * @param user the user
     * @return the left number of recommendation
     */
    public int recommendFriends(int number, User user){
        if (!Neighborhood.getNeighborhood().containsKey(user)) return number;

        List<User> friendList = user.getNeighbor();
        User max = null;
        int len = 0;
        int count = 0;
        for (User i : friendList) {
            if (Neighborhood.getNeighborhood().get(i).size() > len) {
                max = i;
                len = Neighborhood.getNeighborhood().get(i).size();
            }
        }
        List<User> maxFriend = Neighborhood.getNeighborhood().get(max);
        if (!recommend.containsKey(user)) {
                recommend.put(user, new ArrayList<>());
        }

        for (int i = 0; i < number; i++) {
            if (!recommend.get(user).contains(maxFriend.get(i))) {
                recommend.get(user).add(maxFriend.get(i));
                maxFriend.get(i).increaseRecommendationTimes();
                recommendedUser.add(maxFriend.get(i));
                count++;
            }
        }

        return 0;
    }

}
