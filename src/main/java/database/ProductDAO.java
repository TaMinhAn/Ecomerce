package database;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    String query = "SELECT products_id, name, price, description, image_url FROM products";

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {

        while (rs.next()) {
            Product product = new Product(
                rs.getInt("products_id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("description"),
                rs.getString("image_url") // new field
            );
            products.add(product);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return products;
}
}
       