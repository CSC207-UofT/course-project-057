package views;

import javax.swing.*;
import java.awt.*;


public class PatternLeaderboardPage {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JTable table;
    private String[] columns;
    private Object[][] info;

    private Font f;

    /**
     * default constructor
     * generates the Pattern Leaderboard Page
     */
    public PatternLeaderboardPage() {
        //initialize variables
        columns = new String[] {
                "Game ID", "Username", "Time"};
        // insert helper method to populate info in here
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
