package Lab6;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Колонка", 1000.0);
        Service service = new Service("Ремонт", 5000.0, 5);

        System.out.println("Цена товара: " + product.getPrice());
        System.out.println("Цена услуги: " + service.getPrice());
    }
}
