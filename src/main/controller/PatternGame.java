package controller;

import entity.PatternBoard;
import entity.Tile;
import usecase.BoardManager;

import java.util.ArrayList;

public class PatternGame {

    public static boolean checkMove (PatternBoard patternBoard, int counter, int currentCounter, ArrayList<Tile> tileList, int rowNum, int colNum) {

            int[] move = new int[]{rowNum, colNum};
            int moveKey = patternBoard.getTileKey(move[0], move[1]);
            int correctKey = tileList.get(currentCounter).getKey();
            if (moveKey == correctKey) {
                BoardManager.flipTile(patternBoard, rowNum, colNum);

                return true;
            }else {
                return false;
            }

    }

    public static boolean checkEnd(PatternBoard board, int counter){
        return board.getTotalTiles() == counter;
    }

}
