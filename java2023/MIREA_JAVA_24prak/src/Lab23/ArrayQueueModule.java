package Lab23;

import java.util.Arrays;

public class ArrayQueueModule {
    private static int size;
    private static Object[] elements = new Object[10];
    private static int head = 0;
    private static int tail = 0;

    // Инвариант:
    // - elements - массив для хранения элементов очереди
    // - size - текущий размер очереди
    // - head - индекс начала очереди (первого элемента)
    // - tail - индекс конца очереди (следующего после последнего элемента)
    // - Если size > 0, то элементы очереди находятся в массиве elements от head до tail-1.
    // - Если size == 0, очередь пуста и head == tail.

    public static void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public static Object element() {
        assert size > 0;
        return elements[head];
    }

    public static Object dequeue() {
        assert size > 0;
        Object element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }

    public static void clear() {
        Arrays.fill(elements, null);
        size = 0;
        head = 0;
        tail = 0;
    }

    private static void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            int newCapacity = Math.max(2 * elements.length, capacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}