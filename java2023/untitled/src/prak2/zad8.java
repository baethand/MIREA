package prak2;

public class zad8 {
    public static void main(String[] args) {
        String[] array = {"one", "two", "three", "four", "five"};
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            String temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
        for (String element : array) {
            System.out.println(element);
        }
    }
}
