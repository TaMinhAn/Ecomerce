package view;

import javax.swing.*;
import java.awt.*;

public class OrderWindow extends JFrame {
    public OrderWindow() {
        setTitle("Order Confirmation");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel message = new JLabel("Your order has been placed!", SwingConstants.CENTER);
        message.setFont(new Font("Arial", Font.BOLD, 16));
        add(message, BorderLayout.CENTER);
    }
}
