package views;

import controller.PatternGame;
import entity.*;
import gateways.database.SQLDatabase;
import usecase.BoardManager;
import usecase.GameStatManager;
import usecase.IDatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * move to controller
 */

public class PatternGamePage {
    static JFrame frame1;
    private JPanel panel;
    private JLabel title, time, round;
    private JButton setting;
    private JLabel[][] tiles;
    private Font f1, f2;
    private Icon settingImg;
    private static final IDatabaseConnection db = new SQLDatabase();
    private static GameStatManager gm = new GameStatManager(db);
    private static PatternBoard board;
    private static int counter, currentCounter;
    private int rowNum, colNum, theme;
    private Timer timer;
    private User user;
    private Icon[] img;
    private Icon back;
    public ArrayList<Tile> tileList;


    /**
     * constructor
     * generates PatternGame window by selected difficulty and theme
     */
    public PatternGamePage(User user){
        //initialize variables
        frame1 = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("Pattern MEMORY");
        time = new JLabel("Time: 00:00");
        round = new JLabel("");
        setting = new JButton();
        settingImg = new ImageIcon(new ImageIcon("src/main/views/pictures/settings.png").getImage()
                .getScaledInstance(40,40,Image.SCALE_DEFAULT));
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font
        tiles = new JLabel[ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[0]]
                [ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[1]];
        board = DifficultyStrategy.valueOf(user.getDifficulty()).generatePatternBoard();
        theme = user.getTheme();
        tileList = board.getTileList();
        this.user = user;
        counter = 0;
        currentCounter = 0;


        // taken from MatchingGamePage
        settingImg = new ImageIcon(new ImageIcon("src/main/views/pictures/settings.png").getImage()
                .getScaledInstance(40,40,Image.SCALE_DEFAULT));
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font

        tiles = new JLabel[ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[0]]
                [ DifficultyStrategy.valueOf(user.getDifficulty()).setDimension()[1]];
        board = DifficultyStrategy.valueOf(user.getDifficulty()).generatePatternBoard();
        theme = user.getTheme();
        back = new ImageIcon(new ImageIcon("src/main/views/pictures/theme"+theme+"/imgBack.jpg").getImage()
                .getScaledInstance(60,60,Image.SCALE_DEFAULT));
        setImg();
        // above taken from MatchingGamePage

        //setup settings
        setting.setBounds(460,460,40,40);
        setting.setFont(new Font(title.getFont().getName(), Font.BOLD, 15));
        setting.setIcon(settingImg);
        setting.setBackground(Color.GRAY);
        setting.setOpaque(true);
        setting.addActionListener(e -> new GameSettingsPage(user));


        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,700);
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
                round.setText("Round:" + (counter+1));
                if (PatternGame.checkEnd(board, counter)) { // add this
                    timer.stop();
                    user.setTime(elapsed);
                    try {
                        gm.addPatternGameHistory(user);
                        new LeaderBoardPage(user, gm , db);
                        frame1.dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        timer = new Timer(1000, listener); // 1 sec
        timer.start();
        time.setBounds(780,440,100,50);
        time.setForeground(Color.green);
        time.setFont(f2);

        round.setBounds(780,200,100,50);
        round.setForeground(Color.green);
        round.setFont(f2);

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new JLabel();
                tiles[i][j].setBounds(40+160*i,100+90*j,60,60);
                tiles[i][j].setFont(f2);
                tiles[i][j].setIcon(back);
                panel.add(tiles[i][j]);
            }
        }


        int[] next = board.getIndexOfTile(tileList.get(counter));
        tiles[next[0]][next[1]].setIcon(img[0]); // rng the image LATER

        //add components and setup frame
        frame1.setBounds(0,0,960,700);
        panel.add(title);
        panel.add(time);
        panel.add(setting);
        panel.add(round);
        frame1.add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }


    /**
     * when the user flips a tile
     * @throws InterruptedException throws the InterruptedException
     */
    public void flipTile() throws InterruptedException {
        //player's move
        if (currentCounter ==0 ){
            for (JLabel[] tile : tiles) {
                for (JLabel jLabel : tile) {
                    jLabel.setIcon(back);
                }
            }
        }


        if (PatternGame.checkMove(board, counter, currentCounter,tileList,rowNum,colNum)){
            tiles[rowNum][colNum].setIcon(img[1]);
            currentCounter++;

            if(counter < currentCounter){
                currentCounter = 0;
                counter++;

            }
        }else {
            JOptionPane.showMessageDialog(new JFrame(), "Wrong move!");
            frame1.setVisible(false);
            new PatternGamePage(user);
        }
        boolean end = PatternGame.checkEnd(board,counter);
        if (currentCounter == 0 && !end) {
//            // computer's move
            int[] next = board.getIndexOfTile(tileList.get(counter));
            tiles[next[0]][next[1]].setIcon(img[0]);
            ActionListener listener = e -> {
                tiles[next[0]][next[1]].setIcon(back); // rng the image LATER
            };
            Timer t = new Timer(3000, listener);
            t.setRepeats(false);
            t.start();
        }else if (end){
            JOptionPane.showMessageDialog(new JFrame(), DisplayPrompts.winGameDisplay());
        }else{
            int[] next = board.getIndexOfTile(tileList.get(counter));
            tiles[next[0]][next[1]].setIcon(back); // rng the image LATER
        }

    }

    public void setImg(){
        String url;
        img = new ImageIcon[15];
        for (int i = 0; i < 15; i++) {
            url = "src/main/views/pictures/theme"+theme+"/img"+i+".jpg";
            img[i] = new ImageIcon(new ImageIcon(url).getImage()
                    .getScaledInstance(60,60,Image.SCALE_DEFAULT));
        }
    }

}
