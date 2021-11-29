package entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extends Board class for pattern game.
 * The main object that will be acted on by other use case classes for the pattern game. Related to Entity.Tile class.
 */
public class PatternBoard extends Board{

    public PatternBoard(DifficultyStrategy difficulty) {
        super(difficulty);
    }

    /**
     * reveals a number of tiles in the board
     * @param reveal the number of tiles
     * @param tileArrayList the randomized list of Tiles
     */
    public void reveal(int reveal, ArrayList<Tile> tileArrayList) {
        if (reveal <= totalTiles) {
            for (int i = 0; i < reveal; i++) {
                tileArrayList.get(i).setFlipped(true);
            }
        }
    }
}
