package views;

import gateways.database.UserSQLDatabase;

import java.sql.SQLException;

<<<<<<< HEAD
/**
 * options to log in or sign up
 */
public class LoginOrSignup {
    /**
     *
     * @param UserDatabase the SQL database
     * @return string list of username and password
     * @throws SQLException provides information on a database access error
     */
=======
public class LoginOrSignup {

>>>>>>> origin/junlynli
    public static String[] loginOrSignup(UserSQLDatabase UserDatabase) throws SQLException {
        String input = UserGameInput.promptLoginOrSignup();
        String[] userData = new String[]{};
        if (input.equals("login")) {
            userData = UserLogin.login(UserDatabase);
        }
        else if (input.equals("sign up")) {
            SignUpPage.signUp(UserDatabase);
            DisplayPrompts.loginDisplay();
            userData = UserLogin.login(UserDatabase);
        }
        return userData;
    }

}
<<<<<<< HEAD

=======
>>>>>>> origin/junlynli
