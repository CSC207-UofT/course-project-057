import java.sql.*;

public class GameHistorySQLDatabase {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    private final String USER = "postgres";
    private final String PASS = "Password";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    // This method creates a new table 'GameHistory'
    public void createTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {
            // Removes the table 'GameHistory' if it already exists in the database
            stmt.executeUpdate("DROP TABLE IF EXISTS GameHistory");
            // Create a table called 'GameHistory' that stores user information
            stmt.executeUpdate("CREATE TABLE GameHistory (GID INT, Username TEXT, TotalMoves INT, Time FLOAT, " +
                    "Difficulty TEXT, PRIMARY KEY (GID))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    // This method adds a game record to the game history
    public void AddGameHistory(Integer GID, String Username, Integer TotalMoves, Double Time, String Difficulty)
            throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Insert game info to the table 'GameHistory'
            stmt.executeUpdate("INSERT INTO GameHistory VALUES" + "('" + GID +
                    "', '" + Username + "', '" + TotalMoves + "', '" + Time + "', '" + Difficulty + "')" );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    public static void main (String[] args) throws SQLException {
        //TODO: Test for the methods, we can move it test classes later!!!!!!!!
        String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
        String USER = "postgres";
        String PASS = "Password";


        GameHistorySQLDatabase db = new GameHistorySQLDatabase();
        db.createTable();
        db.AddGameHistory(1, "Jun", 12, 30.5, "easy");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            ResultSet results = stmt.executeQuery("SELECT * FROM GameHistory");
            while (results.next()) {
                String gid = results.getString("GID");
                String Username = results.getString("Username");
                String Time = results.getString("Time");
                System.out.println("GID: " + gid + ", Username: " + Username
                        + ", Time: " + Time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
