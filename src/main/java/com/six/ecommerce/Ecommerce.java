/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


package com.six.ecommerce;

import database.DBConnection;
import database.ProductDAO;
import model.Product;
import view.ProductCatalogUI;

import java.sql.Connection; 
import java.util.List;
import model.Cart;


/**
 *
 * @author ACER
 */
public class Ecommerce {

    

public static void main(String[] args) {
        // Step 1: Connect to the database
        Connection conn = DBConnection.getConnection();

        // Step 2: Fetch products using DAO
        ProductDAO productDAO = new ProductDAO(conn);
        List<Product> products = productDAO.getAllProducts();

        // Step 3: Launch the product catalog UI
        new ProductCatalogUI(products);
    }
private static Cart cart = new Cart();

        public static Cart getCart() {
            return cart;
        }
}

