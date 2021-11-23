import usecase.BoardGenerator;
import org.junit.Test;
import entity.*;
import usecase.*;

import static org.junit.Assert.*;

public class BoardManagerTest {

    @Test(timeout = 200)
    public void FlipTileTest() {
        TileBoard tb = BoardGenerator.generateBoard(1);
        BoardManager.flipTile(tb, 1, 1);
        Tile tile = tb.getTileAtIndex(1, 1);
        assertTrue(tile.getFlipped());
    }

    @Test(timeout = 200)
    public void allFlippedTest() {
        TileBoard tb = BoardGenerator.generateBoard(1);
        for(Tile[] row : tb.getTilePositions()) {
            for (Tile tile : row) {
                tile.setFlipped(true);
            }
        }
        assertTrue(BoardManager.allFlipped(tb));
    }
}
