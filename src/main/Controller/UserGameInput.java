package Controller;

import Entity.Tile;
import Entity.TileBoard;
import UseCase.BoardGenerator;

import java.util.Scanner;

public class UserGameInput {

    public static int[] getUserMove() {
        //TODO implement exception for invalid inputs
        //Store move data in an array [row number, column number]
        int[] input = new int[2];
        //Get user's move and store in input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row number: ");
        int rowNum = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter column number: ");
        int colNum = Integer.parseInt(scanner.nextLine());
        input[0] = rowNum;
        input[1] = colNum;
        return input;
    }

    public static int getUserDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Difficulty (1-3): ");
        //TODO: Implement exception for invalid input
        return Integer.parseInt(scanner.nextLine());
    }
}
