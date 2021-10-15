import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class TileTest {
    Tile tt;

    @Before
    public void setUp() {
        tt = new Tile(0);
    }

    @Test(timeout = 50)
    public void createTileListTest(){
        assertEquals(tt.createTileList().size(), 12);
    }

    @Test(timeout = 50)
    public void flippedTest(){
        String[][] unflippedBoard =
                {{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}};
        assertFalse(tt.flipped(unflippedBoard));
        String[][] testBoard =
                {{"1", "1", "3", "3"}, {"2", "2", "5", "5"}, {"0", "4", "0", "-"}};
        assertFalse(tt.flipped(testBoard));
        String[][] flippedBoard =
                {{"1", "1", "3", "3"}, {"2", "2", "5", "5"}, {"0", "4", "0", "4"}};
        assertTrue(tt.flipped(flippedBoard));
    }
}