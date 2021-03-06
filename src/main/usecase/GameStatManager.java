package usecase;

import java.util.ArrayList;
import java.util.Random;
import entity.*;
import java.sql.*;

public class GameStatManager {
    private final IDatabaseConnection database;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public GameStatManager(IDatabaseConnection database) {
        this.database = database;
    }

    private Connection getConnection() throws SQLException {
        return this.database.getConnection();
    }

    /**
     * This method adds a game record to the Pattern game history database table
     * @throws SQLException provides information on a database access error
     */
    public void addPatternGameHistory(User user)
            throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()){
            // Insert game info to the table 'GameHistory'
            Random rand = new Random();
            int GID = rand.nextInt(1000);
            String Username = user.getUsername();
            double Time = user.getTime();
            String Difficulty = user.getDifficulty();
            stmt.executeUpdate("INSERT INTO PatternGameHistory VALUES" + "('" + GID +
                    "', '" + Username + "', '" +  Time + "', '" + Difficulty + "')" );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {};
        }
    }

    /**
     * This method adds a game record to the Matching game history database
     * @throws SQLException provides information on a database access error
     */
    public void addMatchingGameHistory(User user)
            throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()){
            // Insert game info to the table 'GameHistory'
            Random rand = new Random();
            int GID = rand.nextInt(1000);
            String Username = user.getUsername();
            double Time = user.getTime();
            String Difficulty = user.getDifficulty();
            int TotalMoves = user.getNumMove();
            stmt.executeUpdate("INSERT INTO MatchingGameHistory VALUES" + "('" + GID +
                    "', '" + Username + "', '" + TotalMoves + "', '" + Time + "', '" + Difficulty + "')" );
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
    }

    /**
     * Generates a Leaderboard for matching game
     * @param Difficulty the difficulty of game mode that leaderboard should display
     * @throws SQLException provides information on a database access error
     */
    // return type was void in PR62
    public String[][] generateMatchingLeaderboard(String Difficulty) throws SQLException {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            // updates a leaderboard according to the given difficulty
            ResultSet rs = stmt.executeQuery("SELECT DENSE_RANK AS rank, username, totalmoves, time FROM " +
                    "(SELECT *, DENSE_RANK() OVER(ORDER BY totalmoves, time) FROM MatchingGameHistory WHERE " +
                    "difficulty = '" + Difficulty + "') AS t1 WHERE DENSE_RANK <= 10 ORDER BY DENSE_RANK;");
            System.out.println("Leaderboard for difficulty " + Difficulty);
            while (rs.next()) {
                String Rank = rs.getString("rank");
                String Username = rs.getString("Username");
                String TotalMoves = rs.getString("totalmoves");
                int Time = Integer.parseInt(rs.getString("Time"));
                String formatted = (((Time / (1000*60*60)) % 24) + ":" + ((Time / (1000*60)) % 60) + ":" + ((Time/ 1000) % 60));
                System.out.println("Rank: " + Rank + ", Username: " + Username
                        + ", TotalMoves: " + TotalMoves + ", Time: " + formatted);
                list.add(new String[]{Rank, Username, TotalMoves, formatted}); // changed after PR62
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        // for loop and return added after PR62
        String [][] list2d = new String[20][4];
        String [] row;
        for (int i = 0; i < list.size(); i++) {
            row = list.get(i);
            System.arraycopy(row, 0, list2d[i], 0, 4);
        }
        return list2d;
    }

    /**
     * Generates a Leaderboard for pattern game
     * @param Difficulty the difficulty of game mode that leaderboard should display
     * @throws SQLException provides information on a database access error
     */
    public String[][] generatePatternLeaderboard(String Difficulty) throws SQLException {
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
            // updates a leaderboard according to the given difficulty
            ResultSet rs = stmt.executeQuery("SELECT DENSE_RANK AS rank, username, time FROM " +
                    "(SELECT *, DENSE_RANK() OVER(ORDER BY time) FROM PatternGameHistory WHERE " +
                    "difficulty = '" + Difficulty + "') AS t1 WHERE DENSE_RANK <= 10 ORDER BY DENSE_RANK;");
            System.out.println("Leaderboard for difficulty " + Difficulty);
            while (rs.next()) {
                String Rank = rs.getString("rank");
                String Username = rs.getString("Username");
                int Time = Integer.parseInt(rs.getString("Time"));
                String formatted = (((Time / (1000*60*60)) % 24) + ":" + ((Time / (1000*60)) % 60) + ":" + ((Time/ 1000) % 60));
                System.out.println("Rank: " + Rank + ", Username: " + Username
                        + ", Time: " + formatted);
                list.add(new String[]{Rank, Username, formatted}); // changed after PR62
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        // for loop and return added after PR62
        String [][] list2d = new String[20][3];
        String [] row;
        for (int i = 0; i < list.size(); i++) {
            row = list.get(i);
            System.arraycopy(row, 0, list2d[i], 0, 3);
        }
        return list2d;
    }

}
