import gateways.database.MatchingGameHistorySQLDatabase;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.*;

public class MatchingGameHistorySQLDatabaseTest {
    MatchingGameHistorySQLDatabase db;

    String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    String USER = "postgres";
    String PASS = "Password";

    @Before
    public void setUp() {
        db = new MatchingGameHistorySQLDatabase();
    }

    @Test(timeout = 500)
    public void AddGameHistoryTest() throws SQLException {
        // Create a new table called 'GameHistory' in the gateways.database
        db.createTable();
        db.addGameHistory(1, "Jun", 12, 30.5, "easy");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet results = stmt.executeQuery("SELECT * FROM GameHistory");
            while (results.next()) {
                assertEquals("1", results.getString("GID"));
                assertEquals("Jun", results.getString("Username"));
                assertEquals("12", results.getString("TotalMoves"));
                assertEquals("30.5", results.getString("Time"));
                assertEquals("easy", results.getString("UseCase.Difficulty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
