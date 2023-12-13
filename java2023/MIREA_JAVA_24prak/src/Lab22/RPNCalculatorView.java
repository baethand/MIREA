package Lab22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RPNCalculatorView {
    private JFrame frame;
    private JTextField textField;
    JButton[] basicButtons;
    JButton equalButton;
    JButton spaceButton;
    JButton backspaceButton;
    JButton clearButton;

    public RPNCalculatorView() {
        frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 1));

        textField = new JTextField("");
        frame.add(textField);

        JPanel firstButtonPanel = new JPanel(new GridLayout(4, 4));
        JPanel secondButtonPanel = new JPanel(new GridLayout(1, 3));

        basicButtons = new JButton[15];
        basicButtons[0] = new JButton("1");
        firstButtonPanel.add(basicButtons[0]);
        basicButtons[1] = new JButton("2");
        firstButtonPanel.add(basicButtons[1]);
        basicButtons[2] = new JButton("3");
        firstButtonPanel.add(basicButtons[2]);
        basicButtons[3] = new JButton("+");
        firstButtonPanel.add(basicButtons[3]);
        basicButtons[4] = new JButton("4");
        firstButtonPanel.add(basicButtons[4]);
        basicButtons[5] = new JButton("5");
        firstButtonPanel.add(basicButtons[5]);
        basicButtons[6] = new JButton("6");
        firstButtonPanel.add(basicButtons[6]);
        basicButtons[7] = new JButton("-");
        firstButtonPanel.add(basicButtons[7]);
        basicButtons[8] = new JButton("7");
        firstButtonPanel.add(basicButtons[8]);
        basicButtons[9] = new JButton("8");
        firstButtonPanel.add(basicButtons[9]);
        basicButtons[10] = new JButton("9");
        firstButtonPanel.add(basicButtons[10]);
        basicButtons[11] = new JButton("*");
        firstButtonPanel.add(basicButtons[11]);
        basicButtons[12] = new JButton("0");
        firstButtonPanel.add(basicButtons[12]);
        basicButtons[13] = new JButton("/");
        firstButtonPanel.add(basicButtons[13]);
        basicButtons[14] = new JButton(".");
        firstButtonPanel.add(basicButtons[14]);

        //for(JButton but: basicButtons){
        //  firstButtonPanel.add(but);
        // }
        spaceButton = new JButton("_");
        firstButtonPanel.add(spaceButton);

        clearButton = new JButton("C");
        secondButtonPanel.add(clearButton);

        backspaceButton = new JButton("Backspace");
        secondButtonPanel.add(backspaceButton);

        equalButton = new JButton("=");
        secondButtonPanel.add(equalButton);

        frame.add(secondButtonPanel);
        frame.add(firstButtonPanel);

        frame.setVisible(true);
    }

    public void setTextField(String text) {
        textField.setText(text);
    }

    public String getTextField() {
        return textField.getText();
    }
}
