package views;

import entity.User;
import gateways.database.UserSQLDatabase;
import usecase.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Scanner;

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

    /**
     * default constructor
     * generates SignUpPage window
     */
    public SignUpPage(User user){
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
        this.user = user;
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,540,540);
        panel.setBackground(Color.GRAY);

        //setup labels
        title.setBounds(180,88,250,55);
        title.setFont(f);

        usernameLabel.setBounds(57,180,150,45);
        usernameLabel.setFont(f);

        passwordLabel.setBounds(57,270,150,45);
        passwordLabel.setFont(f);

        //setup textfields
        username.setBounds(250,180,200,45);
        password.setBounds(250,270,200,45);

        //setup home button
        home.setBounds(30,430, 60,60);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        home.addActionListener(e -> {new LoginOrSignupPage(user);});

        //setup buttons
        signup.setBounds(200, 360, 180, 55);
        signup.setBackground(Color.CYAN);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.addActionListener(e -> {
            usernameInput = username.getText();
            passwordInput = password.getText();
            //#TODO: signup
            new StartPage(user);
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

    /**
     * the sign-up page
     * @param user_database the SQL database
     * @throws SQLException provides information on a database access error
     */
    public static void signUp(UserSQLDatabase user_database) throws SQLException {
        Scanner s = new Scanner(System.in);
        String [] userInput = new String[2];
        // nameValid is false when user name has been taken
        boolean nameValid;
        do {
            System.out.print("Please enter a username: ");
            userInput[0] = s.next();
            // if the username is not available
            if (!user_database.checkUsernameAvailable(userInput[0])) {
                nameValid = false;
                System.out.println("Your username has been taken. Please enter a different username.");
            } else {
                nameValid = true;
            }
        } while (!nameValid);

        System.out.print("Please enter your password: ");
        userInput[1] = s.next();

        UserManager.createUser(userInput[0], userInput[1]); // call Entity.User or UseCase.UserManager?
        user_database.addUser(userInput[0], userInput[1]);
    }

}
