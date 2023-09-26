package prak5;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class DisplayImage extends JFrame {
    private BufferedImage image;

    public DisplayImage() {
        try {
            String imagePath = "C:\\_gitHub\\MIREA\\sait\\.idea\\image1.jpg";

            image = ImageIO.read(new File(imagePath));
            setTitle("Display Image");
            setSize(image.getWidth(), image.getHeight());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при загрузке изображения", "Ошибка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DisplayImage();
            }
        });
    }
}
