package usecase;

import entity.*;
import java.sql.*;

/**
 * Manages Entity.User Attributes
 * Use Case class
 */
public class UserManager{
    private final IDatabaseConnection database;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public UserManager(IDatabaseConnection database) {
        this.database = database;
    }

    private Connection getConnection() throws SQLException {
        return this.database.getConnection();
    }

    /**
     * This method inserts the username and password into the table 'users'.
     * @param user A user object
     * @throws SQLException provides information on a database access error
     */
    public void createUser(User user) throws SQLException{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            // Insert username into the table 'users'
            String username = user.getUsername();
            String password = user.getPassword();
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
    public boolean checkUsernameAvailable(String new_username) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();){
            // Check for new_username in table 'users'
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + new_username + "'");
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
    public boolean checkPassword(String username, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement();){
            // Check for new_username and password in table 'users'
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


    /**
     * @param user a User object
     * @param new_username the new username the user wants
     */
    public void changeUsername(User user, String new_username) {
        user.setUsername(new_username);
    }

    /**
     * @param user a User object
     * @param new_password the new password the user wants
     */
    public void changePassword(User user, String new_password) {
        user.setPassword(new_password);
    }

}
