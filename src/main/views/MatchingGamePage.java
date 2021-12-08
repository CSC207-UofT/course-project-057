package views;

import controller.MatchingGame;
import entity.DifficultyStrategy;
import entity.User;
import entity.MatchingBoard;
import gateways.database.SQLDatabase;
import usecase.BoardManager;
import usecase.GameStatManager;
import usecase.IDatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

/**
 * move to controller
 */

public class MatchingGamePage {
    static JFrame frame1;
    private JPanel panel;
    private JLabel title, time, totalMove;
    private JButton setting;
    private JLabel[][] tiles;
    private Font f1, f2;
    private Icon settingImg;
    private static MatchingBoard board;
    private static final IDatabaseConnection db = new SQLDatabase();
    private static GameStatManager gm = new GameStatManager(db);
    private int rowNum, colNum;
    private Timer timer;
    private int counter, theme;
    private int[] move1, move2;
    private static User user;
    private Icon[] img;
    private Icon back;

    /**
     *
     * constructor
     * generates MatchingGame window by selected difficulty and theme
     * @param user user of current game
     */
    public MatchingGamePage(User user){
        //initialize variables
        frame1 = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("MEMORY MATCHING");
        time = new JLabel();
        totalMove = new JLabel();
        move1 = new int[]{-1,-1};
        move2 = new int[]{-1,-1};
        setting = new JButton();
        settingImg = new ImageIcon(new ImageIcon("src/main/views/pictures/settings.png").getImage()
                .getScaledInstance(40,40,Image.SCALE_DEFAULT));
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font
        MatchingGamePage.user = user;
        tiles = new JLabel[ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[0]]
                [ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[1]];
        board = DifficultyStrategy.valueOf(user.getDifficulty()).generateMatchingBoard();
        theme = user.getTheme();
        back = new ImageIcon(new ImageIcon("src/main/views/pictures/theme"+theme+"/imgBack.jpg").getImage()
                .getScaledInstance(60,60,Image.SCALE_DEFAULT));
        setImg();

        //setup settings
        setting.setBounds(900,40,40,40);
        setting.setFont(new Font(title.getFont().getName(), Font.BOLD, 15));
        setting.setIcon(settingImg);
        setting.setBackground(Color.GRAY);
        setting.setOpaque(true);
        setting.addActionListener(e -> {
            frame1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            new GameSettingsPage(user);});

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((e.getX()-40)%160<=60 && (e.getY()-100)%90<=60) {//make sure user click the tile
                    rowNum = (int) Math.floor((e.getX() - 40) / 160.0);
                    colNum = (int) Math.floor((e.getY() - 100) / 90.0);
                    if ((rowNum >= 0 && rowNum <= tiles.length) && (colNum >= 0 && colNum <= tiles[0].length)) {
                        try {
                            flipTile();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
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
                totalMove.setText(Integer.toString(user.getNumMove()));
                if (MatchingGame.checkEnd(board)) {
                    timer.stop();
                    user.setTime(elapsed);
                    try {
                        gm.addMatchingGameHistory(user);
                        new LeaderBoardPage(user,gm , db);
                        frame1.dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        timer = new Timer(500, listener); // 0.5 sec
        timer.start();
        time.setBounds(890,440,100,50);
        time.setForeground(Color.green);
        time.setFont(f2);

        totalMove.setBounds(890,400,100,50);
        totalMove.setForeground(Color.green);
        totalMove.setFont(f2);

        //setup JLabel tiles
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new JLabel();
                tiles[i][j].setIcon(back);
                tiles[i][j].setBounds(40+160*i,100+90*j,60,60);
                tiles[i][j].setFont(f2);
                panel.add(tiles[i][j]);
            }
        }

        //add components and setup frame
        frame1.setBounds(0,0,960,540);
        panel.add(title);
        panel.add(time);
        panel.add(setting);
        panel.add(totalMove);
        frame1.add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

    }

    public void flipTile() throws InterruptedException {
        if (!board.getTileAtIndex(rowNum,colNum).getFlipped()) {//do if the tile clicked has not flipped yet
            user.setNumMove(counter/2);
            if (counter % 2 == 0) {//if move number is even then store this move in move1[]
                if (!(move1[0]==-1 && move2[0]==-1)){
                    if (!MatchingGame.checkMatch(board, move1, move2)) {
                        tiles[move1[0]][move1[1]].setIcon(back);
                        tiles[move2[0]][move2[1]].setIcon(back);
                    }
                }
                move1 = new int[]{rowNum, colNum};
                BoardManager.flipTile(board, move1[0], move1[1]);
            } else {//otherwise, store it move2
                move2 = new int[]{rowNum, colNum};
                BoardManager.flipTile(board, move2[0], move2[1]);
            }
            tiles[rowNum][colNum].setIcon(img[board.getTileKey(rowNum,colNum)]);
            counter++;
            if (MatchingGame.checkEnd(board)){
                user.setNumMove(counter/2);
                JOptionPane.showMessageDialog(new JFrame(), DisplayPrompts.winGameDisplay());

            }
        }
    }

    public void setImg(){
        String url ;
        img = new ImageIcon[15];
        for (int i = 0; i < 15; i++) {
            url = "src/main/views/pictures/theme"+theme+"/img"+i+".jpg";
            img[i] = new ImageIcon(new ImageIcon(url).getImage()
                    .getScaledInstance(60,60,Image.SCALE_DEFAULT));
        }
    }

    /**
     * runs a new game mode
     * @return number of moves, the time and difficulty of the finished game mode
     */
    public static String[] runMatchingGame(String difficulty, String guest) {
        //get user difficulty

        String[] statistics = new String[3];
        // String difficulty = UserGameInput.getUserDifficulty(); // delete, this is moved to StartPage
        int numMoves = 0;


        //MatchingBoard board = DifficultyStrategy.valueOf(difficulty).generateMatchingBoard();
        // System.out.println("Input a row number from 1 to " + board.getNumRows()
        //        + " and a column from 1 to " + (board.getNumCols()) + ". Tile must not be revealed.");
        System.out.println(DisplayPrompts.enterMoveDisplay(board.getNumRows(), board.getNumCols()));
        System.out.println(board);
        long startTime = System.currentTimeMillis();

        // Game runs until all tiles are flipped
        while(!BoardManager.allFlipped(board)) {
            int[] move1 = BoardManager.Move(board, "Matching", 0);
            int[] move2 = BoardManager.Move(board, "Matching", 0);
            int move1Key = board.getTileKey(move1[0], move1[1]);
            int move2Key = board.getTileKey(move2[0], move2[1]);

            System.out.println(DisplayPrompts.matchDisplay(move1Key == move2Key));
            if (!(move1Key == move2Key)) {
                BoardManager.flipTile(board, move1[0], move1[1]);
                BoardManager.flipTile(board, move2[0], move2[1]);
                System.out.println(board);
            }

            /* done in above if statement and display prompt
            if (move1Key == move2Key)  {
               System.out.println("Match");
            }
            else {
                // If no match, flip them back
                BoardManager.flipTile(board, move1[0], move1[1]);
                BoardManager.flipTile(board, move2[0], move2[1]);
                System.out.println("No Match!");
                System.out.println(board);
            }
            */
            numMoves++;
        }
        System.out.println(DisplayPrompts.winGameDisplay());
        // System.out.println("Congratulations! You matched all the tiles.");
        statistics[0] = Integer.toString(numMoves);
        statistics[1] = Long.toString(System.currentTimeMillis() - startTime);
        statistics[2] = difficulty;
        /* add to leaderboard in this method
        if (guest.equals("N")) {
            Random rand = new Random();
            Integer GID = rand.nextInt();
            // Updates the leaderboard
            GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time / 1000), difficulty);
            LeaderboardDatabase.generateLeaderboard(difficulty);
        }
         */
        return statistics;
    }

//    public static void main (String [] args) throws SQLException { // more for testing, can delete
//        UserSQLDatabase UserDatabase = new UserSQLDatabase();
//        MatchingLeaderboardSQLDatabase LeaderboardDatabase = new MatchingLeaderboardSQLDatabase();
//        MatchingGameHistorySQLDatabase GameHistoryDatabase = new MatchingGameHistorySQLDatabase();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Running the Matching Game from inside the MatchingGame class.");
//        System.out.println("Would you like to play as a guest? (Y/N)");
//        String guest = sc.nextLine();
//        String username = "";
//        if (guest.equals("N")) {
//            String[] userData = LoginOrSignupPage.loginOrSignup(UserDatabase);
//            username = userData[0];
//        }
//        //run the game mode including start page
//        // String[] gameType = StartPage.startPage();
//        String[] statistics = runMatchingGame("Easy", guest);
//        int numMoves = Integer.parseInt(statistics[0]);
//        long time = Long.parseLong(statistics[1]);
//        String difficulty = statistics[2];
//        if (guest.equals("N")) {
//            Random rand = new Random();
//            Integer GID = rand.nextInt();
//            // Updates the leaderboard
//            GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time / 1000), difficulty);
//            LeaderboardDatabase.generateLeaderboard(difficulty);
//        } else {
//            System.out.println("Memory Matching Game Guest Statistics: ");
//            System.out.println("Number of Moves: " + numMoves);
//            System.out.println("Time: " + time);
//            System.out.println("Difficulty: " + difficulty);
//        }
//    }

}

