package gateways.database;

import usecase.IDatabaseConnection;

import java.sql.*;

public class SQLDatabase implements IDatabaseConnection {
    /**
     * Holds usernames and password of every User object
     */
    private final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    private final String USER = "postgres";
    private final String PASS = "Password";

    Connection conn = null;

    @Override
    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Creates a table named 'users' in the local database
     * @throws SQLException provides information on a database access error
     */
    public void createUserTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {
            // Removes the table 'users' if it already exists in the gateways.database
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            // Create a table called 'users' that stores user information
            stmt.executeUpdate("CREATE TABLE users (Username TEXT NOT NULL, Password TEXT, PRIMARY KEY (Username))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * This method creates a new table 'MatchingGameHistory'
     * @throws SQLException provides information on a database access error
     */
    public void createMatchingGameTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) {
            // Removes the table 'GameHistory' if it already exists in the gateways.database
            stmt.executeUpdate("DROP TABLE IF EXISTS MatchingGameHistory");
            // Create a table called 'GameHistory' that stores user information
            stmt.executeUpdate("CREATE TABLE MatchingGameHistory (GID INT NOT NULL, Username TEXT, TotalMoves INT, " +
                    "Time FLOAT, Difficulty TEXT, PRIMARY KEY (GID))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    /**
     * This method creates a new table 'PatternGameHistory'
     * @throws SQLException provides information on a database access error
     */
    public void createPatternTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();) {
            // Removes the table 'GameHistory' if it already exists in the gateways.database
            stmt.executeUpdate("DROP TABLE IF EXISTS PatternGameHistory");
            // Create a table called 'GameHistory' that stores user information
            stmt.executeUpdate("CREATE TABLE PatternGameHistory (GID INT NOT NULL, Username TEXT, " +
                    "Time FLOAT, Difficulty TEXT, PRIMARY KEY (GID))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }
}
