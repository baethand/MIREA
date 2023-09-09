package prak4.zad4;

public class Main {
    public static void main(String[] args) {
        // Создаем компоненты компьютера
        Processor processor = new Processor("Intel Core i7", 3.5, 4);
        Memory memory = new Memory(16, "DDR4");
        Monitor monitor = new Monitor("Dell UltraSharp", 24, "1920x1080");

        // Создаем компьютер с заданными компонентами
        Computer computer = new Computer(ComputerBrand.DELL, processor, memory, monitor);

        // Включаем компьютер и показываем работу
        computer.turnOn();

        // Выключаем компьютер и показываем работу
        computer.turnOff();
    }
}

