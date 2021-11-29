package usecase;

import entity.*;

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
    public static boolean allFlipped(MatchingBoard board) {
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
     *
     * @param board a TileBoard object
     * @param row the row of the tile position
     * @param col the column of the tile position
     */
    public static void flipTile(MatchingBoard board, int row, int col) {
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
    public static boolean checkIfMatched(MatchingBoard board, int row1, int col1, int row2, int col2) {
        Tile tile1 = board.getTileAtIndex(row1, col1);
        Tile tile2 = board.getTileAtIndex(row2, col2);

        return tile1.getKey() == tile2.getKey();
    }
}
