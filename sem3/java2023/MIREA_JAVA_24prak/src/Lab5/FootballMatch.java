package Lab5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FootballMatch {
    public static void main(String args[]) {

        final int[] milanScore = {0};
        final int[] rmScore = {0};

        JFrame frame = new JFrame("Match");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 1));

        JButton button1 = new JButton("AC Milan");
        JButton button2 = new JButton("Real Madrid");

        JLabel result = new JLabel("Result: 0 X 0");
        JLabel lastscorer = new JLabel("Last Scorer: N/A");
        JLabel winner = new JLabel("Winner: DRAW");
        JPanel panel = new JPanel();

        panel.add(button1);
        panel.add(button2);

        frame.getContentPane().add(panel);

        frame.add(result);
        frame.add(lastscorer);
        frame.add(winner);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                milanScore[0]++;
                result.setText("Result: " + milanScore[0] + " X " + rmScore[0]);
                lastscorer.setText("Last Scorer: AC Milan");
                if (milanScore[0] > rmScore[0]) {
                    winner.setText("Winner: AC Milan");
                } else if (rmScore[0] > milanScore[0]) {
                    winner.setText("Winner: Real Madrid");
                } else {
                    winner.setText("Winner: DRAW");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rmScore[0]++;
                result.setText("Result: " + milanScore[0] + " X " + rmScore[0]);
                lastscorer.setText("Last Scorer: Real Madrid");
                if (milanScore[0] > rmScore[0]) {
                    winner.setText("Winner: AC Milan");
                } else if (rmScore[0] > milanScore[0]) {
                    winner.setText("Winner: Real Madrid");
                } else {
                    winner.setText("Winner: DRAW");
                }
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}