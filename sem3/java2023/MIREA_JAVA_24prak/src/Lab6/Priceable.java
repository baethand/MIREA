package Lab6;

interface Priceable {
    double getPrice();
}


class Product implements Priceable {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Класс для услуги
class Service implements Priceable {
    private String serviceName;
    private double Price4hour;
    private int hours;

    public Service(String serviceName, double price4hour, int hours) {
        this.serviceName = serviceName;
        this.Price4hour = price4hour;
        this.hours = hours;
    }

    @Override
    public double getPrice() {
        return Price4hour * hours;
    }
}

