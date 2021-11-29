package entity;

import java.util.Arrays;

/**
 * Entity.TileBoard Entity Class
 * The main object that will be acted on by other use case classes for the game. Related to Entity.Tile class.
 */
public class TileBoard {

    private final Tile[][] TilePositions;
    private final int numPairs;
    private final int totalKeys; // totalTiles ?
    private final int numRows;
    private final int numCols;

    /**
     *
     * @param numRows number of rows
     * @param numCols number of columns
     */
    public TileBoard(int numRows, int numCols) {
        this.TilePositions = new Tile[numRows][numCols];
        this.numPairs = numRows * numCols / 2;
        this.totalKeys = numRows * numCols;
        this.numRows = numRows;
        this.numCols = numCols;
    }
    /**
     * @return total number of tiles in the board
     */
    public int getTotalKeys() {

        return this.totalKeys;
    }
    /**
     * @return total number of pairs
     */
    public int getNumPairs() {

        return this.numPairs;
    }

    /**
     * @return total number of rows
     */
    public int getNumRows() {

        return numRows;
    }

    /**
     * @return total number of columns
     */
    public int getNumCols() {

        return numCols;
    }

    /**
     * @param row the row number of the tile
     * @param col the column number of the tile
     * @return the key of the tile at the specified location
     */
    public int getTileKey(int row, int col) {

        return this.TilePositions[row][col].getKey();
    }

    /**
     * @param row the row number of the tile
     * @param col the column number of the tile
     * @return the Tile object at the specified location
     */
    public Tile getTileAtIndex(int row, int col) {

        return this.getTilePositions()[row][col];
    }

    /**
     * no idea what this means Koji pls help :(
     */
    public Tile[][] getTilePositions() {

        return this.TilePositions;
    }

    /**
     * @param row the row number of the tile
     * @param col the column number of the tile
     * @param tile the tile object
     * assigns a Tile object to the specified location on TileBoard
     */
    public void setTilePositions(int row, int col, Tile tile) {

        this.TilePositions[row][col] = tile;
    }

    /**
     * String form of Entity.TileBoard object
     * Useful for testing in UseCase.BoardGenerator
     * @return the key values of each Entity.Tile in their respective TilePositions
     */
    @Override
    public String toString() {
        boolean first = true;
        StringBuilder result = new StringBuilder("");
        for (Tile[] row : getTilePositions()) {
            if (first) {
                first = false;
            }
            else { result.append("\n");
            }
            result.append(Arrays.deepToString(row));
        }
        return result.toString();
    }
}


