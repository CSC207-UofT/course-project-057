package views;


import entity.User;

import javax.swing.*;
import java.awt.*;

public class StartPage {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JButton pattern, matching, easy, medium, hard, theme1, theme2, theme3, play, home;
    private JLabel mode, difficulty, theme;
    private JButton[] modeArray, difficultyArray, themeArray;
    private User user;

    private Font f;
    /**
     * default constructor
     * generate StartPage window
     */
    public StartPage(User user){
        //initialize variables
        frame = new JFrame("Memory Game");
        panel = new JPanel();
        title = new JLabel("Start Menu");
        mode = new JLabel("Mode");
        difficulty = new JLabel("Difficulty");
        theme = new JLabel("controller.Theme");
        pattern = new JButton("Pattern");
        matching = new JButton("Matching");
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");
        theme1 = new JButton("controller.Theme 1");
        theme2 = new JButton("controller.Theme 2");
        theme3 = new JButton("controller.Theme 3");
        play = new JButton("Play!");
        home = new JButton("Home");
        modeArray = new JButton[]{pattern, matching};
        difficultyArray = new JButton[]{easy, medium, hard};
        themeArray = new JButton[]{theme1, theme2, theme3};
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);
        this.user = user;

        //setup panel
        panel.setLayout(null);
        panel.setSize(960,540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(420,30,200,50);
        title.setFont(f);

        mode.setBounds(40, 100, 200,50);
        mode.setFont(f);
        mode.setHorizontalAlignment(SwingConstants.RIGHT);

        difficulty.setBounds(40, 200, 200,50);
        difficulty.setFont(f);
        difficulty.setHorizontalAlignment(SwingConstants.RIGHT);


        theme.setBounds(40, 300, 200,50);
        theme.setFont(f);
        theme.setHorizontalAlignment(SwingConstants.RIGHT);

        //setup buttons
        pattern.setBounds(290,100,250,50);
        pattern.setBackground(Color.YELLOW);
        pattern.setOpaque(true);
        pattern.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        pattern.setBorderPainted(false);
        pattern.addActionListener(e -> {
            user.setMode(1);
            highLightButton(modeArray, pattern);
        });

        matching.setBounds(590,100,250,50);
        matching.setBackground(Color.MAGENTA);
        matching.setOpaque(true);
        matching.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        matching.setBorderPainted(false);
        matching.addActionListener(e -> {
            user.setMode(2);
            highLightButton(modeArray, matching);
        });

        easy.setBounds(290,200,150,50);
        easy.setBackground(Color.GREEN);
        easy.setOpaque(true);
        easy.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        easy.setBorderPainted(false);
        easy.addActionListener(e -> {
            user.setDifficulty("Easy");
            highLightButton(difficultyArray, easy);
        });

        medium.setBounds(490,200,150,50);
        medium.setBackground(Color.ORANGE);
        medium.setOpaque(true);
        medium.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        medium.setBorderPainted(false);
        medium.addActionListener(e -> {
            user.setDifficulty("Medium");
            highLightButton(difficultyArray, medium);
        });

        hard.setBounds(690,200,150,50);
        hard.setBackground(Color.RED);
        hard.setOpaque(true);
        hard.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        hard.setBorderPainted(false);
        hard.addActionListener(e -> {
            user.setDifficulty("Hard");
            highLightButton(difficultyArray, hard);
        });

        theme1.setBounds(290,300,150,50);
        theme1.setBackground(Color.PINK);
        theme1.setOpaque(true);
        theme1.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme1.setBorderPainted(false);
        theme1.addActionListener(e -> {
            user.setTheme(1);
            highLightButton(themeArray, theme1);
        });

        theme2.setBounds(490,300,150,50);
        theme2.setBackground(Color.cyan);
        theme2.setOpaque(true);
        theme2.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme2.setBorderPainted(false);
        theme2.addActionListener(e -> {
            user.setTheme(2);
            highLightButton(themeArray, theme2);
        });

        theme3.setBounds(690,300,150,50);
        theme3.setBackground(Color.green);
        theme3.setOpaque(true);
        theme3.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme3.setBorderPainted(false);
        theme3.addActionListener(e -> {
            user.setTheme(3);
            highLightButton(themeArray, theme3);
        });

        play.setBounds(300,400,350,80);
        play.setBackground(Color.pink);
        play.setOpaque(true);
        play.setBorderPainted(false);
        play.addActionListener(e -> {
            if (isAllSelected()){
                if (user.getMode() == 1){
                    frame.setVisible(false);
                    new PatternGamePage(user);
                }else {
                    frame.setVisible(false);
                    new MatchingGamePage(user);
                }
            }
        });

        home.setBounds(30,430, 60,60);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        home.addActionListener(e -> {new LoginOrSignupPage(); frame.setVisible(false);});

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
        panel.add(home);
        frame.setSize(960,540);
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

    /**
     * check if every parameter is selected
     * @return true if mode, difficulty and theme are all selected.
     *         Otherwise return false
     */
    private boolean isAllSelected(){
        if (user.getMode() == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select mode!");
            return false;
        } else if (user.getDifficulty().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Please select difficulty!");
            return false;
        } else if (user.getTheme() == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select theme!");
            return false;
        } else {
            return true;
        }
    }


    //old
    public static String[] startPage() {
        System.out.println(DisplayPrompts.welcomeMessage());
        String[] gameType = new String[3];
        gameType[0] = UserGameInput.getGameType();
        gameType[1] = UserGameInput.getUserDifficulty();
        return gameType;

    }

}
