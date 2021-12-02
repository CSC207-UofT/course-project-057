package views;

import gateways.database.UserSQLDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


/**
 * options to log in or sign up
 */
public class LoginOrSignup {
    private JFrame frame;
    private JPanel panel;
    private JButton login, signup, guest;
    private JLabel title, background;
    private Icon backgroundImg;
    private Font f;
    /**
     * Default constructor
     * setting the GUI patterns
     */
    public LoginOrSignup() {

        //initialize JComponent
        frame = new JFrame("Memory Game");
        panel = new JPanel();
        login = new JButton("Login");
        signup = new JButton("Sign Up");
        guest = new JButton("Guest");
        title = new JLabel("Memory Game");
        background = new JLabel();
        backgroundImg = new ImageIcon(new ImageIcon("src/main/views/pictures/rainbowCat.gif").getImage()
                .getScaledInstance(540,540,Image.SCALE_DEFAULT));
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        //set panel
        panel.setLayout(null);
        panel.setBounds(0,0,540,540);

        //set label
        title.setBounds(180,88,250,55);
        title.setFont(f);

        background.setIcon(backgroundImg);
        background.setBounds(0,0,540,540);

        //set buttons
        login.setBounds(180,180,180,55);
        login.setFont(f);
        login.setBackground(Color.CYAN);
        login.setOpaque(true);
        login.setBorderPainted(false);
        //login method, close current frame and open login window
        login.addActionListener(e -> {
            frame.setVisible(false);
            new UserLogin();
        });

        signup.setBounds(180,280,180,55);
        signup.setFont(f);
        signup.setBackground(Color.CYAN);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        signup.addActionListener(e -> new SignUpPage());

        guest.setBounds(180,380,180,55);
        guest.setFont(f);
        guest.setBackground(Color.CYAN);
        guest.setOpaque(true);
        guest.setBorderPainted(false);
        guest.addActionListener(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Play as guest!");
            new StartPage();
            frame.setVisible(false);
        });

        //add all JComponents to frame and set frame visible
        frame.setSize(540,540);
        frame.setResizable(false);
        panel.add(title);
        //panel.add(test);
        panel.add(login);
        panel.add(signup);
        panel.add(guest);
        panel.add(background);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     *
     * @param UserDatabase the SQL database
     * @return string list of username and password
     * @throws SQLException provides information on a database access error
     */
    public static String[] loginOrSignup(UserSQLDatabase UserDatabase) throws SQLException {
        String input = UserGameInput.promptLoginOrSignup();
        String[] userData = new String[]{};
        if (input.equals("login")) {
            userData = UserLogin.login(UserDatabase);
        }
        else if (input.equals("sign up")) {
            SignUpPage.signUp(UserDatabase);
            DisplayPrompts.loginDisplay();
            userData = UserLogin.login(UserDatabase);
        }
        return userData;
    }


}

