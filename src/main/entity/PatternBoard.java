package entity;

/**
 * Extends Board class for pattern game.
 * The main object that will be acted on by other use case classes for the pattern game. Related to Entity.Tile class.
 */
public class PatternBoard extends Board{

    public PatternBoard(DifficultyStrategy difficulty) {
        super(difficulty);
    }
}
