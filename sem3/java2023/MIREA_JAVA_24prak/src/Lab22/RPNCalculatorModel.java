package Lab22;

import java.util.Stack;

public class RPNCalculatorModel {
    private String expression = "";

    public String getExpression() {
        return expression;
    }

    public void clearExpression() {
        this.expression = "";
    }

    public void addToExpression(String str) {
        expression += str;
    }

    public void backspaceExpression() {
        expression = expression.substring(0, expression.length() - 1);
    }

    public static boolean isNumber(String symb) {
        try {
            Double.parseDouble(symb);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isOper(String symb) {
        return symb.equals("+") || symb.equals("-") || symb.equals("*") || symb.equals("/");
    }

    public double operation(Double num1, Double num2, String oper) {
        switch (oper) {
            case "+" -> {
                return num1 + num2;
            }
            case "-" -> {
                return num1 - num2;
            }
            case "*" -> {
                return num1 * num2;
            }
            case "/" -> {

                return num1 / num2;
            }
            default -> throw new IllegalArgumentException("Неверный оператор");
        }
    }

    public void calculate() {
        String[] expr = expression.split(" ");
        Stack<Double> stack = new Stack<>();
        for (String elem : expr) {
            if (isNumber(elem)) {
                stack.push(Double.parseDouble(elem));
            } else if (isOper(elem)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Недостаточное количество чисел");
                } else {
                    double first = stack.pop();
                    double second = stack.pop();
                    stack.push(operation(second, first, elem));
                }
            }
        }
        expression = String.valueOf(stack.pop());
    }
}
