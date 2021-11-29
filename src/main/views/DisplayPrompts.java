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
}
