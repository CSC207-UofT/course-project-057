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
        public PatternBoard generatePatternBoard(){
             return (PatternBoard) getTileBoard(DifficultyStrategy.Easy, "Pattern");
        }
        @Override
        public MatchingBoard generateMatchingBoard(){
            return (MatchingBoard) getTileBoard(DifficultyStrategy.Easy, "Matching");
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
        public PatternBoard generatePatternBoard(){
            return (PatternBoard) getTileBoard(DifficultyStrategy.Medium, "Pattern");
        }
        @Override
        public MatchingBoard generateMatchingBoard(){
            return (MatchingBoard) getTileBoard(DifficultyStrategy.Medium, "Matching");
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
        @Override
        public PatternBoard generatePatternBoard(){
            return (PatternBoard) getTileBoard(DifficultyStrategy.Hard, "Pattern");
        }
        @Override
        public MatchingBoard generateMatchingBoard(){
            return (MatchingBoard) getTileBoard(DifficultyStrategy.Hard, "Matching");
        }
    };

    /**
     * A helper method for the generateBoard method in enums
     * @param difficulty A DifficultyStrategy enum
     * @return a TileBoard object of randomized tiles
     */
    public static Board getTileBoard(DifficultyStrategy difficulty, String gameType) {
        if (gameType.equals("Matching")) {
            MatchingBoard board = new MatchingBoard(difficulty);
            getTileBoardHelper(board);
            return board;
        } else {
            PatternBoard board = new PatternBoard(difficulty);
            getTileBoardHelper(board);
            return board;
        }
    }

    /**
     * a helper method for getTileBoard
     * @param board the board being passed in
     */
    public static void getTileBoardHelper(Board board) {
        int numRows = board.getNumRows();
        int numCols = board.getNumCols();
        ArrayList<Tile> tileList;
        tileList = board.generateTileList();
        int tileListIndex = 0; // counter for tileList's index
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) { // loop over each column position in the row
                Tile newTile = tileList.get(tileListIndex);
                board.setTilePositions(i, j, newTile);
                tileListIndex++; // increases ArrayList index by 1, goes up to tileBoard.totalKeys
            }
        }
    }

    public abstract PatternBoard generatePatternBoard();
    public abstract MatchingBoard generateMatchingBoard();
    public abstract int[] setDimension();
}
