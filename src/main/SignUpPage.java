import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;

/**
 * Controller Class
 * option to sign up for a new account
 * make sure the username isn't taken
 * add the username and password to the repository
 * Move Username taken check to InfoChecker
 */

public class SignUpPage {

    public void SignUpPage(userSQLDatabase user_database) {
        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];
        // nameValid is false when user name has been taken
        boolean nameValid;
        do {
            System.out.print("Please enter a username: ");
            userInput[0] = s.next();
            // if the username is not available
            if (!user_database.checkUsernameAvailable(userInput[0])) { // move to InfoChecker
                nameValid = false;
                System.out.println("Your username has been taken. Please enter a different username.");
            } else {
                nameValid = true;
            }
        } while (!nameValid);

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        UserManager.createUser(userInput[0], userInput[1]); // call User or UserManager?
    }

}
