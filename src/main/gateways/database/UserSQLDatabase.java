package gateways.database;

import usecase.IDatabaseConnection;

import java.sql.*;

public class UserSQLDatabase implements IDatabaseConnection {
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
     *
     * @throws SQLException provides information on a database access error
     */
    public void createTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
}
