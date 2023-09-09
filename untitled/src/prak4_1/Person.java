package prak4_1;

public class Person {
    private String fullName;
    private int age;

    public Person() {
        // Конструктор без параметров
    }

    public Person(String fullName, int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public void move() {
        System.out.println(fullName + " двигается");
    }

    public void talk() {
        System.out.println(fullName + " говорит");
    }

    public static void main(String[] args) {
        Person person1 = new Person(); // Инициализируем объект конструктором без параметров
        person1.fullName = "Иван Иванов";
        person1.age = 30;

        Person person2 = new Person("Петр Петров", 25); // Инициализируем объект конструктором с параметрами

        System.out.println("Первый человек:");
        System.out.println("Полное имя: " + person1.fullName);
        System.out.println("Возраст: " + person1.age);
        person1.move();
        person1.talk();

        System.out.println("\nВторой человек:");
        System.out.println("Полное имя: " + person2.fullName);
        System.out.println("Возраст: " + person2.age);
        person2.move();
        person2.talk();
    }
}
