import gateways.database.GameHistorySQLDatabase;
import gateways.database.LeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import views.LoginOrSignup;

import java.sql.SQLException;
import java.util.Random;

/**
 * runs the whole program.
 */
public class Main {
    public static void main (String [] args) throws SQLException {
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        LeaderboardSQLDatabase LeaderboardDatabase = new LeaderboardSQLDatabase();
        GameHistorySQLDatabase GameHistoryDatabase = new GameHistorySQLDatabase();

        //login
        String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];

        //run the game mode
        String[] statistics = runGame();
        int numMoves = Integer.parseInt(statistics[0]);
        long time = Long.parseLong(statistics[1]);
        String difficulty = statistics[2];

        Random rand = new Random();
        Integer GID = rand.nextInt();
        // Updates the leaderboard
        GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time/1000), difficulty);
        LeaderboardDatabase.generateLeaderboard(difficulty);
    }
}
