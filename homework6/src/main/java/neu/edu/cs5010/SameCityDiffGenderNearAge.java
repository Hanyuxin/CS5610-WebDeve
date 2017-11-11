package neu.edu.cs5010;

import java.util.*;

public class SameCityDiffGenderNearAge extends RecommendationCriteria {

    /**
     * recommend friends that in the same city and the age is not diff than 5 years and with different gender
     * @param number the number of recommendation
     * @param user the user
     * @param recommend the recommend information, the key is user, the value is a list of users that we recommend
     *                  to the key user
     * @param recommendedUser a set that contains all users have been recommended
     * @return the left number of recommendation
     */
    public int recommendFriends(int number, User user, Map<User, List<User>> recommend, Set<User> recommendedUser) {
        Random random = new Random();
        Map<Integer, User> map = Neighborhood.getUserList();
        int size = map.size();
        int i = 0;
        if (!recommend.containsKey(user)) {
            recommend.put(user, new ArrayList<>());
        }
        while (i < number) {
            User randomPick = map.get(1 + random.nextInt(size));
            if(randomPick == null) continue;
            if (!recommend.get(user).contains(randomPick)) {
                if (randomPick.getCity().equals(user.getCity()) && !randomPick.getGender().equals(user.getGender()) &&
                        Math.abs(randomPick.getAge() - user.getAge()) <= 5) {
                    recommend.get(user).add(randomPick);
                    randomPick.increaseRecommendationTimes();
                    recommendedUser.add(randomPick);
                    i++;
                }
            }
        }

        return 0;
    }
}
