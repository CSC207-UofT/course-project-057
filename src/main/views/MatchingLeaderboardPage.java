package views;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import usecase.*;
import gateways.database.SQLDatabase;
import usecase.BoardManager;
import usecase.GameStatManager;
import usecase.IDatabaseConnection;

public class MatchingLeaderboardPage {
    private static final IDatabaseConnection db = new SQLDatabase();
    private static GameStatManager gsm = new GameStatManager(db);
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JTable table;
    private String[] columns;
    private Object[][] info;
    private Font f;

    public MatchingLeaderboardPage(String difficulty) throws SQLException {
        //initialize variables

        columns = new String[] {
                "Game ID", "Username", "Total Moves", "Time"};
        info = gsm.generateMatchingLeaderboard(difficulty); // added in PR63
        frame = new JFrame("Matching Leaderboard");
        panel = new JPanel();
        title = new JLabel("Matching Leaderboard");
        table = new JTable(info, columns);
        f = new Font(title.getFont().getName(), Font.PLAIN, 25);

        panel.setLayout(null);
        panel.setSize(960, 540);
        panel.setBackground(Color.GRAY);
        title.setBounds(180, 88, 250, 55);
        title.setFont(f);

        frame.setSize(540, 540);
        frame.setResizable(false);
        panel.add(title);
        panel.add(table);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
