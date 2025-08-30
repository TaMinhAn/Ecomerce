package view;

import com.six.ecommerce.Ecommerce;
import model.Cart;
import model.CartItem;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class CartWindow extends JFrame {
    public CartWindow() {
        setTitle("Your Cart");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        refreshCart(); // Load cart UI
    }
        private void refreshCart() {
    getContentPane().removeAll();

    Cart cart = Ecommerce.getCart();

    JPanel cartPanel = new JPanel();
    cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
    cartPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    for (CartItem item : cart.getItems()) {
        Product product = item.getProduct();

        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel itemLabel = new JLabel(product.getName() + " x " + item.getQuantity() +
                " - $" + (product.getPrice() * item.getQuantity()));
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");

        plusButton.addActionListener(e -> {
            cart.increaseQuantity(product);
            refreshCart();
        });

        minusButton.addActionListener(e -> {
            cart.decreaseQuantity(product);
            refreshCart();
        });

        itemPanel.add(itemLabel);
        itemPanel.add(plusButton);
        itemPanel.add(minusButton);
        cartPanel.add(itemPanel);
    }
    add(cartPanel, BorderLayout.CENTER); // ✅ This was missing
    JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Total label
        JLabel totalLabel = new JLabel("Total: " + cart.getTotalPrice() + " VND");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalLabel);

        // Order button
        JButton orderButton = new JButton("Place Order");
        orderButton.addActionListener(e -> {
            // You can open a new OrderWindow or handle order logic here
            new OrderWindow().setVisible(true); // Make sure you create this class
        });
        totalPanel.add(orderButton);

        add(totalPanel, BorderLayout.SOUTH);


    revalidate();
    repaint();
}
}