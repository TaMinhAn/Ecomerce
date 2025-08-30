package view;

import com.six.ecommerce.Ecommerce;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class ProductItem extends JPanel {
    public ProductItem(Product product) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setPreferredSize(new Dimension(200, 250));

        // ✅ Image Panel
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            String imagePath = product.getImageURL(); // Should be relative like "img/Earbuds1.jpg"
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon icon = new ImageIcon(imagePath);
                Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                imageLabel.setText("Image not found");
            }
        } catch (Exception e) {
            imageLabel.setText("Image not available");
            e.printStackTrace();
        }

        // ✅ Product Info
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price: " + product.getPrice() + "VND");
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new ProductDetailWindow(product).setVisible(true);
    }
});

        // ✅ Add to Cart Button
        JButton addToCartButton = new JButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> {
            Ecommerce.getCart().addProduct(product);
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart!");
        });

        // ✅ Add components to panel
        add(imageLabel);
        add(Box.createVerticalStrut(10));
        add(nameLabel);
        add(priceLabel);
        add(Box.createVerticalStrut(10));
        add(addToCartButton);
    }
}
