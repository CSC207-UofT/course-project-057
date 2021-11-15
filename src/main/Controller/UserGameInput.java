package Controller;

import java.util.Scanner;
import java.util.InputMismatchException;

/***
 * Controller class
 * Obtains user's input.
 */


public class UserGameInput {

    public static int[] getUserMove(int numRows, int numCols) {
        //TODO implement exception for invalid inputs
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

    public static int getUserDifficulty() {
        int input = 0;
        Scanner scanner = new Scanner(System.in);
        //TODO: Implement exception for invalid input
        boolean done = false;
        while (!done){
            System.out.println("Enter UseCase.Difficulty (1-3): ");
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

        //return Integer.parseInt(scanner.nextLine());
    }

    public static String promptLoginOrSignup() {
        Scanner s = new Scanner(System.in);
        System.out.println("Type 'login' or 'sign up':");
        return s.nextLine();
    }
}
