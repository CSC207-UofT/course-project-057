package entity;

public class User {
    /** stores username and password
     * Entity Class
     * gateways.ui.Game History is being stored in a gateways.database
     */


    private String username;
    private String password;

    /**
     * constructor for User
     */
    public User (String username, String password) {

        this.username = username;
        this.password = password;
    }

    /**
     * @return the username of the User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * @return the password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * changing a User's username
     */
    public void setUsername(String new_username) {
        this.username = new_username;
    }

    /**
     * changing a User's password
     */
    public void setPassword (String new_password) {
        this.password = new_password;
    }

}