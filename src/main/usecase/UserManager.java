package usecase;

import entity.*;
/**
 * Manages Entity.User Attributes
 * Use Case class
 */
public class UserManager {

    /**
     * @param username username of the user
     * @param password password of the user
     */
    public static void createUser(String username, String password) {
        User user = new User(username, password);
    }

    /**
     * @param user a User object
     * @param new_username the new username the user wants
     */
    public void changeUsername(User user, String new_username) {
        user.setUsername(new_username);
    }

    /**
     * @param user a User object
     * @param new_password the new password the user wants
     */
    public void changePassword(User user, String new_password) {
        user.setPassword(new_password);
    }

}
