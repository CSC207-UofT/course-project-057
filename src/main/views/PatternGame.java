package views;

import entity.*;
import gateways.database.PatternGameHistorySQLDatabase;
import gateways.database.PatternLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import usecase.BoardManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * move to controller
 */

public class PatternGame {
    JFrame frame;
    JPanel panel;
    JLabel title, time;
    JButton setting;
    JLabel[] Board;
    Font f1, f2;

    /**
     * default constructor
     * generates PatternGame window
     */
    public PatternGame(){
        //initialize variables
        frame = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("PATTERN MEMORY");
        time = new JLabel("Time: 00:00");
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(320,30,300,50);
        title.setFont(f1);

        time.setBounds(780,440,100,50);
        time.setForeground(Color.green);
        //time.setBorder(BorderFactory.createBevelBorder(0,Color.green,Color.green));
        time.setFont(f2);

        //add components and setup frame
        frame.setBounds(0,0,960,540);
        panel.add(title);
        panel.add(time);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static String[] runPatternGame(String difficulty) {
        String[] statistics = new String[3];
       // String difficulty = UserGameInput.getUserDifficulty(); // delete, this is moved to StartGame

        PatternBoard patternBoard = DifficultyStrategy.valueOf(difficulty).generatePatternBoard();
        ArrayList<Tile> tileList = patternBoard.getTileList();

        // shows the pattern one tile at a time
        int counter = 1;
        long startTime = System.currentTimeMillis();
        boolean allCorrect = true;

        while (counter <= patternBoard.totalTiles && allCorrect) {
            int[] index = patternBoard.getIndexOfTile(tileList.get(counter-1));
            BoardManager.flipTile(patternBoard, index[0], index[1]);
            System.out.println(patternBoard);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            DisplayPrompts.printSpace();
            BoardManager.unflipAll(patternBoard);
            System.out.println(patternBoard);
            // gets user input
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
            if (allCorrect) {
                counter++;
            }
        }
        if (allCorrect) {
            DisplayPrompts.winGameDisplay();
        }
        else {
            DisplayPrompts.loseGameDisplay();
        }

        statistics[0] = Long.toString((System.currentTimeMillis() - startTime)/ 1000);
        statistics[1] = difficulty;
        statistics[2] = Boolean.toString(allCorrect);
        return statistics;
    }

    public static void main (String [] args) throws SQLException { // more for testing, can delete
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        PatternLeaderboardSQLDatabase PatternLeaderboardDatabase = new PatternLeaderboardSQLDatabase();
        PatternGameHistorySQLDatabase PatternHistoryDatabase = new PatternGameHistorySQLDatabase();

        //login
        String[] userData = LoginOrSignup.loginOrSignup(UserDatabase);
        String username = userData[0];

        //run the game mode including start page
        String[] gameType = StartPage.startPage();
        String[] statistics = runPatternGame(gameType[1]); // test
        long time = Long.parseLong(statistics[0]);
        String difficulty = statistics[1];
        if (statistics[2].equals("true")) {
            Random rand = new Random();
            Integer GID = rand.nextInt();
            // Updates the leaderboard
            PatternHistoryDatabase.addGameHistory(GID, username, (double) (time/1000), difficulty);
            PatternLeaderboardDatabase.generateLeaderboard(difficulty);
        }
        PatternLeaderboardDatabase.generateLeaderboard(difficulty);
    }
}
