package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Parent class of all boards.
 *
 */
public class Board {
    public final Tile[][] TilePositions;
    public final int totalTiles;
    public final int numRows;
    public final int numCols;
    public final DifficultyStrategy difficulty;

    public Board(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
        this.numRows = difficulty.setDimension()[0];
        this.numCols = difficulty.setDimension()[1];
        this.TilePositions = new Tile[numRows][numCols];
        this.totalTiles = numRows * numCols;
    }
    /**
     * @return total number of tiles in the board
     */
    public int getTotalTiles() {

        return this.totalTiles;
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

    public ArrayList<Tile> generateTileList() {
        ArrayList<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < totalTiles; i++) { // loops totalTiles times
            Tile newTile = new Tile(i);
            tileList.add(newTile);
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
