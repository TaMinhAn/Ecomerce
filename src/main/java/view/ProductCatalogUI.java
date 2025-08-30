package view;
import com.six.ecommerce.Ecommerce;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Product;

public class ProductCatalogUI extends JFrame {
    public ProductCatalogUI(List<Product> products) {
        setTitle("Product Catalog");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setLayout(new BorderLayout()); // Ensure the frame uses BorderLayout

        // 🔍 Top panel (static)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);
        add(topPanel, BorderLayout.NORTH); // This keeps it fixed at the top

        
        
        //main  grid
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3, 10, 10));

        for (Product product : products) {
            ProductItem item = new ProductItem(product);
            mainPanel.add(item);
        }
        
        // 🔍 Search logic
        searchButton.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();
            mainPanel.removeAll();

            for (Product product : products) {
                if (product.getName().toLowerCase().contains(query)) {
                    mainPanel.add(new ProductItem(product));
                }
            }

            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // ✅ View Cart button
        JButton viewCartButton = new JButton("🛒 View Cart");
        viewCartButton.setPreferredSize(new Dimension(120, 40));
        viewCartButton.addActionListener(e -> {
            CartWindow cartWindow = new CartWindow();
            cartWindow.setVisible(true);
        });

        // Bottom panel to hold the button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(viewCartButton);
        //
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        add(scrollPane);
        setVisible(true);
        add(bottomPanel, BorderLayout.SOUTH);

    }
}

