package nl.bioinf.meggink.webbased.model;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    public static List<User> createDatabase (){
        List<User> users = new ArrayList<>();

        User user1 = new User("Tom", "tom");
        users.add(user1);

        User user2 = new User("Kees", "kees");
        users.add(user2);

        User user3 = new User("Lisa", "lisa");
        users.add(user3);

        return users;
    }

    public static boolean authenticate(String username, String password){
        List<User> users = createDatabase();
        for (User user: users){
            if (username.equals(user.getUsername())){
                return password.equals(user.getPassword());
            }
            else {
                return false;
            }
        }
        return false;
    }
}
