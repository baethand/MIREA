package Lab8;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите слово: ");
        String n = scanner.nextLine();
        String summ = "";
        int k = n.length()-1;
        summ = recursion(n, k, summ);
        if(n.equals(summ)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static String recursion(String n, int k, String summ) {
        if (k == -1) {
            return summ;
        }
        summ += n.charAt(k);
        return recursion(n, k-1, summ);
    }
}