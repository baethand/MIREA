package prak4.zad3;

import java.util.Scanner;

public class OnlineStore {
    private User currentUser;
    private Catalog catalog;
    private ShoppingCart cart;

    public OnlineStore() {
        catalog = new Catalog();
        cart = new ShoppingCart();
    }

    public boolean authenticate(String username, String password) {
        //Здесь логика аутентификации
        return true;
    }

    public void viewCatalog() {
        // Вывод списка каталогов товаров
    }

    public void viewProductsInCategory(ProductCategory category) {
        // Вывод списка товаров в указанной категории
    }

    public void addToCart(Product product) {
        cart.addToCart(product);
    }

    public void removeFromCart(Product product) {
        cart.removeFromCart(product);
    }

    public void checkout() {
        // Процесс оформления покупки
    }

    public static void main(String[] args) {
        OnlineStore store = new OnlineStore();
        Scanner scanner = new Scanner(System.in);

        boolean authenticated = false;
        while (!authenticated) {
            System.out.print("Введите ваш логин: ");
            String username = scanner.nextLine();
            System.out.print("Введите ваш пароль: ");
            String password = scanner.nextLine();

            authenticated = store.authenticate(username, password);

            if (!authenticated) {
                System.out.println("Аутентификация не удалась. Попробуйте снова.");
            }
        }

        System.out.println("Добро пожаловать в интернет-магазин!");

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Просмотреть каталог");
            System.out.println("2. Просмотреть товары в категории");
            System.out.println("3. Добавить товар в корзину");
            System.out.println("4. Посмотреть корзину");
            System.out.println("5. Оформить покупку");
            System.out.println("6. Выйти");

            System.out.print("Выберите действие (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очищаем буфер после ввода числа

            switch (choice) {
                case 1:
                    store.viewCatalog();
                    break;
                case 2:
                    System.out.print("Введите категорию товаров (например, ELECTRONICS): ");
                    String categoryStr = scanner.nextLine();
                    try {
                        ProductCategory category = ProductCategory.valueOf(categoryStr.toUpperCase());
                        store.viewProductsInCategory(category);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверная категория товаров.");
                    }
                    break;
                case 3:
                    System.out.print("Введите название товара для добавления в корзину: ");
                    String productName = scanner.nextLine();
                    // Здесь можно реализовать поиск товара по названию в каталоге и добавление в корзину
                    break;
                case 4:
                    // Вывести содержимое корзины
                    break;
                case 5:
                    // Оформить покупку
                    break;
                case 6:
                    System.out.println("Спасибо за покупки. До свидания!");
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите действие из меню (1-6).");
            }
        }
    }
}
