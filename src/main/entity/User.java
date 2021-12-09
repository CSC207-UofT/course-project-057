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
    private long time;
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
        this.time = 0;
        this.difficulty = "";
    }

    /**
     * @param numMove the move number
     */
    public void setNumMove(int numMove) {
        this.numMove = numMove;
    }

    /**
     * @return the move number
     */
    public int getNumMove() {
        return numMove;
    }

    /**
     * @param difficulty the difficulty of the game
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the difficulty of the game
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * @param time sets the time elapsed
     */
    public void setTime(long time) { this.time = time; }

    /**
     * @return the time elapsed
     */
    public double getTime() { return time; }

    /**
     * @param mode the game mode (Pattern or Matching)
     */
    public void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * @return the game mode (Pattern or Matching)
     */
    public int getMode() {
        return mode;
    }

    /**
     * @return the theme of the game (1=cats, 2=shapes, 3=League of Legends)
     */
    public int getTheme() {
        return theme;
    }

    /**
     * @param theme the theme of the game (1=cats, 2=shapes, 3=League of Legends)
     */
    public void setTheme(int theme) {
        this.theme = theme;
    }

    /**
     * @return if the user is a guest
     */
    public boolean isGuest() {
        return guest;
    }

    /**
     * @param guest if the user is a guest or not
     */
    public void setGuest(boolean guest) {
        this.guest = guest;
    }

    /**
     * resets the game statistics
     */
    public void reset(){
        time = 0;
        numMove = 0;
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

    /**
     * @return the cloned user
     * @throws CloneNotSupportedException throws the exception
     */
    public User clone() throws CloneNotSupportedException {
        User clone = (User) super.clone();
        clone.setGuest(guest);
        clone.setPassword(password);
        clone.setUsername(username);
        clone.setGuest(guest);
        return clone;
    }

}