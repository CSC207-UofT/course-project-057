import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TileBoard {
    private final Tile[][] TilePositions;
    private final int numKeys;
    private final int totalKeys;
    private final int numRows;
    private final int numCols;

    private int getNumKeys() {
        return this.numKeys;
    }

    public TileBoard(int numRows, int numCols) {
        this.TilePositions = new Tile[numRows][numCols];
        this.numKeys = numRows * numCols / 2;
        this.totalKeys = numRows * numCols;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    public ArrayList<Tile> generateTileList() {
        ArrayList<Tile> tileList = new ArrayList<>();
        for (int i = 0; i < numKeys; i++) {          // loops 6 times, this value is used for keys
            for (int j = 0; j < 2; j++) {      // each key is used twice
                Tile newTile = new Tile(i);     // creates 2 tiles with consecutive keys
                tileList.add(newTile);          // adds newly created tile to the list of tiles
            }
        }
        Collections.shuffle(tileList); // randomizes tiles

        return tileList;
    }


    public void GenerateBoard() {
        int arrayListIndex = 0; // this counts the indexes of arrayList
        ArrayList<Tile> tileList = generateTileList(); // create randomized list of Tile objects
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                // sets the value of the matrix in the current position to the key of the corresponding tile object
                this.TilePositions[i][j] = tileList.get(arrayListIndex);
                arrayListIndex++; // increases ArrayList index by 1
            }
        }
        // adds newly created tile to the list of tile
    }

    public static void main(String[] args) {
        TileBoard tileBoard = new TileBoard(3, 4);
        System.out.println(tileBoard.generateTileList());
        tileBoard.GenerateBoard();
        Tile tile1 = new Tile(1);
        System.out.println(tile1);
        // prints dashboard
//        for (Tile[] row : tileBoard.TilePositions) {
//            for (Tile tile : row) {
//                System.out.println(tile);
//            }
//        }
        System.out.println(tileBoard.TilePositions[1][2]);
    }
}


