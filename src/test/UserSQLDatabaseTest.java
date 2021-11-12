import Database.UserSQLDatabase;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;


public class UserSQLDatabaseTest {
    UserSQLDatabase db;

    @Before
    public void setUp() {
        db = new UserSQLDatabase();
    }

    @Test(timeout = 500)
    public void checkUserAvailableTest() throws SQLException {
        // Create a new table called 'users' in the database
        db.createTable();
        db.addUser("Jun", "1234");
        // username does not exist (is available)
        assertTrue(db.checkUsernameAvailable("Artur"));
        // username exists
        assertFalse(db.checkUsernameAvailable("Jun"));
    }

    @Test(timeout = 500)
    public void checkPasswordTest() throws SQLException {
        db.createTable();
        db.addUser("Jun", "1234");
        // both username and password are correct
        assertTrue(db.checkPassword("Jun", "1234"));
        // username is correct, password is incorrect
        assertFalse(db.checkPassword("Jun", "12345"));
        // username does not exist
        assertFalse(db.checkPassword("Artur", "1234"));
    }

}
