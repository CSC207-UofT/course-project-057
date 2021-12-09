package views;

import entity.User;
import usecase.GameStatManager;
import usecase.IDatabaseConnection;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.SQLException;

public class LeaderBoardPage {
    private JFrame frame1;
    private JPanel panel;
    private JButton home;
    private JLabel title;
    private JTable table;
    private String[][] output;
    private Font f1, f2;

    /**
     * the page to display the leaderboard
     * @param user player of the game
     * @param gm gameStatManager
     * @param db Database
     * @throws SQLException exception for SQL
     */
    public LeaderBoardPage(User user, GameStatManager gm, IDatabaseConnection db) throws SQLException {
        frame1 = new JFrame("Leader Board");
        panel = new JPanel();
        home = new JButton("Home");
        title = new JLabel("");
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font

        String[] header = new String[]{};
        if (user.getMode() == 1) {
            output = gm.generatePatternLeaderboard(user.getDifficulty());
            header = new String[] {"Rank", "ID", "Time"};
        }else {
            output = gm.generateMatchingLeaderboard(user.getDifficulty());
            header = new String[] {"Rank", "ID", "Moves", "Time"};
        }

        home.setBounds(180,210, 180,55);
        home.setBackground(Color.PINK);
        home.setOpaque(true);
        //Might have to add something here (getter and setter to output the changes)
        home.addActionListener(e -> {
            frame1.dispose();
            new LoginOrSignupPage(user);});

        table = new JTable(output, header);
        table.setBounds(200,200,600,500);
        table.setFont(f2);


        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);
        frame1.setBounds(0,0,960,540);
        panel.add(home);
        panel.setLayout(new BorderLayout());
        panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        panel.add(table, BorderLayout.CENTER);
        panel.add(home, BorderLayout.SOUTH);
        frame1.add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
}
