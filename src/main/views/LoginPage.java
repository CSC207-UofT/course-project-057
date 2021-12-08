package views;

import entity.User;
import gateways.database.SQLDatabase;
import usecase.IDatabaseConnection;
import usecase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * User can log in with correct combination of username and password
 * Checks if username and password matches from database
 */
public class LoginPage {
    private JFrame frame;
    private JPanel panel;
    private JButton login, home;
    private JTextField username, password;
    private JLabel title, usernameLabel, passwordLabel;
    private Font f;
    private static User user;
    private static String usernameInput, passwordInput;
    private static IDatabaseConnection db = new SQLDatabase();
    private static UserManager userManager = new UserManager(db);

    /**
     * default constructor
     * generates userLogin window
     */
    public LoginPage(User user) {
        //initialize the variables
        usernameInput = "";
        passwordInput = "";

        frame = new JFrame("Memory Game");
        panel = new JPanel();
        login = new JButton("Login");
        home = new JButton("Home");
        username = new JTextField();
        password = new JTextField();
        title = new JLabel("Memory Game");
        usernameLabel = new JLabel("username: ");
        passwordLabel = new JLabel("password: ");
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);
        this.user = user;

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0, 0, 540, 540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(180, 88, 250, 55);
        title.setFont(f);

        usernameLabel.setBounds(57, 180, 150, 45);
        usernameLabel.setFont(f);

        passwordLabel.setBounds(57, 270, 150, 45);
        passwordLabel.setFont(f);

        //setup textfields
        username.setBounds(250, 180, 200, 45);
        password.setBounds(250, 270, 200, 45);

        //setup home button
        home.setBounds(30, 430, 60, 60);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        home.addActionListener(e -> {
            frame.dispose();
            new LoginOrSignupPage(user);
        });
        /*//Might have to add something here (getter and setter to output the changes)
        home.addActionListener(e -> {new LoginOrSignupPage();
            frame.setVisible(false);});
        */

        //setup buttons
        login.setBounds(200, 360, 180, 55);
        login.setBackground(Color.CYAN);
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();
            logIn(usernameInput, passwordInput);
            frame.setVisible(false);
        });

        //add all JComponents to frame and set frame visible
        frame.setSize(540, 540);
        frame.setResizable(false);
        panel.add(login);
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(username);
        panel.add(password);
        panel.add(home);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void logIn(String username, String password) {
        // helper function that check if inputted username is available, signs up if true
        if (userManager.checkPassword(username, password)) {
            JOptionPane.showMessageDialog(new JFrame(), "Log-in successful.");
            user.setUsername(username);
            user.setPassword(password);
            new StartPage(user);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Username or password incorrect! Please try again.");
            new LoginPage(user);
        }
    }
}
