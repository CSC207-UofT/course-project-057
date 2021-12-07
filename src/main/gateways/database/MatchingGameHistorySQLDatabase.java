package gateways.database;

import java.sql.*;

// inheritance -> if it doesn't work, put in design doc

/**
 * Database class
 * Holds all games played (Game ID, username, total moves, time, difficulty)
 */
public class MatchingGameHistorySQLDatabase {

    private final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    private final String USER = "postgres";
    private final String PASS = "Password";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;


    /**
     * This method adds a game record to the game history database
     * @throws SQLException provides information on a database access error
     */
    public void addGameHistory(Integer GID, String Username, Integer TotalMoves, Double Time, String Difficulty)
            throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Insert game info to the table 'GameHistory'
            stmt.executeUpdate("INSERT INTO MatchingGameHistory VALUES" + "('" + GID +
                    "', '" + Username + "', '" + TotalMoves + "', '" + Time + "', '" + Difficulty + "')" );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }
}
