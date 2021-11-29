package views;

import gateways.database.GameHistorySQLDatabase;
import gateways.database.LeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import entity.TileBoard;
import usecase.BoardGenerator;
import usecase.BoardManager;

import java.util.Random;

import java.sql.SQLException;
// TODO javadoc
/**
 * Try to separate the responsibility of output (displaying prompts) and game logic.
 * This should not also be responsible for the login and signup.
 * Should split this class up, your game logic should not be handles by a UI/view class.
 * Think about making a main controller that can delegate tasks by coordinating with other controllers, presenters, use cases.
 * One idea is to have a separate main menu controller and presenter, login controller and presenter
 * (you do this now to an extent but Game still deals with user input for this),
 * use cases and controllers for saving account data, game history and leaderboard history data.
 */

public class Game {
    /**
     * main method
     * login is called, runs its methods, and the player/user and difficulty chosen are returned
     * then, game is called from main, and user + difficulty are passed in
     * NOTE: the actual game calls leaderboard, not the main
     */
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
                // replace above print w DisplayPrompts.invalidMoveDisplay(tileBoard.getNumRows(), tileBoard.getNumCols())
            }
        }
        while (!validMove);

        BoardManager.flipTile(tileBoard, rowMove, colMove);
        System.out.println(tileBoard);
        return new int[]{rowMove, colMove};
    }

    public static long[] runGame() {
        //get user difficulty

        long[] statistics = new long[3];
        int difficulty = UserGameInput.getUserDifficulty();
        int numMoves = 0;

        TileBoard tileBoard = BoardGenerator.generateBoard(difficulty);

        System.out.println("Input a row number from 1 to " + (tileBoard.getNumRows())
                + " and a column from 1 to " + (tileBoard.getNumCols()) + ". Tile must not be revealed.");
        // replace above print w DisplayPrompts.enterMoveDisplay(tileBoard.getNumRows(), tileBoard.getNumCols())

        System.out.println(tileBoard);
        long startTime = System.currentTimeMillis();

        // Game runs until all tiles are flipped
        while(!BoardManager.allFlipped(tileBoard)) {
            int[] move1 = Move(tileBoard);
            int[] move2 = Move(tileBoard);
            int move1Key = tileBoard.getTileKey(move1[0], move1[1]);
            int move2Key = tileBoard.getTileKey(move2[0], move2[1]);
            if (move1Key == move2Key)  {
                System.out.println("Match"); // replace with DisplayPrompts.match(true)
            }
            else {
                // If no match, flip them back
                BoardManager.flipTile(tileBoard, move1[0], move1[1]);
                BoardManager.flipTile(tileBoard, move2[0], move2[1]);
                System.out.println("No Match!"); // replace with DisplayPrompts.match(false)
                System.out.println(tileBoard);
            }
            numMoves++;
        }
        System.out.println("Congratulations! You matched all the tiles."); // replace w DisplayPrompts.endGameDisplay()
        statistics[0] = numMoves;
        statistics[1] = System.currentTimeMillis() - startTime;
        statistics[2] = difficulty;
        return statistics;
    }

    /**
     * duplicated to LoginOrSignup usecase
     */
    public static String[] loginOrSignup(UserSQLDatabase UserDatabase) throws SQLException {
        String input = UserGameInput.promptLoginOrSignup();
        String[] userData = new String[]{};
        if (input.equals("login")) {
            userData = UserLogin.login(UserDatabase);
        }
        else if (input.equals("sign up")) {
            SignUpPage.signUp(UserDatabase);
            System.out.println("Please log in with your username and password");
            userData = UserLogin.login(UserDatabase);
        }
        return userData;
    }

    public static void main (String [] args) throws SQLException {

        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        LeaderboardSQLDatabase LeaderboardDatabase = new LeaderboardSQLDatabase();
        GameHistorySQLDatabase GameHistoryDatabase = new GameHistorySQLDatabase();

        //login
        String[] userData = loginOrSignup(UserDatabase);
        // replace with String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];

        //run the game mode
        long[] statistics = runGame();
        int numMoves = (int) statistics[0];
        long time = statistics[1];
        long difficulty_num = statistics[2];
        String difficulty;
        if (difficulty_num == 1) {
            difficulty = "easy";
        } else if (difficulty_num == 2) {
            difficulty = "medium";
        } else {
            difficulty = "hard";
        }

        Random rand = new Random();
        Integer GID = rand.nextInt();
        // Updates the leaderboard
        GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time/1000), difficulty);
        LeaderboardDatabase.generateLeaderboard(difficulty);
    }
}

