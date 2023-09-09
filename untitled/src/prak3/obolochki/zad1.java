package prak3.obolochki;

public class zad1 {
    public static void main(String[] args) {
        Double d1 = Double.valueOf(3.14);
        Double d2 = Double.valueOf("2.71828");
        Double d3 = Double.valueOf(5); // автоупаковка значения int в объект Double
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }
}
