import gateways.database.*;
import gateways.database.UserSQLDatabase;
import views.*;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * runs the whole program - add test here
 */
public class Main {
    public static void main (String [] args) throws SQLException {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        MatchingLeaderboardSQLDatabase MatchingLeaderboardSQLDatabase = new MatchingLeaderboardSQLDatabase();
        MatchingGameHistorySQLDatabase GameHistoryDatabase = new MatchingGameHistorySQLDatabase();
        PatternLeaderboardSQLDatabase PatternLeaderboardSQLDatabase = new PatternLeaderboardSQLDatabase();
        PatternGameHistorySQLDatabase PatternHistorySQLDatabase = new PatternGameHistorySQLDatabase();

        new LoginOrSignupPage();
        //guest mode?
        Scanner sc =new Scanner(System.in);
        System.out.println(DisplayPrompts.guestMessage());
        String mode = sc.next();
        boolean signed = false;
        String username = "";
        while (!signed){
            if (mode.equals("N")) {
                //login
                String[] userData = LoginOrSignupPage.loginOrSignup(UserDatabase);
                 username = userData[0];
                signed = true;
            } else if(mode.equals("Y")) {
                signed = true;
            } else {
                System.out.println(DisplayPrompts.guestMessage());
            }
        }

        //run the game mode, testing only
        String [] gameType = StartPage.startPage();
        if (gameType[0].equals("Matching")) {
            String[] statistics = MatchingGamePage.runMatchingGame(gameType[1], mode);
            int numMoves = Integer.parseInt(statistics[0]);
            long time = Long.parseLong(statistics[1]);
            String difficulty = statistics[2];
            if (mode.equals("N")){
                System.out.println("Working to add on leaderboard.");
            } else {
                System.out.println("Memory Matching Game Guest Statistics: ");
                System.out.println("Number of Moves: " + numMoves);
                System.out.println("Time: " + time);
                System.out.println("Difficulty: " + difficulty);
            }
        } else {
            String[] statistics = PatternGamePage.runPatternGame(gameType[1]);
            long time = Long.parseLong(statistics[0]);
            String difficulty = statistics[1];
            if (mode.equals("N")){
                System.out.println("Working to add on leaderboard.");
            } else {
                System.out.println("Memory Pattern Game Guest Statistics: ");
                System.out.println("Time: " + time);
                System.out.println("Difficulty: " + difficulty);
            }
        }

        // this part below is for the non-guest mode
        //run the game mode including start page



        /*
         * don't know if code below is needed for testing in guest mode, but will leave it just in case
         *
        Random rand = new Random();
        Integer GID = rand.nextInt();
        if (mode.equals("N")){
            // Updates the leaderboard
            GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time / 1000), difficulty);
            LeaderboardDatabase.generateLeaderboard(difficulty);
            */
        }
    }
