import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;

// write tests for this

/**
 * Entity Class
 * returns a Tile with a numerical key and image based on the theme
 */

public class Tile {

    private final int key;
    // TO-DO private String theme


    // constructor for Tile object
    public Tile(int key) {         // add String "theme" as a parameter for phase 1/2
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return Integer.toString(getKey());
    }


    public ArrayList<Tile> createTileList () {
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


    public String[][] setUpDashBoard() {
        // sets up a matrix of dashes to be initially displayed in the console
        return new String[][]{{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}};
    }

    public String[][] setUpKeyBoard() {
        // setting up a matrix of the keys of the Tile objects in the randomized arrayList
        String[][] keyBoard = new String[][]{{"-", "-", "-", "-"}, {"-", "-", "-", "-"}, {"-", "-", "-", "-"}}; // make a board which will eventually contain the keys
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

    public void printBoard(String[][] board) {
        // prints dashboard
        for (String[] strings : board) {
            System.out.println(Arrays.toString(strings));
        }
    }

    public boolean flipped(String[][] board) {
        // checks if the board has all the elements flipped

        // set up the initial dash
        String dash = "-";

        for (String[] tiles: board)     // each row of tiles
            for (String tile: tiles)        // each tile
                if(Objects.equals(tile, dash))     // check if the title is dash
                    return false;       // board is not flipped
        // if none of the tiles are dashes then the board is flipped
        return true;
    }

    public String[] login() {
        String [] account = new String[2];
        Scanner scanner = new Scanner(System.in); // scanner
        System.out.println("Please enter your username.");
        account[0] = scanner.nextLine();
        System.out.println("Please enter your password.");
        account[1] = scanner.nextLine();
        return account;
        // add password check
    }

    public List<String[][]> setUpBoard() {
        String[][] baseBoard = setUpDashBoard(); // this board will track the user's correct matches
        final String[][] keyBoard = setUpKeyBoard(); // the board representation of the arrayList of Tile keys
        String[][] flexBoard = setUpDashBoard(); // the board which will be changed when user's first choice is printed
        List<String[][]> boardList = new ArrayList<>();
        boardList.add(baseBoard);
        boardList.add(keyBoard);
        boardList.add(flexBoard);
        return boardList;
    }


    public void runGame(String userName) {
        Scanner scanner = new Scanner(System.in); // scanner

        String[][] baseBoard = setUpBoard().get(0);
        String[][] keyBoard = setUpBoard().get(1);
        String[][] flexBoard = setUpBoard().get(2);

        int moves = 0;
        while (!flipped(baseBoard)){
            printBoard(baseBoard);

            // getting input from first user choice
            System.out.println("Please enter the row (1-3) for the tile you want to flip.");
            int move1_row = Integer.parseInt(scanner.nextLine())-1;
            System.out.println("Please enter the column (1-4) for the tile you want to flip.");
            int move1_column = Integer.parseInt(scanner.nextLine())-1;

            // change the tile in flexBoard which corresponds to input from a - to its numerical key
            flexBoard[move1_row][move1_column] = keyBoard[move1_row][move1_column];
            printBoard(flexBoard);

            // getting input from second user choice
            System.out.println("Please enter the row (1-3) for the next tile you want to flip.");
            int move2_row = Integer.parseInt(scanner.nextLine())-1;
            System.out.println("Please enter the column (1-4) for the next tile you want to flip.");
            int move2_column = Integer.parseInt(scanner.nextLine())-1;
            // TODO:something to catch the indexes that are out of bound

            // change the tile in flexBoard which corresponds to input from a - to its numerical key
            flexBoard[move2_row][move2_column] = keyBoard[move2_row][move2_column];
            printBoard(flexBoard);

            // check if the two tiles are a match
            if (Objects.equals(flexBoard[move1_row][move1_column], flexBoard[move2_row][move2_column])) { // if the two numbers shown are equal (match)
                baseBoard[move1_row][move1_column] = flexBoard[move1_row][move1_column]; // set baseboard to reflect this match
                baseBoard[move2_row][move2_column] = flexBoard[move2_row][move2_column];
                System.out.println("You found a match.");
            }
            else { // reset the moves to dashes
                flexBoard[move1_row][move1_column] = "-";
                flexBoard[move2_row][move2_column] = "-";
                System.out.println("No matches found. Please try again.");
            }
            moves++;

        }
        System.out.println("Congratulations! You've won the game!");
        System.out.println("Leaderboard:");
        System.out.println("1: Player" + userName + ", Moves: " + moves);
    }
}
