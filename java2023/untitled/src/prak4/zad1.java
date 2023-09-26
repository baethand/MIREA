package prak4;

// Создаем перечисление для времен года
enum Seasons {
    Весна(10, "Холодное время года"),
    Лето(25, "Теплое время года"),
    Осень(15, "Холодное время года"),
    Зима(0, "Холодное время года");

    private int averageTemperature;
    private String description;

    // Конструктор для установки средней температуры и описания
    Seasons(int averageTemperature, String description) {
        this.averageTemperature = averageTemperature;
        this.description = description;
    }

    // Геттер для средней температуры
    public int getAverageTemperature() {
        return averageTemperature;
    }

    // Метод для получения описания времени года
    public String getDescription() {
        return description;
    }

    // Метод, выводящий информацию о времени года
    public void printInfo() {
        System.out.println("Средняя температура в " + this + ": " + averageTemperature + "°C");
        System.out.println("Описание: " + getDescription());
        switch (this) {
            case Лето:
                System.out.println("Я люблю лето");
                break;
            case Весна:
                System.out.println("Я люблю весну");
                break;
            case Осень:
                System.out.println("Я люблю осень");
                break;
            case Зима:
                System.out.println("Я люблю зиму");
                break;
        }
    }
}

public class zad1 {
    public static void main(String[] args) {
        // Создаем переменную с любимым временем года
        Seasons favoriteSeason = Seasons.Лето;

        // Выводим информацию о любимом времени года
        System.out.println("Информация о моем любимом времени года:");
        favoriteSeason.printInfo();
        System.out.println();

        // Выводим информацию о всех временах года в цикле
        System.out.println("Информация о всех временах года:");
        for (Seasons season : Seasons.values()) {
            season.printInfo();
            System.out.println();
        }
    }
}
