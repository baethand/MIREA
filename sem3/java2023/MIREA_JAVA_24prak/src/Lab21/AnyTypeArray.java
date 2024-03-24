package Lab21;

public class AnyTypeArray {
    private Object[] array;
    private int size;

    public AnyTypeArray(int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        array = new Object[num];
        size = 0;
    }

    public void add(Object element) {
        if (size >= array.length) {
            // Увеличиваем размер массива при необходимости
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        array[size++] = element;
    }

    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        AnyTypeArray storage = new AnyTypeArray(5);

        storage.add(42);          // Добавляем целое число
        storage.add("Hello");     // Добавляем строку
        storage.add(3.14);        // Добавляем число с плавающей запятой

        for (int i = 0; i < storage.size(); i++) {
            Object element = storage.get(i);
            System.out.println("Element at index " + i + ": " + element);

            // Приведение типов, если необходимо
            if (element instanceof Integer) {
                int intValue = (int) element;
                // Далее можно работать с intValue как с целым числом
            } else if (element instanceof String) {
                String stringValue = (String) element;
                // Далее можно работать с stringValue как со строкой
            } else if (element instanceof Double) {
                double doubleValue = (double) element;
                // Далее можно работать с doubleValue как с числом с плавающей запятой
            }
        }
    }
}