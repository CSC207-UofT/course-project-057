package gateways.database;

import java.sql.*;

public class UserSQLDatabase {
    /**
     * Holds usernames and password of every User object
     */
    private final String DB_URL = "jdbc:postgresql://localhost:5432/group57database";
    private final String USER = "postgres";
    private final String PASS = "Password";

    Connection conn = null;
    ResultSet rs = null;

    /**
     * Creates a table named 'users' in the local database
     * @throws SQLException provides information on a database access error
     */
    public void createTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {
            // Removes the table 'users' if it already exists in the gateways.database
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            // Create a table called 'users' that stores user information
            stmt.executeUpdate("CREATE TABLE users (Username TEXT NOT NULL, Password TEXT, PRIMARY KEY (Username))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    /**
     * This method inserts the username and password into the table 'users'.
     * @param username the username of the user to be added
     * @param password the password of the user to be added
     * @throws SQLException provides information on a database access error
     */
    public void addUser(String username, String password) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Insert username into the table 'users'
            stmt.executeUpdate("INSERT INTO users (username, password) VALUES" + "('" + username +
                    "', '" + password + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    /**
     * checks if there is no existing username in the gateways.database
     * @param new_username username being passed in
     * @return if username is available
     */

    public boolean checkUsernameAvailable (String new_username) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Check for new_username in table 'users'
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + new_username + "'");
            // if the resultset is empty:
            return !rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return false;
    }

    /** checks if user's credentials are correct
     * @param username the username of the user
     * @param password the password of the password
     * @return boolean that indicates if the credentials are correct
     */
    public boolean checkPassword (String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Check for new_username in table 'users'
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username +
                    "' AND password = '" + password + "'" );
            // if the result set is empty:
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
        return false;
    }
}
