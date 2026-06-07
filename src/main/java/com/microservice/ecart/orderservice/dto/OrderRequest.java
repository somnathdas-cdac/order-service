package com.microservice.ecart.orderservice.dto;

public class OrderRequest {
    private Long productId;
    private String productName;
    private Double productAmount;

    // Default Constructor for Jackson
    public OrderRequest() {}

    public OrderRequest(Long productId, String productName, Double productAmount) {
        this.productId = productId;
        this.productName = productName;
        this.productAmount = productAmount;
    }

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getProductAmount() { return productAmount; }
    public void setProductAmount(Double productAmount) { this.productAmount = productAmount; }
}
