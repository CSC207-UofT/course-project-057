package views;

import entity.User;
import gateways.database.SQLDatabase;
import usecase.IDatabaseConnection;
import usecase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * option to sign up for a new account
 * make sure the username isn't taken (data from database)
 * add the username and password to the database via addUser
 */
public class SignUpPage {
    private JFrame frame;
    private JPanel panel;
    private JButton signup, home;
    private JTextField username, password;
    private JLabel title, usernameLabel, passwordLabel;
    private Font f;
    private static User user;
    private static String usernameInput, passwordInput;
    private static IDatabaseConnection db = new SQLDatabase();
    private static UserManager userManager = new UserManager(db);

    /**
     * default constructor
     * generates SignUpPage window
     */
    public SignUpPage(User user) {
        //initialize the variables
        usernameInput = "";
        passwordInput = "";

        frame = new JFrame("Memory Game");
        panel = new JPanel();
        signup = new JButton("Sign Up");
        home = new JButton("Home");
        username = new JTextField();
        password = new JTextField();
        title = new JLabel("Memory Game");
        usernameLabel = new JLabel("username: ");
        passwordLabel = new JLabel("password: ");
        SignUpPage.user = user;
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

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

        //setup buttons
        signup.setBounds(200, 360, 180, 55);
        signup.setBackground(Color.CYAN);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();
            signUp(usernameInput, passwordInput);
            frame.setVisible(false);
        });


        //add all JComponents to frame and set frame visible
        frame.setSize(540, 540);
        frame.setResizable(false);
        panel.add(signup);
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

    public void signUp(String username, String password) {
        // helper function that check if inputted username is available, signs up if true
        if (userManager.checkUsernameAvailable(username)) {
            JOptionPane.showMessageDialog(new JFrame(), "Username Available! Sign up successful.");
            user.setUsername(username);
            user.setPassword(password);
            new LoginPage(user);
            try {
                userManager.createUser(user);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Username Unavailable! Please select another username.");
            new SignUpPage(user);
        }
    }
}

