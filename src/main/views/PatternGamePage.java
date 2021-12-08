package views;

import controller.MatchingGame;
import entity.*;
import usecase.BoardManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * move to controller
 */

public class PatternGamePage {
    static JFrame frame1;
    private JPanel panel;
    private JLabel title, time, totalMove;
    private JButton setting;
    private JLabel[][] tiles;
    private Font f1, f2;
    private Icon settingImg;
    private static MatchingBoard board;
    private int rowNum, colNum, theme;
    private Timer timer;
    User user;

    /**
     *
     * constructor
     * generates PatternGame window by selected difficulty and theme

     */
    public PatternGamePage(User user){
        //initialize variables
        frame1 = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("Pattern MEMORY");
        time = new JLabel("Time: 00:00");
        setting = new JButton();
        settingImg = new ImageIcon(new ImageIcon("src/main/views/pictures/settings.png").getImage()
                .getScaledInstance(40,40,Image.SCALE_DEFAULT));
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font
        tiles = new JLabel[ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[0]]
                [ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[1]];
        board = DifficultyStrategy.valueOf(user.getDifficulty()).generateMatchingBoard();
        theme = user.getTheme();

        //setup settings
        setting.setBounds(460,460,40,40);
        setting.setFont(new Font(title.getFont().getName(), Font.BOLD, 15));
        setting.setIcon(settingImg);
        setting.setBackground(Color.GRAY);
        setting.setOpaque(true);
        setting.addActionListener(e -> new MatchingGameSettingsPage(user));

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colNum = (int) Math.floor((e.getX()-40)/100.0);
                rowNum = (int) Math.floor((e.getY()-100)/60.0);
                if ((colNum >= 0 && colNum <= tiles.length)&&(rowNum >= 0 && rowNum <= tiles[0].length)) {
                    BoardManager.flipTile(board, rowNum, colNum);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //setup labels
        title.setBounds(320,30,300,50);
        title.setFont(f1);

        // for timer
        ActionListener listener = new ActionListener() {
            final long start = System.currentTimeMillis();
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - start;
                time.setText(((elapsed / (1000*60*60)) % 24) + ":" + ((elapsed / (1000*60)) % 60) + ":" + ((elapsed / 1000) % 60));
                if (MatchingGame.checkEnd(board)) {
                    timer.stop();
                }
            }
        };
        timer = new Timer(1000, listener); // 1 sec
        timer.start();
        time.setBounds(780,440,100,50);
        time.setForeground(Color.green);
        time.setFont(f2);

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new JLabel("label " + i + "-"+ j);
                tiles[i][j].setBounds(40+100*i,100+60*j,100,60);
                tiles[i][j].setFont(f2);
                tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.green, 2));
                panel.add(tiles[i][j]);
            }
        }

        //add components and setup frame
        frame1.setBounds(0,0,960,540);
        panel.add(title);
        panel.add(time);
        panel.add(setting);
        frame1.add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
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
            System.out.println(DisplayPrompts.printSpace());
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
            System.out.println(DisplayPrompts.winGameDisplay());
        }
        else {
            System.out.println(DisplayPrompts.loseGameDisplay());
        }

        statistics[0] = Long.toString((System.currentTimeMillis() - startTime)/ 1000);
        statistics[1] = difficulty;
        statistics[2] = Boolean.toString(allCorrect);
        return statistics;
    }

//    public static void main (String [] args) throws SQLException { // more for testing, can delete
//        UserSQLDatabase UserDatabase = new UserSQLDatabase();
//        PatternLeaderboardSQLDatabase PatternLeaderboardDatabase = new PatternLeaderboardSQLDatabase();
//        PatternGameHistorySQLDatabase PatternHistoryDatabase = new PatternGameHistorySQLDatabase();
//
//        //login
//        String[] userData = LoginOrSignupPage.loginOrSignup(UserDatabase);
//        String username = userData[0];
//
//        //run the game mode including start page
//        String[] gameType = StartPage.startPage();
//        String[] statistics = runPatternGame(gameType[1]); // test
//        long time = Long.parseLong(statistics[0]);
//        String difficulty = statistics[1];
//        if (statistics[2].equals("true")) {
//            Random rand = new Random();
//            Integer GID = rand.nextInt();
//            // Updates the leaderboard
//            PatternHistoryDatabase.addGameHistory(GID, username, (double) (time/1000), difficulty);
//            PatternLeaderboardDatabase.generateLeaderboard(difficulty);
//        }
//        PatternLeaderboardDatabase.generateLeaderboard(difficulty);
//    }
}
