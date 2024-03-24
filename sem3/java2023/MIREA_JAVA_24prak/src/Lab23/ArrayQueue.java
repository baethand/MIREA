package Lab23;


import java.util.Arrays;

public class ArrayQueue {
    private Object[] elements = new Object[10];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    // Инвариант и пред-постусловия аналогичны ArrayQueueModule.

    public void enqueue(Object element) {
        assert element != null;
        ensureCapacity(size + 1);
        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    public Object element() {
        assert size > 0;
        return elements[head];
    }

    public Object dequeue() {
        assert size > 0;
        Object element = elements[head];
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
        head = 0;
        tail = 0;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            int newCapacity = Math.max(2 * elements.length, capacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }
}

