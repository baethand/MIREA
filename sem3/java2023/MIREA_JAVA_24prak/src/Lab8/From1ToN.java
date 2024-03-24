package Lab8;

import java.util.Scanner;

class From1ToN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите n от 2 до 3000: ");
        int n = scanner.nextInt();
        while(n>3000 || n < 2){
            System.out.print("Введите нормальный n: ");
            n = scanner.nextInt();
        }
        int x = n;
        recursion(n, x);
    }

    public static int recursion(int n, int x) {
        if (n == 0) {
            return 0;
        }
        System.out.print(x - n + 1+" ");
        return recursion(n - 1, x);
    }
}
