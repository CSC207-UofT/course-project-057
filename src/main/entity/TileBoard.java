package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
    private final DifficultyStrategy difficulty;

    /**
     * @param difficulty A DifficultyStrategy enum
     */
    public TileBoard(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
        this.numRows = difficulty.setDimension()[0];
        this.numCols = difficulty.setDimension()[1];
        this.TilePositions = new Tile[numRows][numCols];
        this.numPairs = numRows * numCols / 2;
        this.totalKeys = numRows * numCols;
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
     * @return the difficulty
     */
    public DifficultyStrategy getDifficulty() {
        return difficulty;
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
     * Creates a randomized ArrayList of Entity.Tile objects that are put into the tileBoard object.
     * @return an arraylist of tiles
     */
    public ArrayList<Tile> generateTileList() {
        ArrayList<Tile> tileList = new ArrayList<>();
        int numPairs = this.numPairs;
        for (int i = 0; i < numPairs; i++) {          // loops numPairs times, this value is used for keys
            for (int j = 0; j < 2; j++) {      // adds 2 Tiles of same value to list, this is for creating pairs
                Tile newTile = new Tile(i);
                tileList.add(newTile);
            }
        }
        Collections.shuffle(tileList); // randomizes order of Tiles in tileList
        return tileList;
    }

    /**
     * String form of Entity.TileBoard object
     * Useful for testing in UseCase.BoardGenerator
     * @return the key values of each Entity.Tile in their respective TilePositions
     */
    @Override
    public String toString() {
        boolean first = true;
        StringBuilder result = new StringBuilder();
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


