package controller;

import usecase.BoardManager;

public class PatternGame {

    public boolean checkMove () {
        for (int i = 1; i <= counter; i++) {
            int[] move = BoardManager.Move(patternBoard, "Pattern", i);
            int moveKey = patternBoard.getTileKey(move[0], move[1]);
            int correctKey = tileList.get(i-1).getKey();
            if (!(moveKey == correctKey)) {
                allCorrect = false;
//                    DisplayPrompts.incorrectDisplay();
                break;
            }
        }
    }
}
