package Lab14;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class regular {
    public void Ex1(String input, String reg){
        Pattern regular = Pattern.compile(reg);
        String[] words = regular.split(input);
        for(String s: words){
            System.out.println(s);
        }
    }
    public boolean Ex2(String input){
        Pattern regular = Pattern.compile("abcdefghijklmnopqrstuv18340");
        Matcher m = regular.matcher(input);
        return m.matches();
    }

    public boolean Ex5(String input){
        Pattern regular = Pattern.compile("[0-3][0-9]/[0-1][0-9]/[0-9][0-9][0-9][0-9]");
        Matcher m = regular.matcher(input);
        return m.matches();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите № задания(1, 2, 5): ");
        int num = in.nextInt();
        regular reg = new regular();
        switch (num){
            case(1):
                System.out.println("Введите строку: ");
                String s = in.nextLine();
                System.out.println("Введите разделитель в виде регулярного выражения: ");
                String r = in.nextLine();
                reg.Ex1(s, r);
                break;
            case(2):
                System.out.println("Введите строку: ");
                String s1 = in.nextLine();
                reg.Ex2(s1);
                break;
            case(5):
                System.out.println("Введите строку: ");
                String s2 = in.nextLine();
                reg.Ex5(s2);
                break;
        }
    }
}
