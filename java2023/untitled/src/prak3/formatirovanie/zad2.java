package prak3.formatirovanie;

import java.util.Scanner;

public class zad2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите стоимость товара: ");
        double price = scanner.nextDouble();
        System.out.print("Выберите валюту для оплаты (USD, EUR, RUB): ");
        String currency = scanner.next().toUpperCase();

        double usdToRub = 74.50;
        double usdToEur = 0.85;

        double priceInUsd = price;
        double priceInRub = price * usdToRub;
        double priceInEur = price * usdToEur;

        String formattedPriceInUsd = String.format("$%.2f", priceInUsd);
        String formattedPriceInRub = String.format("%.2f RUB", priceInRub);
        String formattedPriceInEur = String.format("%.2f EUR", priceInEur);

        System.out.println("Стоимость товара: " + formattedPriceInUsd);
        System.out.println("Курс USD к RUB: " + usdToRub);
        System.out.println("Курс USD к EUR: " + usdToEur);
        System.out.println("Стоимость товара в выбранной валюте: " + formattedPriceInUsd);

        switch (currency) {
            case "USD":
                System.out.println("Стоимость товара в RUB: " + formattedPriceInRub);
                System.out.println("Стоимость товара в EUR: " + formattedPriceInEur);
                break;
            case "RUB":
                System.out.println("Стоимость товара в USD: " + formattedPriceInUsd);
                System.out.println("Стоимость товара в EUR: " + formattedPriceInEur);
                break;
            case "EUR":
                System.out.println("Стоимость товара в USD: " + formattedPriceInUsd);
                System.out.println("Стоимость товара в RUB: " + formattedPriceInRub);
                break;
            default:
                System.out.println("Выбрана некорректная валюта");
                break;
        }
    }
}