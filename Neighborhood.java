package neu.edu.cs5010;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Neighborhood {

    private static Map<User, List<User>> neighborhood;
    private static Map<User, Integer> followers;
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
        initialUserFriend();
    }

    /**
     * get Neighborhood
     * @return Map<User, List<User>> neighborhood
     */
    public static Map<User, List<User>> getNeighborhood() {
        return neighborhood;
    }

    /**
     * get Followers
     * @return  Map<Integer, Integer> followers
     */
    public static Map<User, Integer> getFollowers() {
        return followers;
    }

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
            try {
                user = new User(Integer.parseInt(strs[0]),strs[1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

            if (!neighborhood.containsKey(srcVal)) {
                neighborhood.put(userList.get(srcVal), new ArrayList<>());
            }
            neighborhood.get(srcVal).add(userList.get(desVal));

            followers.put(userList.get(desVal), followers.getOrDefault(userList.get(desVal), 0) + 1);
        }
    }

    public void initialUserFriend() {
        for (User user : userList.values()) {
            user.setNeighbor(neighborhood.get(user));
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
