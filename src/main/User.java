/** stores username and password
 * Entity Class
 * @param username
 * @param password
 * Game History is being stored in a database
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

    public void setUsername(String new_username) {
        this.username = new_username;
    }

    public void setPassword (String new_password) {
        this.password = new_password;
    }

}
