package prak4.zad3;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Product> products;

    public Catalog() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProductsInCategory(ProductCategory category) {
        List<Product> productsInCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory() == category) {
                productsInCategory.add(product);
            }
        }
        return productsInCategory;
    }
}
