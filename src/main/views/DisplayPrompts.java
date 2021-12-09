package views;

/**
 * Displays prompts when playing a game
 */
public class DisplayPrompts {
    /**
     * @param match boolean for if a match was made or not
     * @return display for match vs no match
     */
    public static String matchDisplay (boolean match) {
        if (match) {
            return("Match!");
        } else {
            return("No Match");
        }
    }

    /**
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     * @return display for entering a move
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

    /**
     * @return the rules for the matching game
     */
    public static String matchingRules() {
        return ("Matching Rules:\n " +
                "1. Your aim is to uncover every pair of tiles. Each tile in the game has another matching tile.\n" +
                "2. Uncover a tile by clicking on it.\n" +
                "3. Then, press another tile. \n" +
                "4. If the two tiles look the same, you have found a pair!\n" +
                "5. If the two tiles are not the same, they will flip back over and you can try again by flipping another tile.\n" +
                "6. The game ends when all tiles have been uncovered. Good luck and thanks for playing!");
    }

    /**
     * @return the rules for the pattern game
     */
    public static String patternRules() {
        return ("Pattern Rules:\n " +
                "1. Your aim is to copy the pattern shown on screen.\n" +
                "2. One tile will uncover. Remember this tile!\n" +
                "3. Press the same tile that was just shown to you! \n" +
                "4. Another tile will flip.\n" +
                "5. Press the tile shown in step 2, followed by the tile in step 4.\n" +
                "6. The next tile will flip. Keep repeating the same pattern if you can remember!\n" +
                "7. Good luck and thanks for playing!");
    }

}
