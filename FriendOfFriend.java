package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.List;

public class FriendOfFriend extends RecommendationCriteria{

    public int recommendFriends(int number, User user) {
        if(!Neighborhood.getNeighborhood().containsKey(user)) return number;

        int count = 0;
        List<User> friendList = user.getNeighbor();
        friendList.sort((a,b) -> (a.getID() - b.getID()));
        if (!recommend.containsKey(user)) {
            recommend.put(user,new ArrayList<>());
        }

        for (User friend : friendList) {
            for (User friendOfFriend : Neighborhood.getNeighborhood().get(friend)) {
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
