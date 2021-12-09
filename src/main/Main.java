import entity.User;
import gateways.database.*;
import gateways.database.SQLDatabase;
import views.*;

import java.sql.SQLException;

/**
 * runs the whole program
 */
public class Main {
    private static User user;
    public static void main (String [] args) throws SQLException, InterruptedException {
        user = new User("", "");
        new LoginOrSignupPage(user);
        Thread.sleep(5000);

        }
    }
