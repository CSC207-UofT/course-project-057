package views;

import gateways.database.UserSQLDatabase;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GameSettingsPage {
    private JFrame frame;
    private JPanel panel;
    private JButton resume, home;
    private JLabel title;
    private Font f;
    private static String usernameInput, passwordInput;

    /**
     * default constructor
     * generates userLogin window
     */
    public GameSettingsPage(){
        //initialize the variables
        frame = new JFrame("Settings");
        panel = new JPanel();
        resume = new JButton("Resume");
        home = new JButton("Home");
        title = new JLabel("Settings");
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,540,540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(180,88,250,55);
        title.setFont(f);

        /*
        usernameLabel.setBounds(57,180,150,45);
        usernameLabel.setFont(f);

        passwordLabel.setBounds(57,270,150,45);
        passwordLabel.setFont(f);



        //setup textfields
        username.setBounds(250,180,200,45);
        password.setBounds(250,270,200,45);
        */
        //setup home button
        home.setBounds(180,210, 180,55);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        //Might have to add something here (getter and setter to output the changes)
        home.addActionListener(e -> {new LoginOrSignupPage();
            frame.setVisible(false);});

        //setup buttons
        resume.setBounds(180,340,180,55);
        resume.setBackground(Color.CYAN);
        resume.setOpaque(true);
        resume.setBorderPainted(false);
        resume.addActionListener(e ->{frame.setVisible(false);});

        //command for what happens when you click login
        /*
        resume.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();
            //#TODO: login
            new StartPage();
            frame.setVisible(false);
        });
        */

        //add all JComponents to frame and set frame visible
        frame.setSize(540,540);
        frame.setResizable(false);
        panel.add(resume);
        panel.add(title);
        panel.add(home);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
