import java.util.ArrayList;
import java.util.Collections;

public class BoardGenerator {

    public ArrayList<Tile> createTileBoard() {
        ArrayList<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {          // loops 6 times, this value is used for keys
            for (int j = 0; j < 2; j++) {      // each key is used twice
                Tile newTile = new Tile(i);     // creates 2 tiles with consecutive keys
                tileList.add(newTile);          // adds newly created tile to the list of tiles
            }
        }
        Collections.shuffle(tileList); // randomizes tiles
        return tileList;
    }
}
