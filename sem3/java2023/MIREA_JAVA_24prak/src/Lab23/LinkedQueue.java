package Lab23;

import java.util.NoSuchElementException;

public class LinkedQueue {
    private Node front;
    private Node rear;
    private int size;

    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(Object element) {
        Node newNode = new Node(element);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public Object dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Object removed = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return removed;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }
}
