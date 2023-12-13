package Lab15;

import com.sun.jdi.event.ExceptionEvent;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class Calculator extends JFrame{
    JTextField jta1 = new JTextField(10);
    JTextField jta2 = new JTextField(10);
    JButton plus = new JButton(" Plus");
    JButton minus = new JButton(" Minus");
    JButton multiply = new JButton(" Multiply");
    JButton div = new JButton(" Div");
    Font fnt = new Font("Times new roman",Font.BOLD,20);
    Calculator(){
        super("Example");

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(150,250);
// создаем надпись 1
        add(new JLabel("1st Number"));
// добавляем текстовое поле jta1
        add(jta1);
// создаем надпись 2
        add(new JLabel("2nd Number"));
// добавляем текстовое поле jta2
        add(jta2);
// добавляем к
        add(plus);
        add(minus);

        add(multiply);
        add(div);
//добавляем слушателя к кнопке
        plus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                try{
                    double x1 = Double.parseDouble(jta1.getText().trim());
                    double x2 = Double.parseDouble(jta2.getText().trim());

                    JOptionPane.showMessageDialog(null, "Result = "+(x1+x2),"Alert",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){

                    JOptionPane.showMessageDialog( null, "Error in Numbers", "alert" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        minus.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                try{
                    double x1 = Double.parseDouble(jta1.getText().trim());
                    double x2 = Double.parseDouble(jta2.getText().trim());

                    JOptionPane.showMessageDialog(null, "Result = "+(x1-x2),"Alert",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){

                    JOptionPane.showMessageDialog( null, "Error in Numbers", "alert" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        multiply.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                try{
                    double x1 = Double.parseDouble(jta1.getText().trim());
                    double x2 = Double.parseDouble(jta2.getText().trim());

                    JOptionPane.showMessageDialog(null, "Result = "+(x1*x2),"Alert",JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){

                    JOptionPane.showMessageDialog( null, "Error in Numbers", "alert" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        div.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent ae){
                try{
                    double x1 = Double.parseDouble(jta1.getText().trim());
                    double x2 = Double.parseDouble(jta2.getText().trim());
                    if(x2 != 0) {
                        JOptionPane.showMessageDialog(null, "Result = " + (x1/x2), "Alert", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Result = " + "division by zero", "Alert", JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(Exception e){

                    JOptionPane.showMessageDialog( null, "Error in Numbers", "alert" , JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
//В main вызвать: new Calculator();
}