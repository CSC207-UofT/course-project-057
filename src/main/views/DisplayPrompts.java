package views;

/**
 * Displays prompts when playing a game
 */
public class DisplayPrompts {
    /**
     * display for match vs no match
     * @param match boolean for if a match was made or not
     */
    public static String matchDisplay (boolean match) {
        if (match) {
            return("Match!");
        } else {
            return("No Match");
        }
    }

    /**
     * display for entering a move
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     */
    public static String enterMoveDisplay (int getNumRows, int getNumCols) {
        return("Please input a row number from 1 to " + (getNumRows)
                + " and a column from 1 to " + (getNumCols) + ". Tile must not be revealed.");
    }

    /**
     * display if a move is invalid
     * @param getNumRows pass in Board.getNumRows()
     * @param getNumCols pass in Board.getNumColumns()
     */
    public static String invalidMoveDisplay(int getNumRows, int getNumCols) {
        return ("Your move is invalid." + enterMoveDisplay(getNumRows, getNumCols));
    }

    /**
     * display for the user to log in
     */
    public static String loginDisplay () {
        return("Please log in with your username and password");
    }

    /**
     * display when the user wins
     */
    public static String winGameDisplay() {
        return("Congratulations! You won the game!");
    }

    /**
     * display when the user loses
     */
    public static String loseGameDisplay() {
        return("Mistake, too bad! Better luck next time...");
    }

    /**
     * prints a large space
     */
    public static String printSpace() {
        return("\n\n\n\n\n\n");
    }

    /**
     * display for the user to make a match
     * @param rowOrCol: if the user is entering a row number or column number
     */
    public static String userMatchMoveDisplay(String rowOrCol) {
        return("Please enter " + rowOrCol + " number: ");
    }

    /**
     * prompting the user to make a move with a specified number
     * @param rowOrCol: if the user is entering a row number or column number
     * @param moveNumber: the number of moves made
     */
    public static String userPatternMoveDisplay(String rowOrCol, String moveNumber) {
        return("Please enter " + rowOrCol + " number for tile #" + moveNumber);
    }

    /**
     * Login or Sign Up prompt message
     */
    public static String loginOrSignupDisplay() {
        return("Please enter 'login' to log into an existing account or 'signup' to create a new account.");
    }

    /**
     * prompt to get the user's difficulty
     */
    public static String getDifficultyDisplay() {
        return("Please enter difficulty (Easy, Medium, or Hard)");
    }

    /**
     * prompt to get the user's game type
     */
    public static String getGameTypeDisplay() {
        return("Please enter game type (Matching or Pattern): ");
    }

    /**
     * welcomes the user to the game
     */
    public static String welcomeMessage() {
        return("Hello! Welcome to Group 57's Memory Game");
    }

    /**
     * Asks if the user wants to play as a guest
     */
    public static String guestMessage() {
        return("Would you like to play as a guest? (Y/N)");
    }

}
