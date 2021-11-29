package views;

import gateways.database.UserSQLDatabase;

import java.sql.SQLException;

public class LoginOrSignup {

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
