package views;

import entity.DifficultyStrategy;
import gateways.database.GameHistorySQLDatabase;
import gateways.database.LeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import entity.MatchingBoard;
import usecase.BoardManager;

import java.util.Random;

import java.sql.SQLException;
/**
 * Try to separate the responsibility of output (displaying prompts) and game logic.
 * This should not also be responsible for the login and signup.
 * Should split this class up, your game logic should not be handles by a UI/view class.
 * Think about making a main controller that can delegate tasks by coordinating with other controllers, presenters, use cases.
 * One idea is to have a separate main menu controller and presenter, login controller and presenter
 * (you do this now to an extent but Game still deals with user input for this),
 * use cases and controllers for saving account data, game history and leaderboard history data.
 */

public class MatchingGame {
    /**
     * main method
     * login is called, runs its methods, and the player/user and difficulty chosen are returned
     * then, game is called from main, and user + difficulty are passed in
     * NOTE: the actual game calls leaderboard, not the main
     * @param matchingBoard a TileBoard object
     */
    public static int[] Move(MatchingBoard matchingBoard) {
        boolean validMove = false;
        int rowMove;
        int colMove;
        do {
            //Get user's move input as an array [row number, column number] (starting index 1) using controller class
            int numRows = matchingBoard.getNumRows();
            int numCols = matchingBoard.getNumCols();
            int[] input = UserGameInput.getUserMove(numRows, numCols);
            //Subtract 1 to account for index starting at 1 for user
            rowMove = input[0] - 1;
            colMove = input[1] - 1;
            //Check if move is valid based on row and column number, and if the tile is already flipped
            boolean validRow = (rowMove < matchingBoard.getNumRows()) && (rowMove >= 0);
            boolean validCol = (colMove < matchingBoard.getNumCols()) && (colMove >= 0);

            boolean Flipped = matchingBoard.getTileAtIndex(rowMove, colMove).getFlipped();
            if (validRow && validCol && !Flipped) {
                validMove = true;
            }
            //
            else {
                System.out.println("Invalid Move, please input a row number from 1 to " + (matchingBoard.getNumRows())
                        + " and a column from 1 to " + (matchingBoard.getNumCols()) + ". Tile must not be revealed.");
            }
        }
        while (!validMove);

        BoardManager.flipTile(matchingBoard, rowMove, colMove);
        System.out.println(matchingBoard);
        return new int[]{rowMove, colMove};
    }

    /**
     * runs a new game mode
     * @return number of moves, the time and difficulty of the finished game mode
     */
    public static String[] runGame() {
        //get user difficulty

        String[] statistics = new String[3];
        String difficulty = UserGameInput.getUserDifficulty();
        int numMoves = 0;

        MatchingBoard matchingBoard = DifficultyStrategy.valueOf(difficulty).generateBoard();

        System.out.println("Input a row number from 1 to " + (matchingBoard.getNumRows())
                + " and a column from 1 to " + (matchingBoard.getNumCols()) + ". Tile must not be revealed.");
        System.out.println(matchingBoard);
        long startTime = System.currentTimeMillis();

        // Game runs until all tiles are flipped
        while(!BoardManager.allFlipped(matchingBoard)) {
            int[] move1 = Move(matchingBoard);
            int[] move2 = Move(matchingBoard);
            int move1Key = matchingBoard.getTileKey(move1[0], move1[1]);
            int move2Key = matchingBoard.getTileKey(move2[0], move2[1]);
            if (move1Key == move2Key)  {
                System.out.println("Match");
            }
            else {
                // If no match, flip them back
                BoardManager.flipTile(matchingBoard, move1[0], move1[1]);
                BoardManager.flipTile(matchingBoard, move2[0], move2[1]);
                System.out.println("No Match!");
                System.out.println(matchingBoard);
            }
            numMoves++;
        }
        System.out.println("Congratulations! You matched all the tiles.");
        statistics[0] = Integer.toString(numMoves);
        statistics[1] = Long.toString(System.currentTimeMillis() - startTime);
        statistics[2] = difficulty;
        return statistics;
    }


    public static void main (String [] args) throws SQLException {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        LeaderboardSQLDatabase LeaderboardDatabase = new LeaderboardSQLDatabase();
        GameHistorySQLDatabase GameHistoryDatabase = new GameHistorySQLDatabase();

        //login
        String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];

        //run the game mode
        String[] statistics = runGame();
        int numMoves = Integer.parseInt(statistics[0]);
        long time = Long.parseLong(statistics[1]);
        String difficulty = statistics[2];

        Random rand = new Random();
        Integer GID = rand.nextInt();
        // Updates the leaderboard
        GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time/1000), difficulty);
        LeaderboardDatabase.generateLeaderboard(difficulty);
    }
}
