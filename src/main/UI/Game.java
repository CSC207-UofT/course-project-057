package UI;

import Controller.UserGameInput;
import Entity.TileBoard;
import UseCase.BoardGenerator;
import UseCase.BoardManager;

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
            //TODO implement exception for invalid inputs in UserGameInput
            //Get user's move input as an array [row number, column number] (starting index 1) using controller class
            int[] input = UserGameInput.getUserMove();
            //Subtract 1 to account for index starting at 1 for user
            rowMove = input[0] - 1;
            colMove = input[1] - 1;
            //Check if move is valid based on row and column number, and if the tile is already flipped
            boolean validRow = (rowMove < tileBoard.getNumRows()) && (rowMove >= 0);
            boolean validCol = (colMove < tileBoard.getNumCols()) && (colMove >= 0);
            //TODO Implement exception for input out of bounds here
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

    public static void runGame() {
        //get user difficulty
        int difficulty = UserGameInput.getUserDifficulty();
        TileBoard tileBoard = BoardGenerator.generateBoard(difficulty);

        System.out.println("Input a row number from 1 to " + (tileBoard.getNumRows())
                + " and a column from 1 to " + (tileBoard.getNumCols()) + ". Tile must not be revealed.");
        System.out.println(tileBoard);

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
        }
        System.out.println("Congratulations! You matched all the tiles.");
    }

    public static void main (String [] args) {
        //TODO 1. Implement login

        //TODO 2. Implement run game
        runGame();
    }
}
