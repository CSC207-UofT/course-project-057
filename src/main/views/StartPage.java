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
    private int modeInput, themeInput;
    private String difficultyInput;

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
        difficultyInput = "";

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
            modeInput = 1;
            highLightButton(modeArray, pattern);
        });

        matching.setBounds(590,100,250,50);
        matching.setBackground(Color.MAGENTA);
        matching.setOpaque(true);
        matching.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        matching.setBorderPainted(false);
        matching.addActionListener(e -> {
            modeInput = 2;
            highLightButton(modeArray, matching);
        });

        easy.setBounds(290,200,150,50);
        easy.setBackground(Color.GREEN);
        easy.setOpaque(true);
        easy.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        easy.setBorderPainted(false);
        easy.addActionListener(e -> {
            difficultyInput = "easy";
            highLightButton(difficultyArray, easy);
        });

        medium.setBounds(490,200,150,50);
        medium.setBackground(Color.ORANGE);
        medium.setOpaque(true);
        medium.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        medium.setBorderPainted(false);
        medium.addActionListener(e -> {
            difficultyInput = "medium";
            highLightButton(difficultyArray, medium);
        });

        hard.setBounds(690,200,150,50);
        hard.setBackground(Color.RED);
        hard.setOpaque(true);
        hard.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        hard.setBorderPainted(false);
        hard.addActionListener(e -> {
            difficultyInput = "hard";
            highLightButton(difficultyArray, hard);
        });

        theme1.setBounds(290,300,150,50);
        theme1.setBackground(Color.PINK);
        theme1.setOpaque(true);
        theme1.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme1.setBorderPainted(false);
        theme1.addActionListener(e -> {
            themeInput = 1;
            highLightButton(themeArray, theme1);
        });

        theme2.setBounds(490,300,150,50);
        theme2.setBackground(Color.cyan);
        theme2.setOpaque(true);
        theme2.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme2.setBorderPainted(false);
        theme2.addActionListener(e -> {
            themeInput = 2;
            highLightButton(themeArray, theme2);
        });

        theme3.setBounds(690,300,150,50);
        theme3.setBackground(Color.green);
        theme3.setOpaque(true);
        theme3.setBorder(BorderFactory.createLineBorder(Color.WHITE,3));
        theme3.setBorderPainted(false);
        theme3.addActionListener(e -> {
            themeInput = 3;
            highLightButton(themeArray, theme3);
        });

        play.setBounds(300,400,350,80);
        play.setBackground(Color.pink);
        play.setOpaque(true);
        play.setBorderPainted(false);
        play.addActionListener(e -> {
            if (isAllSelected()){
                if (difficultyInput.equals("Easy")){
                    frame.setVisible(false);
                    new PatternGame();
                }else {
                    frame.setVisible(false);
                    new MatchingGame();
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
        frame.setSize(960,540);
        frame.setResizable(false);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * change value of modeInput. Value of modeInput should be restricted by 1 or 2.
     * @param i 1 for pattern game, 2 for matching game
     * @return true if modeInput is set properly
     */
    public boolean setModeInput(int i){
        if (i == 1 || i == 2) {
            this.modeInput = i;
            return true;
        } else {
            return false;
        }
    }

    /**
     * change value of difficultyInput. Value of difficultyInput should be restricted by 1, 2 or 3.
            return true;
        } else {
            return false;
        }
    }

    /**
     * change value of themeInput. Value of themeInput should be restricted by 1, 2 or 3.
     * @param i 1 for theme 1, 2 for theme 2, 3 for theme 3
     * @return true if themeInput is set properly
     */
    public boolean setThemeInput(int i){
        if (i == 1 || i == 2 || i ==3) {
            this.themeInput = i;
            return true;
        } else {
            return false;
        }
    }

    /**
     * modeInput getter
     * @return value of modeInput
     */
    public int getModeInput(){
        return this.modeInput;
    }

    /**
     * difficultyInput getter
     * @return value of difficultyInput
     */
        return this.difficultyInput;
    }

    /**
     * themeInput getter
     * @return value of themeInput
     */
    public int getThemeInput(){
        return this.themeInput;
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
        if (modeInput == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select mode!");
            return false;
        } else if (difficultyInput.equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Please select difficulty!");
            return false;
        } else if (themeInput == 0){
            JOptionPane.showMessageDialog(new JFrame(), "Please select theme!");
            return false;
        } else {
            return true;
        }
    }


    //old
    public static String[] startPage() {
        DisplayPrompts.welcomeMessage();
        String[] gameType = new String[3];
        gameType[0] = UserGameInput.getGameType();
        gameType[1] = UserGameInput.getUserDifficulty();
        return gameType;
    }

}
