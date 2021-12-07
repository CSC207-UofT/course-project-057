package views;

import entity.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


/**
 * options to log in or sign up
 */
public class LoginOrSignupPage {
    private JFrame frame;
    private JPanel panel;
    private JButton login, signup, guest, rules;
    private JLabel title, background;
    private Icon backgroundImg;
    private Font f;
    private static User user;
    /**
     * Default constructor
     * setting the GUI patterns
     */
    public LoginOrSignupPage(User user) {

        //initialize JComponent
        frame = new JFrame(user.getUsername());
        panel = new JPanel();
        login = new JButton("Login");
        signup = new JButton("Sign Up");
        guest = new JButton("Guest");
        title = new JLabel("Memory Game");
        rules = new JButton("Rules");
        background = new JLabel();
        backgroundImg = new ImageIcon(new ImageIcon("src/main/views/pictures/rainbowCat.gif").getImage()
                .getScaledInstance(700,540,Image.SCALE_DEFAULT));
        f = new Font(title.getFont().getName(), Font.PLAIN, 18);
        LoginOrSignupPage.user = user;

        //set panel
        panel.setLayout(null);
        panel.setBounds(0,0,700,540);

        //set label
        title.setBounds(140,88,250,55);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        background.setIcon(backgroundImg);
        background.setBounds(0,0,540,540);

        //set buttons
        login.setBounds(180,340,150,40);
        login.setFont(f);
        login.setBackground(Color.CYAN);
        login.setOpaque(true);
        login.setBorderPainted(false);
        //login method, close current frame and open login window
        login.addActionListener(e -> {
            frame.dispose();
            new LoginPage(user);
        });

        signup.setBounds(180,390,150,40);
        signup.setFont(f);
        signup.setBackground(Color.CYAN);
        signup.setOpaque(true);
        signup.setBorderPainted(false);
        //signup method, close current frame and open login window
        signup.addActionListener(e -> {
            frame.dispose();
            new SignUpPage(user);
        });

        guest.setBounds(180,440,150,40);
        guest.setFont(f);
        guest.setBackground(Color.CYAN);
        guest.setOpaque(true);
        guest.setBorderPainted(false);
        guest.addActionListener(e -> {
            LoginOrSignupPage.user.setGuest(true);
            JOptionPane.showMessageDialog(new JFrame(), "Play as guest!");
            new StartPage(user);
            frame.setVisible(false);
        });

        rules.setBounds(450,460,80,40);
        rules.setFont(new Font(title.getFont().getName(), Font.BOLD, 12));
        rules.setBackground(Color.GRAY);
        rules.setOpaque(true);
        rules.setBorderPainted(false);
        rules.addActionListener(e -> {new GameRulesPage(user); frame.setVisible(false);});

        //add all JComponents to frame and set frame visible
        frame.setSize(540,540);
        frame.setResizable(false);
        panel.add(title);
        //panel.add(test);
        panel.add(login);
        panel.add(signup);
        panel.add(guest);
        panel.add(rules);
        panel.add(background);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     *
     * @return string list of username and password
     * @throws SQLException provides information on a database access error
     */
//    public static String[] loginOrSignup(UserSQLDatabase UserDatabase) throws SQLException {
//        String input = UserGameInput.promptLoginOrSignup();
//        String[] userData = new String[]{};
//        if (input.equals("login")) {
//            userData = LoginPage.login(UserDatabase);
//        }
//        else if (input.equals("sign up")) {
//            SignUpPage.signUp(UserDatabase);
//            System.out.println(DisplayPrompts.loginDisplay());
//            userData = LoginPage.login(UserDatabase);
//        }
//        return userData;
//    }

    public static User getUser() {
        return LoginOrSignupPage.user;
    }
}

