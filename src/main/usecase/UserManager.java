package usecase;

import entity.*;
/**
 * Manages Entity.User Attributes
 * Use Case class
 */
public class UserManager {

    /**
     * @param 'username'
     * @param 'password'
     */
    public static void createUser(String username, String password) {
        User user = new User(username, password);
    }

    /**
     * @param 'user'
     * @param 'the username the user wants'
     */
    public void changeUsername(User user, String new_username) {
        user.setUsername(new_username);
    }

    /**
     * @param 'user'
     * @param 'new_password'
     */
    public void changePassword(User user, String new_password) {
        user.setPassword(new_password);
    }

}
