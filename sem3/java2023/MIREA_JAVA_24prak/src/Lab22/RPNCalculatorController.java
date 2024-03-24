package Lab22;

import javax.swing.*;

public class RPNCalculatorController {

    RPNCalculatorController(RPNCalculatorModel model, RPNCalculatorView view) {
        setupButtons(model, view);
    }

    private static void setupButtons(RPNCalculatorModel model, RPNCalculatorView view) {
        for (JButton button : view.basicButtons) {
            button.addActionListener(e -> {
                JButton button1 = (JButton) e.getSource();
                String but = button1.getText();
                model.addToExpression(but);
                view.setTextField(model.getExpression());
            });
        }

        view.clearButton.addActionListener(e -> {
            model.clearExpression();
            view.setTextField(model.getExpression());
        });

        view.backspaceButton.addActionListener(e -> {
            model.backspaceExpression();
            view.setTextField(model.getExpression());
        });

        view.spaceButton.addActionListener(e -> {
            model.addToExpression(" ");
            view.setTextField(model.getExpression());
        });

        view.equalButton.addActionListener(e -> {
            try {
                model.calculate();
                view.setTextField(model.getExpression());
            } catch (IllegalArgumentException | ArithmeticException e1) {
                System.out.println("Error: " + e1.getMessage());
            }
        });
    }
}
