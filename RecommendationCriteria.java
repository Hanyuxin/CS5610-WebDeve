package neu.edu.cs5010;

import java.util.*;

public class RecommendationCriteria {
    protected Map<User, List<User>> recommend = new HashMap<>(); // the output content
    protected Set<User> recommendedUser = new HashSet<>();

    public Map<User, List<User>>  getRecommend() {
        return recommend;
    }

    public Set<User> getRecommendedUser() {
        return recommendedUser;
    }
}
