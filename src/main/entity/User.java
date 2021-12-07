package entity;

public class User {
    /** stores username and password
     * Entity Class
     * gateways.ui.Game History is being stored in a gateways.database
     */
    private String username;
    private String password;
    private boolean guest;
    private int numMove;
    private double time;
    private String difficulty;
    private int mode, theme;

    /**
     * constructor for User
     * @param username the username of the user object
     * @param password the password of the user object
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.guest = false;
        this.numMove = 0;
        this.time = 0.0;
        this.difficulty = "";
    }

    //add setter and getters
    public void setNumMove(int numMove) {
        this.numMove = numMove;
    }

    public int getNumMove() {
        return numMove;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setTime(double time) { this.time = time; }

    public double getTime() { return time; }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public boolean isGuest() {
        return guest;
    }

    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    public void reset(){
        username = "";
        password = "";
        guest = false;
        difficulty = "";
        time = 0.0;
        mode = 0;
        theme = 0;
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
     * @param new_username the new username that replaces the previous username
     */
    public void setUsername(String new_username) {
        this.username = new_username;
    }

    /**
     * changing a User's password
     * @param new_password the new password that replaces the previous password
     */
    public void setPassword (String new_password) {
        this.password = new_password;
    }

    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        clone.setGuest(guest);
        clone.setPassword(password);
        clone.setUsername(username);
        clone.setGuest(guest);
        return clone;
    }

}