package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NewbiesMimic extends RecommendationCriteria{

    /**
     * find that user's friend with the largest number of friends, max. Then recommend as friends of user all of the
     * friends of max, that are not already friends of A
     * @param number the number of recommendation
     * @param user the user
     * @param recommend the recommend information, the key is user, the value is a list of users that we recommend
     *                  to the key user
     * @param recommendedUser a set that contains all users have been recommended
     * @return the left number of recommendation
     */
    public int recommendFriends(int number, User user, Map<User, List<User>> recommend, Set<User> recommendedUser){
        if (!Neighborhood.getUserList().containsKey(user.getID())) return number;

        List<User> friendList = user.getNeighbor();
        User max = null;
        int len = 0;
        int count = 0;
        for (User i : friendList) {
            if (i.getNeighbor() == null) continue;
            if (i.getNeighbor().size() > len) {
                max = i;
                len = i.getNeighbor().size();
            }
        }
        List<User> maxFriend = max.getNeighbor();
        if (!recommend.containsKey(user)) {
                recommend.put(user, new ArrayList<>());
        }

        for (int i = 0; i < number; i++) {
            if(count >= number) return 0;
            if (!recommend.get(user).contains(maxFriend.get(i))) {
                recommend.get(user).add(maxFriend.get(i));
                maxFriend.get(i).increaseRecommendationTimes();
                recommendedUser.add(maxFriend.get(i));
                count++;
            }
        }

        return number - count;
    }

}
