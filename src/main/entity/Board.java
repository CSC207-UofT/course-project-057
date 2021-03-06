package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Parent class for MatchingBoard and PatternBoard
 */
public class Board {
    public final Tile[][] tilePositions;
    public final int totalTiles;
    public final int numRows;
    public final int numCols;
    public final ArrayList<Tile> tileList;
    public final DifficultyStrategy difficulty;

    public Board(DifficultyStrategy difficulty) {
        this.difficulty = difficulty;
        this.numRows = difficulty.setDimension()[0];
        this.numCols = difficulty.setDimension()[1];
        this.tilePositions = new Tile[numRows][numCols];
        this.totalTiles = numRows * numCols;
        this.tileList = generateTileList();
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
     * @return the randomized tilelist
     */
    public ArrayList<Tile> getTileList() {
        return tileList;
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

        return this.tilePositions[row][col].getKey();
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
     * @return the index of the tile that is passed in
     */
    public int[] getIndexOfTile(Tile tile) {
        for (int i = 0 ; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (getTileKey(i, j) == tile.getKey()) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{100, 100};
    }


    /**
     * @return the positions of all the tiles
     */
    public Tile[][] getTilePositions() {
        return this.tilePositions;
    }

    /**
     * @param row the row number of the tile
     * @param col the column number of the tile
     * @param tile the tile object
     * assigns a Tile object to the specified location on TileBoard
     */
    public void setTilePositions(int row, int col, Tile tile) {
        this.tilePositions[row][col] = tile;
    }

    /**
     * @return a shuffled list of all the tile objects
     */
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
     * String form of Entity.Board object
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
