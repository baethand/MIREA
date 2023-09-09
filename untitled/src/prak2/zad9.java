package prak2;

import java.util.*;

public class zad9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество игроков: ");
        int n = scanner.nextInt();
        String[] suits = {"Пик", "Бубен", "Червей", "Треф"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Валет", "Дама", "Король", "Туз"};
        List<String> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " " + suit);
            }
        }
        Collections.shuffle(deck);
        for (int i = 0; i < n; i++) {
            System.out.println("Игрок " + (i+1) + ":");
            for (int j = 0; j < 5; j++) {
                System.out.println(deck.get(5*i+j));
            }
            System.out.println();
        }
    }
}