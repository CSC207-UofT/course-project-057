import org.junit.Before;
import org.junit.Test;

import entity.*;
import usecase.BoardManager;

import static org.junit.Assert.*;

public class DifficultyStrategyTest {
    PatternBoard easyPB;
    MatchingBoard mediumMB;
    MatchingBoard hardMB;

    @Before
    public void setUp() {
        easyPB = DifficultyStrategy.Easy.generatePatternBoard();
        mediumMB = DifficultyStrategy.Medium.generateMatchingBoard();
        hardMB = DifficultyStrategy.Hard.generateMatchingBoard();
    }

    /**
     * Test what we would expect for a PatternBoard object with difficulty DifficultyStrategy.Easy
     */
    @Test
    public void EasyPatternBoardTest() {
        assertEquals(12, easyPB.getTotalTiles());
        assertEquals(DifficultyStrategy.Easy, easyPB.getDifficulty());
        assertEquals(3, easyPB.getNumRows());
        assertEquals(4, easyPB.getNumCols());
    }

    /**
     * Test what we would expect for a MatchingBoard object with difficulty DifficultyStrategy.Medium
     */
    @Test
    public void MediumMatchingBoardTest() {
        BoardManager.flipTile(mediumMB, 3, 4);
        assertTrue(mediumMB.getTileAtIndex(3, 4).getFlipped()); // Easy board would not have tile at index
        BoardManager.unflipAll(mediumMB);
        assertFalse(mediumMB.getTileAtIndex(3, 4).getFlipped()); // Check if the card we flipped is unflipped
        assertFalse(mediumMB.getTileAtIndex(1, 1).getFlipped()); // Make sure no other cards got flipped
        assertEquals(mediumMB.getNumPairs(), 10);
        assertEquals(4, mediumMB.getNumRows());
        assertEquals(5, mediumMB.getNumCols());
    }

    /**
     * Test what we would expect for a MatchingBoard object with difficulty DifficultyStrategy.Hard
     */
    @Test
    public void HardMatchingBoardTest() {
        BoardManager.flipTile(hardMB, 4, 5);
        assertTrue(hardMB.getTileAtIndex(4, 5).getFlipped()); // Easy/Medium board would not have tile at index
        BoardManager.unflipAll(hardMB);
        assertFalse(hardMB.getTileAtIndex(4, 5).getFlipped()); // Check if the card we flipped is unflipped
        assertFalse(hardMB.getTileAtIndex(1, 1).getFlipped()); // Make sure no other cards got flipped
        assertEquals(hardMB.getNumPairs(), 15);
        assertEquals(5, hardMB.getNumRows());
        assertEquals(6, hardMB.getNumCols());
    }

}
