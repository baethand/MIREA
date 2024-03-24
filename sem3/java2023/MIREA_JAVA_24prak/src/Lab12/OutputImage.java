package Lab12;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.Graphics;


public class OutputImage {
    static Image image;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            image = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel =new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        JFrame frame =new JFrame("Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000,1000);
    }
}