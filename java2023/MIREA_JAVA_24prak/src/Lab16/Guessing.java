package Lab16;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class Guessing extends JFrame{
    JTextField jta = new JTextField(10);
    JButton button = new JButton("Guess");
    Font fnt = new Font("Times new roman",Font.BOLD,20);
    int trys = 4;
    Guessing(){
        super("Guessing");

        Random rand = new Random();
        int num = rand.nextInt(21);
        setLayout(new FlowLayout());
        setSize(500,250);
        add(new JLabel("Я загадал число от 0 до 20, попробуй угадать"));
        add(jta);
        add(button);

        button.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                try{
                    int x1 = Integer.parseInt(jta.getText().trim());
                    if(trys>0) {
                        trys--;
                        if(x1 != num){
                            if(x1 > num){
                                JOptionPane.showMessageDialog(null, "Вы не угадали, загаданное число меньше введенного. Осталось попыток: "+trys, "Result", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "Вы не угадали, загаданное число больше введенного. Осталось попыток: "+trys, "Result", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Вы угадали! Поздравляю). Давайте попробуем еще раз", "Result", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            new Guessing();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Вы не угадали число и у вас не осталось попыток. Сожалею, но вы проиграли(. Давайте начнем заново", "Result", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new Guessing();
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog( null, "Error in Number", "Error" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }


    public static void main(String[] args){
        Guessing guessing = new Guessing();
    }
}