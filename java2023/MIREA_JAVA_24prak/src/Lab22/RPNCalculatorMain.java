package Lab22;

import javax.swing.*;

public class RPNCalculatorMain {
    public static void main(String[] args) {
        RPNCalculatorModel model = new RPNCalculatorModel();
        RPNCalculatorView view = new RPNCalculatorView();
        new RPNCalculatorController(model, view);
    }
}
