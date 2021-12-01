package views;

/**
 * Displays prompts when playing a game
 */
public class DisplayPrompts {
    /**
     * display for match vs no match
     * @param match boolean for if a match was made or not
     */
    public static void matchDisplay (boolean match) {
        if (match) {
            System.out.println("Match!");
        } else {
            System.out.println("No Match");
        }
    }

    /**
     * display for entering a move
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     */
    public static void enterMoveDisplay (int getNumRows, int getNumCols) {
        System.out.println("Please input a row number from 1 to " + (getNumRows)
                + " and a column from 1 to " + (getNumCols) + ". Tile must not be revealed.");
    }

    /**
     * display if a move is invalid
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     */
    public static void invalidMoveDisplay(int getNumRows, int getNumCols) {
        System.out.println ("Your move is invalid.");
        enterMoveDisplay(getNumRows, getNumCols);
    }

    /**
     * display for the user to log in
     */
    public static void loginDisplay () {
        System.out.println("Please log in with your username and password");
    }

    /**
     * display when the game ends
     */
    public static void winGameDisplay() {
        System.out.println("Congratulations! You won the game!");
    }

    /**
     * display for the user to make a match
     * @param 'if the user is entering a row number or column number'
     */
    public static void userMatchMoveDisplay(String rowOrCol) {
        System.out.println("Please enter " + rowOrCol + " number: ");
    }

    /**
     * prompting the user to make a move with a specified number
     * @param 'if the user is entering a row number or column number'
     * @param 'the number of moves made'
     */
    public static void userPatternMoveDisplay(String rowOrCol, String moveNumber) {
        System.out.println("Please enter " + rowOrCol + " number for tile #" + moveNumber);
    }

    /**
     * Login or Sign Up prompt message
     */
    public static void loginOrSignupDisplay() {
        System.out.println ("Please enter 'login' to log into an existing account or 'signup' to create a new account.");
    }

    /**
     * prompt to get the user's difficulty
     */
    public static void getDifficultyDisplay() {
        System.out.println("Please enter difficulty (Easy, Medium, or Hard)");
    }

    /**
     * prompt to get the user's game type
     */
    public static void getGameTypeDisplay() {
        System.out.println("Please enter game type (Matching or Pattern): ");
    }

    /**
     * welcomes the user to the game
     */
    public static void welcomeMessage() {
        System.out.println ("Hello! Welcome to Group 57's Memory Game");
    }

    /**
     * Asks if the user wants to play as a guest
     */
    public static void guestMessage() {
        System.out.println("Would you like to play as a guest? (Y/N)");
    }

    /**
     * Informs the user that they have made the wrong choice
     */
    public static void incorrectDisplay() {
        System.out.println("Your move was incorrect.");
    }



}
