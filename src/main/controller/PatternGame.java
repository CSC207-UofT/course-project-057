package controller;

import entity.PatternBoard;
import entity.Tile;
import usecase.BoardManager;

import java.util.ArrayList;

public class PatternGame {

    /**
     * @param patternBoard the board the player is playing on
     * @param counter the round the player is on
     * @param currentCounter the move we are on
     * @param tileList the list of tiles which determines the pattern order
     * @param rowNum row number
     * @param colNum column number
     * @return if the move was correct or not
     */
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

    /**
     * @param board the board the player is playing on
     * @param counter the round the player is on
     * @return if the game is over or not
     */
    public static boolean checkEnd(PatternBoard board, int counter){
        return board.getTotalTiles() == counter;
    }

}
