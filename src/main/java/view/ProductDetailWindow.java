package view;

import javax.swing.*;
import java.awt.*;
import model.Product;
import java.io.File;

public class ProductDetailWindow extends JFrame {
    public ProductDetailWindow(Product product) {
        setTitle("Product Details - " + product.getName());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
      // ✅ Image Panel
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        String imagePath = product.getImageURL(); // Should be like "img/Earbuds1.jpg"
        System.out.println("Image path: " + imagePath);
        System.out.println("Working directory: " + System.getProperty("user.dir"));

        File imageFile = new File(imagePath);
        if (imageFile.exists()) {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("Image not found: " + imagePath);
            System.err.println("❌ File not found: " + imageFile.getAbsolutePath());
        }

        // ✅ Info Panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + product.getName());
        JLabel priceLabel = new JLabel("Price: " + product.getPrice() + "VND");
        JLabel descLabel = new JLabel("<html><p style='width:250px'>" + product.getDescription() + "</p></html>");

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(priceLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalStrut(20));
        infoPanel.add(closeButton);

        // ✅ Add panels to frame
        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(infoPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}

