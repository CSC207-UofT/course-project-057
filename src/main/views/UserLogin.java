package views;

import gateways.database.UserSQLDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * User can log in with correct combination of username and password
 * Checks if username and password matches from database
 */
public class UserLogin {
    private JFrame frame;
    private JPanel panel;
    private JButton login;
    private JTextField username, password;
    private JLabel title, usernameLabel, passwordLabel;
    private Font f;
    private static String usernameInput, passwordInput;

    /**
     * default constructor
     * generates userLogin window
     */
    public UserLogin(){
        //initialize the variables
        usernameInput = "";
        passwordInput = "";

        frame = new JFrame("Memory Game");
        panel = new JPanel();
        login = new JButton("Login");
        username = new JTextField();
        password = new JTextField();
        title = new JLabel("Memory Game");
        usernameLabel = new JLabel("username: ");
        passwordLabel = new JLabel("password: ");
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,740,740);
        panel.setBackground(Color.WHITE);

        //setup labels
        title.setBounds(260,118,250,55);
        title.setFont(f);

        usernameLabel.setBounds(57,209,150,45);
        usernameLabel.setFont(f);

        passwordLabel.setBounds(57,360,150,45);
        passwordLabel.setFont(f);

        //setup textfields
        username.setBounds(300,209,250,45);
        password.setBounds(300,360,250,45);

        //setup buttons
        login.setBounds(277,541,180,55);
        login.setBackground(Color.CYAN);
        login.setOpaque(true);
        login.setBorderPainted(false);
        login.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();
            //#TODO: login
            new StartPage();
            frame.setVisible(false);
        });

        //add all JComponents to frame and set frame visible
        frame.setSize(740,740);
        frame.setResizable(false);
        panel.add(login);
        panel.add(title);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(username);
        panel.add(password);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * login page
     * @param user_database the SQL database
     * @return a string list of username and password
     */
    public static String[] login(UserSQLDatabase user_database) {
        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];

        System.out.print("Please enter your username: ");
        userInput[0] = s.next();

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        if (!user_database.checkUsernameAvailable(userInput[0]) &&
                user_database.checkPassword(userInput[0], userInput[1])) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login unsuccessful, please re-enter your criteria.");
            login(user_database);
        }
        return userInput;
    }
}
