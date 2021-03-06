package views;

import java.util.Objects;
import java.util.Scanner;
import java.util.InputMismatchException;

public class UserGameInput {
    private int numMove;
    private String difficulty, mode;


    /**
     * Obtains user's input.
     * @param numRows the number of rows in the tileboard
     * @param numCols the number of columns in the tileboard
     * @return integer list of user input of row and col number
     */
    public static int[] getUserMove(int numRows, int numCols, String gameType, int counter) {
        //Store move data in an array [row number, column number]
        int[] input = new int[2];
        //Get user's move and store in input
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            if (gameType.equals("Matching")) {
                System.out.println(DisplayPrompts.userMatchMoveDisplay("row"));
            } else {
                System.out.println(DisplayPrompts.userPatternMoveDisplay("row", Integer.toString(counter)));
            }
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
            if (gameType.equals("Matching")) {
                System.out.println(DisplayPrompts.userMatchMoveDisplay("column"));
            } else {
                System.out.println(DisplayPrompts.userPatternMoveDisplay("column", Integer.toString(counter)));
            }
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
     * obtains user input of difficulty
     * @return integer of difficulty
     */
    public static String getUserDifficulty() {
        String difficulty = "";
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println(DisplayPrompts.getDifficultyDisplay());
            try {
                difficulty = scanner.nextLine();
                if (Objects.equals(difficulty, "Easy") || Objects.equals(difficulty, "Medium")
                        || Objects.equals(difficulty, "Hard")){
                    done = true;
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
            }
        }
        return difficulty;
    }

    /**
     * obtains either 'login' or 'sign up' from user input
     * @return string of either 'login' or 'sign up'
     */
    public static String promptLoginOrSignup() {
        Scanner scanner = new Scanner(System.in);
        String method = "";
        boolean valid = false;
        while (!valid) {
            System.out.println(DisplayPrompts.loginOrSignupDisplay());
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

    // old
    public static String getGameType() {
        String type = "";
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done){
            System.out.println(DisplayPrompts.getGameTypeDisplay());
            try {
                type = scanner.nextLine();
                if (Objects.equals(type, "Matching") || Objects.equals(type, "Pattern")){
                    done = true;
                }
            }
            catch (InputMismatchException e){
                scanner.nextLine();
            }
        }
        return type;
    }
}
