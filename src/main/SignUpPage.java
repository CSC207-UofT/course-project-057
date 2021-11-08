import java.util.Hashtable;
import java.util.Scanner;
import java.io.*;

/**
 * Controller Class
 * option to sign up for a new account
 * make sure the username isn't taken
 * add the username and password to the repository
 */

public class SignUpPage {

    public void SignUpPage(UserDatabase user_database) {

        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];
        boolean nameValid;
        do {
            System.out.print("Please enter a username: ");
            userInput[0] = s.next();
            if (!user_database.checkUsername(userInput[0])) {
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
