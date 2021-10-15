import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

/**
 * Entity Class
 * returns a Tile with a numerical key and image based on the theme
 */

public class Tile {

    private int key;
    // TO-DO private String theme


    // constructor for Tile object
    public Tile(int key) {         // add String "theme" as a parameter for phase 1/2
        this.key = key;
    }

    public int getKey () {
        return this.key;
    }

    public void setKey(int newKey) {
        this.key = newKey;
    }

    public ArrayList<Tile> createTileList () {
        ArrayList<Tile> tileList = new ArrayList<Tile>();
        for (int i = 0; i <= 6; i++) {          // loops 6 times, this value is used for keys
            for (int j = 0; j <= 2; j++) {      // each key is used twice
                Tile newTile = new Tile(i);     // creates 2 tiles with consecutive keys
                tileList.add(newTile);          // adds newly created tile to the list of tiles
            }
        }
        Collections.shuffle(tileList); // randomizes tiles
        return tileList;
    }


    public String[][] setUpDashBoard() {
        // sets up a matrix of dashes to be initially displayed in the console
        String[][] dashBoard = {{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}};
        return dashBoard;
    }

    public String[][] setUpKeyBoard() {
        // setting up a matrix of the keys of the Tile objects in the randomized arrayList
        String[][] keyBoard = setUpDashBoard(); // make a board which will eventually contain the keys
        int arrayListIndex = 0; // this counts the indexes of arrayList
        ArrayList<Tile> tileList = createTileList(); // create randomized list of Tile objects
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                // sets the value of the matrix in the current position to the key of the corresponding tile object
                keyBoard[i][j] = String.valueOf(tileList.get(arrayListIndex).getKey());
                arrayListIndex++; // increases ArrayList index by 1
            }
        }
        return keyBoard;
    }


    public void runGame() {
        String[][] baseBoard = setUpDashBoard(); // this board will track the user's correct matches
        final String[][] keyBoard = setUpKeyBoard(); // the board representation of the arrayList of Tile keys
        String flexBoard[][] = setUpDashBoard(); // the board which will be changed when user's first choice is printed
        Scanner scanner = new Scanner(System.in); // scanner

        printBoard(baseBoard);

        // getting input from first user choice
        System.out.println("Please enter a number from 1-12 for the tile you want to flip.");
        System.out.println("The numbers go from left->right and then top->bottom");
        String input_1 = scanner.nextLine();

        // TO-DO: change the tile which corresponds to input from a - to its numerical key

        // getting input from second user choice
        System.out.println("Please enter another number from 1-12 for the tile you want to flip.");
        System.out.println("The numbers go from left->right and then top->bottom");
        String input_2 = scanner.nextLine();

        // if the two inputs match, leave them as numerical keys
        // if they do not match, turn them both back to dashes

    }

    public void printBoard(String[][] board) {
        // prints dashboard
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String [] args) {
        runGame();

    }

}
