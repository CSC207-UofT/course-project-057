package views;

import gateways.database.UserSQLDatabase;

import java.util.Scanner;

/**
 * User can log in with correct combination of username and password
 * Checks if username and password matches from database
 */
public class UserLogin {

    /**
     * login page
     * @param user_database the SQL database
     * @return a string list of username and password
     */
    public static String[] login(UserSQLDatabase user_database) {
        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];

        System.out.print("Please enter your username: ");
        userInput[0] = s.next();

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        if (!user_database.checkUsernameAvailable(userInput[0]) &&
                user_database.checkPassword(userInput[0], userInput[1])) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login unsuccessful, please re-enter your criteria.");
            login(user_database);
        }
        return userInput;
    }
}
