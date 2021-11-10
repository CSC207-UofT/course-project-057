import java.sql.*;

public class UserSQLDatabase {
    String DB_URL = "jdbc:postgresql://localhost:5432/userdatabase";
    String USER = "postgres";
    String PASS = "Password";

    public void createTable() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();) {
            // Removes the table 'users' if it already exists in the database
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            // Create a table called 'users' that stores user information
            stmt.executeUpdate("CREATE TABLE users (Username TEXT, Password TEXT, PRIMARY KEY (Username))");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // This method inserts the username and password into the sql table.
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
        }
    }

    // checks if there is no existing username in the database
    public boolean checkUsernameAvailable (String new_username) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();){
            // Check for new_username in table 'users'
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + new_username + "'");
            // if the resultset is empty:
            return !rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // checks if user's credentials are correct
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
            // if the resultset is empty:
            return rs.isBeforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main (String[] args) throws SQLException {
        //TODO: Test for the methods, we can move it test classes later!!!!!!!!
        UserSQLDatabase new_class = new UserSQLDatabase();
        new_class.createTable();
        new_class.addUser("Jun", "1234");
        System.out.println(new_class.checkUsernameAvailable("Jun"));
        System.out.println(new_class.checkUsernameAvailable("aRTUR"));
        System.out.println(new_class.checkPassword("Jun", "1234"));
        System.out.println(new_class.checkPassword("Jun", "12345"));
        System.out.println(new_class.checkPassword("Artur", "1234"));
    }
}