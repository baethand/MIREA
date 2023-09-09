package prak4;

// Задание 2. Ателье JAVA

// Создаем перечисление для размеров одежды
enum ClothingSize {
    XXS(32),
    XS(34),
    S(36),
    M(38),
    L(40);

    private final int euroSize;

    // Конструктор перечисления, принимающий euroSize
    ClothingSize(int euroSize) {
        this.euroSize = euroSize;
    }

    // Переопределяем метод getDescription
    public String getDescription() {
        if (this == XXS) {
            return "Детский размер";
        } else {
            return "Взрослый размер";
        }
    }

    // Геттер для euroSize
    public int getEuroSize() {
        return euroSize;
    }
}

public class zad2 {
    public static void main(String[] args) {
        // Создаем массив с разными типами одежды
        Clothes[] clothesArray = {
                new TShirt(ClothingSize.M, 29.99, "Синяя"),
                new Pants(ClothingSize.L, 49.99, "Черные"),
                new Skirt(ClothingSize.S, 39.99, "Красная"),
                new Tie(ClothingSize.XS, 19.99, "Голубой")
        };

        Atelier atelier = new Atelier();
        System.out.println("Мужская одежда:");
        atelier.dressMan(clothesArray);

        System.out.println("\nЖенская одежда:");
        atelier.dressWomen(clothesArray);
    }
}


// Создаем интерфейс MenClothing
interface MenClothing {
    void dressMan();
}

// Создаем интерфейс WomenClothing
interface WomenClothing {
    void dressWomen();
}

// Создаем абстрактный класс Clothes
abstract class Clothes {
    private ClothingSize size;
    private double cost;
    private String color;

    // Конструктор класса Clothes
    public Clothes(ClothingSize size, double cost, String color) {
        this.size = size;
        this.cost = cost;
        this.color = color;
    }

    // Геттеры для полей
    public ClothingSize getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }
}

// Создаем класс TShirt (футболка)
class TShirt extends Clothes implements MenClothing, WomenClothing {
    public TShirt(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    @Override
    public void dressMan() {
        System.out.println("Мужская футболка: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }

    @Override
    public void dressWomen() {
        System.out.println("Женская футболка: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }
}

// Создаем класс Pants (штаны)
class Pants extends Clothes implements MenClothing, WomenClothing {
    public Pants(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    @Override
    public void dressMan() {
        System.out.println("Мужские штаны: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }

    @Override
    public void dressWomen() {
        System.out.println("Женские штаны: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }
}

// Создаем класс Skirt (юбка)
class Skirt extends Clothes implements WomenClothing {
    public Skirt(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    @Override
    public void dressWomen() {
        System.out.println("Женская юбка: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }
}

// Создаем класс Tie (галстук)
class Tie extends Clothes implements MenClothing {
    public Tie(ClothingSize size, double cost, String color) {
        super(size, cost, color);
    }

    @Override
    public void dressMan() {
        System.out.println("Мужской галстук: Размер - " + getSize() + ", Цвет - " + getColor() + ", Стоимость - " + getCost());
    }
}

// Создаем класс Atelier (Ателье)
class Atelier {
    public void dressMan(Clothes[] clothes) {
        for (Clothes item : clothes) {
            if (item instanceof MenClothing) {
                ((MenClothing) item).dressMan();
            }
        }
    }

    public void dressWomen(Clothes[] clothes) {
        for (Clothes item : clothes) {
            if (item instanceof WomenClothing) {
                ((WomenClothing) item).dressWomen();
            }
        }
    }
}


