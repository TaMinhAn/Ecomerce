package view;
import database.DBConnection;
import database.OrderDAO;
import model.Order;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import model.Order;

public class OrderManagementUI extends JFrame {
    private OrderDAO orderDAO;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    
    private int currentBuyerId;

    public OrderManagementUI(Connection connection, int currentBuyerId) {
        this.orderDAO = new OrderDAO(connection);
        this.currentBuyerId = currentBuyerId;

        this.orderDAO = new OrderDAO(connection);
        setTitle("Order Management");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"Order ID", "Buyer ID", "Date", "Status", "Total"}, 0);
        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        JButton refreshBtn = new JButton("Refresh");
        JButton addBtn = new JButton("Add Order");
        JButton updateBtn = new JButton("Update Status");
        JButton myOrdersBtn = new JButton("My Orders");
        
        refreshBtn.addActionListener(e -> loadOrders());
        addBtn.addActionListener(e -> showAddOrderDialog());
        updateBtn.addActionListener(e -> showUpdateStatusDialog());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(myOrdersBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadOrders();
    }

    private void loadOrders() {
        try {
            tableModel.setRowCount(0);
            List<Order> orders = orderDAO.getAllOrders();
            for (Order o : orders) {
                tableModel.addRow(new Object[]{
                    o.getOrderId(),
                    o.getBuyerId(),
                    o.getOrderDate(),
                    o.getStatus(),
                    o.getTotalAmount()
                });
            }
        } catch (SQLException ex) {
            showError("Error loading orders: " + ex.getMessage());
        }
    }

    private void showAddOrderDialog() {
        JTextField buyerField = new JTextField();
        JTextField amountField = new JTextField();
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"pending", "shipped", "delivered", "cancelled"});

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Buyer ID:"));
        panel.add(buyerField);
        panel.add(new JLabel("Total Amount:"));
        panel.add(amountField);
        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Order", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int buyerId = Integer.parseInt(buyerField.getText());
                double total = Double.parseDouble(amountField.getText());
                String status = (String) statusBox.getSelectedItem();
                Order newOrder = new Order(0, buyerId, new Timestamp(System.currentTimeMillis()), status, total);
                orderDAO.insertOrder(newOrder);
                loadOrders();
            } catch (Exception ex) {
                showError("Error adding order: " + ex.getMessage());
            }
        }
    }

    private void showUpdateStatusDialog() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            showError("Please select an order to update.");
            return;
        }

        int orderId = (int) tableModel.getValueAt(selectedRow, 0);
        JComboBox<String> statusBox = new JComboBox<>(new String[]{"pending", "shipped", "delivered", "cancelled"});

        int result = JOptionPane.showConfirmDialog(this, statusBox, "Update Status", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String newStatus = (String) statusBox.getSelectedItem();
                orderDAO.updateOrderStatus(orderId, newStatus);
                loadOrders();
            } catch (SQLException ex) {
                showError("Error updating status: " + ex.getMessage());
            }
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
    Connection conn = DBConnection.getConnection();
    if (conn != null) {
        int currentBuyerId = 1; // Replace with actual logged-in user ID
        SwingUtilities.invokeLater(() -> new OrderManagementUI(conn, currentBuyerId).setVisible(true));
    } else {
        System.out.println("Failed to connect to database.");
    }
}

    private void showMyOrders() {
        
try {
        tableModel.setRowCount(0);
        List<Order> orders = (List<Order>) orderDAO.getOrderById(currentBuyerId);
        for (Order o : orders) {
            tableModel.addRow(new Object[]{
                o.getOrderId(),
                o.getBuyerId(),
                o.getOrderDate(),
                o.getStatus(),
                o.getTotalAmount()
            });
        }
    } catch (SQLException ex) {
        showError("Error loading your orders: " + ex.getMessage());
    }
}

}
