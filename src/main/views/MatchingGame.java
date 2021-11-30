package views;

import entity.DifficultyStrategy;
import gateways.database.MatchingGameHistorySQLDatabase;
import gateways.database.MatchingLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import entity.MatchingBoard;
import usecase.BoardManager;

import java.util.Random;

import java.sql.SQLException;

/**
 * move to controller
 */

public class MatchingGame {
    /**
     * runs a new game mode
     * @return number of moves, the time and difficulty of the finished game mode
     */
    public static String[] runMatchingGame(String difficulty) {
        //get user difficulty

        String[] statistics = new String[3];
        // String difficulty = UserGameInput.getUserDifficulty(); // delete, this is moved to StartPage
        int numMoves = 0;

        MatchingBoard board = DifficultyStrategy.valueOf(difficulty).generateMatchingBoard();

        // System.out.println("Input a row number from 1 to " + board.getNumRows()
        //        + " and a column from 1 to " + (board.getNumCols()) + ". Tile must not be revealed.");
        DisplayPrompts.enterMoveDisplay(board.getNumRows(), board.getNumCols());
        System.out.println(board);
        long startTime = System.currentTimeMillis();

        // Game runs until all tiles are flipped
        while(!BoardManager.allFlipped(board)) {
            int[] move1 = BoardManager.Move(board, "Matching", 0);
            int[] move2 = BoardManager.Move(board, "Matching", 0);
            int move1Key = board.getTileKey(move1[0], move1[1]);
            int move2Key = board.getTileKey(move2[0], move2[1]);
            DisplayPrompts.matchDisplay(move1Key == move2Key);
            if (!(move1Key == move2Key)) {
                BoardManager.flipTile(board, move1[0], move1[1]);
                BoardManager.flipTile(board, move2[0], move2[1]);
                System.out.println(board);
            }

            /*
            if (move1Key == move2Key)  {
               System.out.println("Match");
            }
            else {
                // If no match, flip them back
                BoardManager.flipTile(board, move1[0], move1[1]);
                BoardManager.flipTile(board, move2[0], move2[1]);
                System.out.println("No Match!");
                System.out.println(board);
            }
            */
            numMoves++;
        }
        DisplayPrompts.winGameDisplay();
        // System.out.println("Congratulations! You matched all the tiles.");
        statistics[0] = Integer.toString(numMoves);
        statistics[1] = Long.toString(System.currentTimeMillis() - startTime);
        statistics[2] = difficulty;
        return statistics;
    }

    public static void main (String [] args) throws SQLException { // more for testing, can delete
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        MatchingLeaderboardSQLDatabase LeaderboardDatabase = new MatchingLeaderboardSQLDatabase();
        MatchingGameHistorySQLDatabase GameHistoryDatabase = new MatchingGameHistorySQLDatabase();

        //login
        String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];
        //run the game mode including start page
        String[] gameType = StartPage.startPage();
        String[] statistics = runMatchingGame(gameType[1]);
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
