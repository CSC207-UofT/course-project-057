package entity;

import java.util.ArrayList;

/**
 * Enum class for difficulty strategy, contains different difficulties
 * Follows open/closed principle
 */
public enum DifficultyStrategy {
    /**
     * The easy difficulty with its own generateBoard method that generates a 3x4 board
     */
    Easy {
        @Override
        public int[] setDimension() {
            int[] dim = new int[2];
            dim[0] = 3;
            dim[1] = 4;
            return dim;
        }
        @Override
        public TileBoard generateBoard(){
             return getTileBoard(DifficultyStrategy.Easy);
        }
    },
    /**
     * The medium difficulty with its own generateBoard method that generates a 4x5 board
     */
    Medium {
        @Override
        public int[] setDimension() {
            int[] dim = new int[2];
            dim[0] = 4;
            dim[1] = 5;
            return dim;
        }
        @Override
        public TileBoard generateBoard(){
            return getTileBoard(DifficultyStrategy.Medium);
        }
    },
    /**
     * The hard difficulty with its own generateBoard method that generates a 5x6 board
     */
    Hard {
        @Override
        public int[] setDimension() {
            int[] dim = new int[2];
            dim[0] = 5;
            dim[1] = 6;
            return dim;
        }
        public TileBoard generateBoard(){
            return getTileBoard(DifficultyStrategy.Hard);
        }
    };

    /**
     * A helper method for the generateBoard method in enums
     * @param difficulty A DifficultyStrategy enum
     * @return a TileBoard object of randomized tiles
     */
    public static TileBoard getTileBoard(DifficultyStrategy difficulty) {
        TileBoard tileBoard = new TileBoard(difficulty);
        int numRows = tileBoard.getNumRows();
        int numCols = tileBoard.getNumCols();
        ArrayList<Tile> tileList;
        tileList = tileBoard.generateTileList();
        int tileListIndex = 0; // counter for tileList's index
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) { // loop over each column position in the row
                Tile newTile = tileList.get(tileListIndex);
                tileBoard.setTilePositions(i, j, newTile);
                tileListIndex++; // increases ArrayList index by 1, goes up to tileBoard.totalKeys
            }
        }
        return tileBoard;
    }

    public abstract TileBoard generateBoard();
    public abstract int[] setDimension();
}
