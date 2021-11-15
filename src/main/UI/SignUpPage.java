package UI;

import Database.UserSQLDatabase;
import UseCase.UserManager;

import java.util.Scanner;

/**
 * UI Class
 * option to sign up for a new account
 * make sure the username isn't taken
 * add the username and password to the repository
 * Move Username taken check to InfoChecker
 */

public class SignUpPage {

    public static void signUp(UserSQLDatabase user_database) {
        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];
        // nameValid is false when user name has been taken
        boolean nameValid;
        do {
            System.out.print("Please enter a username: ");
            userInput[0] = s.next();
            // if the username is not available
            if (!user_database.checkUsernameAvailable(userInput[0])) {
                nameValid = false;
                System.out.println("Your username has been taken. Please enter a different username.");
            } else {
                nameValid = true;
            }
        } while (!nameValid);

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        UserManager.createUser(userInput[0], userInput[1]); // call Entity.User or UseCase.UserManager?
    }

}
