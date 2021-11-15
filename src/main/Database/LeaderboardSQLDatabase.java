package Database;

import java.sql.*;
import java.util.ArrayList;

/**
 * database class
 * displays leaderboards separated by difficulty, separated by buttons on top
 * buttons on the bottom to play again or exit game
 */

public class LeaderboardSQLDatabase  {
    private final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    private final String USER = "postgres";
    private final String PASS = "Password";

    Connection conn = null;
    ResultSet rs = null;

    public static void generateLeaderboard(String Difficulty) throws SQLException {
        ArrayList<String> leaderboard_list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {
            // updates a leaderboard according to the given difficulty
            ResultSet rs = stmt.executeQuery("SELECT DENSE_RANK AS rank, username, totalmoves, time FROM " +
                    "(SELECT *, DENSE_RANK() OVER(ORDER BY totalmoves, time) FROM gamehistory WHERE difficulty = '" +
                    Difficulty + "') AS t1 WHERE DENSE_RANK <= 10 ORDER BY DENSE_RANK;");
            while (rs.next()) {
                String Rank = rs.getString("rank");
                String Username = rs.getString("Username");
                String TotalMoves = rs.getString("totalmoves");
                String Time = rs.getString("Time");
                System.out.println("Rank: " + Rank + ", Username: " + Username
                        + ", TotalMoves: " + TotalMoves + ", Time: " + Time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    public static void main (String[] args) throws SQLException {
        //TODO: Test methods
        String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
        String USER = "postgres";
        String PASS = "Password";


        GameHistorySQLDatabase db = new GameHistorySQLDatabase();
        db.createTable();
        db.addGameHistory(1, "Jun", 12, 30.5, "easy");
        db.addGameHistory(2, "Akansha", 20, 50.0, "medium");
        db.addGameHistory(3, "Jun", 10, 29.0, "easy");
        db.addGameHistory(4, "Akansha", 10, 29.0, "easy");
        db.addGameHistory(5, "Chris", 12, 60.4, "hard");
        db.addGameHistory(6, "Chris", 16, 62.4, "hard");
        db.addGameHistory(7, "Jun", 26, 29.4, "hard");
        db.addGameHistory(8, "Akansha", 9, 30.2, "easy");
        db.addGameHistory(9, "Iris", 30, 20.2, "medium");
        db.addGameHistory(10, "Koji", 30, 30.2, "medium");

        LeaderboardSQLDatabase lb = new LeaderboardSQLDatabase();
        System.out.println("easy");
        lb.generateLeaderboard("easy");
        System.out.println("medium");
        lb.generateLeaderboard("medium");
        System.out.println("hard");
        lb.generateLeaderboard("hard");
    }
}
