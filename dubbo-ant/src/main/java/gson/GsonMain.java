package gson;

import com.google.gson.Gson;
import gson.model.User;
import gson.model.UserList;
import gson.model.UserMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonMain {
    Gson gson = null;

    public static void main(String[] args) {
        GsonMain g1 = new GsonMain();
        g1.init();
        g1.test();
    }

    private void test() {
        userToString();
//        stringToUSer();
//        listUserToString();
//        stringToList();
//        mapToString();
    }

    private void mapToString() {
        Map<String, User> map = new HashMap<String, User>() {{
            put("w1", new User("w1", 11));
            put("w2", new User("w2", 22));
        }};
        UserMap m = new UserMap();
        m.setUserMap(map);
        String s = gson.toJson(m);
        System.out.println(s);
    }

    private void stringToList() {
        String s = "{\"users\":[{\"name\":\"w9\",\"age\":41},{\"name\":\"w3\",\"age\":32}]}";
        UserList list = gson.fromJson(s, UserList.class);
        list.getUsers().forEach(System.out::println);
    }

    /**
     * {"users":[{"name":"w1","age":11},{"name":"w2","age":22}]}
     */
    private void listUserToString() {
        UserList list = new UserList();
        List<User> l = new ArrayList<User>() {
            {
                add(new User("w1", 11));
                add(new User("w2", 22));
            }
        };
        list.setUsers(l);
        String listStr = gson.toJson(list);
        String lStr = gson.toJson(l);
        System.out.println(listStr);
        System.out.println(lStr);

    }

    private void stringToUSer() {
        String p1 = "{\"name\":\"wpp\",\"age\":11}";
        User user = gson.fromJson(p1, User.class);
        System.out.println(user.getName() + ":" + user.getAge());
    }

    private void userToString() {
        User wpp = new User("wpp", 20);
        String s = gson.toJson(wpp);
        System.out.println(s);
    }

    private void init() {
        gson = new Gson();
    }
}
