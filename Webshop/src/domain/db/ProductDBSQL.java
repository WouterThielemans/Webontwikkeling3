package domain.db;

import domain.model.DomainException;
import domain.model.Person;
import domain.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDBSQL implements ProductDB {
    private Properties properties;
    String url;

    public ProductDBSQL(Properties properties) {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void addProduct(Product product) {
        if (product == null) throw new DomainException("Nothing to add.");
        String sql = "INSERT INTO r0719212.product VALUES (DEFAULT ,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,product.getName());
            statement.setString(2,product.getDescription());
            statement.setDouble(3,product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void updateProduct(Product product) {

    }

    @Override
    public void deleteProduct(String productid) {
        if (productid == null) throw new DomainException("Nothing to get.");
        String sql = "DELETE FROM r0719212.product WHERE productid=?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,Integer.parseInt(productid));
            statement.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public int getNumberOfProducts() {
        return 0;
    }


    public Product getProduct(String productId) {
        Product product = new Product() ;
        if (productId == null) throw new DomainException("Nothing to get.");
        String sql = "SELECT * FROM r0719212.product WHERE productid=?";
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int id = Integer.parseInt(productId);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String productid = result.getString("productid");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                product = new Product(productid,name,description,price);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> producten = new ArrayList<Product>();
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM r0719212.product");

            while (result.next()) {
                String productid = result.getString("productid");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                Product product = new Product(productid,name,description,price);

                producten.add(product);
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }

        return producten;
    }
}
