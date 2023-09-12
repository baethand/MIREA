package prak4_1;

// Абстрактный класс, описывающий транспортное средство
abstract class Transport {
    private String name;

    public Transport(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Метод для подсчета времени перевозки пассажиров
    public abstract double calculatePassengerTime(int distance);

    // Метод для подсчета стоимости перевозки пассажиров
    public abstract double calculatePassengerCost(int distance);

    // Метод для подсчета времени перевозки грузов
    public abstract double calculateCargoTime(int distance);

    // Метод для подсчета стоимости перевозки грузов
    public abstract double calculateCargoCost(int distance);
}

// Подклассы, представляющие различные виды транспортных средств
class Car extends Transport {
    private double passengerSpeed;
    private double cargoSpeed;

    public Car(String name, double passengerSpeed, double cargoSpeed) {
        super(name);
        this.passengerSpeed = passengerSpeed;
        this.cargoSpeed = cargoSpeed;
    }

    @Override
    public double calculatePassengerTime(int distance) {
        return distance / passengerSpeed;
    }

    @Override
    public double calculatePassengerCost(int distance) {
        return 0.2 * distance; // Пример стоимости на 1 км
    }

    @Override
    public double calculateCargoTime(int distance) {
        return distance / cargoSpeed;
    }

    @Override
    public double calculateCargoCost(int distance) {
        return 0.1 * distance; // Пример стоимости на 1 км
    }
}

class Plane extends Transport {
    private double passengerSpeed;
    private double cargoSpeed;

    public Plane(String name, double passengerSpeed, double cargoSpeed) {
        super(name);
        this.passengerSpeed = passengerSpeed;
        this.cargoSpeed = cargoSpeed;
    }

    @Override
    public double calculatePassengerTime(int distance) {
        return distance / passengerSpeed;
    }

    @Override
    public double calculatePassengerCost(int distance) {
        return 0.5 * distance; // Пример стоимости на 1 км
    }

    @Override
    public double calculateCargoTime(int distance) {
        return distance / cargoSpeed;
    }

    @Override
    public double calculateCargoCost(int distance) {
        return 0.3 * distance; // Пример стоимости на 1 км
    }
}

class Train extends Transport {
    private double passengerSpeed;
    private double cargoSpeed;

    public Train(String name, double passengerSpeed, double cargoSpeed) {
        super(name);
        this.passengerSpeed = passengerSpeed;
        this.cargoSpeed = cargoSpeed;
    }

    @Override
    public double calculatePassengerTime(int distance) {
        return distance / passengerSpeed;
    }

    @Override
    public double calculatePassengerCost(int distance) {
        return 0.15 * distance; // Пример стоимости на 1 км
    }

    @Override
    public double calculateCargoTime(int distance) {
        return distance / cargoSpeed;
    }

    @Override
    public double calculateCargoCost(int distance) {
        return 0.08 * distance; // Пример стоимости на 1 км
    }
}

class Ship extends Transport {
    private double passengerSpeed;
    private double cargoSpeed;

    public Ship(String name, double passengerSpeed, double cargoSpeed) {
        super(name);
        this.passengerSpeed = passengerSpeed;
        this.cargoSpeed = cargoSpeed;
    }

    @Override
    public double calculatePassengerTime(int distance) {
        return distance / passengerSpeed;
    }

    @Override
    public double calculatePassengerCost(int distance) {
        return 0.3 * distance; // Пример стоимости на 1 км
    }

    @Override
    public double calculateCargoTime(int distance) {
        return distance / cargoSpeed;
    }

    @Override
    public double calculateCargoCost(int distance) {
        return 0.2 * distance; // Пример стоимости на 1 км
    }
}

public class TransportCompany {
    public static void main(String[] args) {
        Transport car = new Car("Автомобиль", 100.0, 50.0);
        Transport plane = new Plane("Самолет", 800.0, 500.0);
        Transport train = new Train("Поезд", 120.0, 80.0);
        Transport ship = new Ship("Корабль", 50.0, 30.0);

        int distance = 500; // Пример расстояния в километрах

        // Перевозка пассажиров
        System.out.println(car.getName() + ": Время - " + car.calculatePassengerTime(distance) + " часов, Стоимость - " + car.calculatePassengerCost(distance) + " долларов");
        System.out.println(plane.getName() + ": Время - " + plane.calculatePassengerTime(distance) + " часов, Стоимость - " + plane.calculatePassengerCost(distance) + " долларов");
        System.out.println(train.getName() + ": Время - " + train.calculatePassengerTime(distance) + " часов, Стоимость - " + train.calculatePassengerCost(distance) + " долларов");
        System.out.println(ship.getName() + ": Время - " + ship.calculatePassengerTime(distance) + " часов, Стоимость - " + ship.calculatePassengerCost(distance) + " долларов");

        // Перевозка грузов
        System.out.println(car.getName() + ": Время - " + car.calculateCargoTime(distance) + " часов, Стоимость - " + car.calculateCargoCost(distance) + " долларов");
        System.out.println(plane.getName() + ": Время - " + plane.calculateCargoTime(distance) + " часов, Стоимость - " + plane.calculateCargoCost(distance) + " долларов");
        System.out.println(train.getName() + ": Время - " + train.calculateCargoTime(distance) + " часов, Стоимость - " + train.calculateCargoCost(distance) + " долларов");
        System.out.println(ship.getName() + ": Время - " + ship.calculateCargoTime(distance) + " часов, Стоимость - " + ship.calculateCargoCost(distance) + " долларов");
    }
}
