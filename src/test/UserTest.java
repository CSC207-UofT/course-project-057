import entity.User;
import gateways.database.SQLDatabase;
import org.junit.Before;
import org.junit.Test;
import usecase.UserManager;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class UserTest {
    SQLDatabase db;
    UserManager um;

    @Before
    public void setUp() {
        db = new SQLDatabase();
        um = new UserManager(db);
    }


    @Test(timeout = 500)
    public void checkUserAvailableTest() throws SQLException {
        db.createUserTable();
        User user = new User("Jun", "1234");
        um.createUser(user);
        // username does not exist (is available)
        assertTrue(um.checkUsernameAvailable("Artur"));
        // username exists
        assertFalse(um.checkUsernameAvailable("Jun"));
    }

    @Test(timeout = 500)
    public void checkPasswordTest() throws SQLException {
        db.createUserTable();
        User user = new User("Jun", "1234");
        um.createUser(user);
        // both username and password are correct
        assertTrue(um.checkPassword("Jun", "1234"));
        // username is correct, password is incorrect
        assertFalse(um.checkPassword("Jun", "12345"));
        // username does not exist
        assertFalse(um.checkPassword("Artur", "1234"));
    }

}
