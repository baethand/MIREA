package prak4_1;

public class Phone {
    private String number;
    private String model;
    private double weight;

    public Phone(String number, String model, double weight) {
        this.number = number;
        this.model = model;
        this.weight = weight;
    }

    public Phone(String number, String model) {
        this(number, model, 0.0);
    }

    public Phone() {
        this("", "", 0.0);
    }

    public String getNumber() {
        return number;
    }

    public void receiveCall(String callerName) {
        System.out.println("Звонит " + callerName);
    }

    public void receiveCall(String callerName, String callerNumber) {
        System.out.println("Звонит " + callerName + " (" + callerNumber + ")");
    }

    public void sendMessage(String... phoneNumbers) {
        System.out.println("Отправлено сообщение на следующие номера:");
        for (String phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }
    }

    public static void main(String[] args) {
        Phone phone1 = new Phone("123-456-7890", "Samsung Galaxy S21", 150.5);
        Phone phone2 = new Phone("987-654-3210", "iPhone 12");
        Phone phone3 = new Phone();

        System.out.println("Телефон 1:");
        System.out.println("Номер: " + phone1.getNumber());
        System.out.println("Модель: " + phone1.model);
        System.out.println("Вес: " + phone1.weight);

        System.out.println("\nТелефон 2:");
        System.out.println("Номер: " + phone2.getNumber());
        System.out.println("Модель: " + phone2.model);
        System.out.println("Вес: " + phone2.weight);

        System.out.println("\nТелефон 3:");
        System.out.println("Номер: " + phone3.getNumber());
        System.out.println("Модель: " + phone3.model);
        System.out.println("Вес: " + phone3.weight);

        phone1.receiveCall("Алиса");
        phone2.receiveCall("Боб", "111-222-3333");

        phone1.sendMessage("555-555-5555", "666-666-6666");
        phone2.sendMessage("777-777-7777");
    }
}
