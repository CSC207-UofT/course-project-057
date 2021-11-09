import java.util.Scanner;

/**
 * DUPLICATE CLASS
 * Disregard, use "User Login" instead
 **/

public class PromptLogin {
    public String[] login() {
        String [] account = new String[2];
        Scanner scanner = new Scanner(System.in); // scanner
        System.out.println("Please enter your username.");
        account[0] = scanner.nextLine();
        System.out.println("Please enter your password.");
        account[1] = scanner.nextLine();
        return account;
        // add password check
        // Chris and Akansha added a password check in UserLogin
    }
}
