package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FollowInfluencer extends RecommendationCriteria {

    private int limit;

    public FollowInfluencer(int limit) {
        this.limit = limit;
    }
    /**
     * recommend as friends those nodes that have more than limit of all user
     * @param number the number of recommendation
     * @param user the user
     * @param recommend the recommend information, the key is user, the value is a list of users that we recommend
     *                  to the key user
     * @param recommendedUser a set that contains all users have been recommended
     * @return the left number of recommendation
     */
    public int recommendFriends(int number, User user, Map<User, List<User>> recommend, Set<User> recommendedUser) {

        Map<Integer, User> userList = Neighborhood.getUserList();

        List<User> influenceFriend = new ArrayList<>();

        for (User i : userList.values()) {
            if(i.getFollwers() > limit) {
                influenceFriend.add(i);
            }
        }

        influenceFriend.sort((a,b)->(a.getID() - b.getID()));
        int count = 0;

        if (!recommend.containsKey(user)) {
            recommend.put(user, new ArrayList<>());
        }

        for (User friend : influenceFriend) {
            if (friend.getNeighbor() == null) continue;
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
