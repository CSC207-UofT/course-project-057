package views;

import entity.*;
import usecase.BoardManager;

import java.util.ArrayList;

public class PatternGame {
    public static String[] runPatternGame() {
        String[] statistics = new String[2];
        String difficulty = UserGameInput.getUserDifficulty();

        PatternBoard patternBoard = (PatternBoard) DifficultyStrategy.valueOf(difficulty).generatePatternBoard();
        ArrayList<Tile> tileList = patternBoard.getTileList();

        // shows the pattern one tile at a time
        int counter = 1;
        long startTime = System.currentTimeMillis();
        while (counter <= patternBoard.totalTiles) {
            tileList.get(counter).setFlipped(true);
            System.out.println(patternBoard);

            for (int i = 1; i <= counter; i++) {
                DisplayPrompts.userPatternMoveDisplay("row", Integer.toString(i));
                DisplayPrompts.userPatternMoveDisplay("column", Integer.toString(i));
            }
            BoardManager.unflipAll(patternBoard);
            System.out.println(patternBoard);
            counter++;
        }
        return statistics;
    }
}
