package views;

import entity.User;

import javax.swing.*;
import java.awt.*;


public class MatchingGameSettingsPage {
    private JFrame frame2;
    private JPanel panel;
    private JButton resume, restart, home;
    private JLabel title;
    private Font f;
    private static User user;
    private static String usernameInput, passwordInput;

    /**
     * default constructor
     * generates userLogin window
     */
    public MatchingGameSettingsPage(User user){
        //initialize the variables
        frame2 = new JFrame("Settings");
        panel = new JPanel();
        resume = new JButton("Resume");
        restart = new JButton("Restart");
        home = new JButton("Home");
        title = new JLabel("Settings");
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);
        MatchingGameSettingsPage.user = user;

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
        home.addActionListener(e -> {
            MatchingGamePage.frame1.dispose();
            frame2.dispose();
            new LoginOrSignupPage(user);
            frame2.setVisible(false);});

        //setup buttons
        resume.setBounds(180,280,180,55);
        resume.setBackground(Color.CYAN);
        resume.setOpaque(true);
        resume.setBorderPainted(false);
        resume.addActionListener(e -> frame2.setVisible(false));

        restart.setBounds(180,350,180,55);
        restart.setBackground(Color.CYAN);
        restart.setOpaque(true);
        restart.setBorderPainted(false);
        restart.addActionListener(e ->{
            user.reset();
            MatchingGamePage.frame1.dispose();
            new StartPage(user);
            frame2.setVisible(false);});
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
        frame2.setSize(540,540);
        frame2.setResizable(false);
        panel.add(resume);
        panel.add(title);
        panel.add(home);
        panel.add(restart);
        frame2.add(panel);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }
}