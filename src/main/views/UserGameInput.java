package views;

import java.util.Objects;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Obtains user's input.
 */
public class UserGameInput {
    /**
     * a method that gets the row and column number of the tile that the user wants to flip
     * @param numRows number of the row
     * @param numCols number of the column
     * @return row number and column number
     */
    public static int[] getUserMove(int numRows, int numCols) {
        //Store move data in an array [row number, column number]
        int[] input = new int[2];
        //Get user's move and store in input
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println("Enter row number: ");
            try {
                int n = scanner.nextInt();
                if (n <= numRows && n > 0){
                    done = true;
                    input[0] = n;
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
            }
        }
        boolean check = false;
        while (!check){
            System.out.println("Enter column number: ");
            try {
                int n = scanner.nextInt();
                if (n <= numCols && n > 0){
                    check = true;
                    input[1] = n;
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
            }
        }

        return input;
    }

    /**
     * a method that gets the difficulty from user input
     * @return int of difficulty
     */
    public static int getUserDifficulty() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println("Enter Difficulty (1-3): ");
            try {
                int n = scanner.nextInt();
                if (n <= 3 && n>0){
                    done = true;
                    input = input + n;
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
            }

        }
        return input;

    }

    /**
     * a method that gets either 'login' or 'sign up' from user input
     * @return either log in or sign up
     */
    public static String promptLoginOrSignup() {
        Scanner scanner = new Scanner(System.in);
        String method = "";
        boolean valid = false;
        while (!valid) {
            System.out.println("Type 'login' or 'sign up':");
            try {
                method = scanner.nextLine();
                if (Objects.equals(method, "login") || Objects.equals(method, "sign up")) {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return method;
    }
}
