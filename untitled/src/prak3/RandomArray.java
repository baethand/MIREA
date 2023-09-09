package prak3;

import java.util.Arrays;
import java.util.Random;

public class RandomArray {
    public static void main(String[] args) {
        double[] array1 = generateRandomArrayUsingMath(5);
        System.out.println("Массив (Math.random()): " + Arrays.toString(array1));
        sortArray(array1);
        System.out.println("Отсортированный массив (Math.random()): " + Arrays.toString(array1));

        double[] array2 = generateRandomArrayUsingRandom(5);
        System.out.println("\nМассив (Random): " + Arrays.toString(array2));
        sortArray(array2);
        System.out.println("Отсортированный массив (Random): " + Arrays.toString(array2));
    }

    private static double[] generateRandomArrayUsingMath(int size) {
        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            array[i] = Math.random();
        }

        return array;
    }

    private static double[] generateRandomArrayUsingRandom(int size) {
        double[] array = new double[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }

        return array;
    }

    private static void sortArray(double[] array) {
        Arrays.sort(array);
    }
}