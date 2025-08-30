/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
    private static final String USER = "root"; // change if needed
    private static final String PASSWORD = "teamsix"; // change if you set a password

    
public static Connection getConnection() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver"); // <-- Add this line
        return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (ClassNotFoundException | SQLException e) {
        System.out.println("MySQL connection failed: " + e.getMessage());
        return null;
    }
}

public static void closeConnection(Connection conn) {
    if (conn != null) {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}

}
