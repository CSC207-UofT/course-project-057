package views;
import gateways.database.GameHistorySQLDatabase;
import gateways.database.LeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import entity.TileBoard;
import usecase.BoardGenerator;
import usecase.BoardManager;

import java.util.Random;

import java.sql.SQLException;

public class DisplayPrompts {

    /**
     * @param match if a match was made or not
     */
    public static void matchDisplay (boolean match) {
        if (match) {
            System.out.println("Match!");
        } else {
            System.out.println("No Match");
        }
    }

    /**
     *
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     */
    public static void enterMoveDisplay (int getNumRows, int getNumCols) {
        System.out.println("Please input a row number from 1 to " + (getNumRows)
                + " and a column from 1 to " + (getNumCols) + ". Tile must not be revealed.");
    }

    /**
     *
     * @param getNumRows pass in tileboard.getNumRows()
     * @param getNumCols pass in tileboard.getNumColumns()
     */
    public static void invalidMoveDisplay(int getNumRows, int getNumCols) {
        System.out.println ("Your move is invalid.");
        enterMoveDisplay(getNumRows, getNumCols);
    }

    /**
     * displays message when the user logs in
     */
    public static void loginDisplay () {
        System.out.println("Please log in with your username and password");
    }

    /**
     * displays message when the user finishes a game
     */
    public static void endGameDisplay() {
        System.out.println("Congratulations! You matched all the tiles.");
    }

    /**
     * displays a message when the user wants to flip a tile
     * @param rowOrCol string that is row or column
     */
    public static void userMoveDisplay(String rowOrCol) {
        System.out.println("Please enter " + rowOrCol + " number: ");
    }

    /**
     * message when the user wants to log in or sign up
     */
    public static void loginOrSignupDisplay() {
        System.out.println ("Please enter 'login' to log into an existing account or 'signup' to create a new account.");
    }

    /**
     * message when the user starts a game and needs to input a difficulty
     */
    public static void getDifficultyDisplay() {
        System.out.println("Please enter difficulty (1-3), where 1 is easy, 2 is medium, and 3 is difficult: ");
    }
}

