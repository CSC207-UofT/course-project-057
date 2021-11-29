package usecase;

import entity.*;
import views.UserGameInput;

/**
 * Checks board and tile status, as well as flipping tiles
 */
public class BoardManager {

    public BoardManager() {} // delete?

    /**
     *
     * @param board a TileBoard object to be checked
     * @return boolean indicating if all the tiles of the TileBoard has been flipped
     */
    public static boolean allFlipped(Board board) {
        for(Tile[] row : board.getTilePositions()) {
            for(Tile tile : row) {
                if(!tile.getFlipped()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Un-flips all the tiles
     * @param board A PatternBoard object
     */
    public static void unflipAll(Board board) {
        for(Tile[] row : board.getTilePositions()) {
            for(Tile tile : row) {
                tile.setFlipped(false);
            }
        }
    }

    /**
     *
     * @param board a TileBoard object
     * @param row the row of the tile position
     * @param col the column of the tile position
     */
    public static void flipTile(Board board, int row, int col) {
        Tile tile = board.getTileAtIndex(row, col);
        tile.setFlipped(!tile.getFlipped());
    }

    /**
     *
     * @param board a TileBoard object
     * @param row1 the row of the position of the first tile
     * @param col1 the column of the position of the first tile
     * @param row2 the row of the position of the second tile
     * @param col2 the column of the position of the second tile
     * @return boolean indicating if two tiles share the same key
     */
    public static boolean checkIfMatched(Board board, int row1, int col1, int row2, int col2) {
        Tile tile1 = board.getTileAtIndex(row1, col1);
        Tile tile2 = board.getTileAtIndex(row2, col2);

        return tile1.getKey() == tile2.getKey();
    }

    /**
     * methods that tracks the tile the user wants to move
     * @param board a Board object
     */
    public static int[] Move(Board board) {
        boolean validMove = false;
        int rowMove;
        int colMove;
        do {
            //Get user's move input as an array [row number, column number] (starting index 1) using controller class
            int numRows = board.getNumRows();
            int numCols = board.getNumCols();
            int[] input = UserGameInput.getUserMove(numRows, numCols);
            //Subtract 1 to account for index starting at 1 for user
            rowMove = input[0] - 1;
            colMove = input[1] - 1;
            //Check if move is valid based on row and column number, and if the tile is already flipped
            boolean validRow = (rowMove < board.getNumRows()) && (rowMove >= 0);
            boolean validCol = (colMove < board.getNumCols()) && (colMove >= 0);

            boolean Flipped = board.getTileAtIndex(rowMove, colMove).getFlipped();
            if (validRow && validCol && !Flipped) {
                validMove = true;
            }
            //
            else {
                System.out.println("Invalid Move, please input a row number from 1 to " + (board.getNumRows())
                        + " and a column from 1 to " + (board.getNumCols()) + ". Tile must not be revealed.");
            }
        }
        while (!validMove);

        BoardManager.flipTile(board, rowMove, colMove);
        // prints out the board with the flipped tile
        System.out.println(board);
        return new int[]{rowMove, colMove};
    }
}
