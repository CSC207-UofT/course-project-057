package entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extends Board class for pattern game.
 * The main object that will be acted on by other use case classes for the pattern game. Related to Entity.Tile class.
 */
public class PatternBoard extends Board{

    /**
     * Constructor for the patternBoard
     * @param difficulty the difficulty of the game mode
     */
    public PatternBoard(DifficultyStrategy difficulty) {
        super(difficulty);
    }
}
