import java.util.*;
import java.sql.*;


/*** MOVED TO USERSQLDATABASE CLASS BECAUSE WE ARE USING SQL NOW!!!
 ***/
public class UserDatabase {

    Hashtable<String, String> userDatabase = new Hashtable<String, String>();

    public void addToDatabase(User user) {
        userDatabase.put(user.getUsername(), user.getPassword());
    }

    public void removeFromDatabase(User user) {
        userDatabase.remove(user.getUsername());
    }

    public boolean checkUsername (String new_username) { // used in sign up to see if username exists
        Set<String> usernames = userDatabase.keySet();
        for (String key : usernames) {
            if (new_username.equals(key)) {
                return false;
            }
        }
        return true;
    }


    public boolean checkPassword (String new_password) { // used in login to confirm if user is inputting correct password
        Set<String> usernames = userDatabase.keySet();
        for (String key : usernames) {
            if (new_password.equals(userDatabase.get(key))) {
                return false;
            }
        }
        return true;
    }


}
