package domain.db;

import domain.model.Product;

import java.util.List;

public interface ProductDB {
    Product getProduct(String productid);

    List<Product> getAllProducts();

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(String productid);

    int getNumberOfProducts();
}
