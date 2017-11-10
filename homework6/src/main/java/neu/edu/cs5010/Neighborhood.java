package neu.edu.cs5010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neighborhood {

    private Map<User, List<User>> neighborhood;
    private Map<User, Integer> followers;
    private static Map<Integer, User> userList;

    /**
     * the Constructor, initial neighborhood and followers, and set up
     * @param edgeFileName the file name of edge
     */
    public Neighborhood(String nodeFileName, String edgeFileName) {
        neighborhood = new HashMap<>();
        followers = new HashMap<>();
        userList = new HashMap<>();
        initialUserList(nodeFileName);
        setUp(edgeFileName);
        setUserVariable();
    }

    /**
     * get Neighborhood
     * @return Map<User, List<User>> neighborhood
     */
    public Map<User, List<User>> getNeighborhood() {
        return neighborhood;
    }

    /**
     * get Followers
     * @return  Map<Integer, Integer> followers
     */
    public Map<User, Integer> getFollowers() {
        return followers;
    }

    /**
     * get userList
     * @return  Map<Integer, User> userList
     */
    public static Map<Integer, User> getUserList() {
        return userList;
    }

    /**
     * read content from edgeFileName, and store the information in a list
     * @param nodeFileName the file name of node
     */
    private void initialUserList(String nodeFileName){
        List<String> nodes = IOLibrary.read(nodeFileName);

        for(int i = 1; i < nodes.size(); i++) {
            String[] strs = nodes.get(i).split(",");
            User user = null;
            user = new User(Integer.parseInt(strs[0]),strs[1], strs[2], Integer.parseInt(strs[3]),strs[4]);
            userList.put(user.getID(),user);
        }
    }

    /**
     * read content from edgeFileName, and store the information in neighborhood map, the key is User ID,
     * the value is a list of this User's follows.
     * @param edgeFileName the file name of edge
     */
    private void setUp(String edgeFileName) {
        List<String> list = IOLibrary.read(edgeFileName);

        int src, des;
        String[] strs = list.get(0).split(",");
        if (strs[0].equals("Source")) {
            src = 0;
            des = 1;
        } else {
            src = 1;
            des = 0;
        }

        for (int i = 1; i < list.size(); i++) {
            strs = list.get(i).split(",");
            int srcVal = Integer.parseInt(strs[src]);
            int desVal = Integer.parseInt(strs[des]);

            if(!userList.containsKey(srcVal)) {
                System.out.println("Error : there not exist user " + srcVal);
                continue;
            }

            if(!userList.containsKey(desVal)) {
                System.out.println("Error : there not exist user " + desVal);
                continue;
            }
            if (!neighborhood.containsKey(userList.get(srcVal))) {
                neighborhood.put(userList.get(srcVal), new ArrayList<>());
            }

            neighborhood.get(userList.get(srcVal)).add(userList.get(desVal));

            followers.put(userList.get(desVal), followers.getOrDefault(userList.get(desVal), 0) + 1);
        }
    }

    /**
     * for every user, set his neighbor and the number of people that follow this user
     */
    public void setUserVariable() {
        for (User user : userList.values()) {
            user.setNeighbor(neighborhood.get(user));
            user.setFollwers(followers.get(user));
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
