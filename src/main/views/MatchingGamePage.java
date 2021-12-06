package views;

import controller.MatchingGame;
import entity.DifficultyStrategy;
import gateways.database.MatchingGameHistorySQLDatabase;
import gateways.database.MatchingLeaderboardSQLDatabase;
import gateways.database.UserSQLDatabase;
import entity.MatchingBoard;
import usecase.BoardManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;

import java.sql.SQLException;

/**
 * move to controller
 */

public class MatchingGamePage {
    private JFrame frame;
    private JPanel panel;
    private JLabel title, time, totalMove;
    private JButton setting;
    private JLabel[][] tiles;
    private Font f1, f2;
    private static MatchingBoard board;
    private int rowNum, colNum, boardX, boardY;
    private int counter;
    private int[] move1, move2;

    /**
     *
     * constructor
     * generates MatchingGame window by selected difficulty and theme
     * @param difficulty difficultyInput from StartPage
     * @param theme themeInput from StartPage
     */
    public MatchingGamePage(String difficulty, int theme){
        //initialize variables
        frame = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("MEMORY MATCHING");
        time = new JLabel("Time: 00:00");
        totalMove = new JLabel("");
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font
        tiles = new JLabel[ DifficultyStrategy.valueOf(difficulty).setDimension()[0]]
                [ DifficultyStrategy.valueOf(difficulty).setDimension()[1]];
        board = DifficultyStrategy.valueOf(difficulty).generateMatchingBoard();

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);
        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowNum = (int) Math.floor((e.getX()-40)/100.0);
                colNum = (int) Math.floor((e.getY()-100)/60.0);
                if ((rowNum >= 0 && rowNum <= tiles.length)&&(colNum >= 0 && colNum <= tiles[0].length)) {
                    if(counter%2==0) {
                        move1 = new int[]{rowNum, colNum};
                        tiles[rowNum][colNum].setText("Flipped" + board.getTileKey(rowNum, colNum));//#TODO: change this to images later
                        counter++;

                    }else {
                        move2 = new int[]{rowNum, colNum};
                        tiles[rowNum][colNum].setText("Flipped"+ board.getTileKey(rowNum, colNum));//#TODO: change this to images later
                        if (board.getTileAtIndex(rowNum,colNum).getFlipped()){
                            tiles[move1[0]][move1[1]].setText("label" + move1[0] + "-"+ move1[1]);
                            tiles[move2[0]][move2[1]].setText("label" + move2[0] + "-"+ move2[1]);
                        }else if(MatchingGame.checkMatch(board, move1, move2)){
                            counter++;
                        }else {
                            tiles[move1[0]][move1[1]].setText("label" + move1[0] + "-"+ move1[1]);
                            tiles[move2[0]][move2[1]].setText("label" + move2[0] + "-"+ move2[1]);
                            counter++;
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
        frame.setBounds(0,0,960,540);
        panel.add(title);
        panel.add(time);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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

    public static void main (String [] args) throws SQLException { // more for testing, can delete
        UserSQLDatabase UserDatabase = new UserSQLDatabase();
        MatchingLeaderboardSQLDatabase LeaderboardDatabase = new MatchingLeaderboardSQLDatabase();
        MatchingGameHistorySQLDatabase GameHistoryDatabase = new MatchingGameHistorySQLDatabase();
        Scanner sc = new Scanner(System.in);
        System.out.println("Running the Matching Game from inside the MatchingGame class.");
        System.out.println("Would you like to play as a guest? (Y/N)");
        String guest = sc.nextLine();
        String username = "";
        if (guest.equals("N")) {
            String[] userData = LoginOrSignupPage.loginOrSignup(UserDatabase);
            username = userData[0];
        }
        //run the game mode including start page
        // String[] gameType = StartPage.startPage();
        String[] statistics = runMatchingGame("Easy", guest);
        int numMoves = Integer.parseInt(statistics[0]);
        long time = Long.parseLong(statistics[1]);
        String difficulty = statistics[2];
        if (guest.equals("N")) {
            Random rand = new Random();
            Integer GID = rand.nextInt();
            // Updates the leaderboard
            GameHistoryDatabase.addGameHistory(GID, username, numMoves, (double) (time / 1000), difficulty);
            LeaderboardDatabase.generateLeaderboard(difficulty);
        } else {
            System.out.println("Memory Matching Game Guest Statistics: ");
            System.out.println("Number of Moves: " + numMoves);
            System.out.println("Time: " + time);
            System.out.println("Difficulty: " + difficulty);
        }
    }

    /**
     * rowNum Getter
     * @return rowNum
     */
    public int getRowNum(){
        return rowNum;
    }

    /**
     * colNum Getter
     * @return colNum
     */
    public int getColNum(){
        return colNum;
    }
}