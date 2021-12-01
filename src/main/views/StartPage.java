package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JButton pattern, matching, easy, medium, hard, theme1, theme2, theme3, play;
    private JLabel mode, difficulty, theme;
    private JButton[] modeArray, difficultyArray, themeArray;
    private int modeInput, difficultyInput, themeInput;

    private Font f;
    /**
     * default constructor
     * generate StartPage window
     */
    public StartPage(){
        //initialize variables
        frame = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("Start Menu");
        mode = new JLabel("Mode");
        difficulty = new JLabel("Difficulty");
        theme = new JLabel("Theme");
        pattern = new JButton("Pattern");
        matching = new JButton("Matching");
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");
        theme1 = new JButton("Theme 1");
        theme2 = new JButton("Theme 2");
        theme3 = new JButton("Theme 3");
        play = new JButton("Play!");
        modeArray = new JButton[]{pattern, matching};
        difficultyArray = new JButton[]{easy, medium, hard};
        themeArray = new JButton[]{theme1, theme2, theme3};
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        //setup panel
        panel.setLayout(null);
        panel.setSize(960,740);
        panel.setBackground(Color.WHITE);

        //setup labels
        title.setBounds(420,50,200,55);
        title.setFont(f);

        mode.setBounds(84, 170, 200,55);
        mode.setFont(f);
        mode.setHorizontalAlignment(SwingConstants.RIGHT);

        difficulty.setBounds(84, 320, 200,55);
        difficulty.setFont(f);
        difficulty.setHorizontalAlignment(SwingConstants.RIGHT);


        theme.setBounds(84, 470, 200,55);
        theme.setFont(f);
        theme.setHorizontalAlignment(SwingConstants.RIGHT);

        //setup buttons
        pattern.setBounds(320,170,250,55);
        pattern.setBackground(Color.YELLOW);
        pattern.setOpaque(true);
        pattern.setBorderPainted(false);
        pattern.addActionListener(e -> {
            modeInput = 1;
            highLightButton(modeArray, pattern);
        });

        matching.setBounds(620,170,250,55);
        matching.setBackground(Color.MAGENTA);
        matching.setOpaque(true);
        matching.setBorderPainted(false);
        matching.addActionListener(e -> {
            modeInput = 2;
            highLightButton(modeArray, matching);
        });

        easy.setBounds(320,320,150,55);
        easy.setBackground(Color.GREEN);
        easy.setOpaque(true);
        easy.setBorderPainted(false);
        easy.addActionListener(e -> {
            difficultyInput = 1;
            highLightButton(difficultyArray, easy);
        });

        medium.setBounds(520,320,150,55);
        medium.setBackground(Color.ORANGE);
        medium.setOpaque(true);
        medium.setBorderPainted(false);
        medium.addActionListener(e -> {
            difficultyInput = 2;
            highLightButton(difficultyArray, medium);
        });

        hard.setBounds(720,320,150,55);
        hard.setBackground(Color.RED);
        hard.setOpaque(true);
        hard.setBorderPainted(false);
        hard.addActionListener(e -> {
            difficultyInput = 3;
            highLightButton(difficultyArray, hard);
        });

        theme1.setBounds(320,470,150,55);
        theme1.setBackground(Color.PINK);
        theme1.setOpaque(true);
        theme1.setBorderPainted(false);
        theme1.addActionListener(e -> {
            themeInput = 1;
            highLightButton(themeArray, theme1);
        });

        theme2.setBounds(520,470,150,55);
        theme2.setBackground(Color.cyan);
        theme2.setOpaque(true);
        theme2.setBorderPainted(false);
        theme2.addActionListener(e -> {
            themeInput = 2;
            highLightButton(themeArray, theme2);
        });

        theme3.setBounds(720,470,150,55);
        theme3.setBackground(Color.green);
        theme3.setOpaque(true);
        theme3.setBorderPainted(false);
        theme3.addActionListener(e -> {
            themeInput = 3;
            highLightButton(themeArray, theme3);
        });

        play.setBounds(300,600,350,100);
        play.setBackground(Color.pink);
        play.setOpaque(true);
        play.setBorderPainted(false);
        play.addActionListener(e -> {
            if (isAllSelected()){
                if (difficultyInput == 1){
                    frame.setVisible(false);
                    new MatchingGame();
                }else {
                    frame.setVisible(false);
                    new PatternGame();
                }
            }
        });

        //add all JComponents to frame and set frame visible
        panel.add(title);
        panel.add(mode);
        panel.add(difficulty);
        panel.add(theme);
        panel.add(pattern);
        panel.add(matching);
        panel.add(easy);
        panel.add(medium);
        panel.add(hard);
        panel.add(theme1);
        panel.add(theme2);
        panel.add(theme3);
        panel.add(play);
        frame.setSize(960,740);
        frame.setResizable(false);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * input a button to be highligted and an array that contains that button
     * dehighlight the whole array, then highlight the desired one
     * @param array array contains JButton b
     * @param b Button to be highlighted
     */
    private void highLightButton(JButton[] array, JButton b){
        for (JButton jButton : array) {
            jButton.setBorderPainted(false);
        }
        b.setBorderPainted(true);
    }

    private boolean isAllSelected(){
        if (modeInput == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select mode!");
            return false;
        } else if (difficultyInput == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select difficulty!");
            return false;
        } else if (themeInput == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select theme!");
            return false;
        } else {
            return true;
        }
    }


    public static String[] startPage() {
        DisplayPrompts.welcomeMessage();
        String[] gameType = new String[3];
        gameType[0] = UserGameInput.getGameType();
        gameType[1] = UserGameInput.getUserDifficulty();
        return gameType;
    }

}