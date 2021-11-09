import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * IS THE MAIN METHOD FOR TESTING?
 */

public class BoardGenerator {

    public BoardGenerator() {}

    /*
    Helper method for GenerateBoard

    Creates a randomized ArrayList of Tile objects that are put into the tileBoard object.
     */
    private static ArrayList<Tile> generateTileList(int numPairs) {
        ArrayList<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < numPairs; i++) {          // loops numPairs times, this value is used for keys
            for (int j = 0; j < 2; j++) {      // adds 2 Tiles of same value to list, this is for creating pairs
                Tile newTile = new Tile(i);
                tileList.add(newTile);
            }
        }
        Collections.shuffle(tileList); // randomizes order of Tiles in tileList
        return tileList;
    }

    public static TileBoard GenerateBoard(int numRows, int numCols) {
        TileBoard tileBoard = new TileBoard(numRows, numCols);
        ArrayList<Tile> tileList;
        tileList = generateTileList(tileBoard.getNumPairs());
        int tileListIndex = 0; // counter for tileList's index
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) { // loop over each column position in the row
                Tile newTile = tileList.get(tileListIndex);
                tileBoard.setTilePositions(i, j, newTile);
                tileListIndex++; // increases ArrayList index by 1, goes up to tileBoard.totalKeys
            }
        }
        return tileBoard;
    }



    public static void main(String[] args) {
        TileBoard tileBoard = GenerateBoard(3, 4);
        System.out.println(tileBoard.getTotalKeys());
        System.out.println(tileBoard);
        BoardManager.flipTile(tileBoard, 1, 1);
//        System.out.println(tileBoard.getTileAtIndex(1, 1).getFlipped());
//        System.out.println(tileBoard.getTileAtIndex(1, 2).getFlipped());

    }
}
