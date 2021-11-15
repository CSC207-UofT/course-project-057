package UI;

import Controller.UserGameInput;
import Database.GameHistorySQLDatabase;
import Database.LeaderboardSQLDatabase;
import Database.UserSQLDatabase;
import Entity.TileBoard;
import Entity.User;
import UseCase.BoardGenerator;
import UseCase.BoardManager;
import UI.UserLogin;
import UI.SignUpPage;

import java.util.Timer;

/**
 * main method
 * login is called, runs its methods, and the player/user and difficulty chosen are returned
 * then, game is called from main, and user + difficulty are passed in
 * NOTE: the actual game calls leaderboard, not the main
 */

public class Game {
    public static int[] Move(TileBoard tileBoard) {
        boolean validMove = false;
        int rowMove;
        int colMove;
        do {
            //Get user's move input as an array [row number, column number] (starting index 1) using controller class
            int numRows = tileBoard.getNumRows();
            int numCols = tileBoard.getNumCols();
            int[] input = UserGameInput.getUserMove(numRows, numCols);
            //Subtract 1 to account for index starting at 1 for user
            rowMove = input[0] - 1;
            colMove = input[1] - 1;
            //Check if move is valid based on row and column number, and if the tile is already flipped
            boolean validRow = (rowMove < tileBoard.getNumRows()) && (rowMove >= 0);
            boolean validCol = (colMove < tileBoard.getNumCols()) && (colMove >= 0);
            boolean Flipped = tileBoard.getTileAtIndex(rowMove, colMove).getFlipped();
            if (validRow && validCol && !Flipped) {
                validMove = true;
            }
            //
            else {
                System.out.println("Invalid Move, please input a row number from 1 to " + (tileBoard.getNumRows())
                        + " and a column from 1 to " + (tileBoard.getNumCols()) + ". Tile must not be revealed.");
            }
        }
        while (!validMove);

        BoardManager.flipTile(tileBoard, rowMove, colMove);
        System.out.println(tileBoard);
        return new int[]{rowMove, colMove};
    }

    public static long[] runGame() {
        //TODO add time
        //get user difficulty
        Timer timer = new Timer();

        long[] statistics = new long[2];
        int difficulty = UserGameInput.getUserDifficulty();
        int numMoves = 0;

        TileBoard tileBoard = BoardGenerator.generateBoard(difficulty);

        System.out.println("Input a row number from 1 to " + (tileBoard.getNumRows())
                + " and a column from 1 to " + (tileBoard.getNumCols()) + ". Tile must not be revealed.");
        System.out.println(tileBoard);
        long startTime = System.currentTimeMillis();

        // Game runs until all tiles are flipped
        while(!BoardManager.AllFlipped(tileBoard)) {
            int[] move1 = Move(tileBoard);
            int[] move2 = Move(tileBoard);
            int move1Key = tileBoard.getTileKey(move1[0], move1[1]);
            int move2Key = tileBoard.getTileKey(move2[0], move2[1]);
            if (move1Key == move2Key)  {
                System.out.println("Match");
            }
            else {
                // If no match, flip them back
                BoardManager.flipTile(tileBoard, move1[0], move1[1]);
                BoardManager.flipTile(tileBoard, move2[0], move2[1]);
                System.out.println("No Match!");
                System.out.println(tileBoard);
            }
            numMoves++;
        }
        System.out.println("Congratulations! You matched all the tiles.");
        statistics[0] = numMoves;
        statistics[1] = System.currentTimeMillis() - startTime;
        return statistics;
    }

    public static String[] loginOrSignup(UserSQLDatabase UserDatabase) {
        String input = UserGameInput.promptLoginOrSignup();
        String[] userData = new String[]{};
        if (input.equals("login")) {
            userData = UserLogin.login(UserDatabase);
        }
        else if (input.equals("sign up")) {
            SignUpPage.signUp(UserDatabase);
            userData = UserLogin.login(UserDatabase);
        }
        else {
            //TODO implement invalid input exception
        }
        return userData;
    }

    public static void main (String [] args) {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        LeaderboardSQLDatabase LeaderboardDatabase = new LeaderboardSQLDatabase();
        GameHistorySQLDatabase GameHistoryDatabase = new GameHistorySQLDatabase();

        //TODO 1. Implement login
        String[] userData = loginOrSignup(UserDatabase);
        String username = userData[0];

        //TODO 2. Implement run game
        long[] statistics = runGame();
        int numMoves = (int) statistics[0];
        long time = statistics[1];
        System.out.println(numMoves);
        System.out.println(time);
//        LeaderboardSQLDatabase.generateLeaderboard();
    }
}
