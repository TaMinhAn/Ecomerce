package model;
import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int buyerId;
    private Timestamp orderDate;
    private String status; // Should match ENUM values: "pending", "shipped", "delivered", "cancelled"
    private double totalAmount;

    public Order(int orderId, int buyerId, Timestamp orderDate, String status, double totalAmount) {
        this.orderId = orderId;
        this.buyerId = buyerId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
