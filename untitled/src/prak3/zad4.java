package prak3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class zad4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n;
        do {
            System.out.print("Enter the size of the array (a natural number greater than 0): ");
            n = scanner.nextInt();
        } while (n <= 0);

        int[] array = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n+1);
        }

        System.out.println("The original array: " + Arrays.toString(array));

        int countEven = 0;
        for (int i = 0; i < n; i++) {
            if (array[i] % 2 == 0) {
                countEven++;
            }
        }

        if (countEven == 0) {
            System.out.println("There are no even numbers in the original array.");
        } else {
            int[] evenArray = new int[countEven];
            int j = 0;
            for (int i = 0; i < n; i++) {
                if (array[i] % 2 == 0) {
                    evenArray[j] = array[i];
                    j++;
                }
            }
            System.out.println("The array of even numbers: " + Arrays.toString(evenArray));
        }
    }
}