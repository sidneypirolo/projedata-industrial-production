package com.sidney.production.dto;

import java.math.BigDecimal;

public class ProductionPlanItemDTO {

    private String productName;
    private int quantity;
    private BigDecimal estimatedRevenue;

    public ProductionPlanItemDTO(String productName,
                                  int quantity,
                                  BigDecimal estimatedRevenue) {
        this.productName = productName;
        this.quantity = quantity;
        this.estimatedRevenue = estimatedRevenue;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getEstimatedRevenue() {
        return estimatedRevenue;
    }
}