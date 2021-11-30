import gateways.database.MatchingGameHistorySQLDatabase;
import gateways.database.MatchingLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import views.LoginOrSignup;
import views.MatchingGame;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

/**
 * runs the whole program - add test here
 */
public class Main {
    public static void main (String [] args) throws SQLException {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        MatchingLeaderboardSQLDatabase LeaderboardDatabase = new MatchingLeaderboardSQLDatabase();
        MatchingGameHistorySQLDatabase GameHistoryDatabase = new MatchingGameHistorySQLDatabase();

        //guest mode?
        Scanner sc =new Scanner(System.in);
        System.out.println("Guest mode? (Y/N)");
        String mode = sc.next();
        boolean signed = false;
        String username = "";
        while (!signed){
            if (mode.equals("N")) {
                //login
                String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
                 username = userData[0];
                signed = true;
            } else if(mode.equals("Y")) {
                signed = true;
            } else {
                System.out.println("Please enter 'Y'(yes) or 'N'(no)");
            }
        }

        //run the game mode
        String[] statistics = MatchingGame.runGame();
        int numMoves = Integer.parseInt(statistics[0]);
        long time = Long.parseLong(statistics[1]);
        String difficulty = statistics[2];

        Random rand = new Random();
        Integer GID = rand.nextInt();
        if (mode.equals("N")){
            // Updates the leaderboard
            GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time / 1000), difficulty);
            LeaderboardDatabase.generateLeaderboard(difficulty);
        }
    }
}
