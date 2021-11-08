/** stores username and password as well as game history/statistics
 * Entity Class
 * @param username
 * @param password
 * @param gameHistory of type dictionary, where key is an int and value is an array with score, difficulty, and time
 **/

public class User {

    private String username;
    private String password;

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}
