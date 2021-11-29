package views;

import java.util.Objects;
import java.util.Scanner;
import java.util.InputMismatchException;
// TODO javadoc
public class UserGameInput {

    /**
     * Obtains and analyzes user's input when the user inputs a move
     * @param 'number of rows in the board'
     * @param 'number of columns in the board'
     * @return 'array which containts the user's valid move choices'
     */
    public static int[] getUserMove(int numRows, int numCols) {
        //Store move data in an array [row number, column number]
        int[] input = new int[2];
        //Get user's move and store in input
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println("Enter row number: "); // replace with DisplayPrompts.userMoveDisplay("row")
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
            System.out.println("Enter column number: "); // replace with DisplayPrompts.userMoveDisplay("column")
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

    public static int getUserDifficulty() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println("Enter Difficulty (1-3): "); // replace with DisplayPrompts.getDifficultyDisplay()
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

    public static String promptLoginOrSignup() {
        Scanner scanner = new Scanner(System.in);
        String method = "";
        boolean valid = false;
        while (!valid) {
            System.out.println("Type 'login' or 'sign up':"); // replace with DisplayPrompts.loginOrSignupDisplay()
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
