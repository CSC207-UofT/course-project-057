package views;

import entity.*;
import gateways.database.PatternGameHistorySQLDatabase;
import gateways.database.PatternLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import usecase.BoardManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * move to controller
 */

public class PatternGame {
    public static String[] runPatternGame() {
        String[] statistics = new String[3];
        String difficulty = UserGameInput.getUserDifficulty();

        PatternBoard patternBoard = DifficultyStrategy.valueOf(difficulty).generatePatternBoard();
        ArrayList<Tile> tileList = patternBoard.getTileList();

        // shows the pattern one tile at a time
        int counter = 1;
        long startTime = System.currentTimeMillis();
        boolean allCorrect = true;

        while (counter <= patternBoard.totalTiles && allCorrect) {
            int[] index = patternBoard.getIndexOfTile(tileList.get(counter-1));
            BoardManager.flipTile(patternBoard, index[0], index[1]);
            System.out.println(patternBoard);
            System.out.println("");
            BoardManager.unflipAll(patternBoard);
            System.out.println(patternBoard);
            // gets user input
            for (int i = 1; i <= counter; i++) {
                int[] move = BoardManager.Move(patternBoard, "Pattern", i);
                int moveKey = patternBoard.getTileKey(move[0], move[1]);
                int correctKey = tileList.get(i-1).getKey();
                if (!(moveKey == correctKey)) {
                    allCorrect = false;
                    System.out.println("Incorrect.");
                    break;
                }
            }
            if (allCorrect) {
                counter++;
            }
        }
        statistics[0] = Long.toString(System.currentTimeMillis() - startTime);
        statistics[1] = difficulty;
        statistics[2] = Boolean.toString(allCorrect);
        return statistics;
    }

    public static void main (String [] args) throws SQLException {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        PatternLeaderboardSQLDatabase PatternLeaderboardDatabase = new PatternLeaderboardSQLDatabase();
        PatternGameHistorySQLDatabase PatternHistoryDatabase = new PatternGameHistorySQLDatabase();

        //login
        String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];

        //run the game mode
        String[] statistics = runPatternGame();
        long time = Long.parseLong(statistics[0]);
        String difficulty = statistics[1];
        if (statistics[2].equals("true")) {
            Random rand = new Random();
            Integer GID = rand.nextInt();
            // Updates the leaderboard
            PatternHistoryDatabase.addGameHistory(GID, username, (double) (time/1000), difficulty);
            PatternLeaderboardDatabase.generateLeaderboard(difficulty);
        }
        PatternLeaderboardDatabase.generateLeaderboard(difficulty);
    }
}
