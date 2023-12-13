package Lab8;

import java.util.Scanner;


public class SumOfNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 0 < n < 99999: ");
        String n1 = scanner.nextLine();
        while (n1.length() > 5){
            System.out.print("Слишком большое число, попробуйте еще раз: ");
            n1 = scanner.nextLine();
        }
        int n = Integer.parseInt(n1);
        int summ = 0;
        System.out.println(recursion(n, summ));
    }

    public static int recursion(int n, int summ) {
        if (n == 0) {
            return summ;
        }
        summ += n%10;
        return recursion(n/10, summ);
    }
}