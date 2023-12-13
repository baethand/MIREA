package Lab20;

public class Calculator {
    public static <T, R> double sum(T a, R b) {
        return convertType(a) + convertType(b);
    }

    public static <T, R> double mult(T a, R b) {
        return convertType(a) * convertType(b);
    }

    public static <T, R> double div(T a, R b) {
        if (convertType(b) == 0) throw new RuntimeException("Division by 0");
        return convertType(a) / convertType(b);
    }

    public static <T, R> double minus(T a, R b) {
        return convertType(a) - convertType(b);
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 3, 5, 7, 2};
        MinMax<Integer> intMinMax = new MinMax<>(intArray);
        System.out.println("Min: " + intMinMax.findMin());
        System.out.println("Max: " + intMinMax.findMax());

        Double[] doubleArray = {1.5, 3.2, 5.7, 2.8, 7.1};
        MinMax<Double> doubleMinMax = new MinMax<>(doubleArray);
        System.out.println("Min: " + doubleMinMax.findMin());
        System.out.println("Max: " + doubleMinMax.findMax());

        String num1 = "10";
        int num2 = 5;
        System.out.println("Sum: " + sum(num1, num2));
        System.out.println("Multiply: " + mult(num1, num2));
        System.out.println("Divide: " + div(num1, num2));
        System.out.println("Minus: " + minus(num1, num2));

        double num3 = 8.5;
        double num4 = 3.0;
        System.out.println("Sum: " + sum(num3, num4));
        System.out.println("Multiply: " + mult(num3, num4));
        System.out.println("Divide: " + div(num3, num4));
        System.out.println("Minus: " + minus(num3, num4));
    }

    private static <T> double convertType(T value) {
        if (value instanceof String) {
            return Double.parseDouble((String) value);
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else {
            throw new RuntimeException("Unknown type");
        }
    }
}