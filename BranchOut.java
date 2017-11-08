package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class BranchOut extends RecommendationCriteria {

    public int  recommendFriends(int number, User user) {
        Random random = new Random();
        Map<Integer, User> map = Neighborhood.getUserList();
        int size = map.size();
        if (recommend.containsKey(user)) {
            recommend.put(user, new ArrayList<>());
        }
        for (int i = 0; i < number; i++) {
            User randomPick = map.get(1 + random.nextInt(size));
            if (!recommend.get(user).contains(randomPick)) {
                recommend.get(user).add(randomPick);
                randomPick.increaseRecommendationTimes();
                recommendedUser.add(randomPick);
            }
        }

        return 0;
    }
}
