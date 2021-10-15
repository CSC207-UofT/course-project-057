import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

/**
 * Entity Class
 * returns a Tile with a numerical key and image based on the theme
 */

public class Tile {

    private int key;
    // private String theme


    // constructor for Tile object
    public Tile(int key) {         // add String theme as a parameter
        this.key = key;
    }

    public int getKey () {
        return this.key;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public ArrayList<Tile> createTileList () {
        ArrayList<Tile> tileList = new ArrayList<Tile>();       // check syntax
        for (int i = 0; i <= 6; i++) {          // loops 6 times, this value is used for keys
            for (int j = 0; j <= 2; j++) {
                Tile newTile = new Tile(i);         // creates 2 tiles with consecutive keys
                tileList.add(newTile);
            }
        }
        // randomize tiles
        Collections.shuffle(tileList);
        return tileList;
    }

    // method to arrange the tile list into a 4x3 grid
    // use a 2d array for dashes and a corresponding one for the tiles


    public void runGame() {
        Scanner scanner = new Scanner(System.in);
        // sets up a matrix of dashes to be initially displayed in the console
        final String[][] dashBoard = {{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}};

        // setting up a matrix of the keys of the Tile objects in the randomized arrayList
        int[][] keyBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}}; // sets each value in the 2d array to 0
        int arrayListIndex = 0; // this counts the indexes of arrayList
        ArrayList<Tile> tileList;
        tileList = createTileList(); // create randomized list of Tile objects
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                // sets the value of the matrix in the current position to the key of the corresponding tile object
                keyBoard[i][j] = tileList.get(arrayListIndex).getKey();
                arrayListIndex++; // increases ArrayList index by 1
            }
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(dashBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Please enter a number from 1-12 for the tile you want to flip.");
        System.out.println("The numbers go from left->right and then top->bottom");
        String input_1 = scanner.nextLine();
        // change the tile which corresponds to input from a - to its numerical key
        System.out.println("Please enter another number from 1-12 for the tile you want to flip.");
        System.out.println("The numbers go from left->right and then top->bottom");
        String input_2 = scanner.nextLine();
        // if the two inputs match, leave them as numerical keys
        // if they do not match, turn them both back to dashes

    }

    public static void main (String [] args) {
        runGame();

    }

}
