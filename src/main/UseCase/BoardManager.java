package UseCase;

import Entity.*;

public class BoardManager {

    public BoardManager() {}

    /*
    checks if the board has all the elements flipped
     */
    public boolean checkAllFlipped(TileBoard board) {
        for(Tile[] row : board.getTilePositions()) {
            for(Tile tile : row) {
                if(!tile.getFlipped()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void flipTile(TileBoard board, int row, int col) {
        Tile tile = board.getTileAtIndex(row, col);
        tile.setFlipped(!tile.getFlipped());
    }

    public boolean checkIfMatched(TileBoard board, int row1, int col1, int row2, int col2) {
        Tile tile1 = board.getTileAtIndex(row1, col1);
        Tile tile2 = board.getTileAtIndex(row2, col2);

        return tile1.getKey() == tile2.getKey();
    }
}
