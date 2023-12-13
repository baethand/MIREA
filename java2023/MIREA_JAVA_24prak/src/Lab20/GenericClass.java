package Lab20;

import java.io.Serializable;

interface Animal {

}

public class GenericClass<T extends Comparable<T>, V extends Serializable & Animal, K> {
    private T value1;
    private V value2;
    private K value3;

    public GenericClass(T value1, V value2, K value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public K getValue3() {
        return value3;
    }

    public T getValue1() {
        return value1;
    }

    public V getValue2() {
        return value2;
    }

    public void displayClassNames() {
        Class<?> class1 = value1.getClass();
        Class<?> class2 = value2.getClass();
        Class<?> class3 = value3.getClass();

        System.out.println("Class name for Value1: " + class1.getName());
        System.out.println("Class name for Value2: " + class2.getName());
        System.out.println("Class name for Value3: " + class3.getName());
    }
}