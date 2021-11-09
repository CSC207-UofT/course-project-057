import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;

/**
 * Use this instead of LoginPage and PromptLogin
 * Move password check to InfoChecker
 */

public class UserLogin {

    public boolean UserLogin(UserDatabase user_database) {

        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];

        System.out.print("Please enter your username: ");
        userInput[0] = s.next();

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        // move to InfoChecker
        if (user_database.checkUsername(userInput[0]) && user_database.checkPassword(userInput[1])) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login unsuccessful, please re-enter your criteria.");
            UserLogin(user_database);
        }
        return true;
    }

}
