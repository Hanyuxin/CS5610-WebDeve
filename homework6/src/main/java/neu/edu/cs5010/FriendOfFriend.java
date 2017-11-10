package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendOfFriend extends RecommendationCriteria{

    /**
     * find all of the friends of A, and find their friends, say some nodes in set B. Then recommend as friends of A
     * those nodes from B that are not already friends of A. If, there are too many possible recommendations, starting
     * from the candidate node with the smallest node ID.
     * @param number the number of recommendation
     * @param user the user
     * @param recommend the recommend information, the key is user, the value is a list of users that we recommend
     *                  to the key user
     * @param recommendedUser a set that contains all users have been recommended
     * @return the left number of recommendation
     */
    public int recommendFriends(int number, User user, Map<User, List<User>> recommend, Set<User> recommendedUser) {
        if(!Neighborhood.getUserList().containsKey(user.getID())) return number;

        int count = 0;
        List<User> friendList = user.getNeighbor();
        if(friendList == null) return number;
        friendList.sort((a,b) -> (a.getID() - b.getID()));
        if (!recommend.containsKey(user)) {
            recommend.put(user,new ArrayList<>());
        }

        for (User friend : friendList) {
            if(friend.getNeighbor() == null) continue;
            for (User friendOfFriend : friend.getNeighbor()) {
                if (count >= number) return 0;
                if (!recommend.get(user).contains(friendOfFriend)) {
                    recommend.get(user).add(friendOfFriend);
                    friendOfFriend.increaseRecommendationTimes();
                    recommendedUser.add(friendOfFriend);
                    count++;
                }
            }
        }

        return number - count;
    }
}
