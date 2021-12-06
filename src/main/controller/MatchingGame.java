package controller;

import entity.*;
import gateways.database.MatchingGameHistorySQLDatabase;
import gateways.database.MatchingLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import usecase.BoardManager;
import views.DisplayPrompts;
import views.LoginOrSignupPage;
import views.MatchingGamePage;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class MatchingGame {
    private static String difficulty;
    private User user;

    public MatchingGame(User user,  String difficulty){
        this.user = user;
        MatchingGame.difficulty = difficulty;
    }


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

    public static boolean checkEnd(MatchingBoard board){
        return BoardManager.allFlipped(board);
    }


}
