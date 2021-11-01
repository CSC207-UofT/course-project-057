/*
TileBoard Entity Class

The main object that will be acted on by other use case classes for the game. Related to Tile class.
 */

import java.util.Arrays;

public class TileBoard {
    private final Tile[][] TilePositions;
    private final int numPairs;
    private final int totalKeys;
    private final int numRows;
    private final int numCols;

    public TileBoard(int numRows, int numCols) {
        this.TilePositions = new Tile[numRows][numCols];
        this.numPairs = numRows * numCols / 2;
        this.totalKeys = numRows * numCols;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public int getTotalKeys() {
        return this.totalKeys;
    }

    public int getNumPairs() {
        return this.numPairs;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getTileKey(int row, int col) {
        return this.TilePositions[row][col].getKey();
    }

    public Tile[][] getTilePositions() {
        return this.TilePositions;
    }

    public void setTilePositions(int row, int col, Tile tile) {
        this.TilePositions[row][col] = tile;
    }

    // String form of TileBoard object returns the key values of each Tile in their respective TilePositions
    // Useful for testing in BoardGenerator
    @Override
    public String toString() {
       return Arrays.deepToString(getTilePositions());
    }
}


