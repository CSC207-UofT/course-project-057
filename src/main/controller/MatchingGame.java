package controller;

import entity.*;
import usecase.BoardManager;

public class MatchingGame {
    private static String difficulty;
    private User user;

    /**
     * default constructor for a matching game
     * @param user the player of the game
     * @param difficulty will determine the dimensions of the board
     */
    public MatchingGame(User user, String difficulty){
        this.user = user;
        MatchingGame.difficulty = difficulty;
    }

    /**
     * @param board the matching board that the player is playing on
     * @param move1 user's first move
     * @param move2 user's second move
     * @return if the two moves have the same key/if the two moves are a match
     */
    public static boolean checkMatch(MatchingBoard board, int[] move1, int[] move2){
        //implement later
        int move1Key = board.getTileKey(move1[0], move1[1]);
        int move2Key = board.getTileKey(move2[0], move2[1]);
        if (!(move1Key == move2Key)) {
            BoardManager.flipTile(board, move1[0], move1[1]);
            BoardManager.flipTile(board, move2[0], move2[1]);
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param board the board the user is currently playing on
     * @return if the game is over (all tiles are un-flipped), return true. Otherwise, return false
     */
    public static boolean checkEnd(MatchingBoard board){
        return BoardManager.allFlipped(board);
    }


}
