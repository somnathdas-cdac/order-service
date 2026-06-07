
package com.microservice.ecart.orderservice.model;



import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long productId;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
    private String orderStatus; // PENDING, PLACED, CANCELLED
    private String userEmail;

    // Constructors
    public Order() {}

    public Order(Long productId,String productName, Integer quantity, Double totalPrice, String orderStatus, String userEmail) {
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.userEmail = userEmail;
        this.productName=productName;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }
    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
}
