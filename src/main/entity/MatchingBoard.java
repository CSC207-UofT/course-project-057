package entity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Extends Board class for matching game.
 * The main object that will be acted on by other use case classes for the matching game. Related to Entity.Tile class.
 */
public class MatchingBoard extends Board {
    private final int numPairs;

    /**
     * @param difficulty A DifficultyStrategy enum
     */

    public MatchingBoard(DifficultyStrategy difficulty) {
        super(difficulty);
        this.numPairs = numRows * numCols / 2;
    }
    /**
     * @return total number of pairs
     */
    public int getNumPairs() {

        return this.numPairs;
    }

    /**
     * Creates a randomized ArrayList of Entity.Tile objects that are put into the tileBoard object.
     * @return an arraylist of tiles
     */
    @Override
    public ArrayList<Tile> generateTileList() {
        ArrayList<Tile> tileList = new ArrayList<>();
        int numPairs = this.getNumPairs();
        for (int i = 0; i < numPairs; i++) {          // loops numPairs times, this value is used for keys
            for (int j = 0; j < 2; j++) {      // adds 2 Tiles of same value to list, this is for creating pairs
                Tile newTile = new Tile(i);
                tileList.add(newTile);
            }
        }
        Collections.shuffle(tileList); // randomizes order of Tiles in tileList
        return tileList;
    }
}