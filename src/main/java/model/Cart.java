/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    public void removeProduct(Product product) {
        items.removeIf(item -> item.getProduct().getId() == product.getId());
    }
    public void increaseQuantity(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
    }
    public void decreaseQuantity(Product product) {
    Iterator<CartItem> iterator = items.iterator();
    while (iterator.hasNext()) {
        CartItem item = iterator.next();
        if (item.getProduct().getId() == product.getId()) {
            int newQty = item.getQuantity() - 1;
            if (newQty <= 0) {
                iterator.remove(); // Remove item if quantity is 0
            } else {
                item.setQuantity(newQty);
            }
            return;
        }
    }
}
    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
    
}
