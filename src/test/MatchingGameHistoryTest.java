import entity.User;
import gateways.database.SQLDatabase;
import org.junit.Before;
import org.junit.Test;
import usecase.GameStatManager;

import java.sql.*;

import static org.junit.Assert.*;

public class MatchingGameHistoryTest {
    SQLDatabase db;
    GameStatManager gm;

    @Before
    public void setUp() {
        db = new SQLDatabase();
        gm = new GameStatManager(db);
    }

    @Test(timeout = 500)
    public void AddGameHistoryTest() throws SQLException {
        final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
        final String USER = "postgres";
        final String PASS = "Password";
        // Create a new table called 'MatchingGameHistory' in the gateways.database
        db.createMatchingGameTable();
        User user = new User("Jun", "1234");
        user.setNumMove(12);
        user.setTime(30);
        user.setDifficulty("easy");
        gm.addMatchingGameHistory(user);
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM MatchingGameHistory");
            while (results.next()) {
                assertEquals("Jun", results.getString("Username"));
                assertEquals("12", results.getString("TotalMoves"));
                assertEquals("30", results.getString("Time"));
                assertEquals("easy", results.getString("Difficulty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
