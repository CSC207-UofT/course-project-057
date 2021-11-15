package UI;

import Database.UserSQLDatabase;

import java.util.Scanner;

/**
 * Use this instead of LoginPage and PromptLogin
 * Move password check to InfoChecker
 */

public class UserLogin {

    public boolean UserLogin(UserSQLDatabase user_database) {

        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];

        System.out.print("Please enter your username: ");
        userInput[0] = s.next();

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        // move to InfoChecker
        if (user_database.checkUsernameAvailable(userInput[0]) &&
                user_database.checkPassword(userInput[0], userInput[1])) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login unsuccessful, please re-enter your criteria.");
            UserLogin(user_database);
        }
        return true;
    }
}