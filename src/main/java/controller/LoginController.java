package controller;

import view.LoginWindow;
import database.DBConnection;
import database.ProductDAO;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import model.Product;

public class LoginController {
    private LoginWindow loginWindow;

    public LoginController() {
        loginWindow = new LoginWindow();
        loginWindow.getLoginButton().addActionListener(e -> handleLogin()); // ✅ sửa cú pháp
    }

    private void handleLogin() {
        String username = loginWindow.getUsername();
        String password = loginWindow.getPassword();

        System.out.println("Login button clicked"); // ✅ kiểm tra sự kiện

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter both username and password.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed.");
                return;
            }

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");

                    Connection conn2 = DBConnection.getConnection();
                    ProductDAO dao = new ProductDAO(conn2);
                    List<Product> products = dao.getAllProducts();

                    new view.ProductCatalogUI(products);

                    loginWindow.dispose(); // optional: close login window
                }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }
}
