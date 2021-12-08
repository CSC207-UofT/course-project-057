package views;

import entity.User;
import usecase.GameStatManager;
import usecase.IDatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LeaderBoardPage {
    private JFrame frame1;
    private JPanel panel;
    private JButton home;
    private JLabel title;
    private JTable table;
    private String difficulty, mode;
    private String[][] output;
    private Font f1, f2;
    
    public LeaderBoardPage(User user, GameStatManager gm, IDatabaseConnection db) throws SQLException {
        frame1 = new JFrame("Leader Board");
        panel = new JPanel();
        home = new JButton("Home");
        title = new JLabel("Leader Board");
        f1 = new Font(title.getFont().getName(), Font.PLAIN, 25);//title font
        f2 = new Font(title.getFont().getName(), Font.PLAIN, 15);//paragraph font

        String[] header = new String[] {"Rank","ID", "Moves","Time"};
        if (user.getMode() == 1) {
            output = gm.generatePatternLeaderboard(user.getDifficulty());
        }else {
            output = gm.generateMatchingLeaderboard(user.getDifficulty());
        }

        //
        table = new JTable(output, header);
        table.setBounds(200,100,600,500);
        table.setFont(f2);

        //setup label
        title.setBounds(320,30,300,50);
        title.setFont(f1);

        //setup panel
        panel.setLayout(null);
        panel.setBounds(0,0,960,540);
        panel.setBackground(Color.GRAY);
        frame1.setBounds(0,0,960,540);
        panel.add(home);
        panel.add(title);
        panel.add(table);
        frame1.add(panel);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }
}
