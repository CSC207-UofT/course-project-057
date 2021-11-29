package views;

import entity.*;
import usecase.BoardManager;

import java.util.ArrayList;

public class PatternGame {
    public static String[] runPatternGame() {
        String[] statistics = new String[2];
        String difficulty = UserGameInput.getUserDifficulty();

        PatternBoard patternBoard = DifficultyStrategy.valueOf(difficulty).generatePatternBoard();
        ArrayList<Tile> tileList = patternBoard.getTileList();

        // shows the pattern one tile at a time
        int counter = 1;
        long startTime = System.currentTimeMillis();
        boolean allCorrect = true;

        while (counter <= patternBoard.totalTiles && allCorrect) {
            tileList.get(counter).setFlipped(true);
            System.out.println(patternBoard);

            // gets user input
            for (int i = 1; i <= counter; i++) {
                int[] move = BoardManager.Move(patternBoard, "Pattern", i);
                int moveKey = patternBoard.getTileKey(move[0], move[1]);
                int correctKey = tileList.get(i-1).getKey();
                if (!(moveKey == correctKey)) {
                    allCorrect = false;
                    System.out.println("Incorrect.");
                    break;
                }
            }
            if (allCorrect) {
                BoardManager.unflipAll(patternBoard);
                System.out.println(patternBoard);
                counter++;
            }
        }
        statistics[0] = Long.toString(System.currentTimeMillis() - startTime);
        statistics[1] = difficulty;
        return statistics;
    }
}
