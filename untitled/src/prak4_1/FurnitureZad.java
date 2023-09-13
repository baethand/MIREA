package prak4_1;

abstract class Furniture {
    private String name;
    private double price;

    public Furniture(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayInfo();
}

class Chair extends Furniture {
    public Chair(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayInfo() {
        System.out.println("Стул: " + getName() + ", Цена: " + getPrice());
    }
}

class Table extends Furniture {
    public Table(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayInfo() {
        System.out.println("Стол: " + getName() + ", Цена: " + getPrice());
    }
}

class Sofa extends Furniture {
    public Sofa(String name, double price) {
        super(name, price);
    }

    @Override
    public void displayInfo() {
        System.out.println("Диван: " + getName() + ", Цена: " + getPrice());
    }
}

class FurnitureShop {
    private Furniture[] inventory;

    public FurnitureShop(int capacity) {
        inventory = new Furniture[capacity];
    }

    public void addFurniture(Furniture furniture, int index) {
        if (index >= 0 && index < inventory.length) {
            inventory[index] = furniture;
        } else {
            System.out.println("Недопустимый индекс.");
        }
    }

    public void displayInventory() {
        System.out.println("Мебель в магазине:");
        for (Furniture item : inventory) {
            if (item != null) {
                item.displayInfo();
            }
        }
    }
}

public class FurnitureZad {
    public static void main(String[] args) {
        FurnitureShop shop = new FurnitureShop(3);

        Chair chair = new Chair("Стул IKEA", 50.0);
        Table table = new Table("Стол JYSK", 120.0);
        Sofa sofa = new Sofa("Диван Wayfair", 300.0);

        shop.addFurniture(chair, 0);
        shop.addFurniture(table, 1);
        shop.addFurniture(sofa, 2);

        shop.displayInventory();
    }
}
