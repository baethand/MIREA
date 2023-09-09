package prak4.zad4;

// Перечисление для марок компьютера
enum ComputerBrand {
    ACER,
    ASUS,
    DELL,
    HP,
    LENOVO,
    // Добавьте другие марки по вашему выбору
}

// Класс, представляющий процессор
class Processor {
    private String model;
    private double clockSpeedGHz;
    private int cores;

    public Processor(String model, double clockSpeedGHz, int cores) {
        this.model = model;
        this.clockSpeedGHz = clockSpeedGHz;
        this.cores = cores;
    }

    // Геттеры и сеттеры

    public void start() {
        System.out.println("Процессор запущен");
    }

    public void stop() {
        System.out.println("Процессор остановлен");
    }
}

// Класс, представляющий память
class Memory {
    private int capacityGB;
    private String type;

    public Memory(int capacityGB, String type) {
        this.capacityGB = capacityGB;
        this.type = type;
    }

    // Геттеры и сеттеры

    public void load() {
        System.out.println("Память загружена");
    }

    public void clear() {
        System.out.println("Память очищена");
    }
}

// Класс, представляющий монитор
class Monitor {
    private String model;
    private int screenSizeInches;
    private String resolution;

    public Monitor(String model, int screenSizeInches, String resolution) {
        this.model = model;
        this.screenSizeInches = screenSizeInches;
        this.resolution = resolution;
    }

    // Геттеры и сеттеры

    public void display() {
        System.out.println("Монитор отображает изображение");
    }

    public void turnOff() {
        System.out.println("Монитор выключен");
    }
}

// Класс, представляющий компьютер
class Computer {
    private ComputerBrand brand;
    private Processor processor;
    private Memory memory;
    private Monitor monitor;

    public Computer(ComputerBrand brand, Processor processor, Memory memory, Monitor monitor) {
        this.brand = brand;
        this.processor = processor;
        this.memory = memory;
        this.monitor = monitor;
    }

    // Геттеры и сеттеры

    public void turnOn() {
        System.out.println("Компьютер " + brand + " включен");
        processor.start();
        memory.load();
        monitor.display();
    }

    public void turnOff() {
        System.out.println("Компьютер " + brand + " выключен");
        processor.stop();
        memory.clear();
        monitor.turnOff();
    }
}
