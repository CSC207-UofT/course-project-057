package UseCase;

import Entity.*;

/**
 * Manages Entity.User Attributes
 * Use Case class
 */

public class UserManager {
    public static void createUser(String username, String password) { // this is to add to database
        User user = new User(username, password);
    }

    public void changeUsername(User user, String new_username) {
        user.setUsername(new_username);
    }

    public void changePassword(User user, String new_password) {
        user.setPassword(new_password);
    }

}
