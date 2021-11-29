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
     * @param 'if a match was made or not'
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
     * @param 'pass in tileboard.getNumRows()'
     * @param 'pass in tileboard.getNumColumns()'
     */
    public static void enterMoveDisplay (int getNumRows, int getNumCols) {
        System.out.println("Please input a row number from 1 to " + (getNumRows)
                + " and a column from 1 to " + (getNumCols) + ". Tile must not be revealed.");
    }

    /**
     *
     * @param 'pass in tileboard.getNumRows()'
     * @param 'pass in tileboard.getNumColumns()'
     */
    public static void invalidMoveDisplay(int getNumRows, int getNumCols) {
        System.out.println ("Your move is invalid.");
        enterMoveDisplay(getNumRows, getNumCols);
    }

    public static void loginDisplay () {
        System.out.println("Please log in with your username and password");
    }

    public static void endGameDisplay() {
        System.out.println("Congratulations! You matched all the tiles.");
    }

    public static void userMoveDisplay(String rowOrCol) {
        System.out.println("Please enter " + rowOrCol + " number: ");
    }

    public static void loginOrSignupDisplay() {
        System.out.println ("Please enter 'login' to log into an existing account or 'signup' to create a new account.");
    }

    public static void getDifficultyDisplay() {
        System.out.println("Please enter difficulty (1-3), where 1 is easy, 2 is medium, and 3 is difficult: ");
    }
}
