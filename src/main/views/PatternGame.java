package views;

import entity.*;
import usecase.BoardManager;

import java.util.ArrayList;

public class PatternGame {
    public static String[] runPatternGame() {
        String[] statistics = new String[2];
        String difficulty = UserGameInput.getUserDifficulty();

        PatternBoard patternBoard = (PatternBoard) DifficultyStrategy.valueOf(difficulty).generateBoard();
        ArrayList<Tile> tileList = patternBoard.getTileList();

        int counter = 1;
        for (int i = 0; i < counter; i++) {
            if (i <= patternBoard.totalTiles) {
                for (int j = 0; j <= i; j++) {
                    tileList.get(j).setFlipped(true);
                    System.out.println(patternBoard);
                    BoardManager.unflipAll(patternBoard);
                    System.out.println(patternBoard);
                }
            }
        }
        return statistics;
    }
}
