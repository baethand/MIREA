package Lab3;

import jdk.jshell.execution.LoaderDelegate;

import java.text.NumberFormat;
import java.util.Scanner;
import java.util.Locale;
public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat n1 = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        NumberFormat n2 = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat n3 = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        NumberFormat n4 = NumberFormat.getCurrencyInstance(Locale.UK);

        System.out.println("Введите страну вводимой валюты(US, JPN, CHN, FRN, UK): ");
        String incur = in.nextLine();
        while(incur != "US" && incur != "JPN" && incur != "CHN" && incur != "FRN" && incur != "UK"){
            System.out.println("Видимо вашей страны еще нет в нашем приложении, введите одну из предложенных стран(US, JPN, CHN, FRN, UK): ");
            incur = in.nextLine();
        }
        System.out.println("Введите количество валюты: ");
        double payment = in.nextDouble();
        while (payment <= 0){
            System.out.println("Введите количество больше 0: ");
            payment = in.nextDouble();
        }
        System.out.println("Введите страну желаемой валюты(US, JPN, CHN, FRN, UK):");
        String outcur = in.nextLine();
        while(outcur != "US" && outcur != "JPN" && outcur != "CHN" && outcur != "FRN" && outcur != "UK"){
            System.out.println("Видимо вашей страны еще нет в нашем приложении, введите одну из предложенных стран(US, JPN, CHN, FRN, UK): ");
            outcur = in.nextLine();
        }

        double usdpay = 0.0;
        switch (incur){
            case ("US"):
                usdpay = payment;
                break;
            case ("JPN"):
                usdpay = payment*0.006803;
                break;
            case ("CHN"):
                usdpay = payment*0.136769;
                break;
            case ("FRN"):
                usdpay = payment*1.08;
                break;
            case ("UK"):
                usdpay = payment*1.26;
                break;
        }
        double outpay = 0.0;
        String res = "";
        switch (outcur){
            case ("US"):
                outpay = usdpay;
                res = n.format(outpay);
                break;
            case ("JPN"):
                outpay = usdpay*147;
                res = n1.format(outpay);
                break;
            case ("CHN"):
                outpay = usdpay*7.31;
                res = n2.format(outpay);
                break;
            case ("FRN"):
                outpay = usdpay*0.929784;
                res = n3.format(outpay);
                break;
            case ("UK"):
                outpay = usdpay*0.792519;
                res = n4.format(outpay);
                break;
            default:
                System.out.println("Неизвестная страна желаемой валюты");
        }
        System.out.println("Переведенная валюта: "+res);
    }
}
