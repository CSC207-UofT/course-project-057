package views;

import gateways.database.UserSQLDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GameRulesPage {
    private JFrame frame;
    private JPanel panel;
    private JButton home, matchingRules, patternRules;
    private JLabel title;
    private Font f;

    /**
     * default constructor
     * generates userLogin window
     */
    public GameRulesPage(){
        //initialize the variables
        frame = new JFrame("Game Rules");
        panel = new JPanel();
        home = new JButton("Home");
        matchingRules = new JButton("Matching Rules");
        patternRules = new JButton("Pattern Rules");
        title = new JLabel("Game Rules");
        f = new Font(title.getFont().getName(), Font.PLAIN, 18);

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,540,540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(190,88,200,55);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 25));

        //setup home button
        home.setBounds(30,430, 55,55);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        //Might have to add something here (getter and setter to output the changes)
        home.addActionListener(e -> {new LoginOrSignupPage();
            frame.setVisible(false);});

        //setup buttons
        matchingRules.setBounds(180,220,180,60);
        matchingRules.setFont(f);
        matchingRules.setBackground(Color.ORANGE);
        matchingRules.setOpaque(true);
        matchingRules.setBorderPainted(false);
        //matchingRules.addActionListener(e->{new DisplayPrompts().matchingRules();}); //todo

        patternRules.setBounds(180,330,180,60);
        patternRules.setFont(f);
        patternRules.setBackground(Color.ORANGE);
        patternRules.setOpaque(true);
        patternRules.setBorderPainted(false); //todo

        //add all JComponents to frame and set frame visible
        frame.setSize(540,540);
        frame.setResizable(false);
        panel.add(title);
        panel.add(home);
        panel.add(matchingRules);
        panel.add(patternRules);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

