package prak4.zad3;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addToCart(Product product) {
        items.add(product);
    }

    public void removeFromCart(Product product) {
        items.remove(product);
    }

    public List<Product> getCartItems() {
        return items;
    }
}
