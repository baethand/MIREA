package Lab1;

import java.util.Scanner;

public class MaxMin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите размер массива (одно целочисленное значение в диапазоне[-999999999;999999999]): ");
        int size = in.nextInt();
        while (size <= 0) {
            System.out.println("Длина массива не может быть <= 0. Попробуйте снова!");
            size = in.nextInt();
        }
        int i = 0;
        int[] arr = new int[size];
        int summa = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (i != size) {
            System.out.println("Введите следующее значение(целое число в диапазоне[-999999999;999999999]): ");
            arr[i] = in.nextInt();
            summa += arr[i];
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
            i++;
        }
        System.out.println("Минимальный элемент в массиве: " + min);
        System.out.println("Максимальный элемент в массиве: " + max);
        System.out.println("Сумма элементов в массиве: " + summa);
    }
}
