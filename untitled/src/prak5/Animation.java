package prak5;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Animation extends JFrame {
    private BufferedImage[] frames; // Массив кадров анимации
    private int currentFrame = 0; // Текущий кадр
    private JLabel animationLabel;

    public Animation() {
        setTitle("Анимация");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(133, 181);

        // Загрузка кадров анимации
        loadFrames();

        // Создание метки для отображения анимации
        animationLabel = new JLabel(new ImageIcon(frames[currentFrame]));
        add(animationLabel, BorderLayout.CENTER);

        // Создание таймера для обновления кадров анимации
        int delay = 100; // Интервал в миллисекундах между кадрами
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Обновление текущего кадра и отображение его на метке
                currentFrame = (currentFrame + 1) % frames.length;
                animationLabel.setIcon(new ImageIcon(frames[currentFrame]));
            }
        });
        timer.start();

        setVisible(true);
    }

    // Загрузка кадров анимации
    private void loadFrames() {
        int numFrames = 5; // Количество кадров анимации
        frames = new BufferedImage[numFrames];

        for (int i = 0; i < numFrames; i++) {
            // Замените "frameX.png" на имена ваших изображений кадров
            String frameFilename = "C:\\_gitHub\\MIREA\\untitled\\images\\anim" + i + ".png";

            try {
                frames[i] = ImageIO.read(new File(frameFilename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Animation();
            }
        });
    }
}
