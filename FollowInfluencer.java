package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FollowInfluencer extends RecommendationCriteria {

    public int recommendFriends(int number, User user, int limit) {

        Map<User,Integer> followers = Neighborhood.getFollowers();

        List<User> influenceFriend = new ArrayList<>();

        for (User i : followers.keySet()) {
            if(followers.get(i) > limit) {
                influenceFriend.add(i);
            }
        }

        influenceFriend.sort((a,b)->(a.getID() - b.getID()));
        int count = 0;

        for (User friend : influenceFriend) {
            for (User friendOfFriend : friend.getNeighbor()) {
                if(count >= number) return 0;
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
