import org.junit.Before;
import org.junit.Test;
import entity.*;
import usecase.*;

import static org.junit.Assert.*;

public class BoardManagerTest {
    MatchingBoard tb;

    @Before
    public void setUp() {
        tb = DifficultyStrategy.Easy.generateBoard();
    }

    @Test(timeout = 200)
    public void FlipTileTest() {
        BoardManager.flipTile(tb, 1, 1);
        Tile tile = tb.getTileAtIndex(1, 1);
        assertTrue(tile.getFlipped());
    }

    @Test(timeout = 200)
    public void allFlippedTest() {
        for(Tile[] row : tb.getTilePositions()) {
            for (Tile tile : row) {
                tile.setFlipped(true);
            }
        }
        assertTrue(BoardManager.allFlipped(tb));
    }
}
